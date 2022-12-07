# sequence basic method

```scala

// map
println(Seq("a", "wet", "dog").map(_.permutations.toList ) )

// flatmap
println(Seq("a", "wet", "dog").flatMap(_.permutations.toList))
println(Vector(1, 2, 3).flatMap(num => Seq(num, num * 10)))

// foldLeft
Seq(1, 2, 3).foldLeft(0)(_ + _) // (((0 + 1) + 2) + 3)

// foldRight
Seq(1, 2, 3).foldRight(0)(_ + _) // (1 + (2 + (3 + 0)))

// foreach
List(1, 2, 3).foreach(num => println("And a " + num + "..."))

```
