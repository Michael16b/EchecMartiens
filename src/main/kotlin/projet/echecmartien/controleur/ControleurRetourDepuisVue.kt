package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region

class ControleurRetourDepuisVue(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {

    private val scene: Scene
    private val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        scene.root = oldVue
    }
}