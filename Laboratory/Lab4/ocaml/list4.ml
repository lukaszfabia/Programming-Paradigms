(* zadanie 1  *)

type point = float * float;;

(* kod z dokumentacji  *)
let distance ((x1, y1) : point) ((x2, y2) : point) : float =
  Float.sqrt ((x1 -. x2) ** 2. +. (y1 -. y2) ** 2.)
;;


let dist = distance (1.0, 5.0) (2.0, 3.0)


type point3D = float * float * float


type pointND = float list

let rec distance (p1 : pointND) (p2 : pointND) : float =
  let rec aux acc = function
    | [], [] -> acc
    | x :: restOfFirst, y :: restOfSecond -> aux (acc +. (x -. y) *. (x -. y)) (restOfFirst, restOfSecond)
    | _ -> failwith "Points have to be the same dimension"
  in
  sqrt (aux 0.0 (p1, p2))


let point1 = [1.0; 2.0; 3.0];;
let point2 = [4.0; 5.0; 6.0];;


let dist_points = distance point1 point2;;


(* zadanie 2a *)

type person = string * string * int * char * float

let lukasz: person = ("Lukasz", "", 20, 'm', 43.0)
let fabia: person = ("Fabia", "Lukasz", 20, 'm', 35.5)

type partnership = person * person

let get_younger (p1, p2) =
  let (_, _, age1, _, _) = p1 in 
  let (_, _, age2, _, _) = p2 in 
  if age1 < age2 then p1 else p2
;;

let p: partnership = (lukasz, adam) ;;
let younger = get_younger p ;;

(* zadanie 2b *)

type Person = {
  name : string;
  lastname : string;
  age : int;
  sex : char;
  kicks_size : float;
}

type Partnership = {
  p1 : Person;
  p2 : Person;
}

let get_younger (p : Partnership) : Person =
  if p.p1.age < p.p2.age then p.p1 else p.p2


let me = {name = "Lukasz"; lastname = ""; age = 20; sex = 'm'; kicks_size = 43.0};;
let you = {name = "!Luksz"; lastname = "!Fabia"; age = 80; sex = 'f'; kicks_size = 57.5};;

let p: Partnership = {p1 = me; p2 = you};;
let younger = get_younger p ;;


(* zadanie 3 *)

type weekday = Mon | Tue | Wed | Thu | Fri | Sat | Sun ;;

let get_next_day (day : weekday) : weekday =
  match day with
  | Mon -> Tue
  | Tue -> Wed
  | Wed -> Thu
  | Thu -> Fri
  | Fri -> Sat
  | Sat -> Sun
  | Sun -> Mon
;;


let get_string_of_day (day : weekday) : string =
  match day with
  | Mon -> "poniedzialek"
  | Tue -> "wtorek"
  | Wed -> "sroda"
  | Thu -> "czwartek"
  | Fri -> "piatek"
  | Sat -> "sobota"
  | Sun -> "niedziela"
;;

let afterFriday = get_string_of_day (get_next_day Fri);;

(* zadanie 4 *)

type 'a maybe =
  | Just of 'a
  | Nothing

let safeHead (list : 'a list) : 'a maybe =
  match list with
  | [] -> Nothing
  | x :: _ -> Just(x)
;;

let safeHeadResult = safeHead [1; 2; 3];;


(* zadanie 5 *)
(* type solid_figure = 
  | Sphere of float
  | Cuboid of float * float * float
  | Cylinder of float * float
  | Cone of float * float
;;


let volume (figure : solid_figure) : float =
  match figure with
  | Sphere(r) -> (4.0 /. 3.0) *. Float.pi *. r *. r *. r
  | Cuboid(a, b, c) -> a *. b *. c
  | Cylinder(r, h) -> Float.pi *. r *. r *. h
  | Cone(r, h) -> (1.0 /. 3.0) *. Float.pi *. r *. r *. h
;;

let sphere = (Sphere(2.0).volume);;
let cuboid = volume (Cuboid(2.0, 3.0, 4.0));; *)



