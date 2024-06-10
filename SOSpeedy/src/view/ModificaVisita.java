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
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        HBox topBox = new HBox();
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Modifica Visita");
        topBox.getChildren().addAll(backButton, titleLabel);
        topBox.setPadding(new Insets(10));

        HBox visita = new HBox();
        Label searchLabel = new Label("Nuovo Nome Visita:");
        searchField = new TextField();
        visita.setPadding(new Insets(10));
        visita.getChildren().addAll(searchLabel,searchField);

        Button aggiungi = new Button("Modifica Visita");
        aggiungi.setOnAction(this::aggiungiVisita);

        VBox addButtonBox = new VBox();
        addButtonBox.getChildren().addAll(visita, aggiungi);
        addButtonBox.setSpacing(10);
        addButtonBox.setPadding(new Insets(10));

        vbox.getChildren().addAll(topBox, addButtonBox);
        return vbox;
    }

    private void aggiungiVisita(ActionEvent event) {
        String nomeVisita = searchField.getText();
        // Aggiungi la visita utilizzando il nome inserito nel campo di testo
        this.visita.setNomeVisita(nomeVisita);
        this.admin.modificaVisita(this.visita);

        // Cambia la scena o effettua altre operazioni necessarie
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisite"));
 
    }

    private void goBack(ActionEvent event) {
    	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	stage.setScene(scenes.get("gestioneVisite"));
    }
}