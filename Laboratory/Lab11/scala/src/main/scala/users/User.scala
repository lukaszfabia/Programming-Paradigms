package users
import levels.Low

trait User[+Read <: Low, +Save <: Low]: 
    def secret: String // akcesor

    def secret_= (s: String): Unit // mutator

    def instance: String 
