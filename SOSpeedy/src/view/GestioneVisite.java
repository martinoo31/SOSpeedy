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
import controller.Admin;
import java.util.*;
import javafx.geometry.*;

public class GestioneVisite {
    public Admin admin;
    public Map<String,Scene> scenes;

    public GestioneVisite(Admin admin, Map<String,Scene> scenes) {
        this.admin = admin;
        this.scenes = scenes;
    }

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15,15,15,15));

        HBox topBox = new HBox();
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Gestione Visite");
        topBox.getChildren().addAll(backButton, titleLabel);

        HBox searchBox = new HBox();
        Label searchLabel = new Label("Ricerca Visita:");
        TextField searchField = new TextField();
        searchBox.getChildren().addAll(searchLabel, searchField);

        VBox patientsBox = new VBox();
        Label patientsLabel = new Label("Elenco delle visite:");
        patientsBox.getChildren().add(patientsLabel);
        for(int i=0;i<admin.visite.size();i++){
            HBox patient = new HBox(new Label(admin.visite.get(i).getNomeVisita()), new Button("Modifica"), new Button("Elimina"));
            patientsBox.getChildren().add(patient);
        }

        

        Button addButton = new Button("Aggiungi Visite");

        vbox.getChildren().addAll(topBox, searchBox, patientsBox, addButton);

        return vbox;
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }
}
