package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class ControleurQuitterJeu(scene: Scene): EventHandler<ActionEvent> {
    private val scene : Scene


    init {
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        val vue = MainVue()

        val controleurRulesVue = ControleurRules(scene, vue)
        vue.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)

        val controleurNewJeu = ControleurNewJeu(scene, vue)
        vue.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNewJeu)

        val controleurLoadJeuVue = ControleurLoadJeu(scene, vue)
        vue.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurLoadJeuVue)

        scene.root = vue
    }


}