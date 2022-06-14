package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.shape.Rectangle
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.GameVue

class ControleurClick(vue: GameVue, modele: Jeu): EventHandler<MouseEvent> {

    private val vue: GameVue
    private val modele: Jeu

    init {
        this.vue = vue
        this.modele = modele
    }

    override fun handle(e: MouseEvent) {

        var source = e.pickResult.intersectedNode

        if (source is Rectangle)
            source = source.parent

        if (source !is StackPane)
            return

        val col: Int = GridPane.getColumnIndex(source)
        val row: Int = GridPane.getRowIndex(source)



    }
}