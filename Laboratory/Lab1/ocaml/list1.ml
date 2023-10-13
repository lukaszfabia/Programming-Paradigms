let rec insert (list, element, index) = 
    if list != [] then
      if index=0 then
        element :: list
       else 
        List.hd list :: insert(List.tl list, element, index-1)
    else 
      element :: list




(* insert([12;3;4;5;6], 99, 0)
insert([12;3;4;5;6], 99, 100)
insert([12;3;4;5;6], 99, 3)
insert([12;3;4;5;6], 99, -1) *)

let f = function x+x;;

