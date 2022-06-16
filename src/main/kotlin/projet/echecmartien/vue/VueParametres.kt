package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox


class VueParametres : BorderPane() {

    val buttonContainer : VBox
    val labelTitreContainer : VBox
    val labelTitre : Label
    val buttonFullScreen : Button
    val buttonBack : Button

    init {
        this.styleClass.add("parametres")
        buttonContainer = VBox()
        buttonContainer.alignment = Pos.CENTER

        labelTitre = Label("Paramètres")
        labelTitre.style = "-fx-font-family : 'Cambria'; -fx-font-size : 50; -fx-font-weight : bold"
        labelTitre.alignment = Pos.CENTER
        labelTitre.padding = Insets(40.0,0.0,0.0,0.0)

        labelTitreContainer = VBox()
        labelTitreContainer.children.addAll(labelTitre)
        labelTitreContainer.alignment = Pos.CENTER

        this.top = labelTitreContainer

        buttonFullScreen = Button("Plein écran")
        buttonBack = Button("Retour")

        buttonContainer.children.addAll(buttonFullScreen, buttonBack)
        buttonBack.prefWidth = 200.0
        buttonFullScreen.prefWidth = 200.0
        buttonContainer.spacing = 10.0
        this.center = buttonContainer
    }

}