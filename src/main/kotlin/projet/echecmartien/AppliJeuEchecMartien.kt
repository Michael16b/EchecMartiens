package projet.echecmartien

import javafx.application.Application

import javafx.stage.Stage

class AppliJeuEchecMartien: Application() {
    override fun start(primaryStage: Stage) {

       TODO()
      

    }

}

fun main(){
    val posGrandPion = arrayOf(arrayOf(0, 0), arrayOf(0, 1), arrayOf(1, 0))

    for (pos in posGrandPion) {
        println("${pos[0]}, ${pos[1]}")
        println("${8-1-pos[0]}, ${4-1-pos[1]}")
    }
    Application.launch(AppliJeuEchecMartien::class.java)
}



