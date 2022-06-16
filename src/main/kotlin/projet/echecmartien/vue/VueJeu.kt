package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import projet.echecmartien.modele.*

class VueJeu: BorderPane() {

    val playGrid: GridPane
    val cells: Array<Array<StackPane>>
    val gridHorizontalCenterContainer: HBox
    val gridVerticalCenterContainer: VBox
    val leftContainer: BorderPane
    val rightContainer: BorderPane
    val p1Container: VBox
    val p2Container: VBox
    val buttonsContainer: VBox
    val coupsRestantContainer: HBox

    val labelScore1: Label
    val labelScore2: Label
    val labelPlayer1: Label
    val labelPlayer2: Label

    val labelNbPionsArray: Array<Label>

    val buttonBack: Button
    val buttonSave: Button
    val buttonRules: Button
    val buttonParam : Button

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
        this.styleClass.add("Jeu")
        playGrid = GridPane()
        cells = Array(TAILLEHORIZONTALE) { Array(TAILLEVERTICALE) { StackPane() } }
        gridHorizontalCenterContainer = HBox()
        gridVerticalCenterContainer = VBox()
        leftContainer = BorderPane()
        buttonsContainer = VBox()
        coupsRestantContainer = HBox()
        p1Container = VBox()
        p2Container = VBox()
        rightContainer = BorderPane()

        labelScore1 = Label("Score : 0 pts")
        labelScore2 = Label("Score : 0 pts")
        this.labelScore1.styleClass.add("textInfo")
        this.labelScore2.styleClass.add("textInfo")


        labelPlayer1 = Label("NomJ1")
        this.labelPlayer1.styleClass.add("PseudoLabelSonTour")
        labelPlayer2 = Label("NomJ2")
        this.labelPlayer2.styleClass.add("PseudoLabelPasSonTour")

        labelCoupsRestants = Label()
        this.labelCoupsRestants.styleClass.add("textInfo")

        buttonBack = Button("Quitter")
        buttonSave = Button("Sauvegarder")
        buttonRules = Button("Règles")
        buttonParam = Button("Paramètres")

        buttonBack.id = "buttonBack"
        buttonSave.id = "buttonSave"
        buttonRules.id = "buttonRules"
        buttonParam.id = "buttonParam"

        pionsP1 = GridPane()
        this.pionsP1.styleClass.add("textInfo")
        pionsP2 = GridPane()
        this.pionsP2.styleClass.add("textInfo")

        labelGP1 = Label("0")
        labelMP1 = Label("0")
        labelPP1 = Label("0")
        labelGP2 = Label("0")
        labelMP2 = Label("0")
        labelPP2 = Label("0")

        this.labelGP1.styleClass.add("textInfo")
        this.labelMP1.styleClass.add("textInfo")
        this.labelPP1.styleClass.add("textInfo")
        this.labelGP2.styleClass.add("textInfo")
        this.labelMP2.styleClass.add("textInfo")
        this.labelPP2.styleClass.add("textInfo")


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
                var borderBottom = if (i == TAILLEVERTICALE-1) 2 else 1
                val borderLeft = if (j == 0) 2 else 1
                val borderRight = if (j == TAILLEHORIZONTALE-1) 2 else 1

                // pour délimiter les deux zones
                if (i == 4) {
                    borderTop = 5
                }
                if (i == 3) {
                    borderBottom = 5
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
        buttonsContainer.children.addAll(buttonParam, buttonRules, buttonSave, buttonBack)
        buttonsContainer.alignment = Pos.BOTTOM_LEFT
        buttonsContainer.spacing = 20.0
        buttonsContainer.padding = Insets(0.0, 0.0, 40.0, 10.0)
        buttonParam.prefWidth = 200.0
        buttonBack.prefWidth = 200.0
        buttonSave.prefWidth = 200.0
        buttonRules.prefWidth = 200.0

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

        leftContainer.padding = Insets(12.0, 0.0, 0.0, 20.0)
        leftContainer.top = p1Container
        leftContainer.prefWidth = 350.0

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

        labelCoupsRestants.isWrapText = true
        labelCoupsRestants.prefWidth = 300.0
        labelCoupsRestants.padding= Insets(25.0,0.0,0.0,0.0)
        rightContainer.top = coupsRestantContainer
        rightContainer.bottom = p2Container
        rightContainer.padding = Insets(0.0, 20.0, 12.0, 0.0)
        rightContainer.prefWidth = 350.0
        this.right = rightContainer

    }

    /**
     * fonction qui retourne un grand pion (cercle noir de taille 35)
     * @return Circle
     */
    private fun getGrandPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.BLACK
        c.radius = 35.0
        return c
    }

    /**
     * fonction qui retourne un moyen pion (cerlce gris de taille 25)
     * @return Circle
     */
    private fun getMoyenPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.GRAY
        c.radius = 25.0
        c.stroke = Color.BLACK
        return c
    }

    /**
     * fonction qui retourne un petit pion (cercle gris clair de taille 18)
     * @return Circle
     */
    private fun getPetitPionCircle(): Circle {
        val c = Circle()
        c.fill = Color.LIGHTGRAY
        c.radius = 18.0
        c.stroke = Color.BLACK
        return c
    }

    /**
     * fonction qui affiche un pion dans une case du plateau
     * @param row: abscisse de la case
     * @param column: ordonnée de la case
     * @param pion: pion à afficher
     */
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

    /**
     * fonction qui colorie une case du tableau avec une couleur
     * @param row: abscisse de la case
     * @param column: ordonnée de la case
     * @param color: couleur à appliquer sur la case
     */
    fun colorierCase(row: Int, column: Int, color: Color) {
        require(row in 0 until TAILLEVERTICALE)
        require(column in 0 until TAILLEHORIZONTALE)
        val r = cells[column][row].children[0]

        if (r !is Rectangle)
            return

        r.fill = color
    }

    /**
     * fonction qui remet toutes les cases du plateau à la couleur "WHITESMOKE"
     */
    fun resetCouleur() {
        cells.forEach { row ->
            row.forEach { s ->
                (s.children[0] as Rectangle).fill = Color.WHITESMOKE
            }
        }
    }

    /**
     * fonction qui remplit les cases du tableau avec les cercles des pions correspondant à une matrice de cases
     * @param cases: matrice de cases contenant chacun un ou zéro pion
     */
    fun remplirCases(cases: Array<Array<Case>>) {
        for (i in 0 until TAILLEVERTICALE) {
            for (j in 0 until TAILLEHORIZONTALE) {
                setPion(j, i, cases[j][i].getPion())
            }
        }
    }

    /**
     * fonction qui remplit les labels des pseudos des deux joueurs avec ceux donnés en argument
     * @param joueur1: premier joueur de la partie
     * @param joueur2: second joueur de la partie
     */
    fun setPseudo(joueur1: Joueur, joueur2: Joueur) {
        labelPlayer1.text = joueur1.getPseudo()
        labelPlayer2.text = joueur2.getPseudo()
    }

    /**
     * fonction qui met à jour le label contenant le nombre de coups sans prise restant
     * @param coups: nombre de coups sans prise restant
     */
    fun setCoupsRestants(coups: Int) {
        labelCoupsRestants.text = "Nb coups sans prise avant fin de la partie : $coups"
    }

    /**
     * fonction qui met le label du score du joueur 1 et joueur 2 aux entiers passés en argument
     * @param score1: score joueur 1
     * @param score2: score joueur 2
     */
    fun setScoreJoueurs(score1: Int, score2: Int) {
        labelScore1.text = "Score : $score1 pts"
        labelScore2.text = "Score : $score2 pts"
    }

    fun majPrise(joueurIndice: Int, pion: Pion?) {
        require(joueurIndice in 0..1)

        if (pion == null)
            return

        val decalagePion = when (pion) {
            is MoyenPion -> 1
            is GrandPion -> 0
            is PetitPion -> 2
            else -> throw Exception()
        }
        val labelNb = labelNbPionsArray[joueurIndice * 3 + decalagePion]
        val labelValue = labelNb.text.toInt()
        labelNb.text = "${labelValue + 1}"
    }

    fun setPrise(joueurIndice: Int, pion: Pion, score: Int) {
        require(joueurIndice in 0..1)

        val decalagePion = when (pion) {
            is MoyenPion -> 1
            is GrandPion -> 0
            is PetitPion -> 2
            else -> throw Exception()
        }
        val labelNb = labelNbPionsArray[joueurIndice * 3 + decalagePion]
        labelNb.text = "$score"
    }

}