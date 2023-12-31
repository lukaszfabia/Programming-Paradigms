(*2.1*)

(* let rec cutOut list a b =
  let rec cut input begin_idx end_idx output =
    match input, begin_idx, end_idx with
    | _, _, (-1) -> output
    | head :: tail, 0, _ -> cut tail 0 (end_idx - 1) (head :: output)
    | _ :: tail, _, _ -> cut tail (begin_idx - 1) (end_idx - 1) output
    | [], _, _ -> output
  in
  cut list a b [];; *)

(*jesli funckcja ma arg typu let f(a,b,c) -> postac zwinieta*)
(*jesli funckcja ma arg typu let f a b c -> postac rozwinieta*)
let rec cutOut a b input = 
  match input, a, b with
  | (_, _, -1) -> []
  | (head :: tail, 0, _) -> head :: cutOut 0 (b - 1) tail
  | (_ :: tail, _, _) -> cutOut (a - 1) (b - 1) tail
  | ([], _, _) -> [];;

let cutout15 = cutOut 1 5;;
let cut = cutout15 [1; 2; 3; 4; 5; 6; 7; 8; 9; 10];;



let () =
  print_endline (string_of_bool (cutOut 1 2 [1; 2; 222; 3; 4]= [2; 222]));
  print_endline (string_of_bool (cutOut 0 3 [1; 2; 222; 3; 4]= [1; 2; 222; 3]));
  print_endline (string_of_bool (cutOut 0 0 [1; 2; 222; 3; 4] = [1]));
  print_endline (string_of_bool (cutOut 0 4 [1; 2; 222; 3; 4; 11]= [1; 2; 222; 3; 4]));
  print_endline (string_of_bool (cutOut 0 5 [1; 2; 222; 3; 4]= [1; 2; 222; 3; 4]));
  print_endline (string_of_bool (cutOut (-1) 10 [1; 2; 222; 3; 4]= []));
  print_endline (string_of_bool (cutOut 1 1 []= []));
  print_endline (string_of_bool (cutOut 1 1 [1] = []));
  print_endline (string_of_bool (cutOut 0 0 [1] = [1]));
  print_endline (string_of_bool (cutOut 12 (-12) [1; 2; 3; 5; 15] = []));
  print_endline (string_of_bool (cutOut (-12) 12 [1; 2; 3; 5; 15] = []));
  print_endline (string_of_bool (cutOut (-12) 12 ['1'; '2'; '3'; '5'; 'x'] = []));;
  print_endline (string_of_bool (cutOut 0 12 [true; false; false; true; false] = [true; false; false; true; false]));;
  print_endline (string_of_bool (cutOut 0 (-4) [true; false; false; true; false] = [true; false; false; true; false]));;
 


(*2.2*)
let rec split3Rec input = 
  match input with
  | element1 :: element2 :: element3 :: tail -> 
    let (list1, list2, list3) = split3Rec tail in 
    (element1 :: list1, element2 :: list2, element3 :: list3)
  | _ -> ([], [], [])

let split3Tail input =
  (* przyklad postaci rozwinietej w funkcji split *)
  let rec split input list1 list2 list3 = 
    match input with
    | element1 :: element2 :: element3 :: tail -> split tail (element1 :: list1) (element2 :: list2) (element3 :: list3)
    | _ -> (list1, list2, list3)
  in
  split input [] [] [];;




let () =
  print_endline (string_of_bool (split3Rec [1; 2; 3; 4; 5; 6; 7] = ([1; 4], [2; 5], [3; 6])));
  print_endline (string_of_bool (split3Rec [1; 2; 3; 4; 5; 6] = ([1; 4], [2; 5], [3; 6])));
  print_endline (string_of_bool (split3Rec [1; 2; 3; 4; 5] = ([1], [2], [3])));
  print_endline (string_of_bool (split3Rec [1; 2; 3; 4] = ([1], [2], [3])));
  print_endline (string_of_bool (split3Rec [true; false; true] = ([true], [false], [true])));
  print_endline (string_of_bool (split3Rec ['1'; '2'] = ([], [], [])));
  print_endline (string_of_bool (split3Rec ["1"] = ([], [], [])));
  print_endline (string_of_bool (split3Rec [] = ([], [], [])));

  print_endline (string_of_bool (split3Tail [1; 2; 3; 4; 5; 6; 7] = ([4; 1], [5; 2], [6; 3])));
  print_endline (string_of_bool (split3Tail [1; 2; 3; 4; 5; 6] = ([4; 1], [5; 2], [6; 3])));
  print_endline (string_of_bool (split3Tail [1; 2; 3; 4; 5] = ([1], [2], [3])));
  print_endline (string_of_bool (split3Tail [1; 2; 3; 4] = ([1], [2], [3])));
  print_endline (string_of_bool (split3Tail [true; false; true] = ([true], [false], [true])));
  print_endline (string_of_bool (split3Tail ['1'; '2'] = ([], [], [])));
  print_endline (string_of_bool (split3Tail ['1'] = ([], [], [])));
  print_endline (string_of_bool (split3Tail [] = ([], [], [])));;