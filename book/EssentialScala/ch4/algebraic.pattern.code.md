# TrafficLight

```scala
sealed trait TrafficLight
case object Red extends TrafficLight
case object Green extends TrafficLight
case object Yellow extends TrafficLight
```

# Calculation

```scala
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Failure(reason: String) extends Calculation
```
