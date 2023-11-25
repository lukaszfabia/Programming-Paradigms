(* zadanie 3 *)

let rec stirling n m =
  if n < m then failwith "n < m"
  else 
    match n, m with
    | 0, 0 -> 1
    | n, 0 -> 0
    | 0, m -> 0
    | n, m ->
      if n = m then 1
      else (stirling (n-1) (m-1)) + m * (stirling (n-1) m)
;;


let lazyFactorial = lazy (stirling 10 5);;
let tryhardFactorial = stirling 10 5;;

for i = 1 to 10 do
  print_endline "czekamy na wynik"
done;;
