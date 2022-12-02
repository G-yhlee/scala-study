# Intlist

- 다음의 코드는 , int 만 받을수있고, 코드에 중복이 있기 때문에 개선이 필요하다
- generic 으로 개선할수있다

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
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList
```
