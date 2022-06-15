package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.librairie.PlayerIA
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import projet.echecmartien.modele.*
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.WinVue

class ControleurGame(scene: Scene, vue: GameVue, modele: Jeu, ia: PlayerIA?, joueur1: Joueur, joueur2: Joueur): EventHandler<MouseEvent> {

    private val vue: GameVue
    private val modele: Jeu
    private val scene: Scene
    private val ia: PlayerIA?
    private val j1: Joueur
    private val j2: Joueur
    private var origineSelected: Boolean

    init {
        this.vue = vue
        this.modele = modele
        this.scene = scene
        this.j1 = joueur1
        this.j2 = joueur2
        this.ia = ia
        this.origineSelected = false
    }

    override fun handle(e: MouseEvent) {
        var source = e.pickResult.intersectedNode

        if (source is Rectangle || source is Circle)
            source = source.parent

        if (source !is StackPane)
            return

        val col: Int = GridPane.getColumnIndex(source)
        val row: Int = GridPane.getRowIndex(source)

        val case = modele.getPLateau().getCases()[col][row]

        /* Lorsqu'on sélectionne l'origine, si le déplacement à cette case n'est pas possible
           ou que la case n'appartient pas au joueur courant alors on arrête. Sinon,
           on dit que l'origine est sélectionnée
         */
        if (!origineSelected) {

            if (!modele.deplacementPossible(col,row) || case.getJoueur() != modele.getJoueurCourant())
                return

            origineSelected = true
            vue.resetCouleur()
            modele.setCoordOrigineDeplacement(Coordonnee(col, row))
            montrerDeplacementsPossibles(col, row)
            return
        }

        // on sait ici que l'origine a déjà été selectionnée et que le pion appartient au joueur et peut se déplacer


        if (case.getJoueur() == modele.getJoueurCourant()) {

            val oldOrigine = modele.getCoordOrigineDeplacement()
            // si le déplacement dans la propre zone du joueur est possible
            if (case.estLibre()) {

                if (!modele.deplacementPossible(oldOrigine!!.getX(), oldOrigine.getY(), col, row, modele.getJoueurCourant())) {

                    vue.colorierCase(row, col, Color.RED)
                    return
                }


                modele.deplacer(oldOrigine.getX(), oldOrigine.getY(), col, row)
                tourSuivant()
                return
            }

            // si le joueur clique sur un de ses pions, alors on change la coordonnée d'origine
            vue.resetCouleur()
            val newOrigine = Coordonnee(col, row)
            modele.setCoordOrigineDeplacement(newOrigine)
            montrerDeplacementsPossibles(col, row)

            if (oldOrigine != null && oldOrigine != newOrigine)
                vue.colorierCase(oldOrigine.getY(), oldOrigine.getX(), Color.WHITESMOKE)
            return
        }

        // on sait que la case de destination n'appartient pas au joueur courant
        val origine = modele.getCoordOrigineDeplacement()

        if (!modele.deplacementPossible(origine!!.getX(), origine.getY(), col, row, modele.getJoueurCourant())) {
            vue.colorierCase(row, col, Color.RED)
            return
        }

        // il y a une prise
        majPrises(modele.getJoueurCourant(), modele.getPLateau().getCases()[col][row].getPion())
        modele.deplacer(origine.getX(), origine.getY(), col, row)
        tourSuivant()

    }

    /**
     * fonction qui fait passer le jeu au tour suivant et vérifie si la partie doit se poursuivre ou non
     */
    private fun tourSuivant() {
        modele.changeJoueurCourant()
        vue.resetCouleur()
        vue.remplirCases(modele.getPLateau().getCases())
        majScores()
        origineSelected = false

        if (modele.arretPartie())
            finPartie()

        vue.setCoupsRestants(modele.nbCoupsSansPriseRestants())

        if (!tourJouable())
            modele.changeJoueurCourant()

        if (ia != null && modele.getJoueurCourant() == j2) {
            val coup = ia.prochainCoup()
            modele.deplacer(coup.getOrigine().getX(), coup.getOrigine().getY(), coup.getDestination().getX(), coup.getDestination().getY())
            tourSuivant()
        }
    }

    /**
     * renvoie vrai si le joueur courant peut joueur ou non
     */
    private fun tourJouable(): Boolean {
        val colMin = if (modele.getJoueurCourant() == j1) 0 else 4
        val colMax = if (colMin == 0) 3 else 7
        for (i in colMin..colMax) {
            for (j in 0 until TAILLEHORIZONTALE) {
                if (modele.deplacementPossible(j, i))
                    return true
            }
        }
        return false
    }

    /**
     * fonction pour terminer la partie et afficher le gagnant ou égalité
     */
    private fun finPartie() {
        val winVue = WinVue()
        winVue.setJoueurVainqueur(modele.joueurVainqueur(), j1.calculerScore())
        scene.root = winVue
    }

    /**
     * fonction pour mettre à jour le nombre de prises par pion suivant un joueur
     * @param type: pion capturé
     * @param joueur: joueur qui a capturé le pion
     */
    private fun majPrises(joueur: Joueur?, type: Pion?){

        val decalageJoueur = if (joueur == j1) 0 else 1
        val decalagePion = when (type) {
            is MoyenPion -> 1
            is GrandPion -> 0
            is PetitPion -> 2
            else -> 0
        }
        val labelNb = vue.labelNbPionsArray[decalageJoueur * 3 + decalagePion]

        val labelValue = labelNb.text.toInt()
        labelNb.text = "${labelValue + 1}"
    }

    /**
     * fonction qui met à jour les scores des deux joueurs
     */
    private fun majScores() {
        vue.labelScore1.text = "Score : ${j1.calculerScore()?:0} pts"
        vue.labelScore2.text = "Score : ${j2.calculerScore()?:0} pts"

    }

    /**
     * fonction qui colorie les cases du plateau de jeu pour montrer les déplacements
     * possibles à partir de coordonnées
     * @param row: abscisse d'origine
     * @param col: ordonnée d'origine
     */
    private fun montrerDeplacementsPossibles(row: Int, col: Int) {
        vue.resetCouleur()
        if (!modele.deplacementPossible(row, col))
            return
        vue.colorierCase(col, row, Color.GREEN)

        for (i in 0 until TAILLEVERTICALE) {
            for (j in 0 until TAILLEHORIZONTALE) {

                if (j == row && i == col) {
                    continue
                }
                if (modele.deplacementPossible(row, col, j, i, modele.getJoueurCourant())) {
                    vue.colorierCase(i, j, Color.LIGHTGREEN)
                }

            }
        }

    }
}
