package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.vue.VueParametres

class ControleurParametresJeu(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {

    private val scene: Scene
    private val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val vueParam = VueParametres()
        val controleurRetourDepuisVue = ControleurRetourDepuisVue(scene, oldVue)
        vueParam.buttonBack.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisVue)
        scene.root = vueParam
    }
}