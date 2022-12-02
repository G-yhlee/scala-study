# Expression

- Expression을 BNF 로 표현하면 다음과 같다

```scala
Expression ::= Addition left:Expression right:Expression
             | Subtraction left:Expression right:Expression
             | Division left:Expression right:Expression
             | SquareRoot value:Expression
             | Number value:Int
```

# JSON

- JSON 을 BNF 로 표현하면 다음과 같다

```scala
Json ::= JsNumber value:Double
       | JsString value:String
       | JsBoolean value:Boolean
       | JsNull
       | JsSequence
       | JsObject
JsSequence ::= SeqCell head:Json tail:JsSequence
             | SeqEnd
JsObject ::= ObjectCell key:String value:Json tail:JsObject
           | ObjectEnd
```

# JSON

- scala 코드로 표현하면 다음과 같다

```scala
sealed trait Json
final case class JsNumber(value: Double) extends Json
final case class JsString(value: String) extends Json
final case class JsBoolean(value: Boolean) extends Json
case object JsNull extends Json
sealed trait JsSequence extends Json
final case class SeqCell(head: Json, tail: JsSequence) extends JsSequence
case object SeqEnd extends JsSequence
sealed trait JsObject extends Json
final case class ObjectCell(key: String, value: Json, tail: JsObject) extends JsObject
case object ObjectEnd extends JsObject
```
