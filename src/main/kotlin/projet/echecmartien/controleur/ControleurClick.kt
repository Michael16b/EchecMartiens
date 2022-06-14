package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.GameVue

class ControleurClick(vue: GameVue, modele: Jeu): EventHandler<MouseEvent> {

    private val vue: GameVue
    private val modele: Jeu
    private var origineSelected: Boolean

    init {
        this.vue = vue
        this.modele = modele
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

            println(modele.getJoueurCourant())
            println(case.getJoueur())
            println(modele.deplacementPossible(row, col))
            if (!modele.deplacementPossible(col,row) || case.getJoueur() != modele.getJoueurCourant())
                return

            origineSelected = true
            vue.resetCouleur()
            vue.colorierCase(row, col, Color.LIGHTGREEN)
            modele.setCoordOrigineDeplacement(Coordonnee(col, row))
            return
        }

        // on sait ici que l'origine a déjà été selectionnée et que le pion appartient au joueur et peut se déplacer


        if (case.getJoueur() == modele.getJoueurCourant()) {

            val oldOrigine = modele.getCoordOrigineDeplacement()
            // si le déplacement dans la propre zone du joueur est possible
            if (case.estLibre()) {

                if (!modele.deplacementPossible(oldOrigine!!.getX(), oldOrigine.getY(), col, row, modele.getJoueurCourant()))
                    return

                modele.deplacer(oldOrigine.getX(), oldOrigine.getY(), col, row)
                tourSuivant()
                return
            }

            // si le joueur clique sur un de ses pions, alors on change la coordonnée d'origine
            vue.resetCouleur()
            vue.colorierCase(row, col, Color.LIGHTGREEN)
            val newOrigine = Coordonnee(col, row)
            modele.setCoordOrigineDeplacement(newOrigine)

            if (oldOrigine != null && oldOrigine != newOrigine)
                vue.colorierCase(oldOrigine.getY(), oldOrigine.getX(), Color.WHITESMOKE)
            return
        }

        // on sait que la case de destination n'appartient pas au joueur courant
        val origine = modele.getCoordOrigineDeplacement()

        if (!modele.deplacementPossible(origine!!.getX(), origine.getY(), col, row, modele.getJoueurCourant()))
            return

        modele.deplacer(origine.getX(), origine.getY(), col, row)
        tourSuivant()

    }

    private fun tourSuivant() {
        modele.changeJoueurCourant()
        vue.resetCouleur()
        vue.remplirCases(modele.getPLateau().getCases())
        origineSelected = false
    }
}