package projet.echecmartien.librairie

import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Jeu

class PlayerIA(modele: Jeu) {

    private val modele: Jeu

    init {
        this.modele = modele
    }

    /**
     * fonction qui renvoie le prochain coup que fera l'IA
     * @return Deplacement correspondant au déplacement du prochain coup effectué par l'ia
     */
    fun prochainCoup(): Deplacement {
        TODO()
    }
}