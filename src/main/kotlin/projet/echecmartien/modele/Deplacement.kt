package projet.echecmartien.modele

import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import java.lang.Exception
import kotlin.math.abs


/**
 * cette classe permet de tester les déplacements sur le plateau de jeu
 *
 */

class Deplacement(origine: Coordonnee, destination: Coordonnee) {

    private val origine: Coordonnee
    private val destination: Coordonnee

    init {
        this.origine = origine
        this.destination = destination

        if (!estVertical() && !estHorizontal() && !estDiagonal())
            throw DeplacementException("Le déplacement de $origine à $destination n'est ni vertical, ni horizontal, ni diagonal")

        // les coordonnées d'origine et de destination ne doivent pas être en dehors du plateau de jeu
        require(origine.getX() in 0 until TAILLEHORIZONTALE && origine.getY() in 0 until TAILLEVERTICALE)
        require(destination.getX() in 0 until TAILLEHORIZONTALE && destination.getY() in 0 until TAILLEVERTICALE)

        // l'origine et la destination ne peut pas être situés au même endroit
        require(origine.toString() != destination.toString())
    }

    /**
     * dans le constructeur la validité du déplacement dans la grille est testée
     *@throws DeplacementException si le déplacement n'est ni horizontal, ni vertical est ni diagonal
     * les autres cas lèvent une IllegalArgumentException (peut être mis en place avec "require")
     */


    /**
     * getter
     * @return la destination de ce déplacement
     */
    fun getDestination():Coordonnee = this.destination


    /**
     * getter
     * @return l'origine de ce déplacement
     */
    fun getOrigine():Coordonnee = this.origine

    /**
     *méthode qui permet de tester si le déplacement est horizontal
     * @return true si le déplacement est horizontal, false sinon
     */
    fun estHorizontal() : Boolean {
        return origine.getY() == destination.getY()
    }

    /**
     *méthode qui permet de tester si le déplacement est vertical
     * @return true si le déplacement est vertical, false sinon
     */
    fun estVertical(): Boolean {
       return origine.getX() == destination.getX()
    }

    private fun distanceX(): Int {
        return abs(origine.getX() - destination.getX())
    }

    private fun distanceY(): Int {
        return abs(origine.getY() - destination.getY())
    }

    /**
     * méthode qui permet de tester si le déplacement est diagonal
     * @return true si le déplacement est diagonal, false sinon
     */
    fun estDiagonal():Boolean {
        return distanceX() == distanceY()
    }

    /**
     *méthode qui permet de calculer le nombre de case d'un déplacement
     * @return le nombre de case que le pion sera déplacée
     */
    fun longueur(): Int {
        val distance = distanceX() + distanceY()
        return if (estDiagonal()) {
            distance / 2
        } else {
            distance
        }
    }


    /**
     * méthode qui permet de déterminer le sens d'un déplacement vertical
     *
     *@return true si le déplacement est positif, false sinon
     */
    fun estVerticalPositif():Boolean{
        return destination.getY() > origine.getY()
    }

    /**
     * méthode qui permet de déterminer le sens d'un déplacement horizontal
     *
     * @return true si le déplacement est positif, false sinon
     */
    fun estHorizontalPositif():Boolean{
        return destination.getX() > origine.getX()
    }

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et en Y
     *
     * @return true si le déplacement est positif en X et Y, false sinon
     */
    fun estDiagonalPositifXPositifY(): Boolean{
        return estVerticalPositif() && estHorizontalPositif()
    }
    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et positif en Y
     *
     * @return true si le déplacement est négatif en X et positif en Y, false sinon
     */
    fun estDiagonalNegatifXPositifY(): Boolean{
       return estVerticalPositif() && !estHorizontalPositif()
    }

    /**
     *
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est positif en X et négatif en Y
     *
     * @return true si le déplacement est positif en X et négatif en Y, false sinon
     */
    fun estDiagonalPositifXNegatifY(): Boolean{
       return !estVerticalPositif() && estHorizontalPositif()
    }

    /**
     * méthode qui permet de déterminer si le sens d'un déplacement diagonal est négatif en X et négatif en Y
     *
     * @return true si le déplacement est négatif en X et négatif en Y, false sinon
     */
    fun estDiagonalNegatifXNegatifY(): Boolean{
        return !estVerticalPositif() && !estHorizontalPositif()
    }

    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est vertical.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est vertical. Le point de départ n'est pas stocké dans la liste.
     * @throws DeplacementException est levée si le déplacement n'est pas vertical
     */
    fun getCheminVertical(): List<Coordonnee> {
        if (!estVertical())
           throw DeplacementException("déplacement non verical")

        val coords = mutableListOf<Coordonnee>()
        return coords
    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplaceme{"origine Y dépasse"}nt demandé est horizontal.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée.
     * Le point de départ n'est pas stocké dans la liste.
     * si le déplacement est horizontal
     * @throws DeplacementException est levée si le déplacement n'est pas horizontal
     */
    fun getCheminHorizontal(): List<Coordonnee> {

        if (!estHorizontal())
            throw DeplacementException("déplacement non horizontal")

        val coords = mutableListOf<Coordonnee>()
        val y = origine.getY()

        // le déplacement est positif horizontalement
        if (estHorizontalPositif()) {
            for (i in origine.getX()+1..destination.getX()) {
                coords.add(Coordonnee(i, y))
            }
            return coords
        }
        // le déplacement est négatif horizontalement
        for (i in origine.getX()-1 downTo destination.getX()) {
            coords.add(Coordonnee(i, y))
        }
        return coords

    }


    /**
     * donne le chemin de coordonnées que constitue le déplacement
     * du point de départ vers le point d'arrivée si le déplacement demandé est diagonal.
     * Le point de départ n'est pas stocké dans la liste.
     *
     * @return une liste de coordonnées qui constitue le déplacement du point de départ vers le point d'arrivée
     * si le déplacement est diagonal
     * @throws DeplacementException est levée si le déplacement n'est pas diagonal
     */
    fun getCheminDiagonal(): List<Coordonnee> {
        if (!estDiagonal())
            throw DeplacementException("déplacement non diagonal")

        TODO()
    }


}
