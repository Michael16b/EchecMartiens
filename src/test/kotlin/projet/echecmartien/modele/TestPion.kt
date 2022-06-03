package projet.echecmartien.modele


import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


import java.util.stream.Stream

internal class TestPion {

    //Petits pions
    var petitpion = PetitPion()
    val moyenpion = MoyenPion()



    @Test
    fun testGetScorePetitPion0() {
        assertEquals(1, petitpion.getScore(), "Le petit pion vaut 1")
    }

    @Test
    fun testDeplacementPetitPionException(){
        val deplacement = Deplacement(Coordonnee(0,0),Coordonnee(2,2))
        assertThrows<DeplacementException>("Une exception devrait être levée"){
            petitpion.getDeplacement(deplacement)
        }
    }

    @Test
    fun testDeplacementPetitPionException1(){
        val deplacement = Deplacement(Coordonnee(0,0),Coordonnee(0,1))
        assertThrows<DeplacementException>("Une exception devrait être levée"){
            petitpion.getDeplacement(deplacement)
        }
    }



    @ParameterizedTest
    @MethodSource("listCoordProviderPetitPions")
    fun testDeplacementPetitPions(oracle: List<Coordonnee>, deplacement: Deplacement) {
        val coords = petitpion.getDeplacement(deplacement)
        if (!equalsListCoords(oracle, coords))
            fail("$coords devrait être $oracle")
    }

    @ParameterizedTest
    @MethodSource("listCoordProviderMoyenPions")
    fun testDeplacementMoyenPions(oracle: List<Coordonnee>, deplacement: Deplacement) {
        val coords = moyenpion.getDeplacement(deplacement)
        if (!equalsListCoords(oracle, coords))
            fail("$coords devrait être $oracle")
    }

    fun equalsListCoords(l1: List<Coordonnee>, l2: List<Coordonnee>) : Boolean {
        if (l1.size != l2.size)
            return false

        for (i in l1.indices) {
            if (l1[i].getX() != l2[i].getX() ||
                    l1[i].getY() != l2[i].getY()) {
                return false
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun listCoordProviderPetitPions(): Stream<Arguments?>? {
            val oracle1 = arrayListOf<Coordonnee>(Coordonnee(1,0))
            val oracle2 = arrayListOf<Coordonnee>(Coordonnee(2,2))
            val oracle3 = arrayListOf<Coordonnee>(Coordonnee(2, 1))
            val oracle4 = arrayListOf<Coordonnee>(Coordonnee(2, 2))

            val deplacement1 = Deplacement(Coordonnee(2,1), Coordonnee(1,0))
            val deplacement2 = Deplacement(Coordonnee(3, 1), Coordonnee(2,2))
            val deplacement3 = Deplacement(Coordonnee(1,0), Coordonnee(2,1))
            val deplacement4 = Deplacement(Coordonnee(1, 3), Coordonnee(2,2))

            return Stream.of(
                Arguments.of(oracle1, deplacement1),
                Arguments.of(oracle2, deplacement2),
                Arguments.of(oracle3, deplacement3),
                Arguments.of(oracle4, deplacement4),
            )
        }

        @JvmStatic
        fun listCoordProviderMoyenPions(): Stream<Arguments?>? {
            val oracle5 = arrayListOf<Coordonnee>(Coordonnee(0,1),Coordonnee(0,2))
            val oracle6 = arrayListOf<Coordonnee>(Coordonnee(1,0),Coordonnee(2,0))
            val oracle7 = arrayListOf<Coordonnee>(Coordonnee(1,0),Coordonnee(0,0))
            val oracle8 = arrayListOf<Coordonnee>(Coordonnee(0,1),Coordonnee(0,0))
            val oracle9 = arrayListOf<Coordonnee>(Coordonnee(1,1), Coordonnee(2,2))
            val oracle10 = arrayListOf<Coordonnee>(Coordonnee(1,1),Coordonnee(0,0))

            val deplacement5 = Deplacement(Coordonnee(0,0), Coordonnee(0,2))
            val deplacement6 = Deplacement(Coordonnee(0,0), Coordonnee(2,0))
            val deplacement7 = Deplacement(Coordonnee(2,0), Coordonnee(0,0))
            val deplacement8 = Deplacement(Coordonnee(0,2), Coordonnee(0,0))
            val deplacement9 = Deplacement(Coordonnee(0,0), Coordonnee(2,2))
            val deplacement10 = Deplacement(Coordonnee(2,2), Coordonnee(0,0))

            return Stream.of(
                Arguments.of(oracle5,deplacement5),
                Arguments.of(oracle6,deplacement6),
                Arguments.of(oracle7,deplacement7),
                Arguments.of(oracle8,deplacement8),
                Arguments.of(oracle9,deplacement9),
                Arguments.of(oracle10,deplacement10),
            )
        }
    }

    //Moyens pions


    @Test
    fun testDeplacementMoyenPionException(){
        val deplacement = Deplacement(Coordonnee(0,0),Coordonnee(3,3))
        assertThrows<DeplacementException>("Une exception devrait être levée"){
            petitpion.getDeplacement(deplacement)
        }
    }
}