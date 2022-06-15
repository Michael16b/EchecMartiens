package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import org.controlsfx.control.textfield.CustomTextField




class LoadGameVue(str : String = "LoadGame-s Martiens"): BorderPane()  {

    var TitrePageBandeau = str

    var TitrePage = Label()

    val Renommer = TextField()

    val conteneurgauche = VBox()

    val CasesBoutonRadio = GridPane()
    val CasesBoutons = VBox()
    val boutonChargerPartie = Button("Charger partie")
    val boutonSupprimerPartie = Button("Supprimer partie")
    val boutonRetour = Button("Retour")

    init{
        this.style="-fx-background-color: #c3b9ea "

        val Renommerlabel = Label("Renommer")
        Renommerlabel.labelFor = Renommer
        Renommer.maxWidth = 150.0
        Renommer.maxHeight = 20.0

        conteneurgauche.padding = Insets(0.0,0.0,0.0,100.0)
        Renommer.alignment = Pos.CENTER_RIGHT

        this.TitrePage = Label("Charger la partie")
        this.TitrePage.style = "-fx-font-style : 'Cambria'"
        this.TitrePage.style = "-fx-font-size : 50"
        this.top = this.TitrePage
        BorderPane.setAlignment(this.TitrePage,Pos.TOP_CENTER)
        this.TitrePage.padding = Insets(40.0,0.0,100.0,0.0)

        conteneurgauche.children.addAll(Renommer, CasesBoutonRadio)
        this.left = conteneurgauche
        BorderPane.setAlignment(conteneurgauche,Pos.CENTER_LEFT)

        // Création radio buttons et titre de parties //

        val radioButton1 = RadioButton()
        radioButton1.padding = Insets(40.0,10.0,40.0,5.0)
        val titreLigne1 = Label("nom de partie1")
        titreLigne1.padding = Insets(40.0,0.0,40.0,20.0)

        val radioButton2 = RadioButton()
        radioButton2.padding = Insets(40.0,10.0,40.0,5.0)
        val titreLigne2 = Label("nom de partie2")
        titreLigne2.padding = Insets(40.0,0.0,40.0,20.0)

        val radioButton3 = RadioButton()
        radioButton3.padding = Insets(40.0,10.0,40.0,5.0)
        val titreLigne3 = Label("nom de partie3")
        titreLigne3.padding = Insets(40.0,0.0,40.0,20.0)

        val radioButton4 = RadioButton()
        radioButton4.padding = Insets(40.0,10.0,40.0,5.0)
        val titreLigne4 = Label("nom de partie4")
        titreLigne4.padding = Insets(40.0,0.0,40.0,20.0)

        // caseboutonradio : vBox contenant les boutons radio et et les noms ds parties //


        this.CasesBoutonRadio.isGridLinesVisible = false
        this.CasesBoutonRadio.gridLinesVisibleProperty()
        this.CasesBoutonRadio.add(radioButton1,0,1)
        this.CasesBoutonRadio.add(titreLigne1,1,1)
        this.CasesBoutonRadio.add(radioButton2,0,2)
        this.CasesBoutonRadio.add(titreLigne2,1,2)
        this.CasesBoutonRadio.add(radioButton3,0,3)
        this.CasesBoutonRadio.add(titreLigne3,1,3)
        this.CasesBoutonRadio.add(radioButton4,0,4)
        this.CasesBoutonRadio.add(titreLigne4,1,4)
        this.CasesBoutonRadio.maxHeight(200.0)
        this.CasesBoutonRadio.minWidth(100.0)

        CasesBoutonRadio.padding = Insets(0.0,0.0,0.0,0.0)
        CasesBoutonRadio.prefWidth = 100.0

        val radioGroup = ToggleGroup()
        radioButton1.toggleGroup = radioGroup
        radioButton2.toggleGroup = radioGroup
        radioButton3.toggleGroup = radioGroup
        radioButton4.toggleGroup = radioGroup

        // Cases Boutons de droite//

        this.right = CasesBoutons
        this.CasesBoutons.alignment = Pos.TOP_RIGHT

        val column1 = ColumnConstraints()
        column1.percentWidth = 5.0
        val column2 = ColumnConstraints()
        column2.percentWidth = 95.0
        val row = RowConstraints()
        row.prefHeight = 0.0//Double.MAX_VALUE
        this.CasesBoutonRadio.columnConstraints.addAll(column1,column2)
        this.CasesBoutonRadio.rowConstraints.addAll(row)

        CasesBoutons.padding = Insets(20.0,100.0,0.0,0.0)
        CasesBoutons.spacing = 30.0
        boutonRetour.setPrefSize(150.0,70.0)
        boutonChargerPartie.setPrefSize(150.0,70.0)
        boutonSupprimerPartie.setPrefSize(150.0,70.0)
        this.CasesBoutons.children.addAll(boutonChargerPartie,boutonSupprimerPartie,boutonRetour)
        setMargin(CasesBoutons,Insets(0.0))

    }
}