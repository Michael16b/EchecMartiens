package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import projet.echecmartien.vue.VueChargerJeu

class ControleurChargerJeu(scene: Scene, vue: VueChargerJeu): EventHandler<MouseEvent> {

    private val scene : Scene
    private val vue: VueChargerJeu

    init {
        this.scene = scene
        this.vue = vue
    }

    override fun handle(e: MouseEvent) {
        println(vue.myListSave.selectionModel.selectedItem)
    }

}