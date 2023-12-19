module type Memory = sig
  type 'a t
  val init : int -> 'a t
  val get : 'a t -> int -> 'a option
  val set : 'a t -> int -> 'a -> unit
  val dump : 'a t -> 'a option list
  (*funckje zrobione dla zabawy*)
  val size : 'a t -> int
  val free : 'a t -> int
  val release : 'a t -> unit
end


module Array_memory : Memory = struct
  type 'a t = { mutable arr : 'a option array }

  let init n = { arr = Array.make n None }

  let get s i =
    match s.arr.(i) with
    | None -> None
    | Some v -> Some v

  let set s i v = s.arr.(i) <- Some v

  let dump s =
    let rec loop i n =
      if i < n then
        match s.arr.(i) with
        | None -> None :: loop (i + 1) n
        | Some v -> (Some v) :: loop (i + 1) n
      else
        []
    in
    loop 0 (Array.length s.arr)
  ;;

  let size s = Array.length s.arr;;

  let free s = 
    let rec loop i n =
      if i < n then
        match s.arr.(i) with
        | None -> 1 + loop (i + 1) n
        | Some v -> loop (i + 1) n
      else
        0
    in
    loop 0 (Array.length s.arr)
  ;;

  let release s = 
    let rec loop i n = 
      if i < n then 
      match s.arr.(i) with
      | Some(v) -> s.arr.(i) <- None; loop (i + 1) n
      | _ -> loop (i + 1) n
      else ()
    in
    loop 0 (Array.length s.arr)
  ;;

end


(* let mem = Array_memory.init 3;;
Array_memory.dump mem;;
Array_memory.set mem 0 1;;
Array_memory.set mem 2 3;;
Array_memory.dump mem;;
Array_memory.set mem 0 100;;
Array_memory.dump mem;; *)

(* module Nazwa1 (Nazwa2 : sygnatura) = struktura *)

type instruction = Load of int * int | Add of int * int * int | Sub of int * int * int;;

module RAM_Machine (Mem : Memory) = struct
  type rep = { mem : int Mem.t; instructions : instruction list }

  let init size instructions = { mem = Mem.init size; instructions }

  let dump s = Mem.dump s.mem

  let step s =
    let is_correct address = address >= 0 && address < Mem.size s.mem in

    let rec is_correct_for_list lst = match lst with
      | [] -> true
      | head :: tail -> if is_correct head then is_correct_for_list tail else false
    in

    let execute instr = match instr with
      | Load (d, v) -> if is_correct d then Mem.set s.mem d v else ()
      | Add (d, a1, a2) -> 
        if is_correct_for_list [d; a1; a2] then
          let value1 = Mem.get s.mem a1 in
          let value2 = Mem.get s.mem a2 in
          begin match (value1, value2) with
            | (Some v1, Some v2) -> Mem.set s.mem d (v1 + v2)
            | _ -> ()
          end
        else ()
      | Sub (d, a1, a2) -> 
        if is_correct_for_list ([d; a1; a2]) then
          let value1 = Mem.get s.mem a1 in
          let value2 = Mem.get s.mem a2 in
          begin match (value1, value2) with
            | (Some v1, Some v2) -> Mem.set s.mem d (v1 - v2)
            | _ -> ()
          end
        else ()
    in

    let rec process = function 
      | [] -> ()
      | head :: tail -> execute head; process tail
    in

    process s.instructions;;

  let free s = Mem.free s.mem;;

  let release s = Mem.release s.mem;;
end


module Machine = RAM_Machine(Array_memory)
let ram = ref (Machine.init 3 [Load (1, 2); Sub (2, 0, 1); Add(6, 0, 1)]);;
Machine.step !ram;;
Machine.dump !ram;;
Machine.free !ram;;
Machine.release !ram;;

ram := (Machine.init 4 [Load (0, 123); Load(1, 341); Load(2, -343); Load(3, 301); Add(0, 1, 2)]);;
Machine.step !ram;;
Machine.dump !ram;;