# 재귀적 데이터 타입

- 재귀적 대수적 데이터 타입을 정의할때, 최소 2가지 케이스가 정의되어야한다
- 첫번째는, 재귀적 케이스 이고
- 두번째는, 재귀적이지 않은 케이스 이다.

```scala
sealed trait RecursiveExample
final case class RecursiveCase(recursion: RecursiveExample) extends RecursiveExample
case object BaseCase extends RecursiveExample
```
