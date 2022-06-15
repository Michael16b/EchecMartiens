package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.vue.VuePrincipale

class ControleurQuitterJeu(scene: Scene): EventHandler<ActionEvent> {
    private val scene : Scene


    init {
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        val vue = VuePrincipale()

        val controleurReglesVue = ControleurRegles(scene, vue)
        vue.buttonRules.addEventHandler(ActionEvent.ACTION,controleurReglesVue)

        val controleurNouveauJeu = ControleurNouveauJeu(scene, vue)
        vue.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNouveauJeu)

        val controleurChargerJeuVue = ControleurChargerJeu(scene, vue)
        vue.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurChargerJeuVue)

        scene.root = vue
    }


}