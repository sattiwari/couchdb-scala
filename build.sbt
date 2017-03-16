name := "couchdb-scala"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "org.scalaz"                  %% "scalaz-core"                 % "7.1.0",
  "org.scalaz"                  %% "scalaz-effect"               % "7.1.0",
  "com.github.julien-truffaut"  %% "monocle-core"                % "1.0.1",
  "com.github.julien-truffaut"  %% "monocle-macro"               % "1.0.1",
  "com.lihaoyi"                 %% "upickle"                     % "0.2.6-RC1",
  "org.http4s"                  %% "http4s-core"                 % "0.5.4",
  "org.http4s"                  %% "http4s-client"               % "0.5.4",
  "org.http4s"                  %% "http4s-blazeclient"          % "0.5.4",
  "org.log4s"                   %% "log4s"                       % "1.1.3",
  "org.specs2"                  %% "specs2"                      % "2.4.15" % "test",
  "org.typelevel"               %% "scalaz-specs2"               % "0.3.0"  % "test",
  "org.scalacheck"              %% "scalacheck"                  % "1.12.1" % "test",
  "org.scalaz"                  %% "scalaz-scalacheck-binding"   % "7.1.0"  % "test",
  "ch.qos.logback"              %  "logback-classic"             % "1.1.2"  % "test"
)
    