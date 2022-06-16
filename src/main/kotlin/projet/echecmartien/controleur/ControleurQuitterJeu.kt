package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VuePrincipale

class ControleurQuitterJeu (modele: Jeu, scene: Scene, saveFile: String, ia: Boolean): EventHandler<ActionEvent> {

    private val scene : Scene
    private val modele: Jeu
    private val ia: Boolean
    private val saveFile: String

    init {
        this.scene = scene
        this.modele = modele
        this.ia = ia
        this.saveFile = saveFile
    }

    override fun handle(e: ActionEvent) {
        val dialog = Alert(Alert.AlertType.CONFIRMATION)
        dialog.title = "Quitter la partie"
        dialog.headerText = "Voulez-vous sauvegarder la partie avant de quitter ?"
        (dialog.dialogPane.lookupButton(ButtonType.OK) as Button).text = "Oui"
        (dialog.dialogPane.lookupButton(ButtonType.CANCEL) as Button).text = "Non"
        dialog.showAndWait()

        val result = dialog.result
        if (result == ButtonType.OK) {
            sauvegarderJeu(modele, saveFile, ia)
            val vue = VuePrincipale()

            val controleurReglesVue = ControleurRegles(scene, vue)
            vue.buttonRules.addEventHandler(ActionEvent.ACTION,controleurReglesVue)

            val controleurNouveauJeu = ControleurNouveauJeu(scene, vue)
            vue.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNouveauJeu)

            val controleurChargerJeuVue = ControleurChargerJeuVue(scene, vue)
            vue.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurChargerJeuVue)

            scene.root = vue
        }
    }
}