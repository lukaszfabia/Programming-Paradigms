open Array_Memory
open RAM_Machine

let () = print_endline "Hello, World!"

module Machine = RAM_Machine (Array_Memory)

let ram = Machine.init 3 [ Load (0, 2); Load (1 , 3); Sub (2, 0, 1);];;

Machine.step ram;;
let list = Machine.dump ram;;

let rec print_list l = 
  match l with
  | [] -> ()
  | Some x :: xs -> print_int x; print_string " "; print_list xs
  | None :: xs -> print_string "None "; print_list xs


let () = print_list list;;


