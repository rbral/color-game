module com.example.dsii_pa_01_game {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.dsii_pa_01_game to javafx.fxml;
    exports com.example.dsii_pa_01_game;
}