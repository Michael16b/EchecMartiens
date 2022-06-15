package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.vue.VueRegles

class ControleurRegles(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {
    private val scene: Scene
    private val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val vueRegles = VueRegles()
        val controleurRetourDepuisRegles = ControleurRetourDepuisRegles(scene, oldVue)
        vueRegles.boutonRetour.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisRegles)
        scene.root = vueRegles
    }
}