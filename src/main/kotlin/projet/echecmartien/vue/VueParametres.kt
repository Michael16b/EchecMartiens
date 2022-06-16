package projet.echecmartien.vue


import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import projet.echecmartien.controleur.ControleurFullScreen


class VueParametres : HBox() {

    val buttonContainer : VBox

    val buttonMusic : Button
    val buttonFullScreen : Button
    val buttonBack : Button

    init {
        buttonContainer = VBox()

        buttonMusic = Button("Music")
        buttonFullScreen = Button("Plein écran")
        //buttonFullScreen.addEventHandler(ActionEvent.ACTION, ControleurFullScreen())
        buttonBack = Button("Retour")

        buttonContainer.children.addAll(buttonMusic, buttonFullScreen, buttonBack)
        buttonContainer.spacing = 10.0
        buttonContainer.alignment = Pos.TOP_CENTER

        this.children.add(buttonContainer)


    }

}