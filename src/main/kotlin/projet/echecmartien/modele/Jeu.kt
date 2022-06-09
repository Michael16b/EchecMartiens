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
    private var pionArriveDeZone: Pion?

    init {
        nombreCoupsSansPrise = 0
        nombreCoupsSansPriseMax = 0
        coordOrigine = null
        coordDest = null
        joueurCourant = null
        pionArriveDeZone = null
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
        initialiserJoueur(joueur1, joueur2)
        this.nombreCoupsSansPriseMax = nombreCoupsSansPriseMax
        nombreCoupsSansPrise = 0
        plateau.initialiser()
    }

    /**
     * affectation des joueurs aux cases
     * @param joueur1 premier joueur
     * @param joueur2 second joueur
     */
    private fun initialiserJoueur(joueur1: Joueur, joueur2: Joueur) {
        joueurs[0] = joueur1
        joueurs[1] = joueur2
        joueurCourant = joueur1

        // Joueur 1, partie "haute" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE/2) {
                plateau.getCases()[i][j].setJoueur(joueur1)
            }
        }

        // Joueur 2, partie "basse" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in TAILLEVERTICALE/2 until TAILLEVERTICALE) {
                plateau.getCases()[i][j].setJoueur(joueur2)
            }
        }
    }

    /**
     * indique si oui ou non on peut bouger un certain pion en fonction de là où il est placé
     * @param coordOriginX la coordonnée d'origine en X à partir de laquelle on veut faire un déplacement
     * @param coordOriginY la coordonnée d'origine en Y à partir de laquelle on veut faire un déplacement
     * @return true si au moins un déplacement est possible pour le pion, false sinon
     */
    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE)
            return false

        val cases = plateau.getCases()
        val caseOrigine = cases[coordOriginX][coordOriginY]
        if (caseOrigine.estLibre())
            return false

        for (i in coordOriginX-1 until coordOriginX+2) {
            for (j in coordOriginY-1 until coordOriginY+2) {
                if (i == coordOriginX && j == coordOriginY)
                    continue
                if (deplacementPossible(coordOriginX, coordOriginY, i, j, caseOrigine.getJoueur()))
                    return true

            }
        }
        return false
    }

    /**
     * indique si oui ou non il est possible de bouger un certain pion en fonction de là où il est placé et d'où l'on souhaite le déplacer
     * @param coordOriginX la coordonnée d'origine en X à partir de laquelle on veut faire un déplacement
     * @param coordOriginY la coordonnée d'origine en Y à partir de laquelle on veut faire un déplacement
     * @param coordDestinationX la coordonnée de destination en X où l'on souhaite déplacer le pion
     * @param coordDestinationY la coordonnée de destination en Y où l'on souhaite déplacer le pion
     * @return true si le déplacement voulu est possible pour le pion, false sinon
     */
    fun deplacementPossible(coordOriginX : Int, coordOriginY : Int, coordDestinationX : Int, coordDestinationY : Int, joueur : Joueur?) : Boolean {
        if (coordOriginX !in 0 until TAILLEHORIZONTALE || coordOriginY !in 0 until TAILLEVERTICALE)
            return false

        if (coordDestinationX !in 0 until TAILLEHORIZONTALE || coordDestinationY !in 0 until TAILLEVERTICALE)
            return false

        if (joueur !in joueurs)
            return false

        val cases = plateau.getCases()
        val caseOrigine = cases[coordOriginX][coordOriginY]
        if (caseOrigine.estLibre())
            return false

        val deplacement: Deplacement
        // on teste si le déplacement est valide, sinon le déplacement n'est pas possible
        try {
            deplacement = Deplacement(Coordonnee(coordOriginX, coordOriginY), Coordonnee(coordDestinationX, coordDestinationY))
        } catch (_: DeplacementException) {
            return false
        }

        val coords: List<Coordonnee>
        /**
         * on teste si le déplacement est valide pour le pion dans la case d'origine, sinon
         * le déplacement n'est pas possible
         */
        try {
            coords = caseOrigine.getPion()!!.getDeplacement(deplacement)
        } catch (_: DeplacementException) {
            return false
        }

        // si le pion vient d'arriver dans la zone, il ne peut pas sortir de la zone directement après
        if (pionArriveDeZone == caseOrigine.getPion() && cases[coordDestinationX][coordDestinationY].getJoueur() != joueur)
            return false

        // si la dernière case contient un pion non adverse, le déplacement n'est pas possible
        val lastCoord = coords.last()
        if (!cases[lastCoord.getX()][lastCoord.getY()].estLibre() &&
            cases[lastCoord.getX()][lastCoord.getY()].getJoueur() == joueur)
            return false

        /**
         * on teste s'il y a des pions sur le chemin (sans compter la dernière case)
         * un pion ne peut pas enjamber un autre
          */

        for (i in 0 until coords.size-1) {
            if (!cases[coords[i].getX()][coords[i].getY()].estLibre())
                return false
        }

        return true

    }

    /**
     * déplace le pion placé sur les coordonnées d'origine vers les coordonnées de destination
     * @param coordOriginX la coordonnée d'origine en X à partir de laquelle on veut faire un déplacement
     * @param coordOriginY la coordonnée d'origine en Y à partir de laquelle on veut faire un déplacement
     * @param coordDestinationX la coordonnée de destination en X où l'on souhaite déplacer le pion
     * @param coordDestinationY la coordonnée de destination en Y où l'on souhaite déplacer le pion
     */
    fun deplacer(coordOriginX: Int, coordOriginY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        // le pionArriveDeZone dure qu'un seul tour
        pionArriveDeZone = null

        val cases = plateau.getCases()
        val caseDestination = cases[coordDestinationX][coordDestinationY]
        val caseOrigine = cases[coordOriginX][coordOriginY]

        if (!caseDestination.estLibre())
            joueurCourant?.ajouterPionCaptures(caseDestination.getPion()!!)
        else
            nombreCoupsSansPrise++

        // si le pion change de zone il devient le dernier pion arrivé de zone
        if (caseDestination.getJoueur() != joueurCourant) {
            pionArriveDeZone = caseOrigine.getPion()
        }

        caseDestination.setPion(caseOrigine.getPion())
        caseOrigine.setPion(null)
    }

    /**
     * indique le joueur qui a gagné de la partie
     * @return le joueur gagnant
     */
    fun joueurVainqueur(): Joueur? {
        if (null in joueurs)
            return null

        if (nombreCoupsSansPrise >=  nombreCoupsSansPriseMax)
            return joueurMeilleurScore()

        if (joueurs[0]!!.getNbPionsCaptures() + joueurs[1]!!.getNbPionsCaptures() >= 17)
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

    /**
     * retourne le plateau du jeu
     * @return le plateau du jeu
     */
    fun getPLateau() : Plateau = this.plateau

}