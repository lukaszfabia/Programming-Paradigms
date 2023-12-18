module type Memory = sig
  type 'a t

  val init : int -> 'a t
  val get : 'a t -> int -> 'a option
  val set : 'a t -> int -> 'a -> unit
  val dump : 'a t -> 'a option list
  val size : 'a t -> int
end
