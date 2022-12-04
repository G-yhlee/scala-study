#### generic 이란

- 타입정의를 변수처럼 정의하여, 제너럴한 함수나 클래스를 만들도록함

#### generic algebraic data type

- In a later section we’ll introduce variance, giving us a cleaner way to implement this, but for now this is the pattern we’ll use.

```scala
sealed trait A[T]
final case class B[T]() extends A[T]
final case class C[T]() extends A[T]
```

#### Functions

- function 은 매서드를 값으로 바꿔서, 넘길수 있고 조작할수 있게한다

#### generic folds for generic data

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

#### fold pattern

- polymorphic 보다 패턴매칭에서 사용할때, fold는 이점을 갖는다.
- 대수적 데이터 타입 A에 대해, fold 는 A 를 B 로 변환 한다.
- fold 는 구조재귀이다

```scala

```

#### working with functions

```scala
((_: Int) * 2) // (a: Int) => a * 2
_ + _     // expands to `(a, b) => a + b`
foo(_)    // expands to `(a) => foo(a)`
foo(_, b) // expands to `(a) => foo(a, b)`
_(foo)    // expands to `(a) => a(foo)`
```

#### multiple parameter lists

```scala
def example(x: Int)(y: Int) = x + y
// example: (x: Int)(y: Int)Int

example(1)(2)
// res3: Int = 3
```

#### fold

```scala
def fold[B](end: B)(pair: (A, B) => B): B =
  this match {
    case End() => end
    case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
  }

fold(0){ (total, elt) => total + elt }

```

#### 멀티 파라미터에서의 타입 추론

- 스칼라의 타입 추론 알고리즘은 , 같은 리스트에서, 하나의 파라미터 타입 추론을 다른 파라미터에 사용할수 없다.
- 따라서 2번 형태와 같이 파라미터를 쪼개면 첫번째 타입추론 B 로 두번째 파라미터의 B 도 함께 추론되기 때문에 더 유용하다

```scala
// 1
def fold[B](end: B, pair: (A, B) => B): B

// 2
def fold[B](end: B)(pair: (A, B) => B): B
```

#### Modelling Data with Generic Types

- Generic Product Types
- Tuples ( products type)
- Generic Sum Types :: Either ( :: sum type )
- Generic Optional Values ( :: option)

#### 5.4.6....
