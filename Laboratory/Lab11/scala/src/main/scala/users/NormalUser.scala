package users

import levels.High
import levels.Low

class NormalUser(var secret : String) extends User[High, Low] {
    private var secret_ : String = secret

    override def instance: String = "normal user"

    override def secret(s: String): Unit = secret_ = s
}