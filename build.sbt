import uk.gov.hmrc.DefaultBuildSettings.integrationTestSettings
import uk.gov.hmrc.sbtdistributables.SbtDistributablesPlugin.publishingSettings

lazy val microservice = Project("file-transfer-stub", file("."))
  .enablePlugins(play.sbt.PlayScala, SbtDistributablesPlugin)
  .settings(
    majorVersion        :=  1,
    scalaVersion        :=  "2.13.12",
    libraryDependencies ++= AppDependencies.compile ++ AppDependencies.test,
    scalacOptions       +=  "-Wconf:src=routes/.*&cat=unused-imports:s",
    scalacOptions       +=  "-Wconf:src=routes/.*&cat=unused:s"
  )
  .settings(resolvers += Resolver.jcenterRepo)
