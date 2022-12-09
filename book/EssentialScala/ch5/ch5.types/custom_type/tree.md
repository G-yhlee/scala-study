# tree 데이터 구조

```scala
sealed trait Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B
}
final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    node(left.fold(node, leaf), right.fold(node, leaf))
}
final case class Leaf[A](value: A) extends Tree[A] {
  def fold[B](node: (B, B) => B, leaf: A => B): B =
    leaf(value)
}
```

# tree 데이터 생성

```scala
val tree: Tree[String] =
  Node(Node(Leaf("To"), Leaf("iterate")),
       Node(
            Node(Leaf("is"), Leaf("human,")),
            Node(
                Leaf("to"),
                Node(Leaf("recurse"), Leaf("divine"))
                )))
```

# tree 데이터 연산

```scala
tree.fold[String]((a, b) => a + " " + b, str => str)
```
