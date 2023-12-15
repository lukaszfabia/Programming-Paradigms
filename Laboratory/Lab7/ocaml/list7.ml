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