package projet.echecmartien

import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import javafx.stage.WindowEvent
import projet.echecmartien.controleur.*
import projet.echecmartien.vue.VuePrincipale

class AppliJeuEchecMartien: Application() {

    override fun start(primaryStage: Stage) {
        // Ajout icone
        val icon = Image(javaClass.getResource("/icone.png")?.toExternalForm())
        primaryStage.icons.add(icon)

        val root = VuePrincipale()

        val sceneWidth = 1100.0
        val sceneHeight = 720.0
        val scene = Scene(root, sceneWidth, sceneHeight)

        val controleurReglesVue = ControleurRegles(scene, root)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurReglesVue)

        val controleurNouveauJeu = ControleurNouveauJeu(scene, root)
        root.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNouveauJeu)

        val controleurChargerJeuVue = ControleurChargerJeuVue(scene, root)
        root.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurChargerJeuVue)

        val controleurParametres = ControleurParametres(scene, root)
        root.buttonParam.addEventHandler(ActionEvent.ACTION,controleurParametres)

        val controleurFenetre = ControleurFermerJeu(scene)
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, controleurFenetre)
        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style.css")!!.toString())

        primaryStage.isResizable = false
        primaryStage.fullScreenExitHint = ""
        primaryStage.fullScreenExitKeyCombination = KeyCombination.NO_MATCH

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



