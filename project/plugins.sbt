/*
 * Scala style plugin connection
 * More information about plugin is available here: http://www.scalastyle.org/sbt.html
 *
 * NOTE: explicitly exclude scalariform from scalastyle and add newer version
 * as dependency. It fixes bug with covariance definition, such as
 *  implicit def ordering[A <: Transaction]: Ordering[A] >
 *  Expected token RBRACKET but got Token(XML_START_OPEN,<,1280,...)>
 */
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0" excludeAll(
    ExclusionRule(organization = "com.danieltrinh")))
libraryDependencies += "org.scalariform" %% "scalariform" % "0.1.8"

resolvers += "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
