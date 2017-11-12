import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import org.fusesource.scalate.ScalatePlugin.ScalateKeys._
import org.scalatra.sbt._

val ScalatraVersion = "2.6.+"

ScalatraPlugin.scalatraSettings

scalateSettings

organization := "com.github.raydive"

name := "what's up!?"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.3"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
  "org.scalatra" %% "scalatra-json" % ScalatraVersion,
  "org.json4s" %% "json4s-jackson" % "3.5.3",
  "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "compile;container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
)

javaOptions ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
)

scalateTemplateConfig in Compile := {
  val base = (sourceDirectory in Compile).value
  Seq(
    TemplateConfig(
      base / "webapp" / "WEB-INF" / "templates",
      Seq.empty,  /* default imports should be added here */
      Seq(
        Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
      ),  /* add extra bindings here */
      Some("templates")
    )
  )
}

enablePlugins(JettyPlugin)
enablePlugins(JavaAppPackaging)