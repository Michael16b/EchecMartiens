package projet.echecmartien.modele


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class TestCoordonnee {

    @ParameterizedTest
    @MethodSource("coordProviderGetX")
    fun testGetX(c:Coordonnee,oracle: Int, message: String){
        assertEquals(oracle, c.getX(), message)
    }

    @ParameterizedTest
    @MethodSource("coordProviderGetY")
    fun testGetY(c:Coordonnee,oracle: Int, message: String){
        assertEquals(oracle, c.getY(), message)
    }

    companion object {
        @JvmStatic
        fun coordProviderGetX(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Coordonnee(0, 2), 0, "La coordonnée en x est 0"),
                Arguments.of(Coordonnee(5, 2), 5, "La coordonnée en x est 5")
            )
        }
        @JvmStatic
        fun coordProviderGetY(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Coordonnee(0, 2), 2, "La coordonnée en y est 2"),
                Arguments.of(Coordonnee(5, 8), 8, "La coordonnée en y est 8")
            )
        }
    }
}