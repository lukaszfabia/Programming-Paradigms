import users.User
import levels.Low

class Terminal[Read <: Low, Save <: Low](private var startSecret: String):

    def read(user : User[Read, _]): Unit = 
        println("read and captured by " + user.instance + ": " + startSecret)
        user.secret(startSecret)

    def save(user: User[_, Save]): Unit = 
        startSecret = user.secret
        println("saved by " + user.instance + ": " + startSecret)

    override def toString(): String = startSecret

    def getStartSecret(): String = startSecret

    def setStartSecret(newSecret: String): Unit = startSecret = newSecret