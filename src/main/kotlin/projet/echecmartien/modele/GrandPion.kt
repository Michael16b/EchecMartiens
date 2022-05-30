package projet.echecmartien.modele

open class GrandPion: Pion() {

    override fun getScore(): Int = 3

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        return when {
            deplacement.estHorizontal() -> deplacement.getCheminHorizontal()
            deplacement.estVertical() -> deplacement.getCheminVertical()
            deplacement.estDiagonal() -> deplacement.getCheminDiagonal()
            else -> throw DeplacementException("Le déplacement n'est pas possible")
        }
    }

}