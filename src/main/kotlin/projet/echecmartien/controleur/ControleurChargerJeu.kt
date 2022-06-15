package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import projet.echecmartien.modele.deserialiser
import java.io.File

class ControleurChargerJeu(scene: Scene): EventHandler<ActionEvent> {

    private val scene : Scene

    init {
        this.scene = scene
    }

    override fun handle(p0: ActionEvent?) {
        //val jeu = deserialiser()

    }
    private fun searchSaves() {
        val file = File(System.getProperty("user.home")+ "/.echecsMartiens"+"/.sauvegardes")
        file.mkdirs()
        val listFiles = file.listFiles()
        if (listFiles != null) {
            for (i in listFiles.indices) {
                if (listFiles[i].isDirectory) {
                    println("Directory: " + listFiles[i].name);
                } else {
                    println("File: " + listFiles[i].name);
                }
            }
        }
    }

}