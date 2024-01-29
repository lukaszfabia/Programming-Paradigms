import scala.users.User

class AdminUser(var secret: String) extends User[High, High]{
  private var secret_ : String = secret

  override def secret(s: String): Unit = {
    secret_ = s
    println(instance + " has been changed: " + secret_)
  }

  override def instance: String = "admin user"
}