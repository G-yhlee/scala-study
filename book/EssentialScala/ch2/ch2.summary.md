# 표현식 - 타입,벨류

- expression :: [type,value==object{data,method}]
- code :: [컴파일타임, 런타임]

```scala
// 표현식 :: [컴파일타임,런타임]
// expression :: [type,value==object{data,method}]

// 리터럴 표현식, 리터럴 오브젝트
// (메서드를 따로 적용하지 않고) 표현식만으로 값을 낼때 리터럴 표현식이라 한다

() :: [Unit, ]
"hello ..." :: [String,hello ...]
'a' :: [Char,a]
42 :: [Int,42]
42.0 :: [Double,42.0]
42.0f :: [Float,42.0]
42L :: [Long,42]
true :: [Boolean,true]
false :: [Boolean,true]
null :: [Null,null]
Person :: [Person.type,Person]

```

# 표현식 응용

```scala
// expression :: [type,value==object{data,method}]

"Hello ...".toUpperCase.take(3+1)  split " " ::  [Array[String],Array(HELL, ...)]

43.-(1) :: [Int,42]

// conditional expression
if(1 < 2) "Yes" else "No"


// 블록 expresion
def name: String = {
  val title = "Professor"
  val name = "Funkenstein"
  title + " " + name
}


```

# 기타 표현식

```scala
// expression :: [type,value==object{data,method}]

Double ::   [Double.type,object scala.Double]
throw :: [Nothing, ??? ]
null :: [Null,null]

// 기타 표현식 응용
new Person :: [Person , Person@12345678]
(new Person).name.toList :: [List(Char), _생략_]
throw new Exception("Error") :: [Nothing,   ]

```

# 오브젝트 리터럴

- 선언문은 값과 이름을 연결한다.
- 선언문으로 지정된 이름은 표현식으로 사용할수 있고
- 이 표현식은 마찬가지로 타입과 값을 가진다.
- 오브젝트의 타입은, 새로 생성되며 싱글톤 타입이라고 한다

```scala
// 선언문

object Test {
    // 메서드 선언문
    def name(parameter: type, ...) : type = "expression"
    def name: type = "expression"
    // 필드 선언문
    // - 오브젝트 안에, "오브젝트"를 담는것과 같다.
    val name: type = "expression"
    var name: type = "expression"
    //
}

// 표현식 :: [타입 == *싱글톤 타입, 값]
Test :: [Test.type,Test$@12345678]


```

# 메서드와 필드 차이

```scala
object Test {
   val simpleField = {
    // 블록으로 감싸진 표현식을, 블록 표현식이라 한다.
     println("sayHello")
     42
   }
   def simpleMethod = {
     println("Evaluating noParameterMethod")
     42
   }
}

// Test 선언  || =>  Test 실행 => Test 실행 => ...
// Test 실행 반복할때,
// simpleField 은 처음에만 print 를 찍고, 그다음엔 계산을 하지 않는다.
// 반면, simpleMethod 은 .



```

# lazy loading

- 객체를 선언할때는, 객체가 실행되지 않는다
- 객체는 표현식으로 사용될때 실행되는데, 이를 lazy loading 이라 한다
- 필드의 표현식은 한번 실행된이후, 값만 리턴하고 다시는 반복 실행되지 않는다.
- 반면, 메서드의 표현식은 매번 실행한다

# 메서드 오버로드

```scala
object calc {
  def square(value: Double) = value * value
  def cube(value: Double) = value * square(value)

//   def square(value: Int) = value * value
//   def cube(value: Int) = value * square(value)
}
```

# 메서드 Conversions

-

```scala
// + 자동 형변환
println("a" + 1)
println("a" + 1.toString)

// 자동 형변환
calc.square(2) // 인트형을 받았지만, 컨버젼을 통해 더블형을 리턴한다
x: Int = calc.square(2).toInt // 인트형을 받았디만, 더블형을 리턴하기때문에 명시적 형변환을 해야한다
```

# 평가 순서

```scala
object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}

// argh
// => 출력: b a 결과: argh$@12345678

// argh.a
// => 출력: a 결과: 1

// argh.b
// => 출력:  결과: 3

// argh.a +


```
