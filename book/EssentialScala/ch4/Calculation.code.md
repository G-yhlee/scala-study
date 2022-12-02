# Calculation

```scala
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation


object Calculator {
  def +(calc: Calculation, operand: Int): Calculation =
    calc match {
        case Success(result) => Success(result + operand)
        case Failure(reason) => Failure(reason)
    }
  def -(calc: Calculation, operand: Int): Calculation =
    calc match {
      case Success(result) => Success(result - operand)
      case Failure(reason) => Failure(reason)
    }
  def /(calc: Calculation, operand: Int): Calculation =
    calc match {
    case Success(result) =>
      operand match {
        case 0 => Failure("Division by zero")
        case _ => Success(result / operand)
      }
    case Failure(reason) => Failure(reason)
  }
}
```

# Result

- calculation 을 일반화한 버젼

```scala
sealed trait Result[A]
case class Success[A](result: A) extends Result[A]
case class Failure[A](reason: String) extends Result[A]
```
