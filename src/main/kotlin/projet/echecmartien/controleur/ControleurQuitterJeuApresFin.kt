package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.vue.VuePrincipale

class ControleurQuitterJeuApresFin(scene: Scene): EventHandler<ActionEvent> {
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

        val controleurChargerJeuVue = ControleurChargerJeuVue(scene, vue)
        vue.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurChargerJeuVue)

        val controleurParametres = ControleurParametres(scene,vue)
        vue.buttonParam.addEventHandler(ActionEvent.ACTION, controleurParametres)

        scene.root = vue
    }


}