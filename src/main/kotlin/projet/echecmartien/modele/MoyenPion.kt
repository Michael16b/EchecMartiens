package projet.echecmartien.modele

class MoyenPion: GrandPion() {

    override fun getScore(): Int = 2

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 2)
            throw DeplacementException("Le déplacement des pions moyens est de 2 cases maximum")
        return super.getDeplacement(deplacement)
    }

}