package users

import levels.Super

class SuperUser(var secret: String) extends User[Super, Super]:
    override def secret(s: String): Unit = secret = s
    override def instance: String = "super user"
