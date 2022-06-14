package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = GameVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)

        val rootRules = RulesVue()
        val controleurRulesVue = ControleurRules(scene,rootRules)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)


        val rootGameVue = GameVue()
        val controleurGameVue = ControleurNewGame(scene,rootGameVue)
        root.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurGameVue)
        val controleurBackGameToMainVue = ControleurBackMainVue(scene,root)
        rootGameVue.buttonBack.addEventHandler(ActionEvent.ACTION,controleurBackGameToMainVue)


        val rootLoadGameVue = LoadGameVue()
        val controleurLoadGameVue = ControleurLoadGame(scene,rootLoadGameVue)
        root.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurLoadGameVue)



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



