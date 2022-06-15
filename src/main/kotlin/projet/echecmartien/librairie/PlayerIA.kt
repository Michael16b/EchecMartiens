package projet.echecmartien.librairie

import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Deplacement
import projet.echecmartien.modele.Jeu

/**
 * Classe qui permet par la méthode prochainCoup() de connaître le coup de l'adversaire
 * l'ia est toujours le deuxième joueur de la partie
 *
 * privilégie d'abord les coups avec prise, puis les coups vers le terrain de l'autre joueur
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
        val coupsAvecPrise = mutableListOf<Deplacement>()
        val coupsSansPriseHaut = mutableListOf<Deplacement>()
        val coupsAutres = mutableListOf<Deplacement>()

        for (origineX in 0 until TAILLEHORIZONTALE) {
            for (origineY in 4 until TAILLEVERTICALE) {

                for (destX in origineX-1 until origineX+2) {
                    for (destY in origineY - 1 until origineY + 2) {
                        if (destX == origineX && destY == origineY)
                            continue

                        if (!modele.deplacementPossible(origineX, origineY, destX, destY, modele.getJoueurCourant()))
                            continue

                        val case = modele.getPLateau().getCases()[destX][destY]
                        val dep = Deplacement(Coordonnee(origineX, origineY), Coordonnee(destX, destY))
                        if (case.getJoueur() != modele.getJoueurCourant()) {
                            coupsAvecPrise.add(dep)
                            continue
                        }

                        if (destY < origineY) {
                            coupsSansPriseHaut.add(dep)
                            continue
                        }
                        coupsAutres.add(dep)
                    }
                }
            }
        }

        if (coupsAvecPrise.size > 0)
            return coupsAvecPrise.random()

        if (coupsSansPriseHaut.size > 0)
            return coupsSansPriseHaut.random()

        return coupsAutres.random()

    }
}