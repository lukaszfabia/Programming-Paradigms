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
 
 