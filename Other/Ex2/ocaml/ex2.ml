let rec fib x =
  match x with
  | 0 -> 0
  | 1 -> 1
  | _ -> fib (x - 1) + fib (x - 2)


let rec fibTail x =
  let rec fibTailHelper (x, prev, prevPrev) =
    match x with
    | 0-> prevPrev
    | 1-> prev
    | _ -> fibTailHelper (x - 1, prev + prevPrev, prev)
  in fibTailHelper (x, 1, 0)


(*ta funkcja jest nie pewna i tyle*)
  let rec root3 number =
    let rec root3Helper (number, guess) =
      if abs_float (guess ** 3.0 -. number) <= (10.0 ** (-15.0)) *. abs_float number then
        guess
      else
        root3Helper (number, guess +. (number /. (guess ** 2.0) -. guess) /. 3.0)
    in
    root3Helper (number, 1.0) 
  
let initSegment (l1,l2) =
  let rec initSegmentHelper (l1,l2) =
    match (l1,l2) with
    | ([],_) -> true
    | (_,[]) -> false
    | (h1::t1,h2::t2) -> if h1 == h2 then initSegmentHelper (t1,t2) else false
  in
  initSegmentHelper (l1,l2)


let rec replaceNth (list, index, element) =
  match (list, index) with
  | ([],_) -> []
  | (_ :: tail, 0) -> element :: tail
  | (head :: tail, _) -> head :: replaceNth (tail, index - 1, element)
  

let list = [0;1;2;3;4;5;6;7;8;9;10]

let check = replaceNth (list, 5, -100)
let check2 = replaceNth (list, 0, -100)

let check3 = initSegment (list, list)
let check4 = initSegment (list, [0;1;2;3;4;5;6;7;8;9]) == false
let check5 = initSegment([0;1;2;3;4;5;6;7;8;9], list)

let check6 = root3 (27.0) <=  10.0 **(-15.0) *. 3.0
let check7 = root3 (8.0) <= 10.0 **(-15.0) *. 2.0
let check8 = root3 (1000.0) <= 10.0 **(-15.0) *. 10.0
let check9 = root3 (0.625) <= 10.0 **(-15.0) *. 0.5
let check10 = root3 (0.0) <= 10.0 **(-15.0) *. 0.0


let check11 = fib(0) == fibTail(0) && fib(0) == 0
let check12 = fib(1) == fibTail(1) && fib(1) == 1
let check13 = fib(2) == fibTail(2) && fib(2) == 1
let check14 = fib(3) == fibTail(3) && fib(3) == 2
let check15 = fib(4) == fibTail(4) && fib(4) == 3


