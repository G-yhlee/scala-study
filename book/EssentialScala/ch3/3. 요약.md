# 기본적 클래스 형태

```scala
class Person {
  val firstName = "Noel"
  val lastName = "Welsh"
  def name = firstName + " " + lastName
}

val newPesrson = new Person

- 오브젝트와 마찬가지로 클래스를 선언 할수있다
- 오브젝트명과 달리, 클래스명은 그 자체로 표현식이 될수 없다
- 즉, 클래스는 값이 아니다.
- 스칼라에서 모든 클래스는, java.lang.Object 의 서브 클래스다


```

```scala
// 클래스는 new 를 붙여서 표현식으로 만들수있고
// 표현식은 value 를 내놓고
// value 는 객체이다.
// Person 객체의 name 메서드를 실행하면, String 타입의 값이 되고
// String 객체는 toList 메서드를 가지므로, 메서드 체이닝에 의해
// 결과값이 생성된다
(new Person).name.toList
```

# 컨스트럭터를 활용한 클래스 형태

```scala
// 기본형
class Person(first: String, last: String) {
  val firstName = first
  val lastName = last
  def name = firstName + " " + lastName
}

// 단축형
class Person(val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}
val dave = new Person("Dave", "Gurnell")

```

# 키워드 파라미터

- 모든 메서드, 컨스트럭터에 대해 스칼라는 키워드 파라미터가 지원된다
- 키워드 파라미터를 활용해 호출하게하면, 파라미터 순서가 바뀌거나, 확장되더라도 safe 하다

```scala
new Person(lastName = "Last", firstName = "First")
def greet(firstName: String = "Some", lastName: String = "Body") =
  "Greetings, " + firstName + " " + lastName + "!"

```

# 스칼라의 타입 계층

- 자바와 다르게, 스칼라의 모든것은 객체이다.

```SCALA
Any {
    AnyVal: {
        "AnyVal 은 모든 값 타입의 서브 타입이다",
        Int, Double, Boolean, Unit....

    },
    AnyRef: {
        "AnyRef 은 모든 참조 타입 또는 클래스의 서브 타입이다",
        java.lang.String, Array[T], classes...
    }

}
```

# nothing 타입과 null 타입의 의미

- (이해가 제대로 안되었다)

```scala
def badness = throw new Exception("Error")
// badness: Nothing

def otherbadness = null
// otherbadness: Null

val bar = if(true) 123 else badness
// bar: Int = 123

val baz = if(false) "it worked" else otherbadness
// baz: String = null

```

# class 로 만들수 있는 데이터 구조

```scala
--------------------
name | color | food
--------------------
class Cat (val color, val food )
```

# counter 기본 예제

- 반복적 인스턴스 생성이유? immutable value를 사용하면서도 , 상태변화를 일으키는 목적을 달성하기 위함

```scala
// 생각할점, 왜 new Counter 를 하면서 새로운 인스턴스를 만드는가?
// val count 는 immutable 이기 때문에 인스턴스를 만들어서 상태변화를 시킨다
// 이렇게 해야, 사이드 이펙트 없이 원하는 목적(상태변화)를 이룰수있다
class Counter(val count: Int) {
  def dec = new Counter(count - 1)
  def inc = new Counter(count + 1)
}


new Counter(10).inc.dec.inc.inc.count
// 이렇게 할경우 5개의 새로운 인스턴스가 생긴다.
// 인스턴스가 매번 생성되는것은 메모리상에 불리할거처럼 보이지만, JVM 은 이러한 경우 오버헤드를 최소화시키는 방식으로 동작하기에 개발자는 걱정하지 않아도 된다.
```

# counter 기본 예제 :: case class 버젼

```scala
case class Counter(count: Int = 0) {
  def dec = copy(count = count - 1)
  def inc = copy(count = count + 1)
}

Counter(0) // construct objects without `new`
// res16: Counter = Counter(0)

Counter().inc // printout shows the value of `count`
// res17: Counter = Counter(1)

Counter().inc.dec == Counter().dec.inc // semantic equality check
// res18: Boolean = true
```

# counter : faster 예제

- 메서드 오버로딩을 할때는 , 리턴 타입을 지정해주어야한다

```scala
class Counter(val count: Int) {
  def dec: Counter = dec()
  def inc: Counter = inc()
  def dec(amount: Int = 1): Counter = new Counter(count - amount)
  def inc(amount: Int = 1): Counter = new Counter(count + amount)
}

new Counter(10).inc.inc(10).count
```

# adder 예제 ( apply 개념 활용 )

- 함수 구문은 객체에 apply 매서드를 넣어서 만들수 있다
- 함수 구문을 통해, 일급 객체 값을 만들수 있다
- 객체를 함수처럼 사용할수 있게 해 준다.
- (중요) 매서드와 다르게, 객체는 값처럼 전달될수있다.
- 객체이기 때문에, 값 대신 전달할때, 타입에 의한 컴파일 에러가 뜰수있다. (이는, 제네릭 타입으로 해결할수있다.)

```scala
class Adder(amount: Int) {
  def apply(in: Int): Int = in + amount
}
val add3 = new Adder(3)
// add3: Adder = Adder@4185f338

add3.apply(2)
// res0: Int = 5

add3(4) // shorthand for add3.apply(4)
// res1: Int = 7
```

# Companion 객체 ( 클래스 A , 객체 A 연관되었지만 서로다르다 )

- 특정 클래스에만 소속된 매서드를 만들고 싶을때, 자바에서는 static 매서드를 사용한다. 스칼라에서는, 싱글톤 객체로 이를 구현한다
- 스칼라는, 타입 이름 영역과, 값 이름 영역이 구분되어진다
- 따라서, 클래스이름과 객체이름이 동일하더라도 서로 다른 영역의 이름이기 때문에 conflict 를 일으키지 않는다.
- (중요) companion 객체는 클래스의 인스턴스가 아니라 독립적인 객체이다.
- 동일한 이름의 클래스와 객체를 같은 파일에 정의되어야 연관된다.

# 타임 스탬프

```scala
class Timestamp(val seconds: Long)

object Timestamp {
  def apply(hours: Int, minutes: Int, seconds: Int): Timestamp =
    new Timestamp(hours*60*60 + minutes*60 + seconds)
}
```

# Person

```scala
class Person(val firstName: String, val lastName: String) {
  def name: String =
    s"$firstName $lastName"
}

object Person {
  def apply(name: String): Person = {
    val parts = name.split(" ")
    new Person(parts(0), parts(1))
  }
}

Person("lee yunho").firstName
Person("lee yunho").name


```

# 타입 vs value

```scala
// TYPE
val prestige: Film = bestFilmByChristopherNolan()
new Film("Last Action Hero", 1993, mcTiernan)


// VALUE
Film("Last Action Hero", 1993, mcTiernan)
Film.apply("Last Action Hero", 1993, mcTiernan)
Film.newer(highPlainsDrifter, thomasCrownAffair)
Film.type // 여기서 Film 은 value고 Film.type 은 type이다.



```

# case classes

[ 객체끼리 비교 가능 ]

- case 클래스로 생성된 객체는, 객체가 생성될때의 컨텐츠를 타입으로 가지게 된디
- 따라서, 객체가 생성될때의 컨텐츠를 비교하여 객체의 컨텐츠가 같은지 비교가능하다

[ copy 기능 ]

- 같은 컨텐츠를 가지는 새로운 객체 생성 가능
- copy 메서드는, 현재의 객체를 반환하는게 아니라, 아예새로운 객체를 생성한다
- 파라미터에 새로운 값을 넣음으로써, 새로운 값으로 업데이트된 객체를 생성할수 있다.

[ 그외 추가기능 - 직접 사용되지는 않음 ]

- java.io.Serializable
- scala.Product

```scala
case class Person(firstName: String, lastName: String) {
  def name = firstName + " " + lastName
}

val Person1 = new Person("Dave", "Gurnell") // we have a class

Person // ::[Person.type , Person]
Person1 // :: [Person, Person(Dave,Gurnell)]

```

# case objects

- 컨스트럭터가 없는 클래스라면, case object 로 만들어도 무방하다

```scala
case object Citizen {
  def firstName = "John"
  def lastName  = "Doe"
  def name = firstName + " " + lastName
}

Citizen.toString
// res12: String = Citizen


```

# 주의할점..

- case class 와 companion object 를 같이 정의하면 어찌될까?
- case class 를 통해 이미 apply 매서드가 정의되었는데, companion object 를 한번더 만들면, apply 매서드가 중복되어 매서드 오버라이드가 일어난다. 따라서 case class 의 오동작을 막기위해 한 파일에 case class와 companion object 를 정의해두는게 좋다.

# 패턴 매칭

```scala
object ChipShop {
  def willServe(cat: Cat): Boolean =
    cat match {
      case Cat(_, _, "Chips") => true
      case Cat(_, _, _) => false
    }
}
```
