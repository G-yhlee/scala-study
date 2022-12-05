# function type declaration syntax

```scala
(A, B, ...) => C
```

# function literals syntax

```scala
(parameter: type, ...) => expression
```

```scala
val sayHi = () => "hi!"
val add1 = (x: Int) => x+1
```

# working with functions

```scala
((_: Int) * 2) // (a: Int) => a * 2
_ + _     // expands to `(a, b) => a + b`
foo(_)    // expands to `(a) => foo(a)`
foo(_, b) // expands to `(a) => foo(a, b)`
_(foo)    // expands to `(a) => a(foo)`
```

# multiple parameter lists

```scala
def example(x: Int)(y: Int) = x + y
// example: (x: Int)(y: Int)Int

example(1)(2)
// res3: Int = 3
```
