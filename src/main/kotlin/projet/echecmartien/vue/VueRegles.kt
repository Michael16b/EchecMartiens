package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.*

class VueRegles: HBox() {

    val orga : GridPane
    val vboxBouton : VBox
    val hboxTitre : HBox
    val vboxorga : VBox
    val compositionHbox : HBox
    val gridGeneral : GridPane

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
    val labelComposition : Label

    init {
        this.styleClass.add("rules")
        boutonRetour = Button("Retour")
        this.styleClass.add("main")

        labelTitreGeneral = Label("RÈGLES & COMPOSITION DU JEU")
        this.labelTitreGeneral.styleClass.add("MainTitleRules")

        labelDeroulementTitre = Label("DÉROULEMENT")
        this.labelDeroulementTitre.styleClass.add("SubtitleRules")

        labelExceptionTitre = Label("EXCEPTION")
        this.labelExceptionTitre.styleClass.add("SubtitleRules")

        labelFinTitre = Label("FIN DE LA PARTIE")
        this.labelFinTitre.styleClass.add("SubtitleRules")

        labelPreparationTitre = Label("PRÉPARATION")
        this.labelPreparationTitre.styleClass.add("SubtitleRules")

        labelDeroulement = Label("Chaque joueur, à son tour de jeu, déplace une de ses pièces. Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases. Les petits pions se déplacent diagonalement de 1 case. A son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse.")
        labelDeroulement.prefWidth = 600.0
        this.labelDeroulement.styleClass.add("rulesText")


        labelException = Label("Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone. On capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier. Le saut par dessus un ou n pions adverses ou non n'est pas autorisé.")
        labelException.prefWidth = 600.0
        this.labelException.styleClass.add("rulesText")

        labelFinPartie = Label("La partie est terminée une fois que tous les pions sont capturés ou que plus aucune prise n'est possible. On compte 3 points par grand pion capturé, 2 par moyen et 1 par petit. Le gagnant est le joueur qui à le plus de points.")
        labelFinPartie.prefWidth = 600.0
        this.labelFinPartie.styleClass.add("rulesText")

        labelPreparation = Label("Disposez les 18 pions comme sur la figure ci-contre. Un joueur identifie ses pièces par leur position à un instant donné. Le damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne.")
        labelPreparation.prefWidth = 600.0
        this.labelPreparation.styleClass.add("rulesText")

        labelComposition = Label("1 plateau de jeu et 18 pions")
        this.labelComposition.styleClass.add("rulesText")

        orga = GridPane()
        compositionHbox = HBox()
        compositionHbox.children.addAll(labelComposition)
        compositionHbox.alignment = Pos.CENTER
        orga.add(compositionHbox, 0, 3)

        orga.add(labelDeroulementTitre, 1, 0)
        orga.add(labelDeroulement, 1, 1)
        orga.add(labelPreparationTitre, 0, 4)
        orga.add(labelPreparation, 0, 5)

        orga.add(labelExceptionTitre, 1, 2)
        orga.add(labelException, 1, 3)
        orga.add(labelFinTitre, 1, 4)
        orga.add(labelFinPartie, 1, 5)

        val image = ImageView(javaClass.getResource("/plateau.png")?.toExternalForm())
        image.isPreserveRatio = true
        image.fitHeight = 250.0
        val imageContainer = HBox(image)
        imageContainer.alignment = Pos.BOTTOM_CENTER
        orga.add(imageContainer, 0, 0, 1, 3)

        orga.vgap = 20.0
        orga.hgap = 20.0
        orga.padding = Insets(0.0, 20.0, 0.0, 20.0)

        val c1 = ColumnConstraints()
        c1.percentWidth = 50.0
        val c2 = ColumnConstraints()
        c2.percentWidth = 50.0
        orga.columnConstraints.addAll(c1, c2)

        vboxorga = VBox()
        vboxorga.children.addAll(orga)
        vboxorga.alignment = Pos.CENTER

        hboxTitre = HBox()
        hboxTitre.children.addAll(labelTitreGeneral)
        hboxTitre.alignment = Pos.CENTER

        vboxBouton = VBox()
        vboxBouton.children.addAll(boutonRetour)
        vboxBouton.alignment = Pos.CENTER

        gridGeneral = GridPane()
        val r1 = RowConstraints()
        r1.percentHeight = 20.0
        val r2 = RowConstraints()
        r2.percentHeight = 60.0
        val r3 = RowConstraints()
        r3.percentHeight = 20.0
        gridGeneral.rowConstraints.addAll(r1, r2, r3)

        gridGeneral.add(hboxTitre, 0, 0)
        gridGeneral.add(vboxorga, 0, 1)
        gridGeneral.add(vboxBouton, 0, 2)

        this.children.addAll(gridGeneral)
        this.alignment = Pos.CENTER
    }
}




