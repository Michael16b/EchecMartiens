package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
    fun viderPlateau() {
        plateau.initialiser()
        plateau.getCases().forEach { liste ->
            liste.forEach { case ->
                case.setPion(null)
            }
        }
    }


}