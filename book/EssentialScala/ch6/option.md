# option

- 옵션은 some, none 을 하위타입으로 갖는 트레이트이다

```scala
sealed trait Option[+A] {
  def getOrElse[B >: A](default: B): B

  def isEmpty: Boolean
  def isDefined: Boolean = !isEmpty

  def filter(func: A => Boolean): Option[A]
  def find(func: A => Boolean): Option[A]

  def map[B](func: A => B): Option[B]
  def flatMap[B](func: A => Option[B]): Option[B]
  def foreach(func: A => Unit): Unit

  def foldLeft[B](initial: B)(func: (B, A) => B): B
  def foldRight[B](initial: B)(func: (A, B) => B): B

  // divide 할때 , 에러를 피하는 방법
  def divide(numerator: Int, denominator: Int) =
  if(denominator == 0) None else Some(numerator / denominator)

  // other methods...
}

final case class Some[A](x: A) extends Option[A] {
  def getOrElse[B >: A](default: B) = x

  def isEmpty: Boolean = false

  def map()

  // other methods...
}

case object None extends Option[Nothing] {
  def getOrElse[B >: Nothing](default: B) = default

  def isEmpty: Boolean = true

  // other methods...
}
```

# method

```scala
def readInt(str: String): Option[Int] =
  if(str matches "-?\\d+") Some(str.toInt) else None

readInt("123")
// res2: Option[Int] = Some(123)

readInt("abc")
// res3: Option[Int] = None


def sum(optionA: Option[Int], optionB: Option[Int]): Option[Int] =
  optionA.flatMap(a => optionB.map(b => a + b))

sum(readInt("1"), readInt("2"))
// res2: Option[Int] = Some(3)

sum(readInt("1"), readInt("b"))
// res3: Option[Int] = None

sum(readInt("a"), readInt("2"))
// res4: Option[Int] = None

```

# option as flow control

- option은 map과 flatmap 을 지원하기때문에 ( 모나드 이기 때문에 )
- sum 같은걸 따로 만들지 않더라도 다음과 같은 방식으로 구현 가능하다

```scala
val optionA = readInt("123")
val optionB = readInt("234")

for {
  a <- optionA
  b <- optionB
} yield a + b


// 이렇게 동작 시킬수 있다
// optionA.flatMap(a => optionB.map(b => a + b))

```

# 예제 :: add things

```scala
// 2개 받는 경우
def addOptions(opt1: Option[Int], opt2: Option[Int]) =
  for {
    a <- opt1
    b <- opt2
  } yield a + b

def addOptions2(opt1: Option[Int], opt2: Option[Int]) =
  opt1 flatMap { a =>
    opt2 map { b =>
      a + b
    }
  }


// 3개 받는경우
  def addOptions(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]) =
  for {
    a <- opt1
    b <- opt2
    c <- opt3
  } yield a + b + c

  def addOptions2(opt1: Option[Int], opt2: Option[Int], opt3: Option[Int]) =
  opt1 flatMap { a =>
    opt2 flatMap { b =>
      opt3 map { c =>
        a + b + c
      }
    }
  }
```

#### divide-options

```scala
def divideOptions(numerator: Option[Int], denominator: Option[Int]) =
  for {
    a <- numerator
    b <- denominator
    c <- divide(a, b)
  } yield c
```

#### calculator

```scala
def calculator(operand1: String, operator: String, operand2: String): Unit = {
  def calcInternal(a: Int, b: Int) =
    operator match {
      case "+" => Some(a + b)
      case "-" => Some(a - b)
      case "*" => Some(a * b)
      case "/" => divide(a, b)
      case _   => None
    }

  val result =
    readInt(operand1) flatMap { a =>
      readInt(operand2) flatMap { b =>
        calcInternal(a, b) map { result =>
          result
        }
      }
    }

  val result = for {
    a   <- readInt(operand1)
    b   <- readInt(operand2)
    ans <- calcInternal(a, b)
  } yield ans

  result match {
    case Some(number) => println(s"The answer is $number!")
    case None         => println(s"Error calculating $operand1 $operator $operand2")
  }
}
```
