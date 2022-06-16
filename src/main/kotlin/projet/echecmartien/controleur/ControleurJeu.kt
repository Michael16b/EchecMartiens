package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.librairie.TAILLEHORIZONTALE
import projet.echecmartien.librairie.TAILLEVERTICALE
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.Pion
import projet.echecmartien.vue.VueJeu
import projet.echecmartien.vue.VuePartieTerminee

class ControleurJeu(scene: Scene, vue: VueJeu, modele: Jeu, joueur1: Joueur, joueur2: Joueur, saveFile: String): EventHandler<MouseEvent> {

    private val vue: VueJeu
    private val modele: Jeu
    private val scene: Scene
    private val j1: Joueur
    private val j2: Joueur
    private var origineSelected: Boolean
    private val saveFile: String

    init {
        this.vue = vue
        this.modele = modele
        this.scene = scene
        this.j1 = joueur1
        this.j2 = joueur2
        this.saveFile = saveFile
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
        if (!case.estLibre())
            majPrises(modele.getJoueurCourant(), case.getPion())

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
        vue.setCoupsRestants(modele.nbCoupsSansPriseRestants())
        if (modele.arretPartie()) {
            finPartie()
            return
        }

        if (!tourJouable())
            modele.changeJoueurCourant()

        if (j2 is JoueurIA && modele.getJoueurCourant() == j2) {
            val coup = j2.prochainCoup(modele)
            val case = modele.getPLateau().getCases()[coup.getDestination().getX()][coup.getDestination().getY()]
            if (case.getJoueur() != j2 && !case.estLibre())
                majPrises(j2, case.getPion())
            modele.deplacer(coup.getOrigine().getX(), coup.getOrigine().getY(), coup.getDestination().getX(), coup.getDestination().getY())
            tourSuivant()
        }
        if (modele.getJoueurCourant() == j1) {
            vue.labelPlayer1.styleClass.clear()
            vue.labelPlayer2.styleClass.clear()
            vue.labelPlayer1.styleClass.add("PseudoLabelSonTour")
            vue.labelPlayer2.styleClass.add("PseudoLabelPasSonTour")
        } else {
            vue.labelPlayer1.styleClass.clear()
            vue.labelPlayer2.styleClass.clear()
            vue.labelPlayer1.styleClass.add("PseudoLabelPasSonTour")
            vue.labelPlayer2.styleClass.add("PseudoLabelSonTour")
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
        val dialog = Alert(AlertType.INFORMATION)
        dialog.title = "Fin de la partie"
        dialog.headerText = "La partie est terminée !"
        dialog.showAndWait()
        val vuePartieTerminee = VuePartieTerminee()
        vuePartieTerminee.BoutonQuitter.addEventHandler(ActionEvent.ACTION, ControleurQuitterJeuApresFin(scene))
        vuePartieTerminee.BoutonRejouer.addEventHandler(ActionEvent.ACTION, ControleurRejouer(modele, scene,
            j2 is JoueurIA, j1.getPseudo(), j2.getPseudo()))
        vuePartieTerminee.setJoueurVainqueur(modele.joueurVainqueur(), j1.calculerScore())
        scene.root = vuePartieTerminee
    }

    /**
     * fonction pour mettre à jour le nombre de prises par pion suivant un joueur
     * ajoute 1 au label du bon pion au bon joueur
     * @param type: pion capturé
     * @param joueur: joueur qui a capturé le pion
     */
    private fun majPrises(joueur: Joueur?, type: Pion?){

        val decalageJoueur = if (joueur == j1) 0 else 1
        vue.majPrise(decalageJoueur, type)
    }

    /**
     * fonction qui met à jour les scores des deux joueurs
     */
    private fun majScores() {
        vue.setScoreJoueurs(j1.calculerScore(), j2.calculerScore())
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
