package projet.echecmartien.modele

class MoyenPion: GrandPion() {

    override fun getScore(): Int = 2

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        TODO("Not yet implemented")
    }

}