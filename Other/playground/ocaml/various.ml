let rec (/) list divisior = 
  match list with 
  | [] -> []
  | head :: tail -> 
        if head mod divisior = 0 && head >= divisior then true :: (tail / divisior)
        else false :: (tail / divisior)
 ;;
 
 let test1 = [1;2;32;43;5] / 2 ;;
 
 let rec (<<) list multiplier =
  match list with
  | [] -> []
  | head :: tail -> (head , head * multiplier) :: (tail << multiplier)
 ;;
 
 let test2 = [1;2;3;4;5;6] << 12 ;;
 
 let rec (@) list1 list2 =
  match list1, list2 with
  | ([], _) | _, [] -> []
  | head1 :: tail1, head2 :: tail2 -> (head1, head2) :: (tail1 @ tail2)
 ;;
 
 let test3 = [1;2;3;4;5] @ [6;7;8;9;10] ;;
 
 let rec (-) list element = 
  match list with
  | [] -> []
  | head :: tail -> if head = element then tail - element else head :: tail - element
 ;;
 
 
 let test4 = [1;2;3;4;23;2;2;2;5] - 2 ;; 
 let test5 = [1;21;3;4;5] - 2 ;;
 let test6 = [] - 111 ;;
 
 
 let getDuplicates list = 
  let rec contains list element =   
    match list with 
    | [] -> false 
    | head :: tail -> if head == element then true else contains tail element
   in 
  let rec aux list output = 
    match list with 
    | [] -> output
    | head :: tail -> if contains tail head then aux tail (head :: output) else aux tail output
   in 
  aux list []
 ;;
 
let test7 = getDuplicates [1; 2; 3; 2; 4; 1; 5; 3];;
 
let move list index =
  let rec split list index = 
    match list, index with
    | [], _ -> [], []
    | head :: tail, 0 -> [], tail
    | head :: tail, index -> 
      let part1, part2 = split tail (index - 1) in head :: part1, part2
  in
  
  let part1, part2 = split list index in 
  part2 @ part1
;;


let test8 = move [1;2;3;4;5;6] 2 ;;

let removeNth list n = 
  let aux list index = 
    match list, index with
    | [], _ -> []
    | head :: tail, 0 -> aux tail n
    | head :: tail, _ -> head :: aux tail (index - 1)
  in 
  aux list n
;;

let test9 = removeNth [1;2;3;4;5] 2;;
    

let change_elements list = 
  let is_even fun f x = x mod 2 = 0 in 

  let rec take_lists list = 
    match list with
    | [] -> [], []
    | head :: tail -> 
      let part1, part2 = take_lists tail in 
      if is_even head then head :: part1, part2 
      else part1, head :: part2
  in

  let rec build_lists even odd = 
    match even, odd with
    | [], _ | _, [] -> []
    | head1 :: tail1, head2 :: tail2 -> head2 :: head1 :: build_lists tail1 tail2
  in

  let even, odd = take_lists list in 
  build_lists even odd
;;

let test10 = change_elements [1;2;3;4;5;6] ;; 


let take_identity list1 list2 = 
  let rec count lst1 lst2 acc = 
    match lst1, lst2 with
    | [], _ | _, [] -> acc
    | h1 :: t1, h2 :: t2 -> 
      if h1 = h2 then count t1 t2 (acc+1)
      else count t1 t2 acc
  in
  count list1 list2 0
;;


let test11 = take_identity [1;2;3;4;5] [1;0;3;0;5] ;; 

let power_sum list = 
  List.fold_left (fun acc x -> acc + x * x) 0 list
;;

let test12 = power_sum [1;2;3;4;5] ;;
    

type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;;

let rec ltake = function
      (0, _) -> []
    | (_, LNil) -> []
    | (n, LCons(x,lazy xs)) -> x::ltake(n-1,xs)
;;


type 'a lazy_bt = LEmpty | LNode of 'a * (unit -> 'a lazy_bt) * (unit -> 'a lazy_bt);;

let t =
  LNode (1, 
         (fun () -> LNode (2, (fun () -> LEmpty), (fun () -> LNode(3, (fun() -> LEmpty), (fun() -> LEmpty))))),
         (fun () -> LEmpty)
        )


let rec lazy_tree_map : ('a -> 'b) -> 'a lazy_bt -> 'b lazy_bt =
  fun f tree ->
    match tree with
    | LEmpty -> LEmpty
    | LNode (value, left, right) ->
        let map_left () = lazy_tree_map f (left ()) in
        let map_right () = lazy_tree_map f (right ()) in
        LNode (f value, map_left, map_right)
;;

let lBreadth ltree =
  let rec breadthHelper = function
      [] -> LNil
      | LEmpty::t -> breadthHelper t
      | LNode(v, l, r)::t -> LCons(v, lazy (breadthHelper(t @ [l() ; r()])))
    in breadthHelper [ltree];;


let new_tree = lazy_tree_map (fun x -> x * x * x) t;;

ltake(3, lBreadth new_tree);;
