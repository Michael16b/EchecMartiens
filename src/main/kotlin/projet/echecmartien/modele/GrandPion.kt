package projet.echecmartien.modele

open class GrandPion: Pion() {

    override fun getScore(): Int = 3

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        TODO("Not yet implemented")
    }

}