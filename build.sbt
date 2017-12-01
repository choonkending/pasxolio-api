// Note:
//  Blank lines need to seperate the statements
//  := means you are setting the value for that key
//  += means you are adding to the values for that key

scalaVersion := "2.12.4"

sbtVersion := "0.13.13"

name := "PasxolioApi"

mainClass in Compile := Some("com.choonkending.pasxolio.Main")

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Ywarn-unused-import",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-language:higherKinds")

