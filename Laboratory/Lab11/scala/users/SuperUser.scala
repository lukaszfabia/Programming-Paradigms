import scala.users.User

class SuperUser(var secret: String) extends User[Super, Super] {
  private var secret_ : String = secret

  override def secret(s: String): Unit = {
    secret_ = s
    println(instance + " has been changed: " + secret_)
  }

  override def instance: String = "super user"
}