```scala
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]

sealed trait LinkedList[A] {
    def apply(index: Int): Result[A] =
    this match {
      case Pair(hd, tl) =>
        if(index == 0)
          Success(hd)
        else
          tl(index - 1)
      case End() =>
        Failure("Index out of bounds")
    }
  def length: Int =
    this match {
      case Pair(hd, tl) => 1 + tl.length
      case End() => 0
  }

  def contains(item: A): Boolean =
    this match {
      case Pair(hd, tl) =>
        if(hd == item)
          true
        else
          tl.contains(item)
      case End() => false
    }
  def map[B](fn: A => B): LinkedList[B] =
    this match {
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]()
  }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]
// LinkedList 의 end 는
// case object End extends LinkedList[A]
// 처럼 object 로 쓸수는 없는걸까?

```
