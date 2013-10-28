import sbt._
import Keys._

object MathdokuSolverProject extends Build 
{
    val sharedLibraryDependencies = Seq(
        "org.scalatest" %% "scalatest" % "1.9.1" % "test"
    )

    lazy val mathdokuSolver = Project(
        id = "mathdokuSolver",
        base = file("."),
        settings = Project.defaultSettings ++ Seq(
            libraryDependencies ++= sharedLibraryDependencies,
            scalaVersion := "2.10.2",
            version := "0.0.1"            
        )
    )
                            
}
