# Generic Optional Values 도입 배경

- 표현식은 값으로 떨어진다, 하지만 때로는 값이 null 로 나타나거나 의도하지 않은 값이 나타날수있다. 의도치않게 null 값으로 연산을 할때 발생하는 에러를 방지하기 위해 , optional value 를 사용할수있다.

# Maybe + fold

```scala
sealed trait Maybe[A] {
  def fold[B](full: A => B, empty: B): B =
    this match {
      case Full(v) => full(v)
      case Empty() => empty
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]

```

# maybe + flatmap

```scala
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
```
