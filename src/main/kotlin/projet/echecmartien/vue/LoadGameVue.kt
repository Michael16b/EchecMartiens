package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.layout.*

class LoadGameVue(str : String = "LoadGame-s Martiens"): BorderPane()  {
    var TitrePageBandeau = str

    var TitrePage = Label()
    val nomPartie = TextField()
    val CasesBoutonRadio = GridPane()
    val CasesBoutons = VBox()
    val boutonChargerPartie = Button()
    val boutonSupprimerPartie = Button()
    val boutonRenommerPartie = Button()
    val boutonRetour = Button()

    init{
        this.style="-fx-background-color: #c3b9ea "

        this.TitrePage = Label("Charger la partie")
        this.top = this.TitrePage
        this.left = this.nomPartie
        //BorderPane.setAlignment(this.TitrePage,Pos.TOP_CENTER)
        this.TitrePage.alignment = Pos.TOP_CENTER

        this.nomPartie.minWidth(120.0)
        this.nomPartie.maxHeight(30.0)
        this.nomPartie.alignment = Pos.TOP_LEFT

        this.left = this.CasesBoutonRadio

        //val ligne1 = HBox()
        //ligne1.style = "-fx-border-color: black"
        val radioButton1 = RadioButton("1")
        val titreLigne1 = Label("nom de partie1")
        //ligne1.children.addAll(radioButton1,titreLigne1)
        //val ligne2 = HBox()
        //ligne2.style = "-fx-border-color: black"
        val radioButton2 = RadioButton("1")
        val titreLigne2 = Label("nom de partie2")
        //ligne2.children.addAll(radioButton2,titreLigne2)
        //val ligne3 = HBox()
        //ligne3.style = "-fx-border-color: black"
        val radioButton3 = RadioButton("1")
        val titreLigne3 = Label("nom de partie3")
        //ligne3.children.addAll(radioButton3,titreLigne3)
        //val ligne4 = HBox()
        //ligne4.style = "-fx-border-color: black"
        val radioButton4 = RadioButton("2")
        val titreLigne4 = Label("nom de partie4")
        //ligne4.children.addAll(radioButton4,titreLigne4)

        this.CasesBoutonRadio.isGridLinesVisible = true
        this.CasesBoutonRadio.add(radioButton1,0,1)
        radioButton1.maxHeight = Double.MAX_VALUE
        this.CasesBoutonRadio.add(titreLigne1,1,1)
        this.CasesBoutonRadio.add(radioButton2,0,2)
        this.CasesBoutonRadio.add(titreLigne2,1,2)
        this.CasesBoutonRadio.add(radioButton3,0,3)
        this.CasesBoutonRadio.add(titreLigne3,1,3)
        this.CasesBoutonRadio.add(radioButton4,0,4)
        this.CasesBoutonRadio.add(titreLigne4,1,4)
        this.CasesBoutonRadio.vgap = 30.0

        this.CasesBoutonRadio.maxHeight = Double.MAX_VALUE
        //val column = ColumnConstraints()
        //column.percentWidth = 50.0
        //val row = RowConstraints()
        //row.percentHeight = 25.5
        this.CasesBoutonRadio.style = "-fx-border-color: black"

        this.right = this.CasesBoutons






    }
}