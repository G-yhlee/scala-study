#### for 와 flatMap,map 관계

```scala
for {
  x <- a
  y <- b
  z <- c
} yield e

// 동치

a.flatMap(x => b.flatMap(y => c.map(z => e)))
a.flatMap(x => b.flatMap(y => c.foreach(z => e)))




```

#### 예제 1

```scala
  val test = for {
    x <- Seq(1, 2, 3)
    y <- Seq(1, 2, 3)
    z <- Seq(1, 2, 3)
    w <- Seq(1, 2, 3)
  } yield (x, y, z, w)
```

```scala

Seq(1, 2, 3).flatMap(x =>
      Seq(1, 2, 3).flatMap(y =>
        Seq(1, 2, 3).flatMap(z => Seq(1, 2, 3).map(w => (x, y, z,w)))
      )
)
```

#### 예제2

```scala
 val list = Seq(1, 2, 3)

  val new_list =
    list // List(1, 2, 3)
      .map(Seq(_)) // STEP1 :: List(List(1), List(2), List(3))
      .flatMap(_.map(_ * 2)) // STEP2 :: List(2, 4, 6)


```
