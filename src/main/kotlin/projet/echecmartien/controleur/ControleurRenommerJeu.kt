package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.TextInputDialog
import projet.echecmartien.vue.VueChargerJeu

class ControleurRenommerJeu(vue : VueChargerJeu): EventHandler<ActionEvent> {

    val vue : VueChargerJeu

    init {
        this.vue = vue
    }

    override fun handle(e: ActionEvent) {
        val item = vue.myListSave.selectionModel.selectedItem

        if (item == null) {
            vue.showDialog("Supprimer une partie", "Aucune partie n'est sélectionnée", Alert.AlertType.ERROR)
            return
        }

        val dialog = TextInputDialog(item)
        dialog.title = "Renommer une partie"
        dialog.headerText = "Entrez le nouveau nom de la partie :"
        dialog.contentText = "Nom :"

        dialog.showAndWait()

        println(dialog.result == null)

    }
}