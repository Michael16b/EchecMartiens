package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.stage.WindowEvent
import projet.echecmartien.vue.VueJeu
import kotlin.system.exitProcess

class ControleurFermerJeu(scene: Scene): EventHandler<WindowEvent> {

    private val scene: Scene

    init {
        this.scene = scene
    }

    override fun handle(e: WindowEvent) {

        if (scene.root !is VueJeu)
            return

        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title = "Quitter la partie"
        dialog.headerText = "Voulez-vous vraiment quitter la partie ?"
        (dialog.dialogPane.lookupButton(ButtonType.OK) as Button).text = "Non"
        (dialog.dialogPane.lookupButton(ButtonType.CANCEL) as Button).text = "Oui"
        dialog.showAndWait()

        if (dialog.result == ButtonType.CANCEL)
            exitProcess(0)
        else
            e.consume()

    }

}