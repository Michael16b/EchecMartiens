package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import projet.echecmartien.modele.Jeu
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

class ControleurSauvegardeJeu (modele: Jeu, ia: Boolean, saveFile: String): EventHandler<ActionEvent> {

    private val modele: Jeu
    private val ia: Boolean
    private val saveFile: String

    init {
        this.modele = modele
        this.ia = ia
        this.saveFile = saveFile
    }

    override fun handle(Event: ActionEvent) {
        val res = sauvegarderJeu(modele, saveFile, ia)

        val dialog: Alert
        if (res) {
            dialog = Alert(Alert.AlertType.INFORMATION)
            dialog.headerText = "La partie a été sauvegardée !"
        } else {
            dialog = Alert(Alert.AlertType.ERROR)
            dialog.headerText = "Attention, a partie n'a pas pu être sauvegardée !"
        }

        dialog.title = "Sauvegarde de partie"




        dialog.showAndWait()
    }
}

fun chercherNomDispo() : String {
    val file = File(getBaseDir())
    file.mkdirs()
    val listFiles = file.listFiles() ?: return "Partie1.json"
    val listFilesNames = listFiles.map { it.name }
    var filename = "Partie1.json"
    var i = 1
    while (filename in listFilesNames) {
        i++
        filename = "Partie$i.json"
    }
    return filename
}

fun getBaseDir() : String = Paths.get("${System.getProperty("user.home")}/.echecsMartiens/sauvegardes").toAbsolutePath().toString()

fun sauvegarderJeu(modele: Jeu, saveFile: String, ia: Boolean): Boolean {
    val file = File(getBaseDir())
    file.mkdirs()
    return modele.serialiser(Paths.get("${getBaseDir()}/$saveFile").toAbsolutePath().toString(), ia)
}
