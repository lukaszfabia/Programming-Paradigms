open Memory

module Array_Memory : Memory = struct
  type 'a t = { arr : 'a option array }

  let init n = { arr = Array.make n None }
  let get s i = match s.arr.(i) with None -> None | Some v -> Some v
  let set s i v = s.arr.(i) <- Some v

  let dump s =
    let rec loop i n =
      if i < n then
        match s.arr.(i) with
        | None -> None :: loop (i + 1) n
        | Some v -> Some v :: loop (i + 1) n
      else []
    in
    loop 0 (Array.length s.arr)

  let size s = Array.length s.arr
end
