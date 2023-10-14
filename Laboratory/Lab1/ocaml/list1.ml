let reverse4 (a, b, c, d) = (d, c, b, a)

let sumProd s e =
  let rec createList s e =
    if s >= e then []
    else s :: (createList (s + 1) e)
  in

  let list = createList s e in
  let sum, prod = 0, 1 in

  let rec count list sum prod =
    if list = [] then (sum, prod)
    else count (List.tl list) (sum + List.hd list) (prod * List.hd list)
  in

  count list sum prod;;

let isPerfect n = 

  let rec dividersList dividers i n =
    if i < n then if n mod i = 0 then i :: dividersList dividers (i + 1) n else dividersList dividers (i + 1) n
    else dividers
  in
    
  let rec letsSum list sum =
    if list = [] then sum
    else letsSum (List.tl list) (sum + List.hd list)
  in
  
  if n < 1 then false
  else 
    let resSum = letsSum (dividersList [] 1 n) 0 in 
    if resSum = n then true else false;;


let rec insert list element index =
  if list = [] then [element]
  else if index = 0 then element :: list
  else List.hd list :: insert (List.tl list) element (index - 1);;


(* Testy dla reverse4 *)
(reverse4 (1, 2, 3, 4) = (4, 3, 2, 1));;
(reverse4 ('1', '1', '1', '1') = ('1', '1', '1', '1'));;
(reverse4 (true, 1, true, "string") = ("string", true, 1, true));;

(* Testy dla sumProd *)
(sumProd 1 5) = (10, 24);;
(sumProd 1 1) = (0, 1);;
(sumProd (-1) (-10)) = (0, 1);;
(sumProd 1 0) = (0, 1);;
(sumProd 1 2) = (1, 1);;
(sumProd (-10) (-5)) = (-40, -30240);;

(* Testy dla isPerfect *)
(isPerfect 6 = true);;
(isPerfect 28 = true);;
(isPerfect 496 = true);;
(isPerfect 0 = false);;
(isPerfect 1 = false);;

(* Testy dla insert *)
(insert [1; 2; 3] 4 0 = [4; 1; 2; 3]);;
(insert [1; 2; 3] 4 1 = [1; 4; 2; 3]);;
(insert ['h'; 'e'; 'l'; 'o'] 'l' 2 = ['h'; 'e'; 'l'; 'l'; 'o']);;
(insert [true; false; true] false (-1) = [true; false; true; false]);;
(insert ['1'; '2'; '3'] '4' 4 = ['1'; '2'; '3'; '4']);;
