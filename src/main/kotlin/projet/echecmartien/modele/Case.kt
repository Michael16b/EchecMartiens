package projet.echecmartien.modele



class Case {
    private var joueur: Joueur?
    private var pion: Pion?

    init {
        this.joueur = null
        this.pion = null
    }

    /**
     * teste si une case contient un pion ou non
     * @return true si la case ne contient pas un pion, false sinon.
     */
    fun estLibre(): Boolean {
       return pion == null
    }

    /** getter
     * @return le joueur associé à la case
     */
    fun getJoueur():Joueur? = this.joueur

    /** setter
     * @param joueur qui est associé à la case
     */
    fun setJoueur(joueur: Joueur?) {
        this.joueur = joueur
    }

    /** getter
     * @return le pion associé à la case
     */
    fun getPion():Pion? = this.pion

    /** setter
     * @param pion qui est associé à la case
     */
    fun setPion(pion: Pion?) {
       this.pion = pion
    }

}
