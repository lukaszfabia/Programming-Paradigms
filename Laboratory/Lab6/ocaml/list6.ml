type 'a lazyBinaryTree = LEmpty | LNode of 'a * (unit -> 'a lazyBinaryTree) * (unit -> 'a lazyBinaryTree);;

let rec treeFoldL op acc = function
| LEmpty -> acc
| LNode (value, left, right) -> 
  let helper = treeFoldL op acc (left ()) in
  let acc = op helper value in
  treeFoldL op acc (right ());;

(* leniwe drzewo z nodami ulozonymi w danym porzadku - imitacja BST *)
let my_tree = 
  LNode (2, 
    (fun () -> LNode (1, (fun () -> LEmpty), (fun () -> LEmpty))), 
    (fun () -> LNode (4, (fun() -> LNode (3, (fun () -> LEmpty), (fun () -> LEmpty))), (fun () -> LEmpty)
  )))

let inorder tree = List.rev (treeFoldL (fun acc value -> value :: acc) [] tree);;

inorder my_tree;;


let rec tree_fold_right op acc = function
| LEmpty -> acc
| LNode (value, left, right) -> 
  let helper = tree_fold_right op acc (right ()) in
  let acc = op value helper in
  tree_fold_right op acc (left ());;

let res = tree_fold_right (fun value acc -> value :: acc) [] my_tree;;

res = inorder my_tree;;

<<<<<<< HEAD
type 'a lazy_tree = Empty | Node of 'a * 'a lazy_tree Lazy.t * 'a lazy_tree Lazy.t
=======
(* type 'a lazy_tree = Empty | Node of 'a * 'a lazy_tree Lazy.t * 'a lazy_tree Lazy.t
>>>>>>> aa0891bcbdbaa3a9e6449ff53a3609d396788ab6

let rec fold_left op acc = function
| Empty -> acc
| Node(value, left, right) ->
  let helper = fold_left op acc (Lazy.force left) in 
  let acc = op helper value in
  fold_left op acc (Lazy.force right);;

let my_tree =
  lazy (Node (2,
    lazy (Node (1, lazy Empty, lazy Empty)),
    lazy (Node (4, lazy (Node (3, lazy Empty, lazy Empty)), lazy Empty))
  ))
;;

<<<<<<< HEAD
let inorder = fold_left (fun acc value -> value :: acc) [] (Lazy.force my_tree);;

=======
let inorder = fold_left (fun acc value -> value :: acc) [] (Lazy.force my_tree);; *)
>>>>>>> aa0891bcbdbaa3a9e6449ff53a3609d396788ab6
