package projet.echecmartien



import javafx.application.Application
import javafx.event.ActionEvent
import javafx.scene.Scene
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurBackFromRules
import projet.echecmartien.controleur.ControleurLoadJeu
import projet.echecmartien.controleur.ControleurNewJeu
import projet.echecmartien.controleur.ControleurRules
import projet.echecmartien.vue.GameVue
import projet.echecmartien.vue.MainVue

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

        val root = MainVue()

        val sceneWidth = 1100.0
        val sceneHeight = 700.0
        val scene = Scene(root, sceneWidth, sceneHeight)

        val controleurRulesVue = ControleurRules(scene, root)
        root.buttonRules.addEventHandler(ActionEvent.ACTION,controleurRulesVue)

        val rootGameVue = GameVue()
        val controleurNewJeu = ControleurNewJeu(scene, root)
        root.buttonNewGame.addEventHandler(ActionEvent.ACTION,controleurNewJeu)

        val controleurBackGameToMainVue = ControleurBackFromRules(scene,root)
        rootGameVue.buttonBack.addEventHandler(ActionEvent.ACTION,controleurBackGameToMainVue)

        val controleurLoadJeuVue = ControleurLoadJeu(scene, root)
        root.buttonLoadGame.addEventHandler(ActionEvent.ACTION,controleurLoadJeuVue)



        scene.stylesheets.add(AppliJeuEchecMartien::class.java.getResource("style.css")!!.toExternalForm())
        root.styleClass.add("main")

        // Ajout du CSS des boutons des différentes vues

        // MainVue
        // Bouton
        root.buttonRules.styleClass.add("button")
        root.buttonNewGame.styleClass.add("button")
        root.buttonLoadGame.styleClass.add("button")
        // Label
        root.labelTitle.styleClass.add("title")
        root.labelPlayer1.styleClass.add("LabelBase")
        root.labelPlayer2.styleClass.add("LabelBase")
        root.labelIA.styleClass.add("LabelBase")
        root.labelCoups.styleClass.add("LabelBase")

        primaryStage.minHeight = sceneHeight+40.0
        primaryStage.minWidth = sceneWidth+15.0
        primaryStage.title="Echecs Martiens"
        primaryStage.scene=scene
        primaryStage.show()
    }

}

fun main(){
    Application.launch(AppliJeuEchecMartien::class.java)
}



