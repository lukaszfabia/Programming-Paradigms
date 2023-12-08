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


