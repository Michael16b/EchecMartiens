package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.vue.MainVue

class ControleurQuitterJeu(scene: Scene): EventHandler<ActionEvent> {
    private val scene : Scene


    init {
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        scene.root = MainVue()
    }


}