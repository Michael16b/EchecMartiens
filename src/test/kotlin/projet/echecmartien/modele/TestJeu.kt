package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE

internal class TestJeu {

    private var jeu = Jeu()
    private var plateau = Plateau()
    private val j1 = Joueur("Matthis")
    private val j2 = Joueur("Julien")

    @BeforeEach
    fun setUp() {
        jeu = Jeu()
        plateau = Plateau()
    }

    /**
     * Teste si le joueur courant est bien changé
     */
    @Test
    fun testChangementJoueurCourant() {

        jeu.initialiserPartie(j1, j2, 10)

        assertEquals(j1, jeu.getJoueurCourant())
        jeu.changeJoueurCourant()
        assertEquals(j2, jeu.getJoueurCourant())
        jeu.changeJoueurCourant()
        assertEquals(j1, jeu.getJoueurCourant())
    }

    /**
     * Teste si la coordonnée d'origine du déplacement est bien modifiée
     */
    @Test
    fun testDeplacementOrigine() {
        val origine = Coordonnee(3, 2)
        jeu.setCoordOrigineDeplacement(origine)
        assertEquals(origine, jeu.getCoordOrigineDeplacement())
    }

    /**
     * Teste si la coordonnée de destination du déplacement est bien modifiée
     */
    @Test
    fun testDeplacementDestination() {
        val destination = Coordonnee(7, 3)
        jeu.setCoordDestinationDeplacement(destination)
        assertEquals(destination, jeu.getCoordDestinationDeplacement())
    }

    /**
     * Teste si quand on commence une nouvelle partie, les cases de la partie haute du plateau appartiennent bien au joueur 1 et celles de la partie basse du plateau joueur 2
     */
    @Test
    fun testInitialiserJoueurPlateau() {
        jeu.initialiserPartie(j1, j2, 10)

        val cases = jeu.getPLateau().getCases()

        // Joueur 1, partie "haute" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in 0 until TAILLEVERTICALE /2) {
                assertEquals(j1, cases[i][j].getJoueur())
            }
        }

        // Joueur 2, partie "basse" du plateau
        for (i in 0 until TAILLEHORIZONTALE) {
            for (j in TAILLEVERTICALE /2 until TAILLEVERTICALE) {
                assertEquals(j2, cases[i][j].getJoueur())
            }
        }
    }

    /**
     * teste si le déplacement est possible pour un petit au départ, lorsqu'il change de zone, lorsqu'il n'est plus le
     * pion arrivé de zone, lorsqu'il peut uniquement manger un pion adverse et qu'il n'a pas de déplacements possibles
     */
    @Test
    fun testDeplacementPossiblePetitPion() {
        jeu.initialiserPartie(j1, j2, 10)

        assertTrue(jeu.deplacementPossible(2, 2), "le petit pion en 2, 2 devrait pouvoir se déplacer")

        jeu.deplacer(2, 2, 0, 4)

        assertFalse(jeu.deplacementPossible(0, 4), "le petit pion en 0, 4 ne devrait pas pouvoir se déplacer (pion arrivé de zone)")

        jeu.deplacer(3, 5, 3, 3)

        assertTrue(jeu.deplacementPossible(0, 4),  "le petit pion en 0, 4 devrait pouvoir se déplacer (pion n'est plus celui arrivé de zone")

        jeu.deplacer(0, 2, 1, 3)

        assertTrue(jeu.deplacementPossible(0, 4), "le petit pion en 0, 4 devrait pouvoir se déplacer (en mangeant un pion adverse)")

        jeu.initialiserPartie(j1, j2, 10)
        jeu.getPLateau().getCases()[3][0].setPion(PetitPion())
        jeu.getPLateau().getCases()[3][2].setPion(PetitPion())

        assertFalse(jeu.deplacementPossible(2, 1))
    }

    /**
     * teste le déplacement possible d'un grand pion
     */
    @Test
    fun testDeplacementPossibleGrandPion() {
        jeu.initialiserPartie(j1, j2, 10)
        assertFalse(jeu.deplacementPossible(0, 1), "le grand pion en 0, 1 ne devrait pas pouvoir se déplacer")

        jeu.deplacer(0, 2, 1, 3)
        assertTrue(jeu.deplacementPossible(0, 1), "le grand pion en 0, 1 devrait pouvoir se déplacer")
    }

    /**
     * teste le joueur vainqueur
     */
    @Test
    fun TestJoueurVainqueurPartieNonInit() {
        val game = Jeu()
        assertNull(game.joueurVainqueur())
    }

    /**
     * teste le joueur vainqueur
     */
    @Test
    fun TestJoueurVainqueurPartieInit1() {
        val game = Jeu()
        val joueur1 = Joueur("Matthis")
        val joueur2 = Joueur("Louis")
        game.initialiserPartie(joueur1, joueur2, 2)
        joueur1.ajouterPionCaptures(GrandPion())
        joueur2.ajouterPionCaptures(MoyenPion())
        assertEquals(joueur1, game.joueurVainqueur())
    }

    /**
     * teste le joueur vainqueur
     */
    @Test
    fun TestJoueurVainqueurPartieInit2() {
        val game = Jeu()
        val joueur1 = Joueur("Matthis")
        val joueur2 = Joueur("Louis")
        game.initialiserPartie(joueur1, joueur2, 2)
        joueur1.ajouterPionCaptures(PetitPion())
        joueur2.ajouterPionCaptures(MoyenPion())
        assertEquals(joueur2, game.joueurVainqueur())
    }

    /**
     * teste le joueur vainqueur
     */
    @Test
    fun TestJoueurVainqueurPartieInit3() {
        val game = Jeu()
        val joueur1 = Joueur("Matthis")
        val joueur2 = Joueur("Louis")
        game.initialiserPartie(joueur1, joueur2, 2)
        joueur1.ajouterPionCaptures(MoyenPion())
        joueur2.ajouterPionCaptures(MoyenPion())
        assertEquals(null, game.joueurVainqueur())
    }

    fun testArretPartie() {
        val game = Jeu()
        game.initialiserPartie(Joueur("Matthis"), Joueur("Louis"), 2)

    }

    /**
     * teste si le déplacement avec un joueur extérieur à la partie est bien impossible
     */
    @Test
    fun testDeplacementPossible2AutreJoueur() {
        val j = Joueur("Léo")
        jeu.initialiserPartie(j1, j2, 10)
        assertFalse(jeu.deplacementPossible(0, 0, 1, 1, j), "le déplacement d'un joueur qui ne fait pas partie de la partie ne devrait pas être possible")
    }

    /**
     * teste si un déplacement dont l'origine est une case vide est bien impossible
     */
    @Test
    fun testDeplacementPossible2CaseLibre() {
        jeu.initialiserPartie(j1, j2, 10)
        assertFalse(jeu.deplacementPossible(3, 3, 0, 0, j1), "le déplacement dont l'origine est une case vide ne devrait pas être possible")
    }


}