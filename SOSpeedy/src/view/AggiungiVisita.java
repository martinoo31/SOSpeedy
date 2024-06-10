package view;

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

public class AggiungiVisita {
    public Admin admin;
    public Map<String, Scene> scenes;
    public TextField searchField;

    public AggiungiVisita(Admin admin, Map<String, Scene> scenes) {
        this.admin = admin;
        this.scenes = scenes;
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
        Label titleLabel = new Label("Aggiungi Visita");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(backButton, titleLabel);

        VBox visitaBox = new VBox();
        visitaBox.setAlignment(Pos.CENTER);
        visitaBox.setSpacing(10);

        Label searchLabel = new Label("Nuova Visita");
        searchLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        searchField = new TextField();
        searchField.setPromptText("Nome Visita");
        searchField.setStyle("-fx-pref-width: 200px; -fx-border-color: black; -fx-border-width: 1;");

        visitaBox.getChildren().addAll(searchLabel, searchField);

        Button aggiungiButton = new Button("Aggiungi Visita");
        aggiungiButton.setStyle("-fx-background-color: #8FBC8F;");
        aggiungiButton.setOnAction(this::aggiungiVisita);

        vbox.getChildren().addAll(topBox, visitaBox, aggiungiButton);

        return vbox;
    }

    private void aggiungiVisita(ActionEvent event) {
        String nomeVisita = searchField.getText();
        this.admin.add(new Visita(nomeVisita));

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisite"));
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisite"));
    }
}
