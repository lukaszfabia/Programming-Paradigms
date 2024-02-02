import users.* 
import levels.* 

object Main extends App{
  val terminal = new Terminal[Low, High]("inital")
  
  val normal = new NormalUser("jestem normikiem")
  val admin = new AdminUser("jeste admin")
  val superUser = new SuperUser("jestem super ")

  val myUser = new CustomUser[Super, Low]("czesc to ja customowy userek")

  terminal.read(myUser)
  // terminal.save(myUser) nie ma uprawnien przecie 
  terminal.read(normal)
  // terminal.save(normal) //nie uda sie bo nie ma odpowienich uprawnien

  terminal.save(admin)
  terminal.read(admin)
  terminal.read(normal)
  terminal.save(superUser)
  terminal.read(superUser)

  terminal.read(admin)
  terminal.read(normal)

  println(terminal)


  val cmd = new Terminal[Super, Super]("tajne w opor")

  cmd.read(superUser)
  cmd.save(superUser)
}