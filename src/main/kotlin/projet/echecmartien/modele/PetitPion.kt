package projet.echecmartien.modele

class PetitPion: Pion() {

    override fun getScore(): Int = 1

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        TODO("Not yet implemented")
    }

}