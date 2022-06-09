package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.*
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

    /**
     * teste si le déplacement est possible pour un petit au départ, lorsqu'il change de zone, lorsqu'il n'est plus le
     * pion arrivé de zone, lorsqu'il peut uniquement manger un pion adverse et qu'il n'a pas de déplacements possibles
     */
    @Test
    fun testDeplacementPossiblePetitPion() {
        jeu.initialiserPartie(j1, j2, 10)

        assertTrue(jeu.deplacementPossible(2, 2), "le petit pion en 2, 2 devrait pouvoir se déplacer")

        jeu.deplacer(2, 2, 0, 4)

        assertFalse(jeu.deplacementPossible(0, 4), "le petit pion en 0, 4 ne devrait pas pouvoir se déplacer (pion arrivé de zone)")

        jeu.deplacer(3, 5, 3, 3)

        assertTrue(jeu.deplacementPossible(0, 4),  "le petit pion en 0, 4 devrait pouvoir se déplacer (pion n'est plus celui arrivé de zone")

        jeu.deplacer(0, 2, 1, 3)

        assertTrue(jeu.deplacementPossible(0, 4), "le petit pion en 0, 4 devrait pouvoir se déplacer (en mangeant un pion adverse)")

        jeu.initialiserPartie(j1, j2, 10)
        jeu.getPLateau().getCases()[3][0].setPion(PetitPion())
        jeu.getPLateau().getCases()[3][2].setPion(PetitPion())

        assertFalse(jeu.deplacementPossible(2, 1))
    }

    /**
     * teste le déplacement possible d'un grand pion
     */
    @Test
    fun testDeplacementPossibleGrandPion() {
        jeu.initialiserPartie(j1, j2, 10)
        assertFalse(jeu.deplacementPossible(0, 1), "le grand pion en 0, 1 ne devrait pas pouvoir se déplacer")

        jeu.deplacer(0, 2, 1, 3)
        assertTrue(jeu.deplacementPossible(0, 1), "le grand pion en 0, 1 devrait pouvoir se déplacer")
    }


}