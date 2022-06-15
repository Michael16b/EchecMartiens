package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*

class LoadGameVue(str : String = "LoadGame-s Martiens"): BorderPane()  {
    var TitrePageBandeau = str

    var TitrePage = Label()
    val nomPartie = TextField()
    val CasesBoutonRadio = GridPane()
    val CasesBoutons = VBox()
    val boutonChargerPartie = Button("Charger la partie")
    val boutonSupprimerPartie = Button("Supprimer la partie")
    val boutonRenommerPartie = Button("Renommer la partie")
    val boutonRetour = Button("retour")

    init{
        this.TitrePage = Label("Charger la partie")
        this.TitrePage.style = "-fx-font-style : 'Cambria'"
        this.TitrePage.style = "-fx-font-size : 22"
        this.top = this.TitrePage
        this.left = this.nomPartie
        BorderPane.setAlignment(this.TitrePage,Pos.TOP_CENTER)

        this.nomPartie.minWidth(120.0)
        this.nomPartie.maxHeight(30.0)
        this.nomPartie.alignment = Pos.TOP_LEFT

        //val ligne1 = HBox()
        //ligne1.style = "-fx-border-color: black"
        val radioButton1 = RadioButton()
        val titreLigne1 = Label("nom de partie1")
        //ligne1.children.addAll(radioButton1,titreLigne1)
        //val ligne2 = HBox()
        //ligne2.style = "-fx-border-color: black"
        val radioButton2 = RadioButton()
        val titreLigne2 = Label("nom de partie2")
        //ligne2.children.addAll(radioButton2,titreLigne2)
        //val ligne3 = HBox()
        //ligne3.style = "-fx-border-color: black"
        val radioButton3 = RadioButton()
        val titreLigne3 = Label("nom de partie3")
        //ligne3.children.addAll(radioButton3,titreLigne3)
        //val ligne4 = HBox()
        //ligne4.style = "-fx-border-color: black"
        val radioButton4 = RadioButton()
        val titreLigne4 = Label("nom de partie4")
        //ligne4.children.addAll(radioButton4,titreLigne4)

        this.left = CasesBoutonRadio
        this.CasesBoutonRadio.alignment = Pos.CENTER_LEFT
        this.CasesBoutonRadio.isGridLinesVisible = false
        this.CasesBoutonRadio.add(radioButton1,0,1)
        this.CasesBoutonRadio.add(titreLigne1,1,1)
        this.CasesBoutonRadio.add(radioButton2,0,2)
        this.CasesBoutonRadio.add(titreLigne2,1,2)
        this.CasesBoutonRadio.add(radioButton3,0,3)
        this.CasesBoutonRadio.add(titreLigne3,1,3)
        this.CasesBoutonRadio.add(radioButton4,0,4)
        this.CasesBoutonRadio.add(titreLigne4,1,4)
        this.CasesBoutonRadio.vgap = 70.0
        this.CasesBoutonRadio.maxHeight(400.0)
        this.CasesBoutonRadio.minWidth(300.0)

        CasesBoutonRadio.padding = Insets(0.0,40.0,100.0,40.0)

        val radioGroup = ToggleGroup()
        radioButton1.toggleGroup = radioGroup
        radioButton2.toggleGroup = radioGroup
        radioButton3.toggleGroup = radioGroup
        radioButton4.toggleGroup = radioGroup

        this.right = CasesBoutons
        this.CasesBoutons.alignment = Pos.CENTER_RIGHT

        val column = ColumnConstraints()
        column.percentWidth = 50.0
        val row = RowConstraints()
        row.maxHeight= Double.MAX_VALUE
        this.CasesBoutonRadio.columnConstraints.addAll(column)
        this.CasesBoutonRadio.rowConstraints.addAll(row)
        //this.CasesBoutonRadio.style = "-fx-border-color: black"


        CasesBoutons.padding = Insets(0.0,40.0,40.0,0.0)
        CasesBoutons.spacing = 30.0
        boutonRetour.setPrefSize(150.0,70.0)
        boutonChargerPartie.setPrefSize(150.0,70.0)
        boutonSupprimerPartie.setPrefSize(150.0,70.0)
        boutonRenommerPartie.setPrefSize(150.0,70.0)
        this.CasesBoutons.children.addAll(boutonChargerPartie,boutonSupprimerPartie,boutonRenommerPartie,boutonRetour)
        setMargin(CasesBoutons,Insets(20.0))

    }
}