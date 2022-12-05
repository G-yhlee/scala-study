# Intlist

- 다음의 코드는 , int 만 받을수있고, 코드에 중복이 있기 때문에 개선이 필요하다
- generic 으로 개선할수있다

```scala
sealed trait IntList {
    def sum: Int = this match {
        case End => 0
        case Pair(head, tail) => head + tail.length
    }
    def product: Int = this match {
        case End => 1
        case Pair(head, tail) => head * tail.product
    }
    def length: Int = this match {
        case End => 0
        case Pair(head,tail) => 1 + tail.length
    }
    def double: IntList = this match {
        case End => End
        case Pair(head, tail) => Pair(head*2, tail.double )
    }
}

case class Pair(head:Int,tail:IntList) extends IntList
case object End extends IntList


object Main extends App {
  val list = Pair(1,Pair(2,Pair(3,Pair(4,End))))

  println("list.sum",list.sum)
  println("list.product",list.product)
  println("list.length",list.length)
  println("list.length",list.double)

}

```
