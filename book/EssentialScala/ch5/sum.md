# sum type 필요한 배경

- int 또는 string 타입을 받는 타입을 만들고자 할때가 있는데 이때, sum type을 만들어서 사용할수있다
- sum type 을 스칼라 라이브러리로 구현한것을 either 라고 한다

```scala
def intOrString(input: Boolean): Sum[Int, String] =
  if(input == true) {
    Left[Int, String](123)
  } else {
    Right[Int, String]("abc")
  }
// intOrString: (input: Boolean)sum.Sum[Int,String]
```

# sum type 만들기

```scala
sealed trait Sum[A, B]
final case class Left[A, B](value: A) extends Sum[A, B]
final case class Right[A, B](value: B) extends Sum[A, B]
```

# sum type 활용 예시

```scala
Left[Int, String](1).value
// res9: Int = 1

Right[Int, String]("foo").value
// res10: String = foo

val sum: Sum[Int, String] = Right("foo")
// sum: sum.Sum[Int,String] = Right(foo)

sum match {
  case Left(x) => x.toString
  case Right(x) => x
}
```

# folding sum

```scala
sealed trait Sum[A, B] {
  def fold[C](error: A => C, success: B => C): C =
    this match {
      case Failure(v) => error(v)
      case Success(v) => success(v)
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
final case class Failure[A, B](value: A) extends Sum[A, B]
final case class Success[A, B](value: B) extends Sum[A, B]
```
