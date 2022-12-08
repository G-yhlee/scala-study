#### 예시 1

```scala

Seq(1, 2, 3).map(_ * 2)

for {
  x <- Seq(1, 2, 3)
} yield x * 2

```

#### 예시 2

```scala

val data = Seq(Seq(1), Seq(2, 3), Seq(4, 5, 6))

data.flatMap(_.map(_ * 2))

for {
  subseq  <- data
  element <- subseq
} yield element * 2
```

#### 예시 3

```scala

for {
  x <- a
  y <- b
  z <- c
} yield e

a.flatMap(x => b.flatMap(y => c.map(z => e)))

```

#### 예시 4

- yeild 를 붙이지 않으면, Unit 이 된다.

```scala
for {
  seq <- Seq(Seq(1), Seq(2, 3))
  elt <- seq
} println(elt * 2)
```

#### for comprehensions redux

#### filtering

```scala
for(x <- Seq(-2, -1, 0, 1, 2) if x > 0) yield x
// res0: Seq[Int] = List(1, 2)
```

#### parallel iteration

- 원하는 결과를 얻기 위해서는

```scala
for {
  x <- Seq(1, 2, 3)
  y <- Seq(4, 5, 6)
} yield x + y
// res1: Seq[Int] = List(5, 6, 7, 6, 7, 8, 7, 8, 9)
```

- 다음과 같이 표현할수있다.

```scala
for(x <- Seq(1, 2, 3).zip(Seq(4, 5, 6))) yield { val (a, b) = x; a + b }

for((a,b) <- Seq(1, 2, 3).zip(Seq(4, 5, 6))) yield a + b

```
