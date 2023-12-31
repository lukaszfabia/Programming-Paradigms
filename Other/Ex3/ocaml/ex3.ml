(* 3.1 *)

(*czytamy od prawej do lewej*)
let f1 x = x 1 1;;
(* (int -> int -> A) -> A *)
(*bo 1 jest typu int x jest  dowolnego typu wiec => dowolny typ*)

let f2 x y z = x ( y ^ z );;
(*(string -> A) -> string -> string -> A*)
(*mamy operator konkatenacji stringow w ocamlu wiec bedzie (string -> A) -> string -> string -> A bo y i z zostaly ziterpretowane jako string przez ten operator*)


(* 3.2 *)
let uncurry3WithLukierek f (a,b,c) = f a b c;;
let curry3WithLukierek f a b c = f (a,b,c);;

let uncurry3WithoutLukierek = fun f -> fun (a,b,c) -> f a b c;;
let curry3WithoutLukierek = fun f -> fun a -> fun b -> fun c -> f (a,b,c);;

(* curry ('a -> 'b -> 'c -> 'd) -> 'a * 'b * 'c -> 'd  *)
(* uncurry ('a * 'b * 'c -> 'd) -> 'a -> 'b -> 'c -> 'd = <fun> *)

(* typy sa takie same, czyli typ dowolny *)

(* 3.3 *)

let rec sumProd xs = List.fold_left (fun (sum, prod) x -> (sum+x, prod*x)) (0, 1) xs

let result = sumProd [1;2;3;4;5];;

(* 3.4 *)

let rec quicksort = function
  | [] -> [] (*dodanie |*)
  | [x] -> [x]
  | xs ->
    let small = List.filter (fun y -> y < List.hd xs) xs in (*dodanie in jako zmienna lokalna tak samo w drugim*)
    let large = List.filter (fun y -> y >= List.hd xs) xs in (*let zamiast and *)
    quicksort small @ quicksort large;; (*wywalnie in bo nie ma zadnej f zagnieżdzonej*)


quicksort [6;5;4;3;2];;

let rec quicksort' = function
 | [] -> [] (*dodanie |*)
 | x::xs -> 
    let small = List.filter (fun y -> y < x ) xs in (*dodanie in jako zmienna lokalna tak samo w drugim*)
    let large = List.filter (fun y -> y > x ) xs in (*let zamiast and *)
  quicksort' small @ (x :: quicksort' large);; (*wywalnie in bo nie ma zadnej f zagnieżdzonej*)


quicksort' [9;8;7;6;5;3];;


(* 3.5 *)

let rec insertion_sort (xs, func) = 
  let rec insert (x, xs) = 
    match xs with
    | [] -> [x]
    | y::ys -> if func x y then x::xs else y::(insert (x, ys))
  in

  List.fold_left (fun acc x -> insert(x, acc)) [] xs
;;

insertion_sort ([1;2;3;4;5], (fun x y -> x > y));;

let rec split xs =
  let rec split' (xs, left, right) =
    match xs with
    | [] -> (left, right)
    | x::xs -> split' (xs, right, x::left)
  in
  split' (xs, [], [])


let rec merge_sort func xs =
  let rec merge xs ys =
    match (xs, ys) with
    | ([], ys) -> ys
    | (xs, []) -> xs
    | (x :: xs, y :: ys) ->
      if func x y then x :: merge xs (y :: ys)
      else y :: merge (x :: xs) ys
  in
  let n = List.length xs in
  if n <= 1 then xs
  else
    let left, right = split xs in
    merge (merge_sort func left) (merge_sort func right)
    
  
let result = merge_sort (fun x y -> x > y) [1;2;3;4;5];;

let tupleList = [(1,2);(3,4);(5,6);(7,8);(9,10)];;

let stabilitySort = merge_sort (fun (x1,y1) (x2,y2) -> x1 > x2 && y2 > y1) tupleList;;




