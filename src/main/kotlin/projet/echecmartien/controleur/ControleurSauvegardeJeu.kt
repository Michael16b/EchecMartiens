package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.modele.Jeu

class ControleurSauvegardeJeu (modele: Jeu, ia: Boolean): EventHandler<ActionEvent> {

    private val modele: Jeu
    private val ia: Boolean

    init {
        this.modele = modele
        this.ia = ia
    }

    override fun handle(Event: ActionEvent) {
        // modele.serialiser("test.json", ia)
    }
}