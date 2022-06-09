package projet.echecmartien.modele


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@Suppress("ReplaceCallWithBinaryOperator")
internal class TestCoordonnee {

    /**
     * teste l'égalité des cases dans les cas suivant :
     *    - une case avec elle-même
     *    - deux cases différentes avec les mêmes coordonnées
     *   - deux cases différentes avec des coordonnées différentes
     */
    @Test
    fun testEquals() {
        val c1 = Coordonnee(0, 2)
        val c2 = Coordonnee(0, 2)
        val c3 = Coordonnee(5, 3)

        assertTrue(c1.equals(c2), "Les deux coordonnées sont égaux")
        assertFalse(c1.equals(c3),"Les deux coordonnées ne sont pas égaux")
        assertTrue(c1.equals(c1),"Ce sont des objets égaux")

        assertFalse(c1.equals(null),"Un null et une coordonnée ne sont pas égaux")
        assertFalse(c1.equals(Case()),"Une case et une coordonnée ne sont pas égaux")
    }

    /**
     * teste si getX renvoie la bonne valeur de l'abscisse d'une coordonnée
     */
    @ParameterizedTest
    @MethodSource("coordProviderGetX")
    fun testGetX(c:Coordonnee,oracle: Int, message: String){
        assertEquals(oracle, c.getX(), message)
    }

    /**
     * teste si getY renvoie la bonne valeur de l'ordonnée d'une coordonnée
     */
    @ParameterizedTest
    @MethodSource("coordProviderGetY")
    fun testGetY(c:Coordonnee,oracle: Int, message: String){
        assertEquals(oracle, c.getY(), message)
    }

    companion object {
        @JvmStatic
        fun coordProviderGetX(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Coordonnee(0, 2), 0, "La coordonnée en x devrait être 0"),
                Arguments.of(Coordonnee(5, 2), 5, "La coordonnée en x devrait être 5")
            )
        }
        @JvmStatic
        fun coordProviderGetY(): Stream<Arguments?>? {
            return Stream.of(
                Arguments.of(Coordonnee(0, 2), 2, "La coordonnée en y devrait être 2"),
                Arguments.of(Coordonnee(5, 8), 8, "La coordonnée en y devrait être 8")
            )
        }
    }
}