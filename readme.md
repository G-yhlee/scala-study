# SCALA STUDY LOG

## ALL SUMMARY

- [ch2] `Expressions, Types, and Values`
- [ch3] `Objects and Classes`
- [ch4] `Modelling Data with Traits`
- [ch5] `Sequencing Computations`

[ch2]: /book/EssentialScala/ch2/ch2.summary.md
[ch3]: /book/EssentialScala/ch3/ch3.summary.md
[ch4]: /book/EssentialScala/ch4/ch4.summary.md
[ch5]: /book/EssentialScala/ch5/ch5.summary.md

## ch2 SUMMARY

## ch3 SUMMARY

## ch4 SUMMARY

#### 주요 개념

> trait :: 패턴매칭, 다형성

- [대수적타입] `4가지 유형의 타입 패턴`
- [대수적타입_다형성_패턴매칭] `합타입,곱타입,다형성,패턴매칭`
- [다형성vs패턴매칭] `어떤 상황에 어떤 패턴을 사용해야할까?`

> trait :: 패턴매칭, 다형성 예시

- [visitor] `visitor :: Anonymous , User`
- [visitor_Email] `Email 설계할때 패턴매칭으로 할것인가 다형성으로 할것인가?`
- [drawShape_1]
- [drawShape_2]
- [divide] `패턴매칭, case 클래스, case 오브젝트 ,트레이트`
- [TrafficLight]
- [Calculation]
- [대수적타입_다형성_패턴매칭_예시] `합타입,곱타입,다형성,패턴매칭 예시`

> Recursive Data

- [recursive_algebraic_data_type] `재귀적 데이터 타입 패턴 이란`
- [tail_recursion] `꼬리 재귀`

> Recursive Data 예시

- [IntList] `재귀적 데이터 타입 , IntList :: Pair :: End`
- [tree] `Tree Node Leaf`
- [expression]
- [json]
- music :: 생략

  [visitor]: /book/EssentialScala/ch4/visitor.code.md
  [visitor_email]: /book/EssentialScala/ch4/Email.md
  [drawshape_1]: /book/EssentialScala/ch4/drawShape*1.code.md
  [drawshape_2]: /book/EssentialScala/ch4/drawShape_2.code.md
  [divide]: /book/EssentialScala/ch4/divide.code.md
  [trafficlight]: /book/EssentialScala/ch4/TrafficLight.md
  [calculation]: /book/EssentialScala/ch4/Calculation.code.md
  [대수적타입]: /book/EssentialScala/ch4/대수적%20타입_4가지유형의타입패턴.md
  [다형성vs패턴매칭]: /book/EssentialScala/ch4/다형성vs패턴매칭.md
  [대수적타입_다형성_패턴매칭]: /book/EssentialScala/ch4/대수적%20타입_곱타입_합타입_다형성_패턴매칭.md.md
  [대수적타입_다형성_패턴매칭_예시]: /book/EssentialScala/ch4/대수적%20타입_곱타입_합타입_다형성_패턴매칭.예시.md.예시.md
  [intlist]: /book/EssentialScala/ch4/IntList.code.md
  [recursive_algebraic_data_type]: /book/EssentialScala/ch4/recursive_algebraic_data_type.md
  [tail_recursion]: /book/EssentialScala/ch4/tail_recursion.md
  [tree]: /book/EssentialScala/ch4/tree.md
  [expression]: /book/EssentialScala/ch4/expression.md
  [json]: /book/EssentialScala/ch4/json.md

## ch5 SUMMARY

> 5.1 Generics

- [Box] `간단한 generic 코드 예시`
- [generic_method] `간단한 generic 코드 예시`
- [result]
- [linkedList]

> 5.2 function

- [함수선언구문] `함수선언 구문, 함수리터럴`
- [IntList_to_linkedList] `fold 함수로 축약한 intList`

> 5.3 Generic Folds for Generic Data

- [IntList_to_linkedList] `LinkedList[A] , fold[B] 로 추상화`
- [tree]

> 5.4 Modelling Data with Generic Types

- [pair] `generic product type`
- [Result] `LinkedList fold `
- [sum]
- [maybe]

> 5.5 Map , FlatMap , functor , monad

- [functor_and_monad] `linkedList 예시로부터 설명`
- [sum] `sum type 에서의 fold`

[box]: /book/EssentialScala/ch5/Box.md
[pair]: /book/EssentialScala/ch5/pair.md
[generic_method]: /book/EssentialScala/ch5/generic_method.md.md
[result]: /book/EssentialScala/ch5/result.md
[linkedlist]: /book/EssentialScala/ch5/linkedList.md
[함수선언구문]: /book/EssentialScala/ch5/함수선언구문.md
[tree]: /book//EssentialScala/ch5/tree.md
[intlist_to_linkedlist]: /book/EssentialScala/ch5/IntList_to_linkedList.md
[sum]: /book/EssentialScala/ch5/sum.md
[maybe]: /book/EssentialScala/ch5/maybe.md
[functor_and_monad]: /book/EssentialScala/ch5/IntList_to_linkedList.md.md
