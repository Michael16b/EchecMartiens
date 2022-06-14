package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.VBox

class GameVue: BorderPane() {

    val playGrid: GridPane

    val labelScore1: Label
    val labelScore2: Label
    val labelPlayer1: Label
    val labelPlayer2: Label

    val buttonBack: Button
    val buttonSave: Button

    val pionsP1: VBox
    val pionsP2: VBox

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

        labelScore1 = Label()
        labelScore2 = Label()
        labelPlayer1 = Label()
        labelPlayer2 = Label()

        buttonBack = Button("Retour")
        buttonSave = Button("Sauvegarder Partie")

        pionsP1 = VBox()
        pionsP2 = VBox()

        labelGP1 = Label()
        labelMP1 = Label()
        labelPP1 = Label()
        labelGP2 = Label()
        labelMP2 = Label()
        labelPP2 = Label()

        /* Arbre de la scène */



    }
}