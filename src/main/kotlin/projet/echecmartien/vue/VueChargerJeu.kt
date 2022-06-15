package projet.echecmartien.vue

import javafx.collections.FXCollections
import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.layout.*
import org.controlsfx.control.textfield.CustomTextField
import java.io.File


class ChargerJeu(str : String = "LoadGames"): BorderPane()  {

    var TitrePageBandeau = str

    var TitrePage = Label()

    val conteneurgauche = VBox()

    val CasesBoutonRadio = GridPane()
    val CasesBoutons = VBox()
    val boutonChargerPartie = Button("Charger partie")
    val boutonSupprimerPartie = Button("Supprimer partie")
    val boutonRetour = Button("Retour")

    val myListSave: ListView<String>
    //var tabFichiers : ObservableList<String>
    var tabFichiers : ObservableList<String>

    init{
        this.style="-fx-background-color: #c3b9ea "

        conteneurgauche.padding = Insets(0.0,0.0,0.0,100.0)

        this.TitrePage = Label("Charger la partie")
        this.TitrePage.style = "-fx-font-style : 'Cambria'"
        this.TitrePage.style = "-fx-font-size : 50"
        this.top = this.TitrePage
        BorderPane.setAlignment(this.TitrePage,Pos.TOP_CENTER)
        this.TitrePage.padding = Insets(40.0,0.0,100.0,0.0)


        this.left = conteneurgauche
        BorderPane.setAlignment(conteneurgauche,Pos.CENTER_LEFT)

        tabFichiers = observableArrayList()
        getSaveName()
        myListSave = ListView<String>()
        myListSave.items = tabFichiers
        conteneurgauche.children.addAll(myListSave)


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
        CasesBoutons.spacing = 40.0
        boutonRetour.setPrefSize(150.0,70.0)
        boutonChargerPartie.setPrefSize(150.0,70.0)
        boutonSupprimerPartie.setPrefSize(150.0,70.0)
        this.CasesBoutons.children.addAll(boutonChargerPartie,boutonSupprimerPartie,boutonRetour)
        setMargin(CasesBoutons,Insets(0.0))

    }

    private fun getSaveName() {
        val file = File(System.getProperty("user.home")+ "/.echecsMartiens"+"/.sauvegardes")
        val listFiles = file.listFiles()
        if (listFiles != null) {
              for (i in listFiles) {
                  tabFichiers.add(i.name)
              }
        }
    }


}