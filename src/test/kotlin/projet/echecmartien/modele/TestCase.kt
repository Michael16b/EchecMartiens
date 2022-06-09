package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class TestCase {

    private var c = Case()
    private val p = PetitPion()
    private val p2 = GrandPion()
    private val j1 = Joueur("Matthis")
    private val j2 = Joueur("Louis")

    /**
     * Avant chaque test on souhaite réinitialiser la case à zéro
     */
    @BeforeEach
    fun setUp() {
        c = Case()
    }

    /**
     * teste si une case nouvellement créée est bien libre
     */
    @Test
    fun testCaseLibre1() {
        assertEquals(true, c.estLibre(), "la case devrait être libre")
    }

    /**
     * teste si une case est bien libre lorsque qu'un pion est retiré de celle-ci
     */
    @Test
    fun testCaseLibre2() {
        c.setPion(p)
        c.setPion(null)
        assertEquals(true, c.estLibre(), "la case devrait être libre")
    }

    /**
     * teste si une case est bien occupée lorsqu'on met un pion
     */
    @Test
    fun testCaseOccupee1() {
        c.setPion(p)
        assertEquals(false, c.estLibre(), "la case ne devrait pas être libre")
    }

    /**
     * teste si une case est bien occupée lorsqu'on change de pion
     */
    @Test
    fun testCaseOccupee2() {
        c.setPion(p)
        c.setPion(null)
        c.setPion(p2)
        assertEquals(false, c.estLibre(), "la case ne devrait pas être libre")
    }

    /**
     * teste si getPion renvoie le bon pion (ou null) au départ, avec un petit Pion et un grand Pion
     */
    @Test
    fun testCasePion() {
        assertEquals(null, c.getPion(), "le pion de la case devrait être null")
        c.setPion(p)
        assertEquals(p, c.getPion(), "le pion de la case devrait être $p")
        c.setPion(p2)
        assertEquals(p2, c.getPion(), "le pion de la case devrait être $p2")
    }

    /**
     * teste si la case possède bien aucun joueur au début
     */
    @Test
    fun testCaseJoueurNul() {
        assertEquals(null, c.getJoueur())
    }

    /**
     * teste si getJoueur renvoie bien le joueur de la case
     */
    @Test
    fun testCaseJoueurNonNul() {
        c.setJoueur(j1)
        assertEquals(j1, c.getJoueur(), "le joueur de la case devrait être $j1")
    }

    /**
     * teste si getJoueur renvoie bien le joueur de la case lorsqu'il a été changé plus d'une fois
     */
    @Test
    fun testCaseJoueurChangement() {
        c.setJoueur(j1)
        c.setJoueur(j2)
        assertEquals(j2, c.getJoueur(), "le joueur de la case devrait être $j2")
    }

}