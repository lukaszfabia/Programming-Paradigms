let (><) list1 list2 =
  fun f ->
    let rec aux list1 list2 = 
      match list1, list2 with
      | [], _ -> []
      | _, [] -> []
      | head1 :: tail1, head2 :: tail2 -> f head1 head2 :: aux tail1 tail2
    in
    aux list1 list2;;


let test1 = ([1;2;3] >< [4;5;6]) (fun x y -> x + y);;
let test2 = ([] >< [1;212;431]) (fun x y -> x + y);;
let test3 = (["to"; "marcin"; "tomek"] >< ["jest"; "i"; "dzien dobry"]) (fun x y -> x ^ " " ^ y);;

let test4 = ([] >< [4;5;6]) (fun x y -> x * y);;
let test5 = ([1;2;3] >< []) (fun x y -> x + y);;

let test6 = ([true; false; true] >< [false; false; true]) (fun x y -> x || y);;
let test7 = ([false; false; true] >< [false; false; true]) (fun x y -> x && y);;

let test8 = ([1;2;3] >< [4;5;6;8]) (fun x y -> x * y);;
let test9 = ([1;12;3;0;1] >< [32;42]) (fun x y -> y - x);;