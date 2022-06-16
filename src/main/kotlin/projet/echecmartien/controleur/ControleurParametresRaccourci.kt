package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.stage.Stage
import projet.echecmartien.vue.VueJeu
import projet.echecmartien.vue.VueParametres

class ControleurParametresRaccourci(scene: Scene, vue: VueJeu): EventHandler<KeyEvent> {

    private val scene: Scene
    private val vue: VueJeu

    init {
        this.scene = scene
        this.vue = vue
    }

    override fun handle(e: KeyEvent) {

        if (scene.root !is VueJeu)
            return

        if (e.code == KeyCode.ESCAPE) {
            val vueParam = VueParametres()
            val controleurRetourDepuisVue = ControleurRetourDepuisVue(scene, vue)
            vueParam.buttonBack.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisVue)

            vueParam.setOnKeyPressed {
                if (it.code == KeyCode.ESCAPE) {scene.root = vue}
            }

            val node = e.source as Node
            val stage = node.scene.window as Stage
            val controleurPleinEcran = ControleurPleinEcran(stage)

            vueParam.buttonFullScreen.addEventHandler(ActionEvent.ACTION, controleurPleinEcran)
            scene.root = vueParam
        }
    }
}