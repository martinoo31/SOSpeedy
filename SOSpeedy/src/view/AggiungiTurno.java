package view;

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
import java.time.LocalTime;
import java.util.Map;

public class AggiungiTurno {
    public Admin admin;
    public Map<String, Scene> scenes;
    public ComboBox<Medico> medicoComboBox;
    public ComboBox<Visita> visitaComboBox;
    public TextField startDateField;
    public TextField endDateField;
    public HBox dateBox;
    public ProgrammazioneSettimanale progSettimana;
    public ProgrammazioneGiornaliera progGiornata;

    public AggiungiTurno(Admin admin, Map<String, Scene> scenes) {
        this.admin = admin;
        this.scenes = scenes;
        this.progGiornata = new ProgrammazioneGiornaliera();
    }

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setSpacing(20);
        vbox.setStyle("-fx-background-color: #ffffff;");  // Use a light background color

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
        
        VBox medicoBox = new VBox();
        medicoBox.setAlignment(Pos.CENTER);
        medicoBox.setSpacing(10);
        Label medicoLabel = new Label("Medico:");
        medicoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        medicoComboBox = new ComboBox<>(admin.oMedici);
        medicoBox.getChildren().addAll(medicoLabel,medicoComboBox);
        
        
        VBox visitaBox = new VBox();
        visitaBox.setSpacing(10);
        Label visitaLabel = new Label("Visita:");
        visitaLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        visitaComboBox = new ComboBox<>(admin.oVisite);
        visitaBox.getChildren().addAll(visitaLabel,visitaComboBox);
        
        HBox topBucket = new HBox();
        topBucket.getChildren().addAll(medicoBox,visitaBox);
        topBucket.setAlignment(Pos.CENTER);
        topBucket.setSpacing(20);
        
        String comboBoxStyle = "-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5;";
        medicoComboBox.setStyle(comboBoxStyle);
        visitaComboBox.setStyle(comboBoxStyle);

        HBox schedulingBox = new HBox();
        schedulingBox.setAlignment(Pos.CENTER);
        schedulingBox.setSpacing(10);

        Button weeklyButton = new Button("Programmazione Settimanale");
        weeklyButton.setStyle("-fx-background-color: #F0E68C;");
        weeklyButton.setOnAction(this::cambiaProgSettimanale);

        Button dailyButton = new Button("Programmazione Giornaliera");
        dailyButton.setStyle("-fx-background-color: #F0E68C;");
        dailyButton.setOnAction(this::cambiaProgGiornaliera);
        
        String buttonStyle = "-fx-background-color: #f0e68c; -fx-border-color: #000000; -fx-border-radius: 5; -fx-background-radius: 5; -fx-padding: 5 10 5 10;";
        weeklyButton.setStyle(buttonStyle);
        dailyButton.setStyle(buttonStyle);
        
        schedulingBox.getChildren().addAll(weeklyButton, dailyButton);

        dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);
        dateBox.setSpacing(10);
        progSettimana = new ProgrammazioneSettimanale();
        dateBox.getChildren().add(progSettimana.createContent());

        Button saveButton = new Button("Salva Turno");
        saveButton.setOnAction(this::saveTurno);
        saveButton.setStyle(buttonStyle);


        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);
        bottomBox.getChildren().add(saveButton);
        
        
        vbox.getChildren().addAll(topBox, topBucket, schedulingBox, new HBox(), dateBox, bottomBox);
        saveButton.setStyle(buttonStyle);


        return vbox;
    }

    private void saveTurno(ActionEvent event) {
    	if(this.medicoComboBox.getValue() == null || this.visitaComboBox.getValue() == null)
    		return;
        	var listaPeriodi = this.progSettimana.getListPeriodo();
        	var estremi = this.progSettimana.getEstremi();
        	var eccezioni = this.progGiornata.getListEccezioni();
        	Turno turnoDaAggiungere = new Turno (this.admin.turni.size()+1,LocalDateTime.of(estremi[0], LocalTime.of(0, 0)),
        			LocalDateTime.of(estremi[1], LocalTime.MIDNIGHT),eccezioni,listaPeriodi,
        			this.medicoComboBox.getValue(),this.visitaComboBox.getValue());
        	this.admin.aggiungiTurno(turnoDaAggiungere);
        	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scenes.get("gestioneTurni"));
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneTurni"));
    }
    
    private void cambiaProgGiornaliera(ActionEvent event) {
    	this.dateBox.getChildren().remove(this.progSettimana.createContent());
    	this.dateBox.getChildren().add(this.progGiornata.createContent());
    }
    
    private void cambiaProgSettimanale(ActionEvent event) {
    	this.dateBox.getChildren().remove(this.progGiornata.createContent());
    	this.dateBox.getChildren().add(this.progSettimana.createContent());
    }
    
    
}
