```scala
object Main extends App {
  val list: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
  val sequence = Seq(1, 2, "3a")

  println("a box ",Seq(4, 5, 6) :: List(1,2,3))
  println("a box ", 1 :: 2 :: List(1,2,3))
}
```
