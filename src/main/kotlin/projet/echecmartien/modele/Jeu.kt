package projet.echecmartien.modele

import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import java.lang.IllegalArgumentException


class Jeu {

    private var nombreCoupsSansPrise: Int
    private var nombreCoupsSansPriseMax: Int
    private var coordOrigine : Coordonnee?
    private var coordDest : Coordonnee?
    private var joueurCourant : Joueur?
    private var joueurs : Array<Joueur?>
    private var plateau : Plateau

    init {
        nombreCoupsSansPrise = 0
        nombreCoupsSansPriseMax = 0
        coordOrigine = null
        coordDest = null
        joueurCourant = null
        joueurs = arrayOfNulls(2)
        plateau = Plateau()
    }

    /**
     * getter
     * @return la coordonnée origine du déplacement
     */
    fun getCoordOrigineDeplacement(): Coordonnee? = coordOrigine
    /**
     * getter
     * @return la coordonnée destination du déplacement
     */
    fun getCoordDestinationDeplacement(): Coordonnee? = coordDest


    /**
     * setter
     * @param origine la coordonnée origine du déplacement
     */
    fun setCoordOrigineDeplacement(origine: Coordonnee){
       coordOrigine = origine
    }


    /**
     * setter
     * @param destination la coordonnée destination du déplacement
     */
    fun setCoordDestinationDeplacement(destination: Coordonnee){
        coordDest = destination
    }


    /** retourne le joueur courant
     * @return le joueur courant
     */
    fun getJoueurCourant(): Joueur? = joueurCourant

    fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax : Int) {
        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
        nombreCoupsSansPrise = 0
        plateau.initialiser()
        initialiserJoueur(joueur1, joueur2)
    }

    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        joueurs[0] = joueur1
        joueurs[1] = joueur2

        val cases = plateau.getCases()
        // Joueur 1, partie "haute" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE/2) {
                cases[i][j].setJoueur(joueur1)
            }
        }

        // Joueur 2, partie "basse" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in TAILLEVERTICALE/2 until TAILLEVERTICALE) {
                cases[i][j].setJoueur(joueur2)
            }
        }
    }

    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE)
            return false

        val cases = plateau.getCases()
        val caseOrigine = cases[coordOriginX][coordOriginY]
        if (caseOrigine.estLibre())
            return false

        for (i in coordOriginX-1 until coordOriginX+2) {
            for (j in coordOriginY-1 until coordOriginY+2) {
                val deplacement : Deplacement
                try {
                    deplacement = Deplacement(Coordonnee(coordOriginX,coordOriginY),Coordonnee(i,j))
                } catch (_:IllegalArgumentException) {
                    continue
                }
                try {
                    caseOrigine.getPion()!!.getDeplacement(deplacement)
                } catch (_: DeplacementException) {
                    continue
                }
                if (cases[i][j].estLibre())
                    return true
            }
        }
        return false
    }

    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int, coordDestinationX : Int, coordDestinationY : Int, joueur : Joueur?) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE)
            return false

        if (plateau.getCases()[coordOriginX][coordOriginY].estLibre())
            return false

        TODO("faire la fonction")
    }

    fun deplacer(coordOriginX: Int, coordOriginY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        if (coordDestinationX in 0 until TAILLEHORIZONTALE
            || coordDestinationY in 0 until TAILLEVERTICALE
            || plateau.getCases()[coordDestinationX][coordDestinationY].estLibre()
            && deplacementPossible(coordOriginX,coordOriginY)) {
                this.coordOrigine = Coordonnee(coordDestinationX, coordDestinationY)
        } else {
            throw DeplacementException("Le déplacement n'est pas possible")
        }
    }

    fun joueurVainqueur(): Joueur? {
        if (null in joueurs)
            return null

        if (nombreCoupsSansPrise >=  nombreCoupsSansPriseMax)
            return joueurMeilleurScore()

        if (joueurs[0]!!.getNbPionsCaptures() + joueurs[1]!!.getNbPionsCaptures() == 17)
            return joueurMeilleurScore()

        return null
    }

    /**
     * permet de savoir le joueur ayant le meilleur score
     * @return le joueur ayant le meilleur score, null si joueurs non initialisés
     */
    private fun joueurMeilleurScore(): Joueur? {
        if (null in joueurs)
            return null

        return if (joueurs[0]!!.calculerScore() > joueurs[1]!!.calculerScore()) {
            joueurs[0]
        } else {
            joueurs[1]
        }
    }

    /**
     * permet de savoir si la partie est finie ou non
     * @return true si la partie est finie, false sinon
     */
    fun arretPartie(): Boolean {
        return joueurVainqueur() != null
    }

    /**
     * modifie le joueur courant
     *
     */
    fun changeJoueurCourant() {
        joueurCourant = if (joueurCourant == joueurs[0]) {
            joueurs[1]
        } else {
            joueurs[0]
        }
    }

}