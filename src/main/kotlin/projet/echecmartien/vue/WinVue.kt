package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import projet.echecmartien.modele.Joueur

class WinVue : BorderPane() {
    val infos : VBox
    val BoutonRejouer : Button
    val BoutonQuitter : Button
    val Boutons: HBox

    val labelJoueur : Label
    val labelScore : Label


    init {
        infos = VBox()

        BoutonRejouer = Button("Rejouer")
        BoutonQuitter = Button("Quitter")

        Boutons = HBox()
        Boutons.children.addAll(BoutonRejouer, BoutonQuitter)
        Boutons.spacing = 50.0

        labelJoueur = Label()
        labelScore = Label()

        infos.children.addAll(labelJoueur, Boutons, labelScore)
        infos.spacing = 20.0
        this.children.add(infos)
    }

    fun setJoueurVainqueur(joueur: Joueur?, scoreJ1: Int) {

        if (joueur == null) {
            labelJoueur.text = "Egalité !"
            labelScore.text = "Score des joueurs : $scoreJ1 pts"
            return
        }
        labelJoueur.text = "${joueur.getPseudo()} a remporté la partie"
        labelScore.text = "Score : ${joueur.calculerScore()} pts"
    }
}