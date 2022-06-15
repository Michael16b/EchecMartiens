package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox

class RulesVue: BorderPane() {

    val vboxGenerale: VBox
    val hboxHaut : HBox
    val hboxBas : HBox

    val vboxHautGauche : VBox
    val vboxHautDroit : VBox
    val vboxBasGauche : VBox
    val vboxBasDroit : VBox


    val boutonRetour : Button
    val labelTitreDeroulement : Label
    val labelExceptionTitre : Label
    val labelFinTitre : Label
    val labelPreparationTitre : Label
    val labelTitreGeneral : Label
    val labelException : Label
    val labelFinPartie : Label
    val labelPreparation : Label
    val labelDeroulement : Label
    val labelCompostition : Label

    init {
        this.style = "fx-background-image: url(\"https://images7.alphacoders.com/856/thumb-1920-856231.jpg\");"
        boutonRetour = Button("Retour")
        labelTitreGeneral = Label("Règles & composition du jeu")
        labelTitreDeroulement = Label("DEROULELEMENT")
        labelExceptionTitre = Label("EXCEPTION")
        labelFinTitre = Label("FIN DE LA PARTIE")
        labelPreparationTitre = Label("PREPARATION")

        labelDeroulement = Label("Chaque joueur, à son tour de jeu, déplace une de ses pièces.\n" +
                "Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases.\n" +
                "Les petits pions se déplacent diagonalement de 1 case.\n" +
                "A son tour de jeu un joueur peut déplacer n'importe quel pion de son camp,\n" +
                "soit à l'intérieur de sa zone soit vers la zone adverse.")

        labelException = Label("Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone.\n" +
                "Mais on peut déplacer ce même pion à l'intérieur de sa zone.\n" +
                "On capture un pion adverse en prenant sa place.\n" +
                "(donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse)\n" +
                "Le pion capturé est retiré du damier.\n" +
                "Le saut par dessus un ou n pions adverses ou non n'est pas autorisé.")

        labelFinPartie = Label("La partie est terminée une fois que tous les pions sont capturés\n" +
                "ou que plus aucune prise n'est possible.\n" +
                "On compte 3 points par grand pion capturé,\n" +
                "2 par moyen et 1 par petit.\n" +
                "Le gagnant est le joueur qui à le plus de points.")

        labelPreparation = Label("Disposez les 18 pions comme sur la figure ci-contre.\n" +
                "Un joueur identifie ses pièces par leur position à un instant donné.\n" +
                "Le damier est divisé en 2 zones, une pour chaque joueur.\n" +
                "Toute pièce dans la zone d'un joueur est la sienne.")

        labelCompostition = Label("1 plateau de jeu & 18 pions")

        hboxHaut = HBox()

        vboxHautGauche = VBox()
        vboxHautGauche.children.addAll(labelCompostition)

        vboxHautDroit = VBox()
        vboxHautDroit.children.addAll()

        hboxBas = HBox()
        vboxBasGauche = VBox()
        vboxBasDroit = VBox()

        vboxGenerale = VBox()
        vboxGenerale.children.addAll(labelTitreGeneral, hboxHaut, hboxBas, boutonRetour)
    }
}




