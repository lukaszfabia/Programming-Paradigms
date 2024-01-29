import users.User
import levels.Low

class Terminal[Read <: Low, Save <: Low](private var startSecret: String):

    def read(user : User[Read, _]): Unit = 
        println("read by " + user.instance + ": " + startSecret)

    def save(user: User[_, Save]): Unit = 
        startSecret = user.secret
        user.secret(startSecret)
        println("saved by: " + user.instance + ": " + startSecret)

    override def toString(): String = startSecret

    def getStartSecret(): String = startSecret

    def setStartSecret(newSecret: String): Unit = startSecret = newSecret