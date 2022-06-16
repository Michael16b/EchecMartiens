package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.Region
import javafx.stage.Stage
import projet.echecmartien.vue.VueParametres

class ControleurParametres(scene: Scene, oldVue : Region): EventHandler<ActionEvent> {

    private val scene : Scene
    private val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue

    }

    override fun handle(e: ActionEvent) {
        val vueParam = VueParametres()
        val controleurRetourDepuisVue = ControleurRetourDepuisVue(scene, oldVue)
        vueParam.buttonBack.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisVue)

        val node = e.source as Node
        val stage = node.scene.window as Stage
        val controleurPleinEcran = ControleurPleinEcran(stage)

        vueParam.buttonFullScreen.addEventHandler(ActionEvent.ACTION, controleurPleinEcran)
        scene.root = vueParam
    }
}