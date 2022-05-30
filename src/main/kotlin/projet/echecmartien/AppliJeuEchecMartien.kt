package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.Plateau

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       TODO()
      

    }

}

fun main(){
    val jeu = Jeu()
    val plateau = Plateau()
    jeu.initialiserPartie(Joueur("Michaël"), Joueur("Matthis"), 10)
    plateau.initialiser()
    var cases = plateau.getCases()
    for (i in 0 until cases.size) {
        for (j in 0 until cases[i].size) {
            println(cases[i][j].getPion())
        }
    }
    Application.launch(AppliJeuEchecMartien::class.java)
}



