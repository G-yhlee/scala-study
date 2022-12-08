# Distribution

- H,T 로 구성된 코인 디자인
- 코인을 3개 던졌을때, 각 확률을 나타내는 함수 디자인
- 예시) Distribution(List(H,H,H,0.125),List(H,H,T,0.125),....)

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
    Distribution(events map { case (a, p) => a -> (p / totalWeight) })
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

sealed trait Coin
case object Heads extends Coin
case object Tails extends Coin
case object Body extends Coin

object Main extends App {

  val fairCoin: Distribution[Coin] =
    Distribution.uniform(List(Heads, Tails))

  val threeFlips =
    for {
      c1 <- fairCoin
      c2 <- fairCoin
      c3 <- fairCoin
      c4 <- fairCoin
    } yield (c1, c2, c3)

  println(threeFlips)
}

```
