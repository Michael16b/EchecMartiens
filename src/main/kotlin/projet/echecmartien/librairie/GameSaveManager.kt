package projet.echecmartien.librairie

import com.google.gson.Gson
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import java.io.FileWriter

class GameSaveManager {

    fun serialiser(jeu: Jeu, j1: Joueur, j2: Joueur, filepath: String) {
        val writer = FileWriter(filepath)
        Gson().toJson(jeu, writer)
        writer.flush()
        writer.close()
    }

}