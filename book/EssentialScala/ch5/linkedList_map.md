```scala
sealed trait LinkedList[A] {
  def map[B](fn: A => B): LinkedList[B] =
    this match {
      case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
      case End() => End[B]()
    }
}
case class Pair[A](hd: A, tl: LinkedList[A]) extends LinkedList[A]
case class End[A]() extends LinkedList[A]
```
