
import org.example.linkedListRealisation.views.View
import scalafx.application.JFXApp3.PrimaryStage

import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene

object Main extends JFXApp3 {

  def start(): Unit = {
    stage = new PrimaryStage() {
      title = "LinkedListRealisation"
      scene = new Scene(new View)
    }
  }
}