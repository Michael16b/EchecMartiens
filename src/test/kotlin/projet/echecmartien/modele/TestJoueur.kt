package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

internal class TestJoueur{

    private var joueur1: Joueur = Joueur("toto")
    private val nbPetit = 7
    private val nbMoyen = 2
    private val nbGrand = 3
    private val nbTotal = nbPetit + nbMoyen + nbGrand

    /**
     * le joueur doit être réinitialisé avant chaque test
     */
    @BeforeEach
    fun setUp() {
        joueur1 = Joueur("toto")
    }

    /**
     * teste si getPseudo renvoie bien le bon nom du joueur
     */
    @Test
    fun testNom() {
        assertEquals("toto", joueur1.getPseudo(), "le nom du joueur devrait être toto")
    }

    /**
     * teste si le nombre de pions capturés est bien 0 pour un joueur nouvellement créé
     */
    @Test
    fun testNbPionsCapturesNul() {
        assertEquals(0, joueur1.getNbPionsCaptures(), "le nombre de pions capturés du joueur devrait être 0")
    }

    /**
     * teste si le nombre de pions capturés est bien nbGrand lorsqu'on lui ajoute des pions capturés
     */
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

    /**
     * teste si la taille de la liste des pions capturés est bien 0 pour un joueur nouvellement créé
     */
    @Test
    fun testPionsCapturesNuls() {
        assertEquals(0, joueur1.getPionsCaptures().size, "Le nombre de pions capturés du joueur devrait être 0")
    }

    /**
     * teste si la taille de la liste des pions capturés est bien égale au nombre de pions ajoutés
     * et que cette liste possède bien tous les pions qu'on fait capturer à un joueur
     */
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

    @Test
    fun testCalculScoreJoueur() {
        for (i in 0 until nbPetit) {
            val p = PetitPion()
            joueur1.ajouterPionCaptures(p)
        }
        for (i in 0 until nbMoyen) {
            val p = MoyenPion()
            joueur1.ajouterPionCaptures(p)
        }
        for (i in 0 until nbGrand) {
            val p = GrandPion()
            joueur1.ajouterPionCaptures(p)
        }
        val scoreAttendu = nbPetit+nbMoyen*2+nbGrand*3
        assertEquals(scoreAttendu,joueur1.calculerScore())

    }

}