package projet.echecmartien.modele

class MoyenPion: GrandPion() {
    /**
     * donne le score d'un pion moyen
     */
    override fun getScore(): Int = 2
    /**
     * donne le déplacement du pion moyen sous forme de liste de cordonnées, sans les coordonnées d'orignines
     */
    override fun getDeplacement(deplacement: Deplacement): List<Coordonnee> {
        if (deplacement.longueur() > 2)
            throw DeplacementException("Le déplacement des pions moyens est de 2 cases maximum")
        return super.getDeplacement(deplacement)
    }

}