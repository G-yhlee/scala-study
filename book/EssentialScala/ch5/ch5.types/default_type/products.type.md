# Product Type :: tuple

- A and B ... 인 상황 처리하기 위한 타입

# custom product type

```scala
case class Pair[A, B](one: A, two: B)

```

# tuple type

```scala
Tuple2("hi", 1) // unsugared syntax
("hi", 1)
```

# 튜플 예시

```scala
def tuplized[A, B](in: (A, B)) = in._1

tuplized(("a", 1))

```

# 튜플 패턴 매칭

```scala
(1, "a") match {
  case (a, b) => a + b
}
```
