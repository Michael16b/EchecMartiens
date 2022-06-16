package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.TextInputDialog
import projet.echecmartien.vue.VueChargerJeu
import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Paths


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

        demanderNomFichier(item)

    }

    fun demanderNomFichier(nomPartie: String) {
        val dialog = TextInputDialog(nomPartie)
        dialog.title = "Renommer une partie"
        dialog.headerText = "Entrez le nouveau nom de la partie :"
        dialog.contentText = "Nom :"

        dialog.showAndWait()

        if (dialog.result == null)
            return

        val filepath = "${getBaseDir()}/${dialog.result}.json"
        try {
            Paths.get(filepath)
            if ("/" in dialog.result || "\\" in dialog.result)
                throw InvalidPathException(dialog.result, "Le nom de partie de doit pas contenir de / ou \\")
        } catch (ex: InvalidPathException) {
            vue.showDialog("Renommer une partie", "Le nom de partie est invalide !",  Alert.AlertType.ERROR)
            demanderNomFichier(nomPartie)
            return
        }

        val f = File(filepath)
        if (f.exists()) {
            vue.showDialog("Renommer une partie", "Une partie du même nom existe déjà !", Alert.AlertType.ERROR)
            demanderNomFichier(nomPartie)
            return
        }

        val partieFile = File(Paths.get("${getBaseDir()}/$nomPartie.json").toAbsolutePath().toString())
        if (!partieFile.exists()) {
            vue.showDialog("Renommer une partie", "La partie n'existe plus !", Alert.AlertType.ERROR)
            vue.tabFichiers.remove(nomPartie)
            return
        }

        val success = partieFile.renameTo(f)
        if (!success) {
            vue.showDialog("Renommer une partie", "La partie n'a pas pu être renommée !", Alert.AlertType.ERROR)
            vue.tabFichiers.remove(nomPartie)
            return
        }
        vue.tabFichiers[vue.myListSave.selectionModel.selectedIndex] = dialog.result
        vue.showDialog("Renommer une partie", "La partie $nomPartie a été renommée en ${dialog.result} !")
    }
}