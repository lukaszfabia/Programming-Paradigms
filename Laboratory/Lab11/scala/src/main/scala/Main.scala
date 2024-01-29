import users.NormalUser
import users.AdminUser
import users.SuperUser
import levels.High
import levels.Low

object Main extends App {
  val terminal = new Terminal[Low, High]("inital")
  
  val normal = new NormalUser("jestem normikiem")
  val admin = new AdminUser("jeste admin")
  val superUser = new SuperUser("jestem super ")

  terminal.read(normal)
  // terminal.save(normal) nie uda sie bo nie ma odpowienich uprawnien

  terminal.save(admin)
  terminal.read(admin)
  terminal.read(normal)
  terminal.save(superUser)
  terminal.read(superUser)

  terminal.read(admin)
  terminal.read(normal)

}