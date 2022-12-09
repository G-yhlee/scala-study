# Sum type 필요한 배경 :: either

- A or B ... 인 상황 처리하기 위한 타입

# custom sum type

```scala
sealed trait Sum[A, B]{
    def fold[C](left: A => C, right: B => C): C =
        this match {
            case Left(a) => left(a)
            case Right(b) => right(b)
    }
  def map[C](f: B => C): Sum[A, C] =
    this match {
      case Failure(v) => Failure(v)
      case Success(v) => Success(f(v))
    }
  def flatMap[C](f: B => Sum[A, C]) =
    this match {
      case Failure(v) => Failure(v)
      case Success(v) => f(v)
    }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]
```

# custom sum type 예시

```scala
sealed trait Sum[A, B] {
  def fold[C](left: A => C, right: B => C): C =
    this match {
      case Left(a)  => left(a)
      case Right(b) => right(b)
    }
}
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]

object Main extends App {
  val test = Left[Int, Int](2)
  println(test.fold[Int](a => a * a, a => a)) // 의미가 별로 없는 예시..
}

```
