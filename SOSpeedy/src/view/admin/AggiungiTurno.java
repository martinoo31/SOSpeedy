package view.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Medico;
import model.Visita;
import model.Turno;
import controller.Admin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class AggiungiTurno {
    public Admin admin;
    public Map<String, Scene> scenes;
    public ComboBox<Medico> medicoComboBox;
    public ComboBox<Visita> visitaComboBox;
    public TextField startTimeField;
    public TextField endTimeField;
    public TextField startDateField;
    public TextField endDateField;

    public AggiungiTurno(Admin admin, Map<String, Scene> scenes) {
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
        Label titleLabel = new Label("Aggiungi Turno");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(backButton, titleLabel);

        VBox formBox = new VBox();
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);

        Label medicoLabel = new Label("Medico:");
        medicoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        medicoComboBox = new ComboBox<>(admin.oMedici);

        Label visitaLabel = new Label("Visita:");
        visitaLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        visitaComboBox = new ComboBox<>(admin.oVisite);

        HBox schedulingBox = new HBox();
        schedulingBox.setAlignment(Pos.CENTER);
        schedulingBox.setSpacing(10);

        Button weeklyButton = new Button("Programmazione Settimanale");
        weeklyButton.setStyle("-fx-background-color: #F0E68C;");

        Button dailyButton = new Button("Programmazione Giornaliera");
        dailyButton.setStyle("-fx-background-color: #F0E68C;");

        schedulingBox.getChildren().addAll(weeklyButton, dailyButton);

        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);
        dateBox.setSpacing(10);
        ProgrammazioneSettimanale progSettimana = new ProgrammazioneSettimanale();
        dateBox.getChildren().add(progSettimana.createContent());

        Button saveButton = new Button("Salva");
        saveButton.setOnAction(this::saveTurno);
        Button removeButton = new Button("Rimuovi Giornata");

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);
        bottomBox.getChildren().addAll(saveButton, removeButton);

        vbox.getChildren().addAll(topBox, medicoLabel, medicoComboBox, visitaLabel, visitaComboBox, schedulingBox, new HBox(), dateBox, bottomBox);

        return vbox;
    }

    private void saveTurno(ActionEvent event) {
        Medico selectedMedico = medicoComboBox.getValue();
        Visita selectedVisita = visitaComboBox.getValue();
        LocalDateTime startDateTime = LocalDateTime.parse(startDateField.getText() + "T" + startTimeField.getText());
        LocalDateTime endDateTime = LocalDateTime.parse(endDateField.getText() + "T" + endTimeField.getText());

        Turno newTurno = new Turno(admin.turni.size() + 1, startDateTime, endDateTime, selectedMedico, selectedVisita);
        admin.oTurni.add(newTurno);

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneTurni"));
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneTurni"));
    }
}
