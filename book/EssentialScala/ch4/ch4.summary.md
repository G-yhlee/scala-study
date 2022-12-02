#### 트레이트

```
- 트레이트는 클래스와 마찬가지로 메서드와 필드를 갖는다
- 트레이트는 컨스트럭터를 가질수 없다. 즉, 오브젝트를 트레이트로 생성할수 없다
- 트레이트는 추상매서드(타입과 이름만 가진)를 정의할수있다. 추상메서드는 클래스에서 구현한다.
- 트레이트에서는 def로 정의하고 클래스에서는 val 또는 def 로 정의할수있다. def는 val보다 더 제너럴한 구현이다
```

#### sealed trait , final case class

```
- sealed 를 붙여주면, trait 의 모든 형태를 한파일에 지정해야한다.
- 즉, 컴파일러가 전체 집합에서 모든 하위타입을 알게된고, 이를통해 패턴매칭할때 컴파일러가 다른 하위 타입을 갖거나 하위 타입이 빠진경우 컴파일에러를 찾아줄수 있게된다.
- sealed trait 를 걸더라도, trait 를 가진 하위클래스는 다른 파일에서 확장이 될수있다. 이를 막고자 할 경우, final 키워드를 붙여준다. final 키워드를 붙임으로써, 하위클래스의 확장을 제한한다.
- sealed trait 와 하위타입인 class 는 같은 파일에 정의되어야한다
```

#### 언제 sealed 를 쓰는가?

```
- If all the subtypes of a trait are known, seal the trait
```

#### 언제 final 을 쓰는가?

```
- Consider making subtypes final if there is no case for extending them
```

#### sealed , final 패턴의 장점

```
- the compiler will warn if we miss a case in pattern matching;
- we can control extension points of sealed traits and thus make stronger guarantees about the behaviour of subtypes.
```

## 대수적 데이터 타입

#### 대수적 타입

```
- 다른 자료형의 값을 가지는 자료형
```

#### 곱타입

```
- AND , 타입이 늘어날때 값이 곱사건으로 계산되어짐
```

#### 합타입

```
- Or , 타입이 늘어날때 값이 합사건으로 계산되어짐
- 합타입은 생성시점에 타입이 지정되어있기 때문에, 패턴매칭가능
```

#### is AND

```scala
// A is a B and C
trait B
trait C
trait A extends B with C
```

#### is OR == SUM TYPE

```scala
// sum type
// If A is a B or C
sealed trait A
final case class B() extends A
final case class C() extends A
```

#### has AND == product TYPE

```scala
// product type
// A has a B and C
case class A(b: B, c: C)
```

#### has OR

```scala
// A has a B or C
// == A is D has b or A is E has c
sealed trait A
final case class D(b: B) extends A
final case class E(c: C) extends A

// == A is D has b or A is E has c
trait A {
  def d: D
}
sealed trait D
final case class B() extends D
final case class C() extends D
```

# structural recursion ( == decomposing of algebraic data type)

- 구조적 재귀 ( 패턴매칭, 다형성 )패턴
- 구조적 재귀 패턴은 대수 데이터 타입을 만드는것의 정 반대의 절차이다
- 구조적 재귀는 데이터를 작게 쪼개는 절차이다

# structural recursion using Polymorphism

- product type polymorphism
- sum type polymorphism

# structural recursion using Pattern Matching

- product type Pattern Matching
- sum type Pattern Matching

# structural recursion 에서 우리가 선택할수 있는 3가지 패턴

1. 다형성 패턴
2. 패턴매칭 패턴1: 트레이트에서 패턴정의 하는 방법
3. 패턴매칭 패턴2: 오브젝트에서 패턴정의 하는 방법

- 1,2 중에서는 주로 패턴매칭 방식이 좀 더 적은 코드로 같은 결과를 낼수 있다.
- 만약 우리가 하나이상의 implementation 을 가질경우, 3번 방식을 채택할수 있다

# oop vs fp

- 전통적인 oop 스타일에서는 다형성을 활용하고, 클래스를 확장하는 것을 허용한다 (스칼라에서는 no sealed traits)
- oop 에서 클래스의 확장을 통한 많은 수의 매서드를 포함하는 클래스는 유지보수가 어렵다
- oop 와 fp 에는 근본적인 차이가 있다.
- oop 스타일에서는 쉽게 데이터를 추가할수 있다. ( extending trait 를 통해 )
- fp 스타일 에서는 메서드를 쉽게 추가할수 있다.
- 스칼라에서는 sealed traits 를 통해 패턴매칭이 지원되기 때문에, sealed 방식을 더 선호 한다

# 4.6.1 Understanding the Base Case and Recursive Case

```scala
sealed trait RecursiveExample
final case class RecursiveCase(recursion: RecursiveExample) extends RecursiveExample
case object BaseCase extends RecursiveExample


// 개념
Recursive Structural Recursion Pattern
When writing structurally recursive code on a recursive algebraic data type:

- whenever we encounter a recursive element in the data we make a recursive call to our method; and
- whenever we encounter a base case in the data we return the identity for the operation we are performing.

```
