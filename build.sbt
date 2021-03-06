// Note:
//  Blank lines need to seperate the statements
//  := means you are setting the value for that key
//  += means you are adding to the values for that key
//  ++= means you are adding another sequence

scalaVersion := "2.12.4"

sbtVersion := "0.13.13"

name := "PasxolioApi"

mainClass in Compile := Some("com.choonkending.pasxolio.Main")

val unfilteredVersion = "0.9.1"
val circeVersion = "0.8.0"

// groupID % artifactID % revision
// %% adds your project’s Scala version to the artifact name
// it is equivalent to % "unfiltered-netty-server_2.12.4"
libraryDependencies ++= Seq(
  "ws.unfiltered"           %% "unfiltered-netty-server"    %   unfilteredVersion,
  "io.circe"                %% "circe-core"                 %   circeVersion,
  "io.circe"                %% "circe-generic"              %   circeVersion,
  "io.circe"                %% "circe-parser"               %   circeVersion,
  "com.google.api-client"   % "google-api-client"           %   "1.23.0",
  "com.google.oauth-client" % "google-oauth-client-jetty"   %   "1.23.0",
  "com.google.apis"         % "google-api-services-sheets"  %   "v4-rev493-1.23.0"
)

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ywarn-unused-import",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-language:higherKinds")

