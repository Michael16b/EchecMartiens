package projet.echecmartien



import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.controleur.*
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = MainVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)

        val controleurRulesVue = ControleurRules(scene, root)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)

        val controleurNewJeu = ControleurNewJeu(scene, root)
        root.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNewJeu)

        val controleurLoadJeuVue = ControleurLoadJeu(scene, root)
        root.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurLoadJeuVue)

        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style.css")!!.toExternalForm())

        primaryStage.minHeight = sceneHeight+40.0
        primaryStage.minWidth = sceneWidth+15.0
        primaryStage.title="Échecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



