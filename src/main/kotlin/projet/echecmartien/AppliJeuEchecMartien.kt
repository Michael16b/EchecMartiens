package projet.echecmartien

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurRules
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = MainVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)
        val rootRules = RulesVue()
        val controleurRules = ControleurRules(scene,rootRules)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRules)

        primaryStage.minHeight = sceneHeight+40.0
        primaryStage.minWidth = sceneWidth+15.0
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



