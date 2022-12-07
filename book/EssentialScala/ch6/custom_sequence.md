# custom_sequences

#### unique

```scala

def insert(seq: Seq[Int], elt: Int): Seq[Int] = {
  if(seq.contains(elt))
    seq
  else
    elt +: seq
}

def unique(seq: Seq[Int]): Seq[Int] = {
  seq.foldLeft(Seq.empty[Int]){ insert _ }
}

unique(Seq(1, 1, 2, 4, 3, 4))
```

#### reverse

```scala
def reverse[A](seq: Seq[A]): Seq[A] = {
  seq.foldLeft(Seq.empty[A]){ (seq, elt) => elt +: seq }
}
```

#### map

```scala
object custom {
  def map[A, B](seq: Seq[A], f: A => B): Seq[B] = {
    seq.foldRight(Seq.empty[B]) { (elt, seq) => f(elt) +: seq }
  }
}

object Main extends App {
  val result = custom.map[Int, Int](Seq(1, 2, 3), (a) => a * a)
  println("seq", result)
}
```
