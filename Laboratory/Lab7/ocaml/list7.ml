let modifiedPascalI n =
  let result = ref (Array.make (n + 1) 1) in
  let curr = ref (Array.make (0) 0) in
  let i = ref 0 in
  let j = ref 0 in

  while !i <= n do
    (* let curr = ref (Array.make (!i + 1) 0) in mozna tak ale nie optymalne n+1 razy zostanie stworzona nowa zmienna*)
    curr := Array.make (!i + 1) 0; (* nadpisanie*)
    (!curr).(0) <- 1;
    (!curr).(!i) <- 1;
    j := 1;
    while !j < !i do
      if !i mod 2 = 0 then !curr.(!j) <- !result.(!j - 1) + !result.(!j)
      else !curr.(!j) <- !result.(!j - 1) - !result.(!j);
      j := !j + 1
    done;
    result := !curr;
    i := !i + 1
  done;

  !result;;

modifiedPascalI 0;;
modifiedPascalI 1;;
modifiedPascalI 2;;
modifiedPascalI 3;;
modifiedPascalI 4;;
modifiedPascalI 5;;
modifiedPascalI 6;;


let sieve n =
  if n < 2 then [||]
  else

  let is_prime = ref (Array.make (n + 1) true) in
  !is_prime.(0) <- false;
  !is_prime.(1) <- false;
  let i = ref 2 in
  while !i * !i <= n do
    if !is_prime.(!i) then
      let j = ref (!i * !i) in
      while !j <= n do
        !is_prime.(!j) <- false;
        j := !j + !i
      done;
    i := !i + 1
  done;
 
  (*robimy tablice int z bool*)
  let len = (Array.length !is_prime) in 
  let result = ref (Array.make (len) 0) in
  let k = ref 0 in 
  let amount_of_primes = ref 0 in 
  while !k < len do 
    if !is_prime.(!k) then begin
       !result.(!k) <- !k;
      amount_of_primes := !amount_of_primes + 1;
    end;
    k := !k + 1
  done;

 (*usuwamy zera*)
 let clear_array = ref (Array.make !amount_of_primes 0) in 
  let h = ref 0 in (*iterator dla clear_array*)
  let g = ref 0 in (*iterator dla result*)
  while !g < len do 
    if !result.(!g) != 0 then begin
      !clear_array.(!h) <- !result.(!g);
      h := !h + 1
    end;
    g := !g + 1
  done;

!clear_array
;;

sieve 10;;
sieve 14;;
sieve (-1);;
sieve (0);;
sieve 2 ;;

sieve (11);;
sieve (-1);;
sieve 1;;
sieve 0;;
sieve 7;;
sieve (15);;