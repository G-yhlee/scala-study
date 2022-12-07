# custom_sequences

#### unique

```scala
object custom {

  def map[A, B](seq: Seq[A], f: A => B): Seq[B] = {
    seq.foldRight(Seq.empty[B]) { (elt, seq) => f(elt) +: seq }
  }

  def foldLeft[A, B](seq: Seq[A], zero: B, f: (B, A) => B): B = {
    var result = zero
    seq.foreach { elt => result = f(result, elt) }
    result
  }

  def reverse[A](seq: Seq[A]): Seq[A] = {
    seq.foldLeft(Seq.empty[A]) { (seq, elt) => elt +: seq }
  }

  def insert(seq: Seq[Int], elt: Int): Seq[Int] = {
    if (seq.contains(elt))
      seq
    else
      elt +: seq
  }

  def unique(seq: Seq[Int]): Seq[Int] = {
    seq.foldLeft(Seq.empty[Int]) { insert _ }
  }

}

object Main extends App {
  val list = 1 +: Seq(2, 3, 4, 4)
  println(custom.map[Int, Int](list, (a) => a * a))
  println(custom.foldLeft[Int, Int](list, 0, (a, b) => a + b))
  println(custom.unique(list))

}

```
