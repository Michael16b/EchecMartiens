package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.util.converter.IntegerStringConverter
import java.util.function.UnaryOperator
import kotlin.system.exitProcess


class VuePrincipale: BorderPane() {

    val bottomContainer: HBox
    val topContainer: VBox
    val topButtonsContainer : GridPane
    val rulesContainer: HBox
    val paramContainer : HBox
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
    val buttonParam : Button
    val buttonQuitter : Button



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

        this.styleClass.add("main")

        /* Initialisation des attributs */

        bottomContainer = HBox()
        topContainer = VBox()
        topButtonsContainer = GridPane()
        rulesContainer = HBox()
        paramContainer = HBox()
        titleContainer = HBox()
        playerGrid = GridPane()
        gridVerticalAlignContainer = VBox()
        gridHorizontalCenterContainer = HBox()
        gridPlayer1Container = HBox()
        gridPlayer2Container = HBox()
        gridIAContainer = HBox()
        coupsMaxContainer = HBox()

        buttonNewGame = Button("Nouvelle partie")
        buttonLoadGame = Button("Charger partie")
        buttonRules = Button("Règles du jeu")
        buttonParam = Button("Paramètres")
        buttonQuitter = Button("Quitter")
        buttonQuitter.setOnMouseClicked{
            exitProcess(0)
        }

        labelPlayer1 = Label("Joueur 1:")
        labelPlayer2 = Label("Joueur 2:")
        labelIA = Label("IA:")
        labelTitle = Label("Échecs Martiens")
        labelCoups = Label("Coups sans prise max (1-99):")

        textFieldPseudo1 = TextField()
        textFieldPseudo2 = TextField()
        textFieldCoups = TextField()

        comboBoxIA = ComboBox<String>()
        comboBoxIA.items.addAll(arrayOf("Sans", "Avec"))
        comboBoxIA.selectionModel.select(0)

        /* Arbre de la scène */
        val textFont = Font.font(Font.getDefault().toString(), 17.0)
        val buttonFont = Font.font(Font.getDefault().toString(), 15.0)
        /* top */
        buttonRules.font = buttonFont
        buttonParam.font = buttonFont

        paramContainer.children.add(buttonParam)
        paramContainer.alignment = Pos.CENTER_LEFT
        paramContainer.maxWidth = Double.MAX_VALUE
        rulesContainer.maxWidth = Double.MAX_VALUE
        rulesContainer.alignment = Pos.CENTER_RIGHT
        rulesContainer.children.add(buttonRules)
        topButtonsContainer.add(paramContainer, 0, 0)
        topButtonsContainer.add(rulesContainer, 1, 0)
        GridPane.setHgrow(paramContainer, Priority.ALWAYS);
        GridPane.setHgrow(rulesContainer, Priority.ALWAYS);
        topButtonsContainer.maxWidth = Double.MAX_VALUE
        paramContainer.padding = Insets(10.0)
        rulesContainer.padding = Insets(10.0)

        labelTitle.font = Font.font(Font.getDefault().toString(), 50.0)
        titleContainer.children.add(labelTitle)
        titleContainer.alignment = Pos.CENTER
        titleContainer.prefHeight = 200.0

        topContainer.children.addAll(topButtonsContainer, titleContainer)
        topContainer.prefWidth = Double.MAX_VALUE
        this.top = topContainer

        /* bottom */
        buttonQuitter.prefHeight =70.0
        buttonQuitter.prefWidth = 200.0
        buttonNewGame.prefHeight = 70.0
        buttonNewGame.prefWidth = 200.0
        buttonLoadGame.prefHeight = 70.0
        buttonLoadGame.prefWidth = 200.0
        buttonNewGame.font = buttonFont
        buttonLoadGame.font = buttonFont
        bottomContainer.children.addAll(buttonNewGame, buttonLoadGame, buttonQuitter)
        bottomContainer.alignment = Pos.CENTER
        bottomContainer.spacing = 100.0
        bottomContainer.prefHeight = 300.0
        this.bottom = bottomContainer

        /* center */
        labelPlayer1.font = textFont
        labelPlayer2.font = textFont
        labelIA.font = textFont
        labelCoups.font = Font.font(Font.getDefault().toString(), 13.0)

        gridPlayer1Container.children.addAll(labelPlayer1, textFieldPseudo1)
        gridPlayer1Container.spacing = 20.0
        gridPlayer1Container.alignment = Pos.CENTER_RIGHT
        gridPlayer2Container.children.addAll(labelPlayer2, textFieldPseudo2)
        gridPlayer2Container.spacing = 20.0
        gridPlayer2Container.alignment = Pos.CENTER_LEFT
        gridIAContainer.children.addAll(labelIA, comboBoxIA)
        gridIAContainer.spacing = 20.0
        gridIAContainer.alignment = Pos.BASELINE_RIGHT

        coupsMaxContainer.children.addAll(labelCoups, textFieldCoups)
        coupsMaxContainer.spacing = 20.0
        textFieldCoups.maxWidth = 50.0

        // Ajout du CSS des boutons des différentes vues

        // MainVue
        // Bouton
        this.buttonRules.styleClass.add("button")
        this.buttonNewGame.styleClass.add("button")
        this.buttonLoadGame.styleClass.add("button")
        this.buttonQuitter.styleClass.add("button")
        // Label
        this.labelTitle.styleClass.add("MainTitle")
        this.labelPlayer1.styleClass.add("LabelBase")
        this.labelPlayer2.styleClass.add("LabelBase")
        this.labelIA.styleClass.add("LabelBase")
        this.labelCoups.styleClass.add("LabelBase")

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