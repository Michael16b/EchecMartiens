package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage

class ControleurFullScreen(primaryStage: Stage): EventHandler<ActionEvent> {

    val stage : Stage

    init {
        this.stage = primaryStage
    }

    override fun handle(e: ActionEvent) {
        stage.isFullScreen = !stage.isFullScreen
    }
}