#### intro

- interface 와 implementation 의 구분
- 불변성과, 변성의 구분
- sequence 를 변경시키는 method 탐구

#### sequences

- sequences는 item 의 집합이다

```scala
val sequence = Seq(1,2,3)
sequence.:+(4)
sequence :+ 4
sequence.+:(0)
0 +: sequence
sequence ++ Seq(4, 5, 6)

seq.contains(2)

```

#### Seq 와 List

- Seq 의 구현이 List이다
- list 상수 연산시간 :: prepend,head,tail
- list 선형 연산시간 :: append,apply,update
- Seq 와 List 는 필요에 따라 잘 선택하면 된다

```scala
  val list = 1 +: Seq(2, 3, 4)
  val list2 = 1 +: Seq(2, 3) :: Nil
  val list3 = 1 :: Seq(2, 3) :: Nil
  val list4 = (1 :: 2 :: Nil) :: List(3, 4) :: Nil
  val list5 = List(1, 2, 3) ::: List(4, 5, 6)

  println("seq", list)
  println("seq", list2)
  println("seq", list3)
  println("seq", list4)
  println("seq", list5)

```

#### importing collections and other libraries

- 기본 컬렉션 : Seq , List
- 라이브러리 컬렉션 : Stack , Queue

#### Seq

- Seq는 List, Stack , Vector, Queue, Array , String

#### 6.2 Working with Sequences

```scala

// map
println(Seq("a", "wet", "dog").map(_.permutations.toList ) )

// flatmap
println(Seq("a", "wet", "dog").flatMap(_.permutations.toList))
println(Vector(1, 2, 3).flatMap(num => Seq(num, num * 10)))

// foldLeft
Seq(1, 2, 3).foldLeft(0)(_ + _) // (((0 + 1) + 2) + 3)

// foldRight
Seq(1, 2, 3).foldRight(0)(_ + _) // (1 + (2 + (3 + 0)))

// foreach
List(1, 2, 3).foreach(num => println("And a " + num + "..."))

```

#### option

- 옵션은 null을 대체하는 값이다
- option 은 some, none 을 하위 타입으로 갖는 트레이트이다.
