package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.layout.Region
import projet.echecmartien.modele.Jeu
import java.io.File

class ControleurSauvegardeJeu (modele: Jeu, ia: Boolean): EventHandler<ActionEvent> {

    private val modele: Jeu
    private val ia: Boolean

    init {
        this.modele = modele
        this.ia = ia
    }

    override fun handle(Event: ActionEvent) {
        val file = File(System.getProperty("user.home")+ "/.echecsMartiens"+"/.sauvegardes")
        createSaves(file)
        val nameSave : String = chooseNameSave(file)
        modele.serialiser("$file/$nameSave", ia)

    }
    private fun createSaves(file : File) {
        file.mkdirs()
    }
    private fun chooseNameSave(file : File) : String {
        var name = "test.json"
        var i = 1
        while (searchSaves(file,name)) {
            i += 1
            name = "test$i.json"
        }
        return name
    }

    private fun searchSaves(file : File, name : String) : Boolean {
        file.mkdirs()
        val listFiles = file.listFiles()
        if (listFiles != null) {
            for (f in listFiles) {
                if (f.name == name) {
                    return true
                }
            }
        }
        return false
    }

}