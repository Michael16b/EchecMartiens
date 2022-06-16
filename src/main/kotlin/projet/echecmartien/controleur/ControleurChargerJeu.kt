package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene

class ControleurChargerJeu(scene: Scene): EventHandler<ActionEvent> {

    private val scene : Scene

    init {
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        //val jeu = deserialiser()
        //jeu.initialiserPartie()
    }

}