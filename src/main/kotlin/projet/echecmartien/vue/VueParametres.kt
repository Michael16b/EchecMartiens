package projet.echecmartien.vue


import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox


class VueParametres : BorderPane() {

    val buttonContainer : VBox

    val buttonMusic : Button
    val buttonFullScreen : Button
    val buttonBack : Button

    init {
        buttonContainer = VBox()
        buttonContainer.alignment = Pos.CENTER

        buttonMusic = Button("Musique")
        buttonFullScreen = Button("Plein écran")
        //buttonFullScreen.addEventHandler(ActionEvent.ACTION, ControleurFullScreen())
        buttonBack = Button("Retour")

        buttonContainer.children.addAll(buttonMusic, buttonFullScreen, buttonBack)
        buttonBack.prefWidth = 200.0
        buttonFullScreen.prefWidth = 200.0
        buttonMusic.prefWidth = 200.0
        buttonContainer.spacing = 10.0
        this.center = buttonContainer



    }

}