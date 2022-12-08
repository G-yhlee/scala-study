#### union

```scala
def union[A, B](map1: Map[A, B], map2: Map[A, B], add: (B, B) => B): Map[A, B] = {
  map1.foldLeft(map2){ (map, elt) =>
    val (k, v) = elt
    val newV = map.get(k).map(v2 => add(v, v2)).getOrElse(v)
    map + (k -> newV)
  }
}
```
