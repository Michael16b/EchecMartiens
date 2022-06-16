package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.vue.ChargerJeu

class ControleurChargerJeuVue(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {

    val scene: Scene
    val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val vueLoadGame = ChargerJeu()
        val controleurRetourDepuisVue = ControleurRetourDepuisVue(scene, oldVue)
        vueLoadGame.boutonRetour.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisVue)
        scene.root = vueLoadGame
    }

}