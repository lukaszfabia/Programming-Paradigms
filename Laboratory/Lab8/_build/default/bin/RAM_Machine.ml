open Memory

type instruction =
  | Load of int * int
  | Add of int * int * int
  | Sub of int * int * int

module RAM_Machine (Mem : Memory) = struct
  type rep = { mem : int Mem.t; instructions : instruction list }

  let init size instructions = { mem = Mem.init size; instructions }
  let dump s = Mem.dump s.mem

  let step s =
    let execute instr =
      match instr with
      | Load (i, v) -> Mem.set s.mem i v
      | Add (d, a1, a2) -> (
          let value1 = Mem.get s.mem a1 in
          let value2 = Mem.get s.mem a2 in
          match (value1, value2) with
          | Some v1, Some v2 -> Mem.set s.mem d (v1 + v2)
          | _ -> ())
      | Sub (d, a1, a2) -> (
          let value1 = Mem.get s.mem a1 in
          let value2 = Mem.get s.mem a2 in
          match (value1, value2) with
          | Some v1, Some v2 -> Mem.set s.mem d (v1 - v2)
          | _ -> ())
    in

    let rec process = function
      | [] -> ()
      | head :: tail ->
          execute head;
          process tail
    in
    process s.instructions
end
