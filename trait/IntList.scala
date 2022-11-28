// 인트리스트
sealed trait IntList
case object End extends IntList
case class Pair(head: Int, tail: IntList) extends IntList

  
object Main extends App {
  val list = Pair(1, Pair(2, Pair(3, End)))

  println("HEAD",list.head)
  println("TAIL",list.tail)
}

