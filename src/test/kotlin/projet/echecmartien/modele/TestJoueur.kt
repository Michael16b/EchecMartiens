package projet.echecmartien.modele

import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.junit.platform.commons.util.CollectionUtils
import projet.echecmartien.modele.Joueur

class TestJoueur{

    private var joueur1: Joueur = Joueur("toto")
    private val nbPetit = 7
    private val nbMoyen = 2
    private val nbGrand = 3
    private val nbTotal = nbPetit + nbMoyen + nbGrand

    @Before
    fun setup() {
        joueur1 = Joueur("toto")
    }

    @Test
    fun testJoueur1() {
        assertEquals("toto", joueur1.getPseudo(), "le nom du joueur devrait être toto")
    }

    @Test
    fun testJoueur2() {
        assertEquals(0, joueur1.getNbPionsCaptures(), "le nombre de pions capturés du joueur devrait être 0")
    }

    @Test
    fun testJoueur3() {

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
    fun testJoueur4() {
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