package projet.echecmartien.vue

import javafx.geometry.Pos
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
        this.styleClass.add("main")
        BoutonRejouer = Button("Rejouer")
        BoutonQuitter = Button("Quitter")

        Boutons = HBox()
        Boutons.alignment = Pos.CENTER
        Boutons.children.addAll(BoutonRejouer, BoutonQuitter)
        Boutons.spacing = 50.0

        labelJoueur = Label()
        labelJoueur.alignment = Pos.CENTER
        labelScore = Label()
        labelScore.alignment = Pos.CENTER

        labelJoueur.style = "-fx-font-style : 'Cambria'"
        labelJoueur.style = "-fx-font-size : 50"

        labelScore.style = "-fx-font-style : 'Cambria'"
        labelScore.style = "-fx-font-size : 50"

        infos.children.addAll(labelJoueur, labelScore, Boutons)
        infos.spacing = 20.0
        infos.alignment = Pos.CENTER
        this.center = infos

    }

    fun setJoueurVainqueur(joueur: Joueur?, scoreJ1: Int) {

        if (joueur == null) {
            labelJoueur.text = "Egalité !"
            labelScore.text = "Score des joueurs : $scoreJ1 pts"
            return
        }
        labelJoueur.text = "${joueur.getPseudo()} a remporté la partie !"
        labelScore.text = "Score : ${joueur.calculerScore()} pts"
    }
}