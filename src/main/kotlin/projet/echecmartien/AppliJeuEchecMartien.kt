package projet.echecmartien



import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.WindowEvent
import projet.echecmartien.controleur.ControleurChargerJeuVue
import projet.echecmartien.controleur.ControleurFermerJeu
import projet.echecmartien.controleur.ControleurNouveauJeu
import projet.echecmartien.controleur.ControleurRegles
import projet.echecmartien.vue.VuePrincipale
import javax.print.attribute.standard.Media





class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

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

        val controleurWindow = ControleurFermerJeu(scene)
        primaryStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, controleurWindow)
        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style.css")!!.toExternalForm())

        // Ajouter de la musique
        // val media = javafx.scene.media.Media(AppliJeuEchecMartien::class.java.getResource("/projet/echecmartien/son/son.mp3")!!.toExternalForm())

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



