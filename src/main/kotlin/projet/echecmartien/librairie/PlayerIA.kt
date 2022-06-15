package projet.echecmartien.librairie

import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Jeu

/**
 * Classe qui permet par la méthode prochainCoup() de connaître le coup de l'adversaire
 * l'ia est toujours le deuxième joueur de la partie
 */
class PlayerIA(modele: Jeu) {

    private val modele: Jeu

    init {
        this.modele = modele
    }

    /**
     * fonction qui renvoie le prochain coup que fera l'IA
     * on suppose qu'au moins un déplacement est possible
     * @return Deplacement correspondant au déplacement du prochain coup effectué par l'ia
     */
    fun prochainCoup(): Deplacement {
        TODO()
    }
}