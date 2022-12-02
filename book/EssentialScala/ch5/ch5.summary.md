#### generic 이란

- 타입정의를 변수처럼 정의하여, 제너럴한 함수나 클래스를 만들도록함

#### generic algebraic data type

```scala
sealed trait A[T]
final case class B[T]() extends A[T]
final case class C[T]() extends A[T]
```

#### Functions

- function 은 매서드,

#### working with functions

```scala
_ + _     // expands to `(a, b) => a + b`
foo(_)    // expands to `(a) => foo(a)`
foo(_, b) // expands to `(a) => foo(a, b)`
_(foo)    // expands to `(a) => a(foo)`
```
