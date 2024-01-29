
object Main extends App {
  val terminal = new Terminal[Low, High]("to jest tajny tekst")

  val normalUser = new NormalUser("jestem normikiem")
  val admin = new AdminUser("jestem adminem")
  val superUser = new SuperUser("jestem super userem")


  terminal.read(normalUser)
//  terminal.save(normalUser)
  terminal.save(admin)
  terminal.read(normalUser)
  terminal.save(superUser)
  terminal.read(superUser)


  terminal.read(admin)
  terminal.read(normalUser)
}