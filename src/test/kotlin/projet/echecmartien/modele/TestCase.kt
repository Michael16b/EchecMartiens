package projet.echecmartien.modele

import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TestCase {

    private var c = Case()
    val p = PetitPion()
    val p2 = GrandPion()
    val p3 = GrandPion()
    val j1 = Joueur("Matthis")
    val j2 = Joueur("Louis")

    @Before
    fun setup() {
        c = Case()
    }

    @Test
    fun testCaseLibre1() {
        assertEquals(true, c.estLibre(), "la case devrait être libre")
    }

    @Test
    fun testCaseLibre2() {
        c.setPion(p)
        c.setPion(null)
        assertEquals(true, c.estLibre(), "la case devrait être libre")
    }

    @Test
    fun testCaseOccupee1() {
        c.setPion(p)
        assertEquals(false, c.estLibre(), "la case ne devrait pas être libre")
    }

    @Test
    fun testCaseOccupee2() {
        c.setPion(p)
        c.setPion(null)
        c.setPion(p2)
        assertEquals(false, c.estLibre(), "la case ne devrait pas être libre")
    }

    @Test
    fun testCasePion() {
        assertEquals(null, c.getPion(), "le pion de la case devrait être null")
        c.setPion(p)
        assertEquals(p, c.getPion(), "le pion de la case devrait être $p")
        c.setPion(p2)
        assertEquals(p2, c.getPion(), "le pion de la case devrait être $p2")
    }

    @Test
    fun testCaseJoueurNul() {
        assertEquals(null, c.getJoueur())
    }

    @Test
    fun testCaseJoueurNonNul() {
        c.setJoueur(j1)
        assertEquals(j1, c.getJoueur(), "le joueur de la case devrait être $j1")
    }

    @Test
    fun testCaseJoueurChangement() {
        c.setJoueur(j1)
        c.setJoueur(j2)
        assertEquals(j2, c.getJoueur(), "le joueur de la case devrait être $j2")
    }


}