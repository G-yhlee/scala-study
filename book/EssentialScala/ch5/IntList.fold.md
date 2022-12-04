# 1단계

```scala
sealed trait IntList {
  def length: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => 1 + tl.length
    }
  def double: IntList =
    this match {
      case End => End
      case Pair(hd, tl) => Pair(hd * 2, tl.double)
    }
  def product: Int =
    this match {
      case End => 1
      case Pair(hd, tl) => hd * tl.product
    }
  def sum: Int =
    this match {
      case End => 0
      case Pair(hd, tl) => hd + tl.sum
    }
}


```

# 2 단계

```scala
sealed trait IntList {
  def fold(end: Int, f: (Int, Int) => Int): Int =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
  def length: Int =
    fold(0, (_, tl) => 1 + tl)
  def product: Int =
    fold(1, (hd, tl) => hd * tl)
  def sum: Int =
    fold(0, (hd, tl) => hd + tl)
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList
```

# 3 단계

```scala
sealed trait IntList {
  def fold[A](end: A, f: (Int, A) => A): A =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
  def length: Int =
    fold[Int](0, (_, tl) => 1 + tl)
  def product: Int =
    fold[Int](1, (hd, tl) => hd * tl)
  def sum: Int =
    fold[Int](0, (hd, tl) => hd + tl)
  def double: IntList =
    fold[IntList](End, (hd, tl) => Pair(hd * 2, tl))
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList
```

# 4 단계

```scala
sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]
```
