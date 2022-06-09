@file:Suppress("JoinDeclarationAndAssignment")

package projet.echecmartien.modele

import projet.echecmartien.librairie.EnumPion
import projet.echecmartien.librairie.GeneralData
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE


class Plateau {

    private var tailleHorizontale: Int
    private var tailleVerticale: Int
    private var cases: Array<Array<Case>>

    init {
        this.tailleHorizontale = TAILLEHORIZONTALE
        this.tailleVerticale = TAILLEVERTICALE
        this.cases = Array(tailleHorizontale) {Array(tailleVerticale) {Case()} }
    }

    /**
     * initialise le plateau de jeu avec les pions
     */
    fun initialiser() {

        val refPlateau = GeneralData()

        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE) {
                val pion: Pion? = when (refPlateau.tableau[i][j]) {
                    EnumPion.GRANDPION -> GrandPion()
                    EnumPion.MOYENPION -> MoyenPion()
                    EnumPion.PETITPION -> PetitPion()
                    else -> null // EnumPion.LIBRE
                }
                this.cases[i][j].setPion(pion)
            }
        }
    }



    /**
     * donne la taille horizontale du plateau
     * @return la taille horizontale du plateau
     */
    fun getTailleHorizontale(): Int = this.tailleHorizontale


    /**
     * donne la taille verticale du plateau
     * @return la taille verticale du plateau
     */
    fun getTailleVerticale(): Int = this.tailleVerticale


    /**
     * donne le tableau des cases du plateau
     * @return les cases du plateau
     */
    fun getCases(): Array<Array<Case>> = this.cases


}