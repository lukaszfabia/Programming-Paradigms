package users

import levels.High

class AdminUser(var secret : String) extends User[High, High] {
    private var secret_ : String = secret

    override def instance: String = "admin user"

    override def secret(s: String): Unit = secret_ = s
}