package projet.echecmartien

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurVueBorderPane
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.LoadGameVue
import projet.echecmartien.vue.MainVue
import projet.echecmartien.vue.RulesVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = MainVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)
        val rootRules = RulesVue()
        val controleurRulesVue = ControleurVueBorderPane(scene,rootRules)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)


        val rootGameVue = GameVue()
        val controleurGameVue = ControleurVueBorderPane(scene,rootGameVue)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurGameVue)

        val rootLoadGameVue = LoadGameVue()
        val controleurLoadGameVue = ControleurVueBorderPane(scene,rootLoadGameVue)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurLoadGameVue)



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



