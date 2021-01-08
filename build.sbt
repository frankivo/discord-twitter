name := "discord-twitter"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "com.discord4j" % "discord4j-core" % "3.1.3"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.10"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.2"
libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.7"

scalacOptions := Seq("-unchecked", "-deprecation")
