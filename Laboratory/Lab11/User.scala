// ten zapis gwarantuje ze typy Read i Save zawsze beda podtypami Low lub typem Low
// i userzy beda w relacji ze User[Low, High] bedzie podtypem User[Low, Low]

trait User[+Read <: Low, +Save <: Low]:
  def secret: String

  def secret(s: String): Unit

  def instance: String // metoda uzywana do wyswietlania logów