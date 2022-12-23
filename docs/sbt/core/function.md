#### / 함수

```scala
case class MyFilePath(pathFragments: List[String]) {
    def / (other: String) = MyFilePath(pathFragments :+ other)
}

object program {
  def main(args: Array[String]): Unit = {
    import MyFilePath._
    println( MyFilePath(Nil) / "hello" / "world") // MyFilePath(List(hello, world))
  }
}

```
