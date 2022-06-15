package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import projet.echecmartien.vue.RulesVue

class ControleurRules(scene: Scene, oldVue: Region): EventHandler<ActionEvent> {
    private val scene: Scene
    private val oldVue: Region

    init {
        this.scene = scene
        this.oldVue = oldVue
    }

    override fun handle(Event: ActionEvent) {
        val rulesVue = RulesVue()
        val controleurBackFromRules = ControleurBackFromRules(scene, oldVue)
        rulesVue.buttonBack.addEventHandler(ActionEvent.ACTION, controleurBackFromRules)
        scene.root = rulesVue
    }
}