let rec sumProd xs = List.fold_left (fun (sum, prod) x -> (sum+x, prod*x)) (0, 1) xs

let result = sumProd [1;2;3;4;5];;


let rec insertion_sort (xs, func) = 
  let rec insert (x, xs) = 
    match xs with
    | [] -> [x]
    | y::ys -> if func x y then x::xs else y::(insert (x, ys))
  in
  match xs with
  | [] -> []
  | x::xs -> insert (x, insertion_sort (xs, func));;

insertion_sort ([1;2;3;4;5], (fun x y -> x > y));;

let rec split xs =
  let rec split' (xs, left, right) =
    match xs with
    | [] -> (left, right)
    | x::xs -> split' (xs, right, x::left)
  in
  split' (xs, [], [])


let rec merge_sort func xs =
  let rec merge xs ys =
    match (xs, ys) with
    | ([], ys) -> ys
    | (xs, []) -> xs
    | (x :: xs, y :: ys) ->
      if func x y then x :: merge xs (y :: ys)
      else y :: merge (x :: xs) ys
  in
  let n = List.length xs in
  if n <= 1 then xs
  else
    let left, right = split xs in
    merge (merge_sort func left) (merge_sort func right)
    
  
let result = merge_sort (fun x y -> x > y) [1;2;3;4;5];;