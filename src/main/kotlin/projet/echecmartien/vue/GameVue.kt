package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.librairie.EnumPion
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import projet.echecmartien.modele.*

class GameVue: BorderPane() {

    val playGrid: GridPane
    val cells: Array<Array<StackPane>>
    val gridHorizontalCenterContainer: HBox
    val gridVerticalCenterContainer: VBox
    val leftContainer: BorderPane
    val rightContainer: BorderPane
    val p1Container: VBox
    val p2Container: VBox
    val buttonsContainer: HBox
    val coupsRestantContainer: HBox

    val labelScore1: Label
    val labelScore2: Label
    val labelPlayer1: Label
    val labelPlayer2: Label

    val labelNbPionsArray: Array<Label>

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

    val labelCoupsRestants: Label

    init {

        /* Initialisation des attributs */

        playGrid = GridPane()
        cells = Array(TAILLEHORIZONTALE) { Array(TAILLEVERTICALE) { StackPane() } }
        gridHorizontalCenterContainer = HBox()
        gridVerticalCenterContainer = VBox()
        leftContainer = BorderPane()
        buttonsContainer = HBox()
        coupsRestantContainer = HBox()
        p1Container = VBox()
        p2Container = VBox()
        rightContainer = BorderPane()

        labelScore1 = Label("Score : 0 pts")
        labelScore2 = Label("Score : 0 pts")
        labelPlayer1 = Label("NomJ1")
        labelPlayer2 = Label("NomJ2")

        labelCoupsRestants = Label()

        buttonBack = Button("Retour")
        buttonSave = Button("Sauvegarder Partie")

        pionsP1 = GridPane()
        pionsP2 = GridPane()

        labelGP1 = Label("0")
        labelMP1 = Label("0")
        labelPP1 = Label("0")
        labelGP2 = Label("0")
        labelMP2 = Label("0")
        labelPP2 = Label("0")

        labelNbPionsArray = arrayOf(labelGP1, labelMP1, labelPP1, labelGP2, labelMP2, labelPP2)
        /* Arbre de la scène */

        /* center */

        val cellWidth = 90.0
        val cellHeight = 80.0
        for (i in 0 until TAILLEVERTICALE) {
            for (j in 0 until TAILLEHORIZONTALE) {
                val r = Rectangle(cellWidth, cellHeight)
                r.fill = Color.WHITESMOKE
                r.isPickOnBounds = false
                val s = cells[j][i]

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

        pionsP1.add(StackPane(getGrandPionCircle()), 0, 0)
        pionsP1.add(StackPane(getMoyenPionCircle()), 0, 1)
        pionsP1.add(StackPane(getPetitPionCircle()), 0, 2)
        pionsP1.add(labelGP1, 1, 0)
        pionsP1.add(labelMP1, 1, 1)
        pionsP1.add(labelPP1, 1, 2)

        pionsP1.vgap = 10.0
        pionsP1.hgap = 25.0
        pionsP1.style = "-fx-border-color: black;"
        pionsP1.padding = Insets(10.0)
        pionsP1.prefWidth = 200.0

        p1Container.children.addAll(labelPlayer1, pionsP1, labelScore1)
        p1Container.spacing = 10.0

        leftContainer.padding = Insets(20.0, 0.0, 0.0, 20.0)
        leftContainer.top = p1Container

        this.left = leftContainer

        /* right */
        pionsP2.add(StackPane(getGrandPionCircle()), 0, 0)
        pionsP2.add(StackPane(getMoyenPionCircle()), 0, 1)
        pionsP2.add(StackPane(getPetitPionCircle()), 0, 2)
        pionsP2.add(labelGP2, 1, 0)
        pionsP2.add(labelMP2, 1, 1)
        pionsP2.add(labelPP2, 1, 2)

        pionsP2.vgap = 10.0
        pionsP2.hgap = 25.0
        pionsP2.style = "-fx-border-color: black;"
        pionsP2.padding = Insets(10.0)
        pionsP2.prefWidth = 200.0

        p2Container.children.addAll(labelScore2, pionsP2, labelPlayer2)
        p2Container.alignment = Pos.BOTTOM_RIGHT
        p2Container.spacing = 10.0

        coupsRestantContainer.children.add(labelCoupsRestants)
        coupsRestantContainer.alignment = Pos.TOP_RIGHT
        coupsRestantContainer.padding = Insets(10.0, 10.0, 0.0, 0.0)
        coupsRestantContainer.prefWidth = 200.0
        labelCoupsRestants.isWrapText = true
        rightContainer.top = coupsRestantContainer
        rightContainer.bottom = p2Container
        rightContainer.padding = Insets(0.0, 20.0, 20.0, 0.0)
        this.right = rightContainer

    }

    private fun getGrandPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.BLACK
        c.radius = 35.0
        return c
    }

    private fun getMoyenPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.GRAY
        c.radius = 25.0
        c.stroke = Color.BLACK
        return c
    }

    private fun getPetitPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.LIGHTGRAY
        c.radius = 18.0
        c.stroke = Color.BLACK
        return c
    }

    fun setPion(row: Int, column: Int, pion: Pion?) {
        require(row in 0 until TAILLEHORIZONTALE)
        require(column in 0 until TAILLEVERTICALE)

        val cell = cells[row][column]

        val c: Circle? = when (pion) {
            is MoyenPion -> getMoyenPionCircle()
            is GrandPion -> getGrandPionCircle()
            is PetitPion -> getPetitPionCircle()
            else -> null
        }

        if (cell.children.size > 1)
            cell.children.removeAt(1)

        if (c != null)
            cell.children.add(c)
    }

    fun colorierCase(row: Int, column: Int, color: Color) {
        require(row in 0 until TAILLEVERTICALE)
        require(column in 0 until TAILLEHORIZONTALE)
        val r = cells[column][row].children[0]

        if (r !is Rectangle)
            return

        r.fill = color
    }

    fun resetCouleur() {
        cells.forEach { row ->
            row.forEach { s ->
                (s.children[0] as Rectangle).fill = Color.WHITESMOKE
            }
        }
    }

    fun remplirCases(cases: Array<Array<Case>>) {
        for (i in 0 until TAILLEVERTICALE) {
            for (j in 0 until TAILLEHORIZONTALE) {
                setPion(j, i, cases[j][i].getPion())
            }
        }
    }

}
