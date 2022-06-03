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

    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true

        if (other !is Coordonnee)
            return false

        return getX() == other.getX() && getY() == other.getY()
    }

}