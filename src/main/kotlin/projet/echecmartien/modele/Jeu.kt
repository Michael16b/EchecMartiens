package projet.echecmartien.modele

import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE


class Jeu {

    private var nombreCoupsSansPrise: Int
    private var nombreCoupsSansPriseMax: Int
    private var coordOrigine : Coordonnee?
    private var coordDest : Coordonnee?
    private var joueurCourant : Joueur?
    private var joueurs : Array<Joueur?>
    private var plateau : Plateau

    init {
        this.nombreCoupsSansPrise = 0
        this.nombreCoupsSansPriseMax = 0
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
    fun getCoordOrigineDeplacement(): Coordonnee?{
       return coordOrigine
    }
    /**
     * getter
     * @return la coordonnée destination du déplacement
     */
    fun getCoordDestinationDeplacement(): Coordonnee?{
        return coordDest
    }


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
    fun getJoueurCourant(): Joueur? {
       return joueurCourant
    }

    fun initialiserPartie(joueur1: Joueur, joueur2: Joueur, nombreCoupsSansPriseMax : Int) {
        initialiserJoueur(joueur1, joueur2)
        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
    }

    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @paral joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        joueurs[0] = joueur1
        joueurs[1] = joueur2
    }

    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE) {
            return false
        }
        if (plateau.getCases()[coordOriginX][coordOriginY].estLibre()) {
            return false
        }

        return true
    }

    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int, coordDestinationX : Int, coordDestinationY : Int, joueur : Joueur?) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE) {
            return false
        }
        if (plateau.getCases()[coordOriginX][coordOriginY].estLibre()) {
            return false
        }
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
        if (nombreCoupsSansPrise == nombreCoupsSansPriseMax && joueurs[0] != null && joueurs[1] != null) {
            if (joueurs[0]!!.calculerScore() > joueurs[1]!!.calculerScore()) {
                return joueurs[0]
            } else if (joueurs[0]!!.calculerScore() < joueurs[1]!!.calculerScore()) {
                return joueurs[1]
            }
        }
        return null
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