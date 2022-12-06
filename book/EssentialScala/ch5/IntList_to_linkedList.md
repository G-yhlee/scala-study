# 1단계 :: 기본형

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

# 2 단계 :: fold 로 축약형

```scala
sealed trait IntList {
  def fold(end: Int, f: (Int, Int) => Int): Int =
    this match {
      case End => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
  def length: Int =
    }
    fold(0, (_, tl) => 1 + tl)
  def product: Int =
    fold(1, (hd, tl) => hd * tl)
  def sum: Int =
    fold(0, (hd, tl) => hd + tl)
}
case object End extends IntList
final case class Pair(head: Int, tail: IntList) extends IntList
```

# 3 단계 :: fold[A] 로 축약형

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

# 4 단계 :: LinkedList[A],fold[B]

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

# Map , flatMap

- F[A] => F[B] by A => B , 이 함수를 map 이라 한다
- F[A] => F[B] by A => F[B] , 이 함수를 flatMap 이라 한다

# 4 단계 :: map

- List[Int] => List[User] by Int => User

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

# 4 단계 :: FlatMap

- LinkedList[User] => LinkedList[Order] by User => LinkedList[Order]
- Maybe[User] => Maybe[Order] by User => Maybe[Order]
- Sum[String, Order] => Sum[String, Id] by Order => Sum[String, Id]

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

# Functors and Monads

- functor : F[A] 이고 map 연산이 가능하면, 펑터이다
- monad : F[A] 이고 map 연산이 가능하고, flatMap 연산이 가능하면, 모나드이다

# 모나드 아이디어

- 모나드는 값을 context에 위치한 값을 연산할수 있는 데이터 구조이다.
- context는 모나드에 의존한다.
- context[value] => context[value`]

# Maybe 모나드 1단계

```scala
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
  def map[B](fn: A => B): Maybe[B] =
    this match {
      case Full(v) => Full(fn(v))
      case Empty() => Empty[B]()
    }
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
```

# Maybe 모나드 2단계

```scala
sealed trait Maybe[A] {
  def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
    this match {
      case Full(v) => fn(v)
      case Empty() => Empty[B]()
    }
  def map[B](fn: A => B): Maybe[B] =
    flatMap[B](v => Full(fn(v)))
}
final case class Full[A](value: A) extends Maybe[A]
final case class Empty[A]() extends Maybe[A]
```

# 예제

```scala

  val input = List(1, 2, 3)

  val ouput_확장_flatmap =
    input.flatMap(x => List(x, -x)) // List(1, -1, 2, -2, 3, -3)
  val ouput_자기자신_map = input.map(x => x) // List(1, 2, 3)
  val ouput_자기자신_flatmap = input.flatMap { x => List(x) } // List(1, 2, 3)

  val ouput_짝수만반환 = input
    .map(v => Full(v))
    .map(maybe =>
      maybe.flatMap[Int] { x => if (x % 2 == 0) Full(x) else Empty() }
    )



```
