package users

import levels.High

class AdminUser(var secret : String) extends User[High, High]:
    override def instance: String = "admin user"