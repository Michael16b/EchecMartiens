package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.vue.VueChargerJeu
import java.io.File

class ControleurChargerJeuVue(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {

    val scene: Scene
    val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val vueLoadGame = VueChargerJeu()
        val controleurRetourDepuisVue = ControleurRetourDepuisVue(scene, oldVue)
        vueLoadGame.boutonRetour.addEventHandler(ActionEvent.ACTION, controleurRetourDepuisVue)


        val sauvegardes: List<String>

        val file = File(getBaseDir())
        val listFiles = file.listFiles()
        sauvegardes = listFiles?.map {it.name.removeSuffix(".json")}?.sorted() ?: listOf()

        vueLoadGame.ajouterSauvegardes(sauvegardes)
        vueLoadGame.boutonChargerPartie.addEventHandler(ActionEvent.ACTION, ControleurChargerJeu(scene, vueLoadGame))
        scene.root = vueLoadGame
    }

}