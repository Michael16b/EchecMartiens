package projet.echecmartien.modele

import com.google.gson.*
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter


class Jeu {

    private var nombreCoupsSansPrise: Int
    private var nombreCoupsSansPriseMax: Int
    private var coordOrigine : Coordonnee?
    private var coordDest : Coordonnee?
    private var joueurCourant : Joueur?
    private var joueurs : Array<Joueur?>
    private var plateau : Plateau
    private var pionArriveDeZone: Pion?
    private var coordPionArriveDeZone : Coordonnee?

    init {
        nombreCoupsSansPrise = 0
        nombreCoupsSansPriseMax = 0
        coordOrigine = null
        coordDest = null
        joueurCourant = null
        pionArriveDeZone = null
        coordPionArriveDeZone = null
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
            for (j in 0 until TAILLEVERTICALE) {
                val joueur = if(j in 0..3) joueur1 else joueur2
                plateau.getCases()[i][j].setJoueur(joueur)
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
        coordPionArriveDeZone = null

        val cases = plateau.getCases()
        val caseDestination = cases[coordDestinationX][coordDestinationY]
        val caseOrigine = cases[coordOriginX][coordOriginY]

        if (!caseDestination.estLibre()) {
            joueurCourant?.ajouterPionCaptures(caseDestination.getPion()!!)
            nombreCoupsSansPrise = 0
        } else
            nombreCoupsSansPrise++

        // si le pion change de zone il devient le dernier pion arrivé de zone
        if (caseDestination.getJoueur() != joueurCourant) {
            pionArriveDeZone = caseOrigine.getPion()
            coordPionArriveDeZone = Coordonnee(coordDestinationX, coordDestinationY)
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

        val scoreJ1 = joueurs[0]!!.calculerScore()
        val scoreJ2 = joueurs[1]!!.calculerScore()
        return when {
            scoreJ1>scoreJ2 -> joueurs[0]
            scoreJ1<scoreJ2 -> joueurs[1]
            else -> null
        }
    }

    /**
     * permet de savoir si la partie est finie ou non
     * @return true si la partie est finie, false sinon
     */
    fun arretPartie(): Boolean {
        if (nombreCoupsSansPrise >=  nombreCoupsSansPriseMax)
            return true

        if (joueurs[0]!!.getNbPionsCaptures() + joueurs[1]!!.getNbPionsCaptures() >= 17)
            return true

        return false
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

    /**
     * retourne le nombre de coups sans prise restant
     * @return entier positif ou nul, nombre de coups sans prise max - nombre coups sans prise
     */
    fun nbCoupsSansPriseRestants() : Int = this.nombreCoupsSansPriseMax - this.nombreCoupsSansPrise

    /**
     * fonction qui retourne le nombre de coups maximum sans prise
     * @return nombreCoupsSansPriseMax
     */
    fun nbCoupsSansPriseMax() : Int = this.nombreCoupsSansPriseMax

    /**
     * fonction qui retourne les joueurs du jeu
     * @return joueurs du jeu, array de Joueur? de taille 2
     */
    fun getJoueurs() = this.joueurs

    /**
     * Sérialise le jeu en fichier json
     * @param filepath: fichier du jeu sérialisé
     * @param présence d'une IA en joueur 2 ou non
     */
    fun serialiser(filepath: String, ia: Boolean): Boolean {
        val builder = Gson().newBuilder()
        builder.serializeNulls()
        builder.registerTypeAdapter(Pion::class.java, PionAdapter())
        builder.setPrettyPrinting()
        val gson = builder.create()
        val jeu = gson.toJsonTree(this).asJsonObject

        val pions = mutableListOf<String?>()
        plateau.getCases().forEach { row ->
            row.forEach { case ->
                val s = when (case.getPion()) {
                    is MoyenPion -> "MoyenPion"
                    is GrandPion -> "GrandPion"
                    is PetitPion -> "PetitPion"
                    else -> "null"
                }
                pions.add(s)
            }
        }
        jeu.remove("plateau")
        jeu.add("plateau", gson.toJsonTree(pions))
        jeu.remove("joueurCourant")
        jeu.addProperty("joueurCourant", this.joueurCourant!!.getPseudo())
        jeu.remove("pionArriveDeZone")
        jeu.add("pionArriveDeZone", gson.toJsonTree(coordPionArriveDeZone))
        jeu.addProperty("ia", ia)
        val writer: FileWriter
        try {
            writer = FileWriter(filepath)
        } catch (e: java.lang.Exception){
            println(e.printStackTrace())
            return false
        }
        try {
            gson.toJson(jeu, writer)
        } catch (_: java.lang.Exception) {
            return false
        } finally {
            writer.flush()
            writer.close()
        }
        return true
    }

    /**
     * fonction qui permet de redéfinir le dernier pion arrivé de zone
     * @param pion: Pion qui arrive de zone
     * @param coordonnee: coordonnée du pion qui arrive de zone
     */
    fun setPionArriveDeZone(pion: Pion?, coordonnee : Coordonnee?) {
        pionArriveDeZone = pion
        coordPionArriveDeZone = coordonnee
    }

    /**
     * fonction qui permet de redéfinir le nombre de coups sans prise
     * @param coups: entier supérieur ou égal à 0, nouveau nombre de coups sans prise
     */
    fun setNombreCoupsSansPrise(coups: Int) {
        require(coups >= 0)
        nombreCoupsSansPrise = coups
    }
}

fun deserialiser(filepath: String): Jeu? {
    val reader: FileReader
    try {
        reader = FileReader(filepath)
    } catch(_: FileNotFoundException) {
        return null
    }
    val gson = Gson().newBuilder()
    gson.serializeNulls()
    gson.registerTypeAdapter(Pion::class.java, PionAdapter())
    val data: JsonObject

    try {
        data = gson.create().fromJson(reader, JsonElement::class.java).asJsonObject
    } catch (_: JsonSyntaxException ) {
        reader.close()
        return null
    }

    val jeu = Jeu()
    try {
        // créer les joueurs
        val joueurs = data.get("joueurs").asJsonArray
        val j1 = Joueur(joueurs[0].asJsonObject.get("pseudo").asString)
        val pseudo2 = joueurs[1].asJsonObject.get("pseudo").asString
        val ia = data.get("ia").asBoolean
        val j2 = if (!ia) Joueur(pseudo2) else JoueurIA(pseudo2)

        // initialiser la partie
        jeu.initialiserPartie(j1, j2, data.get("nombreCoupsSansPriseMax").asInt)
        jeu.setNombreCoupsSansPrise(data.get("nombreCoupsSansPrise").asInt)

        // avoir le bon joueur courant
        val jcourant = data.get("joueurCourant").asString
        if (jeu.getJoueurCourant()!!.getPseudo() != jcourant)
            jeu.changeJoueurCourant()

        // associer chaque case au bon joueur et mettre le bon pion dedans
        val cases = jeu.getPLateau().getCases()
        val pions = data.get("plateau").asJsonArray
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE) {
                val joueur = if(j in 0..3) j1 else j2
                cases[i][j].setJoueur(joueur)
                val pdata = pions[i*8+j]
                val p = when(pdata.asString) {
                    "MoyenPion" -> MoyenPion()
                    "GrandPion" -> GrandPion()
                    "PetitPion" -> PetitPion()
                    else -> null
                }
                cases[i][j].setPion(p)
            }
        }

        // donner les pions capturés aux joueurs
        val joueursCapture = arrayOf(j1, j2)
        for (i in 0..1) {
            for (pion in joueurs[i].asJsonObject.get("pionsCaptures").asJsonArray) {
                val pion = when(pion.asJsonObject.get("TYPE").asString) {
                    "MoyenPion" -> MoyenPion()
                    "GrandPion" -> GrandPion()
                    "PetitPion" -> PetitPion()
                    else -> throw JsonParseException("Pion ne doit pas être null")
                }
                joueursCapture[i].ajouterPionCaptures(pion)
            }
        }

        // mettre le bon pion arrivé de zone
        val coordPionZone : Coordonnee? = gson.create().fromJson(data.get("pionArriveDeZone"), Coordonnee::class.java)
        if (coordPionZone != null) {
            val pion = jeu.getPLateau().getCases()[coordPionZone.getX()][coordPionZone.getY()].getPion()
            jeu.setPionArriveDeZone(pion, coordPionZone)
        }

    } catch (e: java.lang.Exception) {
        println(e.printStackTrace())
        return null
    } finally {
        reader.close()
    }

    return jeu
}