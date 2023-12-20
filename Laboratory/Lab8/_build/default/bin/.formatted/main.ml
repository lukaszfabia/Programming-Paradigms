open Array_Memory
open RAM_Machine

let () = print_endline "Hello, World!"

module Machine = RAM_Machine (Array_Memory)

let ram = Machine.init 3 [ Load (1, 2); Sub (2, 0, 1); Add (2, 0, 1) ];;

Machine.step ram;;
Machine.dump ram
