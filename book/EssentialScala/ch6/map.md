#### map

```scala
val example = Map("a" -> 1, "b" -> 2, "c" -> 3)

example("a")
example.get("a")
example.getOrElse("d", -1)
example.+("c" -> 100, "d" -> "11")

```

#### map and flatMap

```scala
example.map(pair => pair._1 -> pair._2 * 2)

```

- Map[String,Int] => Iterable[String]

```scala
 Map("a" -> 1, "b" -> 2, "c" -> 3).map(p=>p._1 + " = " + p._2)
val res2: scala.collection.immutable.Iterable[String] = List(a = 1, b = 2, c = 3)

```

```scala
example.flatMap {
         case (str, num) =>
           (1 to 3).map(x => (str + x) -> (num * x))
       }
// 동치

for{
    (str, num) <- example
    x         <- 1 to 3
} yield (str + x) -> (num * x)

```
