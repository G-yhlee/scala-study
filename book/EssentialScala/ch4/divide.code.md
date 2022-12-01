```scala
sealed trait DivisionResult
final case class Finite(value: Int) extends DivisionResult
case object Infinite extends DivisionResult

object divide {
  def apply(num: Int, den: Int): DivisionResult =
    if(den == 0) Infinite else Finite(num / den)
}

divide(1, 0) match {
  case Finite(value) => s"It's finite: ${value}"
  case Infinite      => s"It's infinite"
}
// res34: String = It's infinite
```
