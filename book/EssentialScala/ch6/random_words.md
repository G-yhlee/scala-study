#### generating random data

```scala
object Main extends App {
  val subjects = List("Noel", "The cat", "The dog")

  def verbsFor(subject: String): List[String] =
    subject match {
      case "Noel"    => List("wrote", "chased", "slept on")
      case "The cat" => List("meowed at", "chased", "slept on")
      case "The dog" => List("barked at", "chased", "slept on")
    }

  def objectsFor(verb: String): List[String] =
    verb match {
      case "wrote"     => List("the book", "the letter", "the code")
      case "chased"    => List("the ball", "the dog", "the cat")
      case "slept on"  => List("the bed", "the mat", "the train")
      case "meowed at" => List("Noel", "the door", "the food cupboard")
      case "barked at" => List("the postman", "the car", "the cat")
    }

  def allSentencesConditional: List[(String, String, String)] =
    for {
      subject <- subjects
      verb <- verbsFor(subject)
      obj <- objectsFor(verb)
    } yield (subject, verb, obj)

  println(allSentencesConditional.length)
}

```
