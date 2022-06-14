package projet.echecmartien.vue

import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.util.converter.IntegerStringConverter
import java.util.function.UnaryOperator


class MainVue: BorderPane() {

    val bottomContainer: HBox
    val topContainer: VBox
    val rulesContainer: HBox
    val titleContainer: HBox
    val playerGrid: GridPane
    val gridVerticalAlignContainer: VBox
    val gridHorizontalCenterContainer: HBox
    val gridPlayer1Container: HBox
    val gridPlayer2Container: HBox
    val gridIAContainer: HBox
    val coupsMaxContainer: HBox

    val buttonNewGame: Button
    val buttonLoadGame: Button
    val buttonRules: Button

    val labelPlayer1: Label
    val labelPlayer2: Label
    val labelIA: Label
    val labelTitle: Label
    val labelCoups: Label

    val textFieldPseudo1: TextField
    val textFieldPseudo2: TextField
    val textFieldCoups: TextField

    val comboBoxIA: ComboBox<String>

    init {

        /* Initialisation des attributs */

        bottomContainer = HBox()
        topContainer = VBox()
        rulesContainer = HBox()
        titleContainer = HBox()
        playerGrid = GridPane()
        gridVerticalAlignContainer = VBox()
        gridHorizontalCenterContainer = HBox()
        gridPlayer1Container = HBox()
        gridPlayer2Container = HBox()
        gridIAContainer = HBox()
        coupsMaxContainer = HBox()

        buttonNewGame = Button("Nouvelle partie")
        buttonLoadGame = Button("Charger une partie")
        buttonRules = Button("Règles du jeu")

        labelPlayer1 = Label("Joueur 1:")
        labelPlayer2 = Label("Joueur 2:")
        labelIA = Label("IA:")
        labelTitle = Label("Echecs Martiens")
        labelCoups = Label("Coups sans prise max (1-99):")

        textFieldPseudo1 = TextField()
        textFieldPseudo2 = TextField()
        textFieldCoups = TextField()

        comboBoxIA = ComboBox<String>()
        comboBoxIA.items.addAll(arrayOf("Sans", "Avec"))
        comboBoxIA.selectionModel.select(0)

        /* Arbre de la scène */

        /* top */
        rulesContainer.children.add(buttonRules)
        rulesContainer.alignment = Pos.TOP_RIGHT

        titleContainer.children.add(labelTitle)
        titleContainer.alignment = Pos.CENTER
        titleContainer.prefHeight = 200.0

        topContainer.children.addAll(rulesContainer, titleContainer)
        this.top = topContainer

        /* bottom */
        buttonNewGame.prefHeight = 70.0
        buttonNewGame.prefWidth = 150.0
        buttonLoadGame.prefHeight = 70.0
        buttonLoadGame.prefWidth = 150.0
        bottomContainer.children.addAll(buttonNewGame, buttonLoadGame)
        bottomContainer.alignment = Pos.CENTER
        bottomContainer.spacing = 100.0
        bottomContainer.prefHeight = 300.0
        this.bottom = bottomContainer

        /* center */
        gridPlayer1Container.children.addAll(labelPlayer1, textFieldPseudo1)
        gridPlayer1Container.spacing = 20.0
        gridPlayer2Container.children.addAll(labelPlayer2, textFieldPseudo2)
        gridPlayer2Container.spacing = 20.0
        gridIAContainer.children.addAll(labelIA, comboBoxIA)
        gridIAContainer.spacing = 55.0

        coupsMaxContainer.children.addAll(labelCoups, textFieldCoups)
        coupsMaxContainer.spacing = 20.0
        textFieldCoups.maxWidth = 50.0


        val integerFilter: UnaryOperator<TextFormatter.Change?> = UnaryOperator { change: TextFormatter.Change? ->
            val newText: String = change!!.controlNewText

            if (newText == "" || newText.matches("^0*(?:[1-9][0-9]?|99)\$".toRegex())) {
                return@UnaryOperator change
            }
            null
        }

        textFieldCoups.textFormatter = TextFormatter(IntegerStringConverter(), 10, integerFilter)

        playerGrid.add(gridPlayer1Container, 0, 0)
        playerGrid.add(gridPlayer2Container, 1, 0)
        playerGrid.add(gridIAContainer, 1, 1)
        playerGrid.add(coupsMaxContainer, 0, 1)
        playerGrid.hgap = 50.0
        playerGrid.vgap = 10.0

        gridHorizontalCenterContainer.children.add(playerGrid)
        gridHorizontalCenterContainer.alignment = Pos.CENTER

        gridVerticalAlignContainer.children.add(gridHorizontalCenterContainer)
        gridVerticalAlignContainer.alignment = Pos.CENTER
        this.center = gridVerticalAlignContainer



    }




}