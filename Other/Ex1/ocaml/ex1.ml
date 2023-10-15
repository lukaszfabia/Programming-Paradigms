let rec flatten1 xs =
  if xs = [] then []
  else List.hd xs @ flatten1 (List.tl xs);;

let rec count (x, xss) =
  if xss = [] then 0
  else (if List.hd xss = x then 1 else 0) + count (x, List.tl xss);;

let rec replicate (n, x) =
  if n = 0 then []
  else x :: replicate (n-1, x);;

let rec sqrList xs =
  if xs = [] then []
  else List.hd xs * List.hd xs :: sqrList (List.tl xs);;

let rec palindrome xs =
  List.rev xs = xs

let rec listLength xs =
  if xs = [] then 0
  else 1 + listLength (List.tl xs);;
  
(*Tests 1*)
let test1 = flatten1 [[1;2;3];[1;3];[5]] = [1;2;3;1;3;5];;
let test2 = flatten1 [[1];[2]] = [1;2];;
let test3 = flatten1 [[0]] = [0];;
let test4 = flatten1 [] = [];;

(*Tests 2*)
let test5 = count (1, [1;2;3;1;3;5]) = 2;;
let test6 = count (1, [1;2;3;1;3;5;1]) = 3;;
let test7 = count (1, [2;3;4;5]) = 0;;
let test8 = count (1, []) = 0;;

(*Tests 3*)
let test9 = replicate (3, 5) = [5;5;5];;
let test10 = replicate (0, 5) = [];;
let test11 = replicate (1, 5) = [5];;
let test12 = replicate (5, 5) = [5;5;5;5;5];;

(*Tests 4*)
let test13 = sqrList [1;2;3] = [1;4;9];;
let test14 = sqrList [0] = [0];;
let test15 = sqrList [] = [];;

(*Tests 5*)
let test16 = palindrome [1;2;3;2;1] = true;;
let test17 = palindrome [1;2;3;2;2] = false;;
let test18 = palindrome [1;2;3;2;1;1] = false;;
let test19 = palindrome [] = true;;

(*Tests 6*)
let test20 = listLength [1;2;3;2;1] = 5;;
let test21 = listLength [1;2;3;2;2] = 5;;
let test22 = listLength [1;2;3;2;1;1] = 6;;
let test23 = listLength [] = 0;;


