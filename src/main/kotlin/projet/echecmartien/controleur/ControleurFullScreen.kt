package projet.echecmartien.controleur

import javafx.stage.Stage

class ControleurFullScreen(primaryStage: Stage){

    val stage : Stage

    init {
        this.stage = primaryStage
    }

    fun fullscreen(primaryStage: Stage) {
        if (primaryStage.isFullScreen) {
            primaryStage.isFullScreen = false
        } else {
            primaryStage.isFullScreen = true
        }
    }
}