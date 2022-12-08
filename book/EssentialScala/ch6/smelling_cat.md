#### smelling cats

```scala
// We assume cooked food makes delicious smells with probability 1.0, and raw
// food makes no smell with probability 0.0.
sealed trait Food
case object Raw extends Food
case object Cooked extends Food

val food: Distribution[Food] =
  Distribution.discrete(List(Cooked -> 0.3, Raw -> 0.7))

sealed trait Cat
case object Asleep extends Cat
case object Harassing extends Cat

def cat(food: Food): Distribution[Cat] =
  food match {
    case Cooked => Distribution.discrete(List(Harassing -> 0.8, Asleep -> 0.2))
    case Raw => Distribution.discrete(List(Harassing -> 0.4, Asleep -> 0.6))
  }

val foodModel: Distribution[(Food, Cat)] =
  for {
    f <- food
    c <- cat(f)
  } yield (f, c)
```
