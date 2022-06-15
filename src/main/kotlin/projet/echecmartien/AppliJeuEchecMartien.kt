package projet.echecmartien



import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurBackFromRules
import projet.echecmartien.controleur.ControleurLoadGame
import projet.echecmartien.controleur.ControleurNewGame
import projet.echecmartien.controleur.ControleurRules
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.LoadGameVue
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = MainVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)

        val controleurRulesVue = ControleurRules(scene, root)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)

        val rootGameVue = GameVue()
        val controleurNewGame = ControleurNewGame(scene, root)
        root.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNewGame)

        val controleurBackGameToMainVue = ControleurBackFromRules(scene,root)
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



