module projet.echecmartien {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.google.gson;
    requires java.desktop;

    opens projet.echecmartien to javafx.fxml;
    opens projet.echecmartien.modele to com.google.gson;
    opens projet.echecmartien.librairie to com.google.gson;
    exports projet.echecmartien;
}
