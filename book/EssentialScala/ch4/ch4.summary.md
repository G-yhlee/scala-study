# 트레이트

- 트레이트는 클래스와 마찬가지로 메서드와 필드를 갖는다
- 트레이트는 컨스트럭터를 가질수 없다. 즉, 오브젝트를 트레이트로 생성할수 없다
- 트레이트는 추상매서드(타입과 이름만 가진)를 정의할수있다. 추상메서드는 클래스에서 구현한다.
- 트레이트에서는 def로 정의하고 클래스에서는 val 또는 def 로 정의할수있다. def는 val보다 더 제너럴한 구현이다

# sealed trait , final case class

- sealed 를 붙여주면, trait 의 모든 형태를 한파일에 지정해야한다.
- 즉, 컴파일러가 전체 집합에서 모든 하위타입을 알게된고, 이를통해 패턴매칭할때 컴파일러가 다른 하위 타입을 갖거나 하위 타입이 빠진경우 컴파일에러를 찾아줄수 있게된다.
- sealed trait 를 걸더라도, trait 를 가진 하위클래스는 다른 파일에서 확장이 될수있다. 이를 막고자 할 경우, final 키워드를 붙여준다. final 키워드를 붙임으로써, 하위클래스의 확장을 제한한다.
- sealed trait 와 하위타입인 class 는 같은 파일에 정의되어야한다

# 언제 sealed 를 쓰는가?

- If all the subtypes of a trait are known, seal the trait

# 언제 final 을 쓰는가?

- Consider making subtypes final if there is no case for extending them

# sealed , final 패턴의 장점

- the compiler will warn if we miss a case in pattern matching;
- we can control extension points of sealed traits and thus make stronger guarantees about the behaviour of subtypes.

# Visitor 트레이트

```scala
import java.util.Date

sealed trait Visitor {
  def id: String
  def createdAt: Date
  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class Anonymous(
  id: String,
  createdAt: Date = new Date()
) extends Visitor

final case class User(
  id: String,
  email: String,
  createdAt: Date = new Date()
) extends Visitor
```

# shape 트레이트

```scala
trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * math.Pi * radius
  val area = math.Pi * radius * radius
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  override val perimeter = 2*width + 2*height
  override val area = width*height
}

case class Square(size: Double) extends Rectangular {
  val width = size
  val height = size
}

case class Rectangle(
  val width: Double,
  val height: Double
) extends Rectangular
```

# shape 트레이트를 받는 함수 예시

```scala
object Draw {
  def apply(shape: Shape): String = shape match {
    case Rectangle(width, height) =>
      s"A rectangle of width ${width}cm and height ${height}cm"

    case Square(size) =>
      s"A square of size ${size}cm"

    case Circle(radius) =>
      s"A circle of radius ${radius}cm"
  }
}

Draw(Circle(10))
// res1: String = A circle of radius 10.0cm

Draw(Rectangle(3, 4))
// res2: String = A rectangle of width 3.0cm and height 4.0cm
```
