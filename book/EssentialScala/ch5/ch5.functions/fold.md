#### 폴드에 대한 개념

```
Fold is just an adaptation of structural recursion where we allow the user to pass in the functions we apply at each case. As structural recursion is the generic pattern for writing any function that transforms an algebraic datatype, fold is the concrete realisation of this generic pattern. That is, fold is the generic transformation or iteration method. Any function you care to write on an algebraic datatype can be written in terms of fold.

- 폴드는 재귀데이터를 함수를 통해 연산할수 있는 방법을 제공한다.
- 대수 데이터 타입을 다루는 어떤 함수라도, 폴드를 통해 작성할수있다
```

```scala
sealed trait LinkedList[A] {
  def fold[B](end: B, f: (A, B) => B): B =
    this match {
      case End() => end
      case Pair(hd, tl) => f(hd, tl.fold(end, f))
    }
}
final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
final case class End[A]() extends LinkedList[A]
```

#### fold pattern

- polymorphic 보다 패턴매칭에서 사용할때, fold는 이점을 갖는다.
- 대수적 데이터 타입 A에 대해, fold 는 A 를 B 로 변환 한다.
- fold 는 구조재귀이다

#### fold

```scala
def fold[B](end: B)(pair: (A, B) => B): B =
  this match {
    case End() => end
    case Pair(hd, tl) => pair(hd, tl.fold(end, pair))
  }

fold(0){ (total, elt) => total + elt }

```
