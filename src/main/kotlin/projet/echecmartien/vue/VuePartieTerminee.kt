package projet.echecmartien.vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import projet.echecmartien.modele.Joueur

class VuePartieTerminee : BorderPane() {
    val infos : VBox
    val BoutonRejouer : Button
    val BoutonQuitter : Button
    val Boutons: HBox

    val labelJoueur : Label
    val labelScore : Label


    init {
        infos = VBox()
        this.styleClass.add("main")
        this.styleClass.add("win")

        this.
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



        this.labelJoueur.styleClass.add("winLabel")
        labelJoueur.isWrapText = true
        labelJoueur.textAlignment = TextAlignment.CENTER
        this.labelScore.styleClass.add("winScore")

        infos.children.addAll(labelJoueur, labelScore, Boutons)
        infos.spacing = 20.0
        infos.alignment = Pos.CENTER
        this.center = infos

    }

    fun setJoueurVainqueur(joueur: Joueur?, scoreJ1: Int) {

        if (joueur == null) {
            labelJoueur.text = "Égalité !"
            labelScore.text = "Score des joueurs : $scoreJ1 pts"
            return
        }
        labelJoueur.text = "${joueur.getPseudo()} \n a remporté la partie !"
        labelScore.text = "Score : ${joueur.calculerScore()} pts"
    }
}