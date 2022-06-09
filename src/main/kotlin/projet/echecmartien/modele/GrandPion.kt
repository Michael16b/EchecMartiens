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
        return when {
            deplacement.estHorizontal() -> deplacement.getCheminHorizontal()
            deplacement.estVertical() -> deplacement.getCheminVertical()
            deplacement.estDiagonal() -> deplacement.getCheminDiagonal()
            else -> throw DeplacementException("Le déplacement n'est pas possible")
        }
    }

}