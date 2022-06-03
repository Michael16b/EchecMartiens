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
    println(jeu.deplacementPossible(2, 2))

    Application.launch(AppliJeuEchecMartien::class.java)
}



