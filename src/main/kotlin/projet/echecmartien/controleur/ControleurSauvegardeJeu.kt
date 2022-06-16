package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import projet.echecmartien.modele.Jeu
import java.io.File

class ControleurSauvegardeJeu (modele: Jeu, ia: Boolean, saveFile: String): EventHandler<ActionEvent> {

    private val modele: Jeu
    private val ia: Boolean
    private val saveFile: String

    init {
        this.modele = modele
        this.ia = ia
        this.saveFile = "${getBaseDir()}/$saveFile"
        val file = File(getBaseDir())
        file.mkdirs()
        modele.serialiser(this.saveFile, ia)

    }

    override fun handle(Event: ActionEvent) {
        modele.serialiser(this.saveFile, ia)
    }
}

fun chercherNomDispo() : String {
    val file = File(getBaseDir())
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

fun getBaseDir() : String = "${System.getProperty("user.home")}/.echecsMartiens/sauvegardes"

