package projet.echecmartien.vue

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment

class RulesVue: BorderPane() {

    val orga : GridPane
    val vboxBouton : VBox

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

        labelDeroulement = Label("Chaque joueur, à son tour de jeu, déplace une de ses pièces. Les grands pions se déplacent verticalement, horizontalement et diagonalement de n cases. Les petits pions se déplacent diagonalement de 1 case. A son tour de jeu un joueur peut déplacer n'importe quel pion de son camp, soit à l'intérieur de sa zone soit vers la zone adverse.")
        labelDeroulement.isWrapText = true
        labelDeroulement.textAlignment = TextAlignment.JUSTIFY

        labelException = Label("Il est interdit de renvoyer dans la zone adverse un pion qui vient d'arriver dans sa zone. Mais on peut déplacer ce même pion à l'intérieur de sa zone. On capture un pion adverse en prenant sa place (donc fatalement en prenant un pion de sa zone et en allant dans la zone adverse). Le pion capturé est retiré du damier. Le saut par dessus un ou n pions adverses ou non n'est pas autorisé.")
        labelException.isWrapText = true
        labelException.textAlignment = TextAlignment.JUSTIFY

        labelFinPartie = Label("La partie est terminée une fois que tous les pions sont capturés ou que plus aucune prise n'est possible. On compte 3 points par grand pion capturé, 2 par moyen et 1 par petit. Le gagnant est le joueur qui à le plus de points.")
        labelFinPartie.isWrapText = true
        labelFinPartie.textAlignment = TextAlignment.JUSTIFY

        labelPreparation = Label("Disposez les 18 pions comme sur la figure ci-contre. Un joueur identifie ses pièces par leur position à un instant donné. Le damier est divisé en 2 zones, une pour chaque joueur. Toute pièce dans la zone d'un joueur est la sienne.")
        labelPreparation.isWrapText = true
        labelPreparation.textAlignment = TextAlignment.JUSTIFY

        labelCompostition = Label("1 plateau de jeu & 18 pions")

        orga = GridPane()
        orga.add(labelCompostition, 0, 3)
        orga.add(labelDeroulementTitre, 1, 0)
        orga.add(labelDeroulement, 1, 1)
        orga.add(labelPreparationTitre, 0, 4)
        orga.add(labelPreparation, 0, 5)

        orga.add(labelExceptionTitre, 1, 2)
        orga.add(labelException, 1, 3)
        orga.add(labelFinTitre, 1, 4)
        orga.add(labelFinPartie, 1, 5)

        orga.vgap = 20.0
        orga.hgap = 20.0
        orga.padding = Insets(0.0, 20.0, 0.0, 20.0)
        orga.isGridLinesVisible = true

        val c1 = ColumnConstraints()
        c1.percentWidth = 50.0
        val c2 = ColumnConstraints()
        c2.percentWidth = 50.0
        orga.columnConstraints.addAll(c1, c2)

        labelTitreGeneral.padding = Insets(10.0, 0.0, 50.0, 0.0)
        setAlignment(labelTitreGeneral, Pos.CENTER)

        vboxBouton = VBox()
        vboxBouton.children.addAll(boutonRetour)
        vboxBouton.padding = Insets(50.0, 0.0, 50.0, 0.0)
        vboxBouton.alignment = Pos.CENTER

        this.center = orga
        this.top = labelTitreGeneral
        this.bottom = vboxBouton

    }
}




