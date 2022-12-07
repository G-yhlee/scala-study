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
