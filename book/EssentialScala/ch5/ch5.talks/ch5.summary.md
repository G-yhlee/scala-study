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

- Generic products type :: Tuples
- Generic Sum Types :: Either
- Generic Optional Values :: option

#### 5.4.6....
