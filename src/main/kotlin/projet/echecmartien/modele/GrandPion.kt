package projet.echecmartien.modele

open class GrandPion: Pion() {
    /**
     * donne le score d'un pion moyen
     */
    override fun getScore(): Int = 3
    /**
     * donne le déplacement d'un grand pion sous forme de liste de cordonnées, sans les coordonnées d'orignines
     */
    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.estHorizontal())
            return deplacement.getCheminHorizontal()

        if (deplacement.estVertical())
            return deplacement.getCheminVertical()

        return deplacement.getCheminDiagonal()
    }

}