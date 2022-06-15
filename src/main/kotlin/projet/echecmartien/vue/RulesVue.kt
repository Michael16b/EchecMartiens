package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

class RulesVue: BorderPane() {

    val vboxDroite : VBox
    val vboxGauche : VBox

    val boutonRetour : Button
    val labelDeroulementTitre : Label
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
        this.styleClass.add("rules")
        boutonRetour = Button("Retour")

        labelTitreGeneral = Label("Règles & composition du jeu")
        labelTitreGeneral.style = "-fx-font-style : 'Cambria'"
        labelTitreGeneral.style = "-fx-font-size : 40"

        labelDeroulementTitre = Label("DEROULEMENT")
        labelDeroulementTitre.style = "-fx-font-style : 'Cambria'"
        labelDeroulementTitre.style = "-fx-font-size : 22"

        labelExceptionTitre = Label("EXCEPTION")
        labelExceptionTitre.style = "-fx-font-style : 'Cambria'"
        labelExceptionTitre.style = "-fx-font-size : 22"

        labelFinTitre = Label("FIN DE LA PARTIE")
        labelFinTitre.style = "-fx-font-style : 'Cambria'"
        labelFinTitre.style = "-fx-font-size : 22"

        labelPreparationTitre = Label("PREPARATION")
        labelPreparationTitre.style = "-fx-font-style : 'Cambria'"
        labelPreparationTitre.style = "-fx-font-size : 22"

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

        vboxGauche = VBox()
        vboxGauche.children.addAll(labelCompostition, labelPreparationTitre, labelPreparation)
        vboxGauche.spacing = 30.0
        vboxGauche.padding = Insets(0.0, 0.0, 0.0, 20.0)

        vboxDroite = VBox()
        vboxDroite.children.addAll(labelDeroulementTitre, labelDeroulement, labelExceptionTitre, labelException, labelFinTitre, labelFinPartie)
        vboxDroite.spacing = 30.0
        vboxDroite.padding = Insets(0.0, 20.0, 0.0, 0.0)


        labelTitreGeneral.padding = Insets(10.0, 0.0, 50.0, 0.0)
        setAlignment(labelTitreGeneral, Pos.CENTER)
        setAlignment(boutonRetour, Pos.TOP_CENTER)

        this.left = vboxGauche
        this.left.prefWidth(300.0)
        this.right = vboxDroite
        this.right.prefWidth(300.0)
        this.top = labelTitreGeneral
        this.bottom = boutonRetour


    }
}




