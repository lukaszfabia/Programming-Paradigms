package users
import levels.Low


trait User[+Read <: Low, +Save <: Low]{
    def secret: String

    def secret(s: String): Unit

    def instance: String 
}
