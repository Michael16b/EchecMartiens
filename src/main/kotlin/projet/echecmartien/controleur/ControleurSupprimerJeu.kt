package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import projet.echecmartien.vue.VueChargerJeu
import java.io.File

class ControleurSupprimerJeu (vue: VueChargerJeu): EventHandler<ActionEvent> {

    private val vue: VueChargerJeu

    init {
        this.vue = vue
    }

    override fun handle(e: ActionEvent) {
        val item = vue.myListSave.selectionModel.selectedItem

        if (item == null) {
            vue.showDialog("Supprimer une partie", "Aucune partie n'est sélectionnée", Alert.AlertType.ERROR)
            return
        }

        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title = "Supprimer une partie"
        dialog.headerText = "Voulez-vous vraiment supprimer la partie $item ?"
        (dialog.dialogPane.lookupButton(ButtonType.OK) as Button).text = "Oui"
        (dialog.dialogPane.lookupButton(ButtonType.CANCEL) as Button).text = "Non"
        dialog.showAndWait()

        val result = dialog.result
        if (result != ButtonType.OK)
            return

        val saveFile = "$item.json"
        val filepath = "${getBaseDir()}/$saveFile"
        val file = File(filepath)

        vue.tabFichiers.remove(item)

        if (!file.exists()) {
            vue.showDialog("Supprimer une partie", "La partie n'existe $item pas ou plus.", Alert.AlertType.ERROR)
            return
        }

        file.delete()
    }
}