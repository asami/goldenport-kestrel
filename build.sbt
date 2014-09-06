organization := "org.goldenport"

name := "goldenport-kestrel"

version := "1.0.0-SNAPSHOT"

scalaVersion := "2.10.3"
// crossScalaVersions := Seq("2.9.2", "2.9.1")

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

scalacOptions += "-feature"

resolvers += "Asami Maven Repository" at "http://www.asamioffice.com/maven"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// Defines important library dependencies
libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

libraryDependencies += "junit" % "junit" % "4.8" % "test"

// libraryDependencies += "org.goldenport" %% "goldenport-scalatest-lib" % "1.0.0" % "test"

//
publishTo := Some(Resolver.file("asamioffice", file("target/maven-repository")))
