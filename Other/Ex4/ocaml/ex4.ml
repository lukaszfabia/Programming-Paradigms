let f1 x y z = x y z;; 
(* val f1 : ('a -> 'b -> 'c) -> 'a -> 'b -> 'c = <fun> *)
let f2 x y = function z -> x::y;;
(* val f2 : 'a -> 'a list -> 'b -> 'a list = <fun> *)

(* zadanie 2 *)

let f (x : int) : char = char_of_int x ;;
(* val f : int -> char = <fun> *)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt ;;
 
type 'a graph = Graph of ('a -> 'a list) ;;

let tt =
  Node(1,
    Node(2,
      Node(4,
        Empty,
        Empty
      ),
      Empty
    ),
    Node(3,
      Node(5,
        Empty,
        Node(6,
          Empty,
          Empty
        )
      ),
      Empty
    )
  )
;;

let breadthBT tree = 
  let rec aux tree = 
    match tree with
    | Empty -> []
    | Node (value, left, right) -> value::(aux left)@(aux right)
  in
  aux tree  
;;

let res = breadthBT tt;;
(* val res : int list = [1; 2; 3; 4; 5; 6] *)
