lazy val root = (project in file(".")).
  settings(
    name := "trees",
    version := "1.0",
    scalaVersion := "2.11.7",
    scalacOptions ++= Seq("-deprecation"),  // print deprecation warnings in sbt
    libraryDependencies ++= Seq(
      "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
    )
  )
