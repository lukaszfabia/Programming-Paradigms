class NormalUser(var secret: String) extends User[High, Low]:
  private var secret_ : String = secret

  override def secret(s: String): Unit = {
    secret_ = s
    println(instance + " has been changed: " + secret_)
  }

  override def instance: String = "normal user"
