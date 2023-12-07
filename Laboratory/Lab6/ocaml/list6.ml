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
