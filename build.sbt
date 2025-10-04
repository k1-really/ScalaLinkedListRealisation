ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "LR2"
  )



mainClass := Some("org.example.linkedListRealisation.Main")

libraryDependencies += "org.scalafx" %% "scalafx" % "16.0.0-R25"

// Определяем версию операционной системы для бинарников JavaFX
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux")   => "linux"
  case n if n.startsWith("Mac")     => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Добавляем зависимость от библиотек JavaFX, с учетом операционной системы
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map(m =>
  "org.openjfx" % s"javafx-$m" % "16" classifier osName
)

mainClass := Some("org.example.linkedListRealisation.Main")
