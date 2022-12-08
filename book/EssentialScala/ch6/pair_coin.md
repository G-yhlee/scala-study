# Distribution

```scala
final case class Distribution[A](events: List[(A, Double)]) {
  def map[B](f: A => B): Distribution[B] =
    Distribution(events map { case (a, p) => f(a) -> p })

  def flatMap[B](f: A => Distribution[B]): Distribution[B] =
    Distribution(events flatMap { case (a, p1) =>
                   f(a).events map { case (b, p2) => b -> (p1 * p2) }
                 }).compact.normalize

  def normalize: Distribution[A] = {
    val totalWeight = (events map { case (a, p) => p }).sum
    Distribution(events map { case (a,p) => a -> (p / totalWeight) })
  }

  def compact: Distribution[A] = {
    val distinct = (events map { case (a, p) => a }).distinct
    def prob(a: A): Double =
      (events filter { case (x, p) => x == a } map { case (a, p) => p }).sum

    Distribution(distinct map { a => a -> prob(a) })
  }
}

object Distribution {
  def uniform[A](atoms: List[A]): Distribution[A] = {
    val p = 1.0 / atoms.length
    Distribution(atoms.map(a => a -> p))
  }
}
```

# coin

```scala
sealed trait Coin
case object Heads extends Coin
case object Tails extends Coin

val fairCoin: Distribution[Coin] = Distribution.uniform(List(Heads, Tails))

val threeFlips =
  for {
    c1 <- fairCoin
    c2 <- fairCoin
    c3 <- fairCoin
  } yield (c1, c2, c3)
// threeFlips: Distribution[(Coin, Coin, Coin)] = Distribution(List(((Heads,Heads,Heads),0.125), ((Heads,Heads,Tails),0.125), ((Heads,Tails,Heads),0.125), ((Heads,Tails,Tails),0.125), ((Tails,Heads,Heads),0.125), ((Tails,Heads,Tails),0.125), ((Tails,Tails,Heads),0.125), ((Tails,Tails,Tails),0.125)))
```
