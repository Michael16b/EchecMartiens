package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.BorderPane

class ControleurRules(scene: Scene , vue: BorderPane): EventHandler<ActionEvent> {
    val scene: Scene
    val vue: BorderPane

    init {
        this.scene = scene
        this.vue = vue
    }

    override fun handle(Event: ActionEvent) {
        scene.root = vue
    }
}