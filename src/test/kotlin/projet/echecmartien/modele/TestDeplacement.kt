package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class TestDeplacement {


    @ParameterizedTest
    @MethodSource("horizontalProvider")
    fun testEstHorizontal(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estHorizontal(), message)
    }

    @ParameterizedTest
    @MethodSource("verticalProvider")
    fun testEstVertical(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estVertical(), message)
    }

    companion object {
        @JvmStatic
        fun horizontalProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(0, 0), Coordonnee(2, 0)), true, "le déplacement est bien horizontzal"),
                Arguments.of(Deplacement(Coordonnee(3, 7), Coordonnee(0, 7)), true, "le déplacement est bien horizontzal"),
                Arguments.of(Deplacement(Coordonnee(2, 2), Coordonnee(2, 5)), false, "le déplacement n'est pas horizontzal")
            )
        }

        @JvmStatic
        fun verticalProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(0, 0), Coordonnee(0, 2)), true, "le déplacement est bien vertical"),
                Arguments.of(Deplacement(Coordonnee(3, 7), Coordonnee(0, 2)), true, "le déplacement est bien vertical"),
                Arguments.of(Deplacement(Coordonnee(2, 2), Coordonnee(3, 2)), false, "le déplacement n'est pas vertical")
            )
        }
    }
}