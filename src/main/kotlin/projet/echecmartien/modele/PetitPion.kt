package projet.echecmartien.modele



class PetitPion: Pion() {
    /**
     * donne le score d'un petit pion
     */
    override fun getScore(): Int = 1

    /**
     * donne le déplacement du petit pion sous forme de liste sans les coordonnées d'orignines
     */
    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 1)
            throw DeplacementException("Le déplacement des pions moyens est de 1 cases maximum")

        if (!deplacement.estDiagonal())
            throw DeplacementException("Le déplacement n'est pas possible")

        return deplacement.getCheminDiagonal()
    }

}

