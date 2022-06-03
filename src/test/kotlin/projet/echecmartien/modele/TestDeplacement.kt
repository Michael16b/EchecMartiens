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

    @ParameterizedTest
    @MethodSource("diagonalProvider")
    fun testEstDiagonal(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estDiagonal(), message)
    }

    @ParameterizedTest
    @MethodSource("verticalPositifProvider")
    fun testEstVerticalPositif(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estVerticalPositif(), message)
    }

    @ParameterizedTest
    @MethodSource("horizontalPositifProvider")
    fun testEstHorizontalPositif(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estHorizontalPositif(), message)
    }

    @ParameterizedTest
    @MethodSource("diagonalPositifXPositifYProvider")
    fun testEstDiagonalPositifXPositifY(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estDiagonalPositifXPositifY(), message)
    }

    @ParameterizedTest
    @MethodSource("diagonalPositifXNegatifYProvider")
    fun testEstDiagonalPositifXNegatifY(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estDiagonalPositifXNegatifY(), message)
    }

    @ParameterizedTest
    @MethodSource("diagonalNegatifXPositifYProvider")
    fun testEstDiagonalNegatifXPositifY(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estDiagonalNegatifXPositifY(), message)
    }

    @ParameterizedTest
    @MethodSource("diagonalNegatifXNegatifYProvider")
    fun testEstDiagonalNegatifXNegatifY(d:Deplacement,oracle: Boolean, message: String){
        assertEquals(oracle, d.estDiagonalNegatifXNegatifY(), message)
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
                Arguments.of(Deplacement(Coordonnee(3, 7), Coordonnee(3, 0)), true, "le déplacement est bien vertical"),
                Arguments.of(Deplacement(Coordonnee(2, 2), Coordonnee(3, 2)), false, "le déplacement n'est pas vertical")
            )
        }

        @JvmStatic
        fun diagonalProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(0, 0), Coordonnee(2, 2)), true, "le déplacement est bien diagonal"),
                Arguments.of(Deplacement(Coordonnee(3, 2), Coordonnee(0, 5)), true, "le déplacement est bien diagonal"),
                Arguments.of(Deplacement(Coordonnee(0, 2), Coordonnee(3, 2)), false, "le déplacement n'est pas diagonal")
            )
        }

        @JvmStatic
        fun verticalPositifProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(1, 1), Coordonnee(1, 6)), true, "le déplacement est bien vertical positif"),
                Arguments.of(Deplacement(Coordonnee(0, 5), Coordonnee(2, 5)), false, "le déplacement n'est pas vertical positif"),
                Arguments.of(Deplacement(Coordonnee(3, 6), Coordonnee(3, 1)), false, "le déplacement n'est pas vertical positif")
            )
        }

        @JvmStatic
        fun horizontalPositifProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(1, 1), Coordonnee(3, 1)), true, "le déplacement est bien horizontal positif"),
                Arguments.of(Deplacement(Coordonnee(0, 5), Coordonnee(0, 1)), false, "le déplacement n'est pas horizontal positif"),
                Arguments.of(Deplacement(Coordonnee(2, 4), Coordonnee(0, 4)), false, "le déplacement n'est pas horizontal positif")
            )
        }

        @JvmStatic
        fun diagonalPositifXPositifYProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(0, 1), Coordonnee(2, 3)), true, "le déplacement est bien diagonal positif en X et positif en Y"),
                Arguments.of(Deplacement(Coordonnee(3, 1), Coordonnee(3, 6)), false, "le déplacement n'est pas diagonal positif en X et positif en Y"),
                Arguments.of(Deplacement(Coordonnee(3, 1), Coordonnee(0, 4)), false, "le déplacement n'est pas diagonal positif en X et positif en Y")
            )
        }

        @JvmStatic
        fun diagonalPositifXNegatifYProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(0, 6), Coordonnee(2, 4)), true, "le déplacement est bien diagonal positif en X et négatif en Y"),
                Arguments.of(Deplacement(Coordonnee(3, 1), Coordonnee(1, 1)), false, "le déplacement n'est pas diagonal positif en X et négatif en Y"),
                Arguments.of(Deplacement(Coordonnee(3, 3), Coordonnee(1, 1)), false, "le déplacement n'est pas diagonal positif en X et négatif en Y")
            )
        }

        @JvmStatic
        fun diagonalNegatifXPositifYProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(3, 1), Coordonnee(0, 4)), true, "le déplacement est bien diagonal négatif en X et positif en Y"),
                Arguments.of(Deplacement(Coordonnee(0, 2), Coordonnee(3, 2)), false, "le déplacement n'est pas diagonal négatif en X et positif en Y"),
                Arguments.of(Deplacement(Coordonnee(0, 3), Coordonnee(2, 1)), false, "le déplacement n'est pas diagonal négatif en X et positif en Y")
            )
        }

        @JvmStatic
        fun diagonalNegatifXNegatifYProvider(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Deplacement(Coordonnee(3, 6), Coordonnee(0, 3)), true, "le déplacement est bien diagonal négatif en X et négatif en Y"),
                Arguments.of(Deplacement(Coordonnee(1, 0), Coordonnee(1, 5)), false, "le déplacement n'est pas diagonal négatif en X et négatif en Y"),
                Arguments.of(Deplacement(Coordonnee(0, 2), Coordonnee(2, 4)), false, "le déplacement n'est pas diagonal négatif en X et négatif en Y")
            )
        }
    }
}