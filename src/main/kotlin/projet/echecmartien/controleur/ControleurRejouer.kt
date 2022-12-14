package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueJeu
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ControleurRejouer (modele: Jeu, scene: Scene, ia: Boolean, pseudo1: String, pseudo2: String): EventHandler<ActionEvent> {

    private val modele: Jeu
    private val scene: Scene
    private val ia: Boolean
    private val pseudo1: String
    private val pseudo2: String

    init {
        this.modele = modele
        this.scene = scene
        this.ia = ia
        this.pseudo1 = pseudo1
        this.pseudo2 = pseudo2
    }

    override fun handle(Event: ActionEvent) {
        val jeu = Jeu()
        val j1 = Joueur(pseudo1)
        val j2 = if (!ia) Joueur(pseudo2) else JoueurIA(pseudo2)

        jeu.initialiserPartie(j1, j2, modele.nbCoupsSansPriseMax())
        val vueJeu = VueJeu()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val time = LocalDateTime.now().format(formatter)
        val file = File("${getBaseDir()}/$time.json")
        val saveFile = if (file.exists())
            chercherNomDispo() else "$time.json"

        val controleurClick = ControleurJeu(scene, vueJeu, jeu, j1, j2, saveFile)
        val controleurRegles = ControleurRegles(scene, vueJeu)
        val controleurParametres = ControleurParametres(scene, vueJeu)
        val controleurSave = ControleurSauvegardeJeu(jeu, j2 is JoueurIA, saveFile)
        val controleurBack = ControleurQuitterJeu(jeu, scene, saveFile, j2 is JoueurIA)
        vueJeu.buttonBack.addEventHandler(ActionEvent.ACTION, controleurBack)
        vueJeu.buttonParam.addEventHandler(ActionEvent.ACTION, controleurParametres)
        vueJeu.playGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, controleurClick)
        vueJeu.addEventHandler(KeyEvent.KEY_PRESSED, ControleurParametresRaccourci(scene, vueJeu))
        vueJeu.buttonRules.addEventHandler(ActionEvent.ACTION, controleurRegles)
        vueJeu.buttonSave.addEventHandler(ActionEvent.ACTION, controleurSave)
        vueJeu.addEventHandler(KeyEvent.KEY_PRESSED, ControleurParametresRaccourci(scene, vueJeu))

        vueJeu.setCoupsRestants(jeu.nbCoupsSansPriseRestants())
        vueJeu.setPseudo(j1, j2)
        vueJeu.remplirCases(jeu.getPLateau().getCases())
        scene.root = vueJeu
    }
}