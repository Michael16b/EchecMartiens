package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class ControleurNewGame(scene: Scene): EventHandler<ActionEvent> {

    val scene: Scene

    init {
        this.scene = scene
    }

    override fun handle(Event: ActionEvent) {

        if (scene.root !is MainVue)
            return

        val vue = scene.root as MainVue

        val pseudo1 = vue.textFieldPseudo1.text
        val pseudo2 = vue.textFieldPseudo2.text

        val coupsMax: Int
        try {
            coupsMax = vue.textFieldCoups.text.toInt()
        } catch(_: java.lang.NumberFormatException) {
            mauvaisCoupMax()
            return
        }

        if (coupsMax > 99 || coupsMax < 1) {
            mauvaisCoupMax()
            return
        }

        if (pseudo1 == "" || !pseudo1.matches("^[a-zA-Z0-9]*$".toRegex())) {
            mauvaisPseudo(1)
            return
        }
        if (pseudo2 == "" || !pseudo2.matches("^[a-zA-Z0-9]*$".toRegex())) {
            mauvaisPseudo(2)
            return
        }

        val j1 = Joueur(pseudo1)
        val j2 = Joueur(pseudo2)

        val jeu = Jeu()
        jeu.initialiserPartie(j1, j2, 10)

        val gameVue = GameVue()

        val controleurClick = ControleurClick(gameVue, jeu)
        gameVue.playGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, controleurClick)
        gameVue.remplirCases(jeu.getPLateau().getCases())
        scene.root = gameVue

    }

    fun mauvaisPseudo(n: Int) {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = "Mauvais nom de joueur"
        dialog.headerText = "Le nom du joueur ${n} est invalide !"
        dialog.contentText = "caractères de l'alphabet, espaces et chiffres uniquement"
        dialog.showAndWait()
    }

    fun mauvaisCoupMax() {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = "Mauvais nombre de coups sans prise max"
        dialog.headerText = "Le nombre de coups sans prise maximum est invalide !"
        dialog.contentText = "Merci de choisir nombre valide entre 1 et 99"
        dialog.showAndWait()
    }
}