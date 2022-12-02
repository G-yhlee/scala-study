# 꼬리 재귀

- 재귀호출을 통해 stack space가 초과되는것을 방지하기위해 꼬리 재귀를 사용할수있다
- 꼬리재귀는 호출자가 값을 즉시 리턴하는 방식이다
- 꼬리 호출은 스택공간을 사용하지 않게 최적화할수 있다
- 스칼라는 꼬리 호출을 호출자 자기자신을 호출할경우에만 최적화할수있다(jvm이 제한이 있다)
- 꼬리호출은 중요한 요소이기 때문에, @tailrec 어노테이션으로 컴파일러에게 우리가 꼬리 호출이라고 생각하는 코드가 정말 꼬리호출인지 체크할게 할수있다
- 스칼라는 풍부한 collection 라이브러리가 지원되는데, 대부분의 경우 꼬리 재귀가 사용되기 때문에 개발자는 꼬리재귀를 직접 사용하지 않아도 된다.
- 만약 개발자가 직접 데이터구조를 만들어서 사용할때는 꼬리재귀를 염두에 두는것이 좋다
- accumulator 를 추가함으로써 꼬리재귀가 아닌 함수를 꼬리재귀로 변환할수 있다

```scala
@tailrec
def sum(list: IntList): Int =
  list match {
    case End => 0
    case Pair(hd, tl) => hd + sum(tl)
  }
```

```scala
@tailrec
def sum(list: IntList, total: Int = 0): Int =
  list match {
    case End => total
    case Pair(hd, tl) => sum(tl, total + hd)
  }
```
