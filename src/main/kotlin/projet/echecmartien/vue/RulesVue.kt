package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.layout.BorderPane

class RulesVue: BorderPane() {

    val buttonBack: Button

    init {
        buttonBack = Button("Retour")
        this.center = buttonBack
        this.style = "fx-background-image: url(\"https://images7.alphacoders.com/856/thumb-1920-856231.jpg\");"
    }
}




