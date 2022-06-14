package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class LoadGameVue(str : String = "LoadGame-s Martiens"): BorderPane()  {
    var TitrePageBandeau = str

    var TitrePage = Label()
    val nomPartie = TextField()
    val CasesBoutonRadio = VBox()
    val boutonChargerPartie = Button()
    val boutonSupprimerPartie = Button()
    val boutonRenommerPartie = Button()
    val boutonRetour = Button()

    init{
        this.style="-fx-background-color: purple"

        this.TitrePage = Label("Charger la partie")
        this.top = this.TitrePage
        this.left = this.nomPartie

        this.nomPartie.minWidth(120.0)
        this.nomPartie.maxHeight(20.0)

        this.left = this.CasesBoutonRadio
        val ligne1 = HBox()
        val radioButton1 = RadioButton("1")
        val titreLigne1 = Label("nom de partie1")
        val ligne2 = HBox()
        val radioButton2 = RadioButton("1")
        val titreLigne2 = Label("nom de partie2")
        val ligne3 = HBox()
        val radioButton3 = RadioButton("1")
        val titreLigne3 = Label("nom de partie3")
        val ligne4 = HBox()
        val radioButton4 = RadioButton("2")
        val titreLigne4 = Label("nom de partie4")






    }
}