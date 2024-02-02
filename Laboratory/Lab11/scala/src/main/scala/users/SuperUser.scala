package users

import levels.Super

class SuperUser(var secret: String) extends User[Super, Super]:
    override def instance: String = "super user"
