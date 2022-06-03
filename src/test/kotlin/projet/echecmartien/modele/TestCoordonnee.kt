package projet.echecmartien.modele


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class TestCoordonnee {

    @Test
    fun testEquals() {
        val c1 : Coordonnee = Coordonnee(0, 2)
        val c2 : Coordonnee = Coordonnee(0, 2)
        val c3 : Coordonnee = Coordonnee(5, 3)

        assertEquals(true,c1.equals(c2), "Les deux coordonnées sont égaux")
        assertEquals(false, c1.equals(c3),"Les deux coordonnées ne sont pas égaux")
        assertEquals(true, c1.equals(c1),"Ce sont des objets égaux")
    }


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
    @ParameterizedTest
    @MethodSource("coordProviderEquals")
    fun testEquals(c1:Coordonnee,c2 :Coordonnee,oracle: Boolean, message: String){
        assertEquals(oracle, c1.equals(c2), message)
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
        @JvmStatic
        fun coordProviderEquals(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Coordonnee(5, 2),Coordonnee(0, 2), false, "")
            )
        }
    }
}