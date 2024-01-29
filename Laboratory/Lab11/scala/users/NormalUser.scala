import scala.users.User

class NormalUser(var secret: String) extends User[Low, High] {
  private var secret_ : String = secret

  override def secret(s: String): Unit = {
    secret_ = s
    println(instance + " has been changed: " + secret_)
  }

  override def instance: String = "normal user"
}
