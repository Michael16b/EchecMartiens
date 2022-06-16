package projet.echecmartien.vue

import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox


class VueParametres : BorderPane() {

    val buttonContainer : VBox

    val buttonFullScreen : Button
    val buttonBack : Button

    init {
        buttonContainer = VBox()
        buttonContainer.alignment = Pos.CENTER

        buttonFullScreen = Button("Plein écran")
        buttonBack = Button("Retour")

        buttonContainer.children.addAll(buttonFullScreen, buttonBack)
        buttonBack.prefWidth = 200.0
        buttonFullScreen.prefWidth = 200.0
        buttonContainer.spacing = 10.0
        this.center = buttonContainer
    }

}