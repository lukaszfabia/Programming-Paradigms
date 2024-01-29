package users

import levels.Super

class SuperUser(var secret: String) extends User[Super, Super]{
    private var secret_ : String = secret

    override def secret(s: String): Unit = secret_ = s
    override def instance: String = "super user"
}