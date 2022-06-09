package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE

internal class TestJeu {

    private var jeu = Jeu()
    private var plateau = Plateau()
    private val j1 = Joueur("Matthis")
    private val j2 = Joueur("Julien")

    @BeforeEach
    fun setUp() {
        jeu = Jeu()
        plateau = Plateau()
    }

    @Test
    fun testChangementJoueurCourant() {

        jeu.initialiserPartie(j1, j2, 10)

        assertEquals(j1, jeu.getJoueurCourant())
        jeu.changeJoueurCourant()
        assertEquals(j2, jeu.getJoueurCourant())
        jeu.changeJoueurCourant()
        assertEquals(j1, jeu.getJoueurCourant())
    }

    @Test
    fun testDeplacementOrigine() {
        val origine = Coordonnee(3, 2)
        jeu.setCoordOrigineDeplacement(origine)
        assertEquals(origine, jeu.getCoordOrigineDeplacement())
    }

    @Test
    fun testDeplacementDestination() {
        val destination = Coordonnee(7, 3)
        jeu.setCoordDestinationDeplacement(destination)
        assertEquals(destination, jeu.getCoordDestinationDeplacement())
    }

    @Test
    fun testInitialiserJoueurPlateau() {
        jeu.initialiserPartie(j1, j2, 10)

        val cases = jeu.getPLateau().getCases()

        // Joueur 1, partie "haute" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE /2) {
                assertEquals(j1, cases[i][j].getJoueur())
            }
        }

        // Joueur 2, partie "basse" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in TAILLEVERTICALE /2 until TAILLEVERTICALE) {
                assertEquals(j2, cases[i][j].getJoueur())
            }
        }
    }

    fun viderPlateau() {
        plateau.initialiser()
        plateau.getCases().forEach { liste ->
            liste.forEach { case ->
                case.setPion(null)
            }
        }
    }
}