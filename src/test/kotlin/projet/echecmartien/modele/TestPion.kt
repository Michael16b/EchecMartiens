package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import java.util.stream.Stream

internal class TestPion {
    var petitpion = PetitPion()

    val deplacement1 = Deplacement(Coordonnee(1, 0), Coordonnee(3, 2))


    @Test
    fun testGetScorePetitPion0() {
        assertEquals(1, petitpion.getScore(), "Le petit pion vaut 1")
    }


    @ParameterizedTest
    @MethodSource("listCoordProviderPetitPions")
    fun testDeplacementPetitPions(oracle: List<Coordonnee?>?, deplacement: Deplacement, message: String) {
        assertEquals(oracle, petitpion.getDeplacement(deplacement), message)
    }

    companion object {
        @JvmStatic
        fun listCoordProviderPetitPions(): Stream<Arguments?>? {
            val oracle1 = listOf<Coordonnee>(Coordonnee(2, 1), Coordonnee(3, 2))
            val oracle2 = listOf<Coordonnee>(Coordonnee(2, 2), Coordonnee(1, 3))
            val oracle3 = listOf<Coordonnee>(Coordonnee(2, 1), Coordonnee(1, 0))
            val oracle4 = listOf<Coordonnee>(Coordonnee(2, 2), Coordonnee(1, 0))

            val deplacement1 = Deplacement(Coordonnee(1,0), Coordonnee(3, 2))
            val deplacement2 = Deplacement(Coordonnee(3, 1), Coordonnee(1, 3))
            val deplacement3 = Deplacement(Coordonnee(3, 2), Coordonnee(1, 0))
            val deplacement4 = Deplacement(Coordonnee(1, 3), Coordonnee(3, 1))

            return Stream.of(
                Arguments.of(oracle1, deplacement1, "$deplacement1" + ": $oracle1"),
                Arguments.of(oracle2, deplacement2, "$deplacement2" + ": $oracle2"),
                Arguments.of(oracle3, deplacement3,"$deplacement3" + ": $oracle3"),
                Arguments.of(oracle4, deplacement4,"$deplacement4" + ": $oracle4")
                )
        }
    }
}