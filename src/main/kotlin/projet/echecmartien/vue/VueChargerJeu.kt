package projet.echecmartien.vue

import javafx.collections.FXCollections.observableArrayList
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.layout.*


class VueChargerJeu: BorderPane()  {

    var TitrePage = Label()

    val conteneurgauche = VBox()


    val CasesBoutonRadio = GridPane()
    val CasesBoutons = VBox()
    val boutonChargerPartie = Button("Charger partie")
    val boutonSupprimerPartie = Button("Supprimer partie")
    val boutonRetour = Button("Retour")
    val boutonRenommer = Button("Renommer partie")

    val myListSave: ListView<String>
    var tabFichiers : ObservableList<String>

    init{
        this.style="-fx-background-color: #c3b9ea "

        conteneurgauche.padding = Insets(0.0,0.0,0.0,100.0)
        val titreConteneurGauche = Label("Liste des parties enregistrées")
        titreConteneurGauche.style = "-fx-font-family : 'Cambria';-fx-font-size : 18;-fx-underline: true"
        titreConteneurGauche.padding = Insets(0.0,0.0,10.0,0.0)
        //titreConteneurGauche.labelFor = conteneurgauche
        this.conteneurgauche.children.add(titreConteneurGauche)

        this.TitrePage = Label("Charger la partie")
        this.TitrePage.style = "-fx-font-family : 'Cambria'; -fx-font-size : 50; -fx-font-weight : bold"
        this.top = this.TitrePage
        BorderPane.setAlignment(this.TitrePage,Pos.TOP_CENTER)
        this.TitrePage.padding = Insets(40.0,0.0,100.0,0.0)

        this.left = conteneurgauche
        BorderPane.setAlignment(conteneurgauche,Pos.CENTER_LEFT)

        tabFichiers = observableArrayList()
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
        boutonRetour.setPrefSize(200.0,70.0)
        boutonChargerPartie.setPrefSize(200.0,70.0)
        boutonSupprimerPartie.setPrefSize(200.0,70.0)
        this.CasesBoutons.children.addAll(boutonChargerPartie, boutonRenommer, boutonSupprimerPartie,boutonRetour)
        setMargin(CasesBoutons,Insets(0.0))
    }

    fun ajouterSauvegardes(sauvegardes: List<String>) {
        tabFichiers.addAll(sauvegardes)
    }

    fun showDialog(title: String, headerText: String) {
        val dialog = Alert(Alert.AlertType.INFORMATION)
        dialog.title = title
        dialog.headerText = headerText
        dialog.showAndWait()
    }

}