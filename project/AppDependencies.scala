import play.core.PlayVersion.current
import sbt._

object AppDependencies {

  val compile = Seq(
    "uk.gov.hmrc" %% "bootstrap-backend-play-27" % "3.2.0"
  )

  val test = Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.0" % "test",
    "org.pegdown"            %  "pegdown"            % "1.6.0" % "test",
    "com.typesafe.play"      %% "play-test"          % current % "test"
  )
}
