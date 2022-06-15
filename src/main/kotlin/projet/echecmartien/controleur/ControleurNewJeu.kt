package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.modele.GrandPion
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.modele.deserialiser
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class ControleurNewJeu(scene: Scene, vue: MainVue): EventHandler<ActionEvent> {

    val scene: Scene
    val vue: MainVue

    init {
        this.scene = scene
        this.vue = vue
    }

    override fun handle(Event: ActionEvent) {

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

        if (pseudo1 == pseudo2) {
            memePseudo()
            return
        }

        val jeu = Jeu()
        val j1 = Joueur(pseudo1)
        val j2 = if (vue.comboBoxIA.selectionModel.selectedIndex == 0) Joueur(pseudo2) else JoueurIA(pseudo2)

        jeu.initialiserPartie(j1, j2, coupsMax)
        val gameVue = GameVue()

        val controleurClick = ControleurJeu(scene, gameVue, jeu, j1, j2)
        val controleurRules = ControleurRules(scene, gameVue)
        val controleurSave = ControleurSauvegardeJeu(jeu, j2 is JoueurIA)
        gameVue.playGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, controleurClick)
        gameVue.buttonRules.addEventHandler(ActionEvent.ACTION, controleurRules)
        gameVue.buttonSave.addEventHandler(ActionEvent.ACTION, controleurSave)
        gameVue.setCoupsRestants(jeu.nbCoupsSansPriseRestants())
        gameVue.setPseudo(j1, j2)
        gameVue.remplirCases(jeu.getPLateau().getCases())
        scene.root = gameVue

    }

    /**
     * fonction qui affiche une boîte de dialogue (erreur) pour informer que les deux joueurs ont le même nom
     */
    fun memePseudo() {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = "Mauvais nom de joueur"
        dialog.headerText = "Les deux joueurs ont le même nom !"
        dialog.contentText = "Merci de mettre un pseudo différent aux deux joueurs"
        dialog.showAndWait()
    }

    /**
     * fonction qui affiche une boîte de dialogue (erreur) pour informer que le nom du joueur n est invalide
     * @param: numéro de joueur affiché dans la boîte de dialogue dont le nom est invalide (1 ou 2)
     */
    fun mauvaisPseudo(n: Int) {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = "Mauvais nom de joueur"
        dialog.headerText = "Le nom du joueur ${n} est invalide !"
        dialog.contentText = "caractères de l'alphabet et chiffres uniquement et sans espaces"
        dialog.showAndWait()
    }

    /**
     * fonction qui affiche une boîte de dialogue (erreur) pour informer que le nombre de coups sans prise maximum est invalide
     */
    fun mauvaisCoupMax() {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = "Mauvais nombre de coups sans prise max"
        dialog.headerText = "Le nombre de coups sans prise maximum est invalide !"
        dialog.contentText = "Merci de choisir nombre valide entre 1 et 99"
        dialog.showAndWait()
    }
    
}