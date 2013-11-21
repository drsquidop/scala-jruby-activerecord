organization := "com.drsquidop"

name         := "scala-jruby-activerecord-scala-client"

version      := "1.0-SNAPSHOT"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")

libraryDependencies += "org.jruby" % "jruby-complete" % "1.7.6"

libraryDependencies += "com.drsquidop" % "scala-jruby-activerecord-model-gem" % "1.0-SNAPSHOT"

libraryDependencies += "org.specs2" %% "specs2" % "2.3.4" % "test"
