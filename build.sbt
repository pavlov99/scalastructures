lazy val root = (project in file(".")).
  settings(
    name := "trees",
    version := "1.0",
    crossScalaVersions := Seq("2.10.5", "2.11.8"),
    scalacOptions ++= Seq("-deprecation", "-feature"),  // print deprecation warnings in sbt
    resolvers += Resolver.sonatypeRepo("releases"),

    libraryDependencies := {
      val commonDependencies = libraryDependencies.value ++ Seq(
        "ch.qos.logback" % "logback-classic" % "1.1.3",  // logging backend, log4j substitutor
        "org.scalatest" % "scalatest" % "2.2.4" % "test" cross CrossVersion.binary
      )

      CrossVersion.partialVersion(scalaVersion.value) match {
        // if scala 2.11+ is used, quasiquotes are merged into scala-reflect
        case Some((2, scalaMajor)) if scalaMajor >= 11 => commonDependencies
        // in Scala 2.10, quasiquotes are provided by macro paradise
        case Some((2, 10)) => commonDependencies ++ Seq(
          compilerPlugin("org.scalamacros" % "paradise" % "2.0.1" cross CrossVersion.full)
          // "org.scalamacros" %% "quasiquotes" % "2.0.0" cross CrossVersion.binary)
        )
      }
    }
  )
