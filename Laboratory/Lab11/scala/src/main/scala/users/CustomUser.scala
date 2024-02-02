package users
import levels.* 

/**
 * Create your own user with various levels!
 *
 * @param secret The secret associated with the user.
 * @tparam Read The type of read capability.
 * @tparam Save The type of save capability.
 */
class CustomUser[+Read <: Low, +Save <: Low](var secret : String) extends User[Read, Save]:
    override def instance: String = "custom user"
