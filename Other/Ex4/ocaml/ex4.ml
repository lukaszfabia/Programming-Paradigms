let f1 x y z = x y z;;
(* val f1 : ('a -> 'b -> 'c) -> 'a -> 'b -> 'c = <fun> *)

let f2 x y = function z -> x::y;;
(* val f2 : 'a -> 'a list -> 'b -> 'a list = <fun> *)

(* zadanie 2 *)

let f (x : int) : char = char_of_int x ;;
let rec f x = f x;;
(* f: 'a -> 'b *)
(* val f : int -> char = <fun> *)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt ;;

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


(* zadanie 4 *)

let inner_path tree = 
  let rec eval tree res = 
    match tree with
    | Empty -> failwith "empty"
    | Node(_, Empty, Empty) -> res
    | Node(_, left, Empty) -> res + (eval left (res + 1))
    | Node(_, Empty, right) -> res + (eval right (res + 1))
    | Node(_, left, right) -> res + (eval left (res + 1) + eval right (res + 1))
  in 
  eval tree 0
;;

let outer_path tree = 
  let rec eval tree res = 
    match tree with
    | Empty -> res
    | Node(_, Empty, Empty) -> 2 * (res + 1)
    | Node(_, left, Empty) -> (res + 1) + (eval left (res + 1))
    | Node(_, Empty, right) -> (res + 1) + (eval right (res + 1))
    | Node(_, left, right) -> eval left (res + 1) + eval right (res + 1)
  in 
  eval tree 0
;;

(* zadanie 5 *)

type 'a graph = Graph of ('a -> 'a list);;

let g = Graph
(function
0 -> [3]
| 1 -> [0;2;4]
| 2 -> [1]
| 3 -> []
| 4 -> [0;2]
| n -> failwith ("Graph g: node "^string_of_int n^" doesn't exist")
);;

let breadthSearch (Graph succ) node =
  let rec search visited = function
   | [] -> []
   | h::t -> 
    if List.mem h visited then search visited t
    else h :: search (h :: visited) (t @ succ h)
  in 
  search [] [node]
;;

breadthSearch g 4;; 
(* val res : int list = [4; 0; 2; 3; 1] *)

let depthSearch (Graph succ) node = 
  let rec aux result visited rest = 
    match rest with 
    | [] -> []
    | head :: tail -> 
      if List.mem head visited then aux result visited tail
      else head :: aux result (head :: visited) (succ head @ tail)
  in
  aux [] [] [node]
;;

let res = depthSearch g 4;;
(* val res : int list = [4; 0; 3; 2; 1] *)
