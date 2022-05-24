package projet.echecmartien.modele

class Coordonnee(x: Int, y: Int) {

    private var x: Int
    private var y: Int

    init {
        this.x = x
        this.y = y
    }

    /**
     *@return la coordonnée en x
     */
    fun getX(): Int = x

    /**
     *@return la coordonnée en y
     */
    fun getY(): Int = y


    override fun toString():String = "$x $y"




}