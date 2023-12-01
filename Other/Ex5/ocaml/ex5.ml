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
