package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.vue.VueChargerJeu

class ControleurRenommerJeu(scene: Scene, vue : VueChargerJeu): EventHandler<ActionEvent> {

    val vue : VueChargerJeu
    val scene : Scene

    init {
        this.vue = vue
        this.scene = scene
    }
    override fun handle(p0: ActionEvent?) {
        val nomPartie : String  = vue.myListSave.selectionModel.selectedItem
    }
}