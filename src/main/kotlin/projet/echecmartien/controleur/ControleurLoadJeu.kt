package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Region
import projet.echecmartien.vue.LoadGameVue

class ControleurLoadJeu(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {

    val scene: Scene
    val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val vueLoadGame = LoadGameVue()
        val controleurBackFromLoad = ControleurBackFromLoad(scene, oldVue)
        vueLoadGame.boutonRetour.addEventHandler(ActionEvent.ACTION, controleurBackFromLoad)
        scene.root = vueLoadGame
    }

}