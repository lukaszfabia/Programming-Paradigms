module type Memory = sig
  type 'a t
  exception Alloc_error of string 
  exception IndexOutOfBound of string
  val init : int -> 'a t
  val get : 'a t -> int -> 'a option
  val set : 'a t -> int -> 'a -> unit
  val dump : 'a t -> 'a option list
  (*funckje zrobione dla zabawy*)
  val size : 'a t -> int
  val free : 'a t -> int
  val clear : 'a t -> unit
end

(*modul arr sam w sobie jest juz mutowalny*)
module Array_memory : Memory = struct
  type 'a t = { arr : 'a option array; size: int };;

  exception Alloc_error of string;;

  exception IndexOutOfBound of string;;

  let default_size = 1;;

  let init n = 
    if n <= 0 then {arr = Array.make default_size None; size = default_size}
    else {arr = Array.make n None; size = n}
  ;;
  
  let get memory i =
    if i >= memory.size || i < 0 then raise (IndexOutOfBound "no such a index")
    else memory.arr.(i)
  ;;

  let set memory i v = 
    if i>= memory.size || i < 0  then raise (IndexOutOfBound "no such a index") 
    else memory.arr.(i) <- Some v
  ;;

  let dump memory =
    let rec loop i n =
      if i < n then
        memory.arr.(i) :: loop (i + 1) n
      else
        []
    in
    loop 0 (memory.size)
  ;;

  let size memory = memory.size;;

  let free memory = 
    let rec loop i n =
      if i < n then
        match memory.arr.(i) with
        | None -> 1 + loop (i + 1) n
        | Some v -> loop (i + 1) n
      else
        0
    in
    loop 0 (memory.size)
  ;;

  let clear memory = 
    let rec loop i n = 
      if i < n then 
      match memory.arr.(i) with
      | Some(v) -> memory.arr.(i) <- None; loop (i + 1) n
      | None -> loop (i + 1) n
      else ()
    in
    loop 0 (memory.size)
  ;;

end


let mem = Array_memory.init (-4);;
Array_memory.dump mem;;
Array_memory.set mem 0 1;;
Array_memory.set mem 2 3;;
Array_memory.dump mem;;
Array_memory.set mem 0 100;;
Array_memory.dump mem;;
Array_memory.get mem 2;;
Array_memory.get mem 0;;
Array_memory.free mem;;
Array_memory.clear mem;;
Array_memory.dump mem;;
Array_memory.size mem;;

(* module Nazwa1 (Nazwa2 : sygnatura) = struktura *)
type compression = int->int->int;;

type instruction = 
Load of int * int (* loads value to given address*)
| Add of int * int * int 
| Sub of int * int * int
| Mul of int * int * int
| Div of int * int * int
| Move of int * int (*moves value from address to destination*)
| Compare of compression * int * int * int (*compares two values by given function*)
;;

(*lista w ocaml nie jest domyslnie mutowalna*)
module RAM_Machine (Mem : Memory) = struct
  type rep = { mem : int Mem.t; mutable instructions : instruction list };;

  let init size new_instructions = { mem = Mem.init size; instructions = new_instructions };;

  let dump memory = Mem.dump memory.mem;;

  let step memory =
    let is_correct address = address >= 0 && address < Mem.size memory.mem in

    let rec is_correct_for_list lst = match lst with
      | [] -> true
      | head :: tail -> if is_correct head then is_correct_for_list tail else false
    in

    let make_operation f d a1 a2 memory = 
      let value1 = Mem.get memory.mem a1 in
      let value2 = Mem.get memory.mem a2 in
      begin match (value1, value2) with
        | (Some v1, Some v2) -> Mem.set memory.mem d (f v1 v2)
        | _ -> raise (Mem.Alloc_error "error during operation")
      end
    in 

    let execute instr = match instr with
      | Load (d, v) -> if is_correct d then Mem.set memory.mem d v else raise (Mem.Alloc_error "error during loading")
      | Add (d, a1, a2) -> 
        if is_correct_for_list [d; a1; a2] then
          make_operation (+) d a1 a2 memory
        else ()
      | Sub (d, a1, a2) -> 
        if is_correct_for_list ([d; a1; a2]) then
          make_operation (-) d a1 a2 memory
        else ()
        | Mul (d, a1, a2) ->
          if is_correct_for_list ([d; a1; a2]) then 
            make_operation (fun x y -> x*y) d a1 a2 memory
          else ()
        | Div (d, a1, a2) ->
          if is_correct_for_list ([d; a1; a2]) then 
            make_operation (/) d a1 a2 memory
          else ()
        | Move (d, a) ->
          if is_correct_for_list [d; a] then
            let value = Mem.get memory.mem a in
            begin match value with
              | Some v -> Mem.set memory.mem d v
              | _ -> raise (Mem.Alloc_error "wrong value")
            end
          else ()
        | Compare (f, d, a1, a2) ->
          if is_correct_for_list [d; a1; a2] then
            let value1 = Mem.get memory.mem a1 in
            let value2 = Mem.get memory.mem a2 in
            begin match (value1, value2) with
            | (Some v1, Some v2) -> Mem.set memory.mem d (f v1 v2)
            | _ -> raise (Mem.Alloc_error "error during operation")
          end 
        else ()
    in
    
    (* let rec process = function 
    | [] -> ()
    | head :: tail -> execute head; process tail
    in *)
    match memory.instructions with
    | [] -> () 
    | instr :: rest ->
      execute instr;
      memory.instructions <- rest
    ;;


  let free memory = Mem.free memory.mem;;

  let clear memory = Mem.clear memory.mem;;

  let preprocess memory = 
    let rec loop = function
      | [] -> ()
      | head :: tail -> step memory; memory.instructions <- tail; loop tail
    in
    loop memory.instructions
  ;;
end

module Machine = RAM_Machine(Array_memory)
let ram = ref (Machine.init 3 [Load (1, 2); Move(0, 1); Sub (2, 0, 1); Add(6, 0, 1); Mul(0, 1, 2)]);;
Machine.step !ram;;
Machine.dump !ram;;
Machine.free !ram;;
Machine.clear !ram;;
Machine.dump !ram;;

ram := (Machine.init 4 [Load (0, 123); Load(1, 341); Load(2, -343); Load(3, 301); Add(0, 1, 2); Sub(1, 1, 2); Mul(2, 2, 2)]);;
(* Machine.step !ram;;
Machine.step !ram;;
Machine.dump !ram;; *)
Machine.preprocess !ram;;
Machine.dump !ram;;

ram := (Machine.init 3 [Load (0, 6); Load(1, 4); Move(0, 3); Compare((fun x y -> if x > y then x else y), 2, 0, 1)]);;
Machine.preprocess !ram;;
Machine.dump !ram;;
