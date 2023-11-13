let (><) list1 list2 = 
 fun f ->
    let rec aux lst1 lst2 = 
      match lst1, lst2 with
      | [], _ -> []
      | _, [] -> []
      | x1 :: xTail, y1 :: yTail -> f x1 y1 :: aux xTail yTail
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

let modyfikacja_z_listy_1 = ([3;1;65;3;1] >< [1;8;4;12;76;2]) (fun x y -> if x > y then x else y);; 



