package projet.echecmartien.vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle

class GameVue: BorderPane() {

    val playGrid: GridPane
    val gridHorizontalCenterContainer: HBox
    val gridVerticalCenterContainer: VBox

    val labelScore1: Label
    val labelScore2: Label
    val labelPlayer1: Label
    val labelPlayer2: Label

    val buttonBack: Button
    val buttonSave: Button

    val pionsP1: GridPane
    val pionsP2: GridPane

    /* Labels pour afficher le nombre de grands pions, moyens pions et petits pions
    * capturés par le joueur 1 et joueur 2 */
    val labelGP1: Label
    val labelMP1: Label
    val labelPP1: Label

    val labelGP2: Label
    val labelMP2: Label
    val labelPP2: Label

    init {

        /* Initialisation des attributs */

        playGrid = GridPane()
        gridHorizontalCenterContainer = HBox()
        gridVerticalCenterContainer = VBox()

        labelScore1 = Label()
        labelScore2 = Label()
        labelPlayer1 = Label()
        labelPlayer2 = Label()

        buttonBack = Button("Retour")
        buttonSave = Button("Sauvegarder Partie")

        pionsP1 = GridPane()
        pionsP2 = GridPane()

        labelGP1 = Label()
        labelMP1 = Label()
        labelPP1 = Label()
        labelGP2 = Label()
        labelMP2 = Label()
        labelPP2 = Label()

        /* Arbre de la scène */

        /* center */

        val cellWidth = 95.0
        val cellHeight = 85.0
        for (i in 0..7) {
            for (j in 0..3) {
                val r = Rectangle(cellWidth, cellHeight)
                r.fill = Color.LIGHTGRAY
                playGrid.add(r, j, i)
            }
        }
        playGrid.isGridLinesVisible = true

        gridHorizontalCenterContainer.children.add(playGrid)
        gridHorizontalCenterContainer.alignment = Pos.CENTER

        gridVerticalCenterContainer.children.add(gridHorizontalCenterContainer)
        gridVerticalCenterContainer.alignment = Pos.CENTER
        this.center = gridVerticalCenterContainer


        /* left */

        /* right */


    }
}