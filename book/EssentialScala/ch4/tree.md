```scala
sealed trait Tree
final case class Node(l: Tree, r: Tree) extends Tree
final case class Leaf(elt: Int) extends Tree
```

# polymorphism 방식으로 sum 과 double 정의

```scala
object TreeOps {
  def sum(tree: Tree): Int =
    tree match {
      case Leaf(elt) => elt
      case Node(l, r) => sum(l) + sum(r)
    }

  def double(tree: Tree): Tree =
    tree match {
      case Leaf(elt) => Leaf(elt * 2)
      case Node(l, r) => Node(double(l), double(r))
    }
}
```

# 패턴매칭 방식으로 sum 과 double 정의

```scala
sealed trait Tree {
  def sum: Int
  def double: Tree
}
final case class Node(l: Tree, r: Tree) extends Tree {
  def sum: Int =
    l.sum + r.sum

  def double: Tree =
    Node(l.double, r.double)
}
final case class Leaf(elt: Int) extends Tree {
  def sum: Int =
    elt

  def double: Tree =
    Leaf(elt * 2)
}
```
