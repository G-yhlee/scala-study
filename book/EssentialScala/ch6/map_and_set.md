#### map and set

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
 Map("a" -> 1, "b" -> 2, "c" -> 3).map(p=>p._1 + " = " + p._2.toString)
val res2: scala.collection.immutable.Iterable[String] = List(a = 1, b = 2, c = 3)

```
