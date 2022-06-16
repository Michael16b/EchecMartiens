package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.input.MouseEvent
import projet.echecmartien.librairie.JoueurIA
import projet.echecmartien.modele.GrandPion
import projet.echecmartien.modele.MoyenPion
import projet.echecmartien.modele.PetitPion
import projet.echecmartien.modele.deserialiser
import projet.echecmartien.vue.VueChargerJeu
import projet.echecmartien.vue.VueJeu
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

class ControleurChargerJeu(scene: Scene, vue: VueChargerJeu): EventHandler<ActionEvent> {

    private val scene : Scene
    private val vue: VueChargerJeu

    init {
        this.scene = scene
        this.vue = vue
    }

    override fun handle(e: ActionEvent) {
        val item = vue.myListSave.selectionModel.selectedItem

        if (item == null) {
            vue.showDialog("Charger une partie", "Aucune partie n'est sélectionnée")
            return
        }
        val saveFile = "$item.json"
        val filepath = "${getBaseDir()}/$saveFile"
        val file = File(filepath)

        if (!file.exists()) {
            vue.tabFichiers.remove(item)
            vue.showDialog("Charger une partie", "La partie n'existe $item pas ou plus.")
            return
        }

        val jeu = deserialiser(filepath)

        if (jeu == null) {
            vue.tabFichiers.remove(item)
            Files.deleteIfExists(Paths.get(filepath))
            vue.showDialog("Charger une partie", "La partie est corrompue et va être effacée.")
            return
        }

        val vueJeu = VueJeu()
        val joueurs = jeu.getJoueurs()
        val j1 = joueurs[0]!!
        val j2 = joueurs[1]!!
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
        vueJeu.setCoupsRestants(jeu.nbCoupsSansPriseRestants())

        for (j in joueurs) {
            var nbGrandPion = 0
            var nbMoyenPion = 0
            var nbPetitPion = 0

            for (p in j!!.getPionsCaptures()) {
                if (p is MoyenPion) {
                    nbMoyenPion++
                    continue
                }
                if (p is GrandPion) {
                    nbGrandPion++
                    continue
                }
                if (p is PetitPion) {
                    nbPetitPion++
                    continue
                }
            }
            val joueurIndice = if (j == j1) 0 else 1
            vueJeu.setPrise(joueurIndice, GrandPion(), nbGrandPion)
            vueJeu.setPrise(joueurIndice, PetitPion(), nbPetitPion)
            vueJeu.setPrise(joueurIndice, MoyenPion(), nbMoyenPion)
        }

        vueJeu.setScoreJoueurs(j1.calculerScore(), j2.calculerScore())
        scene.root = vueJeu

    }

}