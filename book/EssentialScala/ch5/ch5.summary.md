# 5.1 generics

#### generic 이란

- 타입정의를 변수처럼 정의하여, 제너럴한 함수나 클래스를 만들도록함

#### generic syntax

```scala
case class Name[A](...){ ... }
trait Name[A]{ ... }
def name[A](...){ ... }

```

#### generic algebraic data type

- In a later section we’ll introduce variance, giving us a cleaner way to implement this, but for now this is the pattern we’ll use.

```scala
// Invariant Generic Sum Type Pattern

sealed trait A[T]
final case class B[T]() extends A[T]
final case class C[T]() extends A[T]
```

#### Functions

- function 은 매서드를 값으로 바꿔서, 넘길수 있고 조작할수 있게한다
- Function Type Declaration Syntax
- Function literals

#### generic folds for generic data

#### 폴드에 대한 개념

```
Fold is just an adaptation of structural recursion where we allow the user to pass in the functions we apply at each case. As structural recursion is the generic pattern for writing any function that transforms an algebraic datatype, fold is the concrete realisation of this generic pattern. That is, fold is the generic transformation or iteration method. Any function you care to write on an algebraic datatype can be written in terms of fold.

- 폴드는 재귀데이터를 함수를 통해 연산할수 있는 방법을 제공한다.
- 대수 데이터 타입을 다루는 어떤 함수라도, 폴드를 통해 작성할수있다
```

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

#### 5.4 Modelling Data with Generic Types

- Generic Product Types
- Tuples ( products type)
- Generic Sum Types :: Either ( :: sum type )
- Generic Optional Values ( :: option)

#### 5.4.6....
