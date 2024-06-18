package viewAdmin;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Visita;
import controller.Admin;
import java.util.*;
import javafx.geometry.*;

public class ModificaVisita {
    public Admin admin;
    public Map<String, Scene> scenes;
    public TextField searchField;
    public Visita visita;

    public ModificaVisita(Admin admin, Map<String, Scene> scenes, Visita visita) {
        this.admin = admin;
        this.scenes = scenes;
        this.visita = visita;
    }

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setSpacing(20);

        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(10);

        Button backButton = new Button("←");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Modifica Visita");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(backButton, titleLabel);

        VBox visitaBox = new VBox();
        visitaBox.setAlignment(Pos.CENTER);
        visitaBox.setSpacing(10);

        Label currentVisitaLabel = new Label("Visita: " + visita.getNomeVisita());
        currentVisitaLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label newVisitaLabel = new Label("Nuovo Nome Visita");
        searchField = new TextField();
        searchField.setStyle("-fx-pref-width: 200px; -fx-border-color: black; -fx-border-width: 1;");

        visitaBox.getChildren().addAll(currentVisitaLabel, newVisitaLabel, searchField);

        Button modificaButton = new Button("Modifica Visita");
        modificaButton.setStyle("-fx-background-color: #F0E68C;");
        modificaButton.setOnAction(this::modificaVisita);

        vbox.getChildren().addAll(topBox, visitaBox, modificaButton);

        return vbox;
    }

    private void modificaVisita(ActionEvent event) {
        String nomeVisita = searchField.getText();
        this.visita.setNomeVisita(nomeVisita);
        this.admin.modificaVisita(this.visita);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisite"));
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisite"));
    }
}
