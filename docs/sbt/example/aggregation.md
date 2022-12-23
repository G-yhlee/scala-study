```sbt
lazy val root = (project in file("."))
  .aggregate(util, core)
  .settings(
    update / aggregate := false
  )


lazy val util = (project in file("util"))

lazy val core = (project in file("core")).dependsOn(bar, baz)
```
