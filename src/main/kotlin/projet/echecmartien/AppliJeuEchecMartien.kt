package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       TODO()
      

    }

}

fun main(){
    val j = Jeu()
    j.initialiserPartie(Joueur("m"), Joueur("a"), 10)
    println(j.deplacementPossible(1, 1))
    Application.launch(AppliJeuEchecMartien::class.java)
}



