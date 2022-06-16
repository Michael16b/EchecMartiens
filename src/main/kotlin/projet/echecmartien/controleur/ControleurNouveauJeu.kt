package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.input.MouseEvent
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueJeu
import projet.echecmartien.vue.VuePrincipale
import java.io.File
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ControleurNouveauJeu(scene: Scene, vue: VuePrincipale): EventHandler<ActionEvent> {

    val scene: Scene
    val vue: VuePrincipale

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
            afficherDialog("Mauvais nombre de coups sans prise max",
                "Le nombre de coups sans prise maximum est invalide !",
                "Le nombre de coups sans prise maximum est invalide !")
            return
        }

        if (pseudo1 == "" || !pseudo1.matches("^[a-zA-Z0-9]*$".toRegex())) {
            afficherDialog("Mauvais nom de joueur","Le nom du joueur 1 est invalide !",
                " Seuls les lettres et les chiffres de l'alphabet peuvent être uilisées")
            return
        }

        if (pseudo2 == "" || !pseudo2.matches("^[a-zA-Z0-9]*$".toRegex())) {
            afficherDialog("Mauvais nom de joueur","Le nom du joueur 2 est invalide !",
                " Seuls les lettres et les chiffres de l'alphabet peuvent être uilisées")
            return
        }

        if (pseudo1 == pseudo2) {
            afficherDialog("Mauvais nom de joueur","Les deux joueurs ont le même nom !",
                "Merci de mettre un pseudo différent aux deux joueurs")
            return
        }

        if( pseudo1.length > 30) {
            afficherDialog("Nom de joueur trop long", raison = "Le nom du joueur 1 a trop de caractères",
                message = "le nom du joueur ne doit pas dépasser 30 caractères")
            return
        }
        if(pseudo2.length > 30) {
            afficherDialog("Nom de joueur trop long", raison = "Le nom du joueur 2 a trop de caractères",
                message = "le nom du joueur ne doit pas dépasser 30 caractères")
            return
        }

        val jeu = Jeu()
        val j1 = Joueur(pseudo1)
        val j2 = if (vue.comboBoxIA.selectionModel.selectedIndex == 0) Joueur(pseudo2) else JoueurIA(pseudo2)

        jeu.initialiserPartie(j1, j2, coupsMax)
        val vueJeu = VueJeu()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss.SSS")
        val time = LocalDateTime.now().format(formatter)

        val file = File(Paths.get("${getBaseDir()}/$time.json").toAbsolutePath().toString())
        val saveFile = if (file.exists())
            chercherNomDispo() else "$time.json"


        val controleurClick = ControleurJeu(scene, vueJeu, jeu, j1, j2, saveFile)
        val controleurRegles = ControleurRegles(scene, vueJeu)
        val controleurSave = ControleurSauvegardeJeu(jeu, j2 is JoueurIA, saveFile)
        val controleurBack = ControleurQuitterJeu(jeu, scene, saveFile, j2 is JoueurIA)
        vueJeu.playGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, controleurClick)
        vueJeu.buttonRules.addEventHandler(ActionEvent.ACTION, controleurRegles)
        vueJeu.buttonSave.addEventHandler(ActionEvent.ACTION, controleurSave)
        vueJeu.buttonBack.addEventHandler(ActionEvent.ACTION, controleurBack)
        vueJeu.setCoupsRestants(jeu.nbCoupsSansPriseRestants())
        vueJeu.setPseudo(j1, j2)
        vueJeu.remplirCases(jeu.getPLateau().getCases())
        scene.root = vueJeu

    }

    /**
     * affiche les messages d'erreurs sur la saisie des pseudos
     */

    fun afficherDialog(titre : String,message : String,raison : String) {
        val dialog = Alert(Alert.AlertType.ERROR)
        dialog.title = titre
        dialog.headerText = raison
        dialog.contentText = message
        dialog.showAndWait()
    }
}