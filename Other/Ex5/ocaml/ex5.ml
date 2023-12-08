<<<<<<< HEAD
type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t

let rec lfrom k = LCons (k, lazy (lfrom (k+1)))

let rec ltake = function
 | (0, _) -> []
 | (_, LNil) -> []
 | (n, LCons(x, lazy xs)) -> x :: ltake(n-1, xs)
;; 

let rec lrepeat : int -> 'a llist -> 'a llist =
  let rec repeat x n =
    if n <= 0 then LNil
    else LCons (x, lazy (repeat x (n - 1)))
  in
  let rec aux list n =
    match list with
    | LNil -> LNil
    | LCons (x, xs) -> LCons (x, lazy (repeat x n));;
  aux 


let rec list_to_llist = function
 [] -> LNil
 | x :: xs -> LCons(x, lazy (list_to_llist xs))
;;

let rec take_print n ll =
  match n, ll with
  | 0, _ -> ()
  | _, LNil -> ()
  | n, LCons (x, lazy xs) -> 
    print_int x; print_string " ";
    take_print (n - 1) xs
;;


let lista = list_to_llist (ltake(10, lfrom 1));;
(* take_print 10 lista *)
let wynik = lrepeat 3 lista;;
take_print 10 wynik;;


=======
type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let rec ltake (n, lxs) =
    match (n, lxs) with
    | (0, _) -> []
    | (_, LNil) -> []
    | (n, LCons(x,xf)) -> x::ltake(n-1, xf())
;;
  

let rec lfrom k = LCons (k, function () -> lfrom (k+1));;

(0, 1)

(* zadanie 1 *)

let rec repeat (n: int) (list: 'a llist): 'a llist =
  let rec aux (x: int) (lst: 'a llist): 'a llist =
    match lst, x with
    | LNil, _ -> LNil
    | LCons(head, tail), 0 -> aux n (tail())
    | LCons(head, tail), _ -> LCons(head, fun() -> aux (x - 1) lst)
  in
  aux n list
;;

let rec toLazyList xs = match xs with
    []   -> LNil
  | h::t -> LCons(h, fun () -> toLazyList t);;


let list = ltake(10, lfrom 1);;

let result1 = repeat 3 (toLazyList list);;

let rec lazy_list_to_list ll =
  match ll with
  | LNil -> []
  | LCons (x, xf) -> x :: lazy_list_to_list (xf ())
;;

lazy_list_to_list result1;;

(* XDDDDDDDDD przeicez ten ocaml nie ma sensu *)


(* zadanie 2  *)

let fib() : int llist =
  let rec aux a b : int llist =
    LCons(a, fun () -> aux b (a + b))
  in
  aux 0 1
;;

let result = ltake(10, fib());;


type 'a lBT = LEmpty | LNode of 'a * (unit -> 'a lBT) * (unit -> 'a lBT)

let l_breadth tree = 
  let rec aux = function
  [] -> LNil
  | LEmpty :: tail -> aux tail
  | LNode(elem, left, right) :: tail -> LCons(elem, fun() -> aux (tail @ [left(); right()]))
in
aux [tree]
;;

let rec l_tree x = LNode(x, (fun()-> l_tree(2*x)), (fun()->l_tree(2*x+1)));;


let res3 = ltake(10, l_breadth(l_tree 4));;


(* zadanie 3 *)
>>>>>>> aa0891bcbdbaa3a9e6449ff53a3609d396788ab6
