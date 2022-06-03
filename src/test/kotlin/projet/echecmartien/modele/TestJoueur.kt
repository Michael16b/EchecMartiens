package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class TestJoueur{

    private var joueur1: Joueur = Joueur("toto")
    private val nbPetit = 7
    private val nbMoyen = 2
    private val nbGrand = 3
    private val nbTotal = nbPetit + nbMoyen + nbGrand

    @BeforeEach
    fun setUp() {
        joueur1 = Joueur("toto")
    }

    @Test
    fun testNom() {
        assertEquals("toto", joueur1.getPseudo(), "le nom du joueur devrait être toto")
    }

    @Test
    fun testNbPionsCapturesNul() {
        assertEquals(0, joueur1.getNbPionsCaptures(), "le nombre de pions capturés du joueur devrait être 0")
    }

    @Test
    fun testNbPionsCapturesNonNul() {

        for (i in 0 until nbPetit) {
            joueur1.ajouterPionCaptures(PetitPion())
        }
        for (i in 0 until nbMoyen) {
            joueur1.ajouterPionCaptures(MoyenPion())
        }
        for (i in 0 until nbGrand) {
            joueur1.ajouterPionCaptures(GrandPion())
        }
        assertEquals(nbTotal, joueur1.getNbPionsCaptures(), "le nombre de pions capturés du joueur devrait être $nbTotal")
    }

    @Test
    fun testPionsCapturesNuls() {
        assertEquals(0, joueur1.getPionsCaptures().size, "Le nombre de pions capturés du joueur devrait être 0")
    }

    @Test
    fun testPionsCapturesNonNuls() {
        val mesPions = mutableSetOf<Pion>()
        for (i in 0 until nbPetit) {
            val p = PetitPion()
            joueur1.ajouterPionCaptures(p)
            mesPions.add(p)
        }
        for (i in 0 until nbMoyen) {
            val p = MoyenPion()
            joueur1.ajouterPionCaptures(p)
            mesPions.add(p)
        }
        for (i in 0 until nbGrand) {
            val p = GrandPion()
            joueur1.ajouterPionCaptures(p)
            mesPions.add(p)
        }
        val joueurPions = joueur1.getPionsCaptures()
        assertTrue(mesPions.size == joueurPions.size, "le nombre de pions capturés par le joueur devrait être ${mesPions.size}")

        for (pion in mesPions) {
            if (pion !in joueurPions) {
                fail("Les pions capturés par le joueur devraient être $mesPions")
            }
        }

    }

}