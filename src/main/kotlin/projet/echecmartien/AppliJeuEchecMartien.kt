package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.*

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       TODO()
      

    }

}

fun main(){
    val jeu = Jeu()
    jeu.initialiserPartie(Joueur("Matthis"), Joueur("Michaël"), 10)
    val plateau = Plateau()
    val cases = plateau.getCases()
    for (i in 0 until cases.size){
        for (j in 0 until cases[i].size) {
            println(cases[i][j].getPion())
        }
    }
    Application.launch(AppliJeuEchecMartien::class.java)
}



