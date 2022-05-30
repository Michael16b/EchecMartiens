package projet.echecmartien.modele

class PetitPion: Pion() {

    override fun getScore(): Int = 1

    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 1)
            throw DeplacementException("Le déplacement des pions moyens est de 1 cases maximum")

        if (!deplacement.estDiagonal())
            throw DeplacementException("Le déplacement n'est pas possible")

        return deplacement.getCheminDiagonal()
    }

}

