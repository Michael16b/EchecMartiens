package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

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

        labelJoueur = Label("a remporté la partie")
        labelScore = Label("Score : pts")

        infos.children.addAll(labelJoueur, Boutons, labelScore)
        infos.spacing = 20.0
    }
}