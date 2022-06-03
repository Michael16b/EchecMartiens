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
     * @paral joueur2 second joueur
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

                val deplacement : Deplacement
                // on teste si le déplacement dans une direction est possible, sinon on passe au suivant
                try {
                    deplacement = Deplacement(Coordonnee(coordOriginX,coordOriginY),Coordonnee(i,j))
                } catch (_:IllegalArgumentException) {
                    continue
                }
                // on regarde si le pion peut faire ce déplacement, sinon on passe au déplacement suivant
                try {
                    caseOrigine.getPion()!!.getDeplacement(deplacement)
                } catch (_: DeplacementException) {
                    continue
                }

                // si le pion vient d'arriver dans la zone, il ne peut pas sortir de la zone directement après
                if (pionArriveDeZone == caseOrigine.getPion() && cases[i][j].getJoueur() != joueurCourant)
                    return false

                if (cases[i][j].estLibre())
                    return true

                // la case n'est pas libre, le pion peut bouger si le pion sur la case est un pion adverse
                if (cases[i][j].getJoueur() != joueurCourant)
                    return true
            }
        }
        return false
    }

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

        // on teste si la dernière case est libre ou le pion dessus peut être mangé
        val lastCoord = coords.last()
        if (!cases[lastCoord.getX()][lastCoord.getY()].estLibre())
            return false

        if (cases[lastCoord.getX()][lastCoord.getY()].getJoueur() == joueur)
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

    fun deplacer(coordOriginX: Int, coordOriginY: Int, coordDestinationX: Int, coordDestinationY: Int) {
        // le pionArriveDeZone dure qu'un seul tour
        pionArriveDeZone = null

        val cases = plateau.getCases()
        val caseDestination = cases[coordDestinationX][coordDestinationY]
        val caseOrigine = cases[coordOriginX][coordOriginY]

        if (!caseDestination.estLibre())
            joueurCourant?.ajouterPionCaptures(caseDestination.getPion()!!)

        // si le pion change de zone il devient le dernier pion arrivé de zone
        if (caseDestination.getJoueur() != joueurCourant)
            pionArriveDeZone = caseOrigine.getPion()

        caseDestination.setPion(caseOrigine.getPion())
        caseOrigine.setPion(null)
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