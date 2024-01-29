// brak + przed read i save bo termianale raczej sa nie zalezne od siebie czyli sa inwariantne
class Terminal[Read <: Low, Save <: Low](var startSecret: String):
  private var secret_ : String = startSecret

  def read(user: User[Read, _]): Unit = {
    println("read by " + user.instance + ": " + secret_)
  }

  def save(user: User[_, Save]): Unit = {
    secret_ = user.secret
    user.secret(secret_)
    println("save: " + user.instance + ": " + secret_)
  }