#### Monads

[ 모나드의 정의 ]

- map,flatMap을 메서드로 갖는 제네릭 타입

[ 모나드의 쓰임 ]

- context로 감싸진 value를 안전하게 연산할수있도록 지원

[ 모나드 종류 ]

- option 모나드: optional value에 대한 연속적인 계산 환경
- seq 모나드: multiple value 에 대한 연속적인 계산 환경
- future 모나드: asynchronous value 에 대한 연속적인 계산 환경
  ... 등등

[ 모나드 활용 ]

- 다음의 표현식에서, 모나드로 감싸진 값들은 안전하게 계산이 된다.

```scala

for {
  a <- getFirstNumber  // option[a] , seq[a], future[a] .... are possible
  b <- getSecondNumber
} yield a + b



```
