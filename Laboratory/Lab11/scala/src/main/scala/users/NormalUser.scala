package users

import levels.High
import levels.Low

class NormalUser(var secret : String) extends User[High, Low]:
    override def instance: String = "normal user"