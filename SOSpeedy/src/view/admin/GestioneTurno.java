package view.admin;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Turno;
import controller.Admin;
import java.util.*;

public class GestioneTurno {
    private Admin admin;
    private Map<String, Scene> scenes;
    private TextField medicoField;
    private TextField visitaField;
    private ComboBox<String> giornoSettimanaComboBox;

    public GestioneTurno(Admin admin, Map<String, Scene> scenes, Stage stage) {
        this.admin = admin;
        this.scenes = scenes;
    }

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));

        HBox topBox = new HBox();
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Gestione Turni");
        topBox.getChildren().addAll(backButton, titleLabel);

        HBox inputBox = new HBox();
        medicoField = new TextField();
        medicoField.setPromptText("Nome Medico");
        visitaField = new TextField();
        visitaField.setPromptText("Nome Visita");
        giornoSettimanaComboBox = new ComboBox<>(FXCollections.observableArrayList("Tutti", "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi", "Sabato", "Domenica"));
        giornoSettimanaComboBox.getSelectionModel().selectFirst();
        inputBox.getChildren().addAll(new Label("Nome Medico:"), medicoField, new Label("Nome Visita:"), visitaField, new Label("Giorno della Settimana:"), giornoSettimanaComboBox);

        VBox turniBox = new VBox();
        initializeListView(turniBox);

        Button addButton = new Button("Aggiungi Turno");
        addButton.setOnAction(this::aggiungiTurno);

        vbox.getChildren().addAll(topBox, inputBox, turniBox, addButton);

        return vbox;
    }

    private void initializeListView(VBox turniBox) {
        List<HBoxTurno> list = new ArrayList<>();
        for (Turno turno : admin.turni) {
            list.add(new HBoxTurno(turno, this.admin, this.scenes));
        }

        ObservableList<HBoxTurno> observableListTurni = FXCollections.observableList(list);
        ListView<HBoxTurno> listView = new ListView<>(observableListTurni);
        turniBox.getChildren().add(listView);

        // Add listener to admin.turni to update the ListView when it changes
        admin.oTurni.addListener((ListChangeListener<Turno>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateListView(observableListTurni);
                }
            }
        });
    }

    private void updateListView(ObservableList<HBoxTurno> observableListTurni) {
        observableListTurni.clear();
        for (Turno turno : admin.turni) {
            observableListTurni.add(new HBoxTurno(turno, this.admin, this.scenes));
        }
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }

    private void aggiungiTurno(ActionEvent event) {
    	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(new AggiungiTurno(this.admin,this.scenes).createContent(),400,300));
    }
}