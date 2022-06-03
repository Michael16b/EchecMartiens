package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TestCoordonnee {

    val c = Coordonnee(0, 2)

    @Test
    fun testGetX(){
        assertEquals(0,  c.getX(), "")
    }

    @Test
    fun testGetY(){
        assertEquals(2,  c.getX(), "")
    }
}