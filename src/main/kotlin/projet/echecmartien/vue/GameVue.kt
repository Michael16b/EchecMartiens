package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE

class GameVue: BorderPane() {

    val playGrid: GridPane
    val gridHorizontalCenterContainer: HBox
    val gridVerticalCenterContainer: VBox
    val leftContainer: BorderPane
    val p1Container: VBox
    val buttonsContainer: HBox

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
        leftContainer = BorderPane()
        buttonsContainer = HBox()
        p1Container = VBox()

        labelScore1 = Label("Score :")
        labelScore2 = Label("Score :")
        labelPlayer1 = Label("NomJ1")
        labelPlayer2 = Label("NomJ2")

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

        val cellWidth = 90.0
        val cellHeight = 80.0
        for (i in 0 until TAILLEVERTICALE) {
            for (j in 0 until TAILLEHORIZONTALE) {
                val r = Rectangle(cellWidth, cellHeight)
                r.fill = Color.LIGHTGRAY
                val s = StackPane()

                // les cellules sur les côtés doivent avoir une bordure de taille 2
                var borderTop = if (i == 0) 2 else 1
                val borderBottom = if (i == TAILLEVERTICALE-1) 2 else 1
                val borderLeft = if (j == 0) 2 else 1
                val borderRight = if (j == TAILLEHORIZONTALE-1) 2 else 1

                // pour délimiter les deux zones
                if (i == 4) {
                    borderTop = 5
                }

                s.style = "-fx-border-color: black; -fx-border-width: $borderTop $borderRight $borderBottom $borderLeft;"
                s.children.add(r)
                playGrid.add(s, j, i)
            }
        }

        gridHorizontalCenterContainer.children.add(playGrid)
        gridHorizontalCenterContainer.alignment = Pos.CENTER

        gridVerticalCenterContainer.children.add(gridHorizontalCenterContainer)
        gridVerticalCenterContainer.alignment = Pos.CENTER
        this.center = gridVerticalCenterContainer

        /* left */
        buttonsContainer.children.addAll(buttonBack, buttonSave)
        buttonsContainer.alignment = Pos.BOTTOM_LEFT
        buttonsContainer.spacing = 20.0
        buttonsContainer.padding = Insets(0.0, 0.0, 10.0, 10.0)

        leftContainer.bottom = buttonsContainer

        p1Container.children.addAll(labelPlayer1, pionsP1, labelScore1)
        leftContainer.top = p1Container

        this.left = leftContainer

        /* right */


    }
}