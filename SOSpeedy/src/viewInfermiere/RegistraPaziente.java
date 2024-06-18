package viewInfermiere;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CodiceColore;
import controller.Infermiere;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.Map;

public class RegistraPaziente {
    private Infermiere infermiere;
    private Map<String, Scene> scenes;
    private TextField nomeField;
    private TextField cognomeField;
    private DatePicker dataNascitaPicker;
    private TextField codiceFiscaleField;
    private ToggleGroup codiceColoreGroup;
    private TextArea descrizioneArea;
    private Label codiceIdentificativoLabel; // Label per il Codice Identificativo Paziente

    public RegistraPaziente(Infermiere infermiere, Map<String, Scene> scenes) {
        this.infermiere = infermiere;
        this.scenes = scenes;
    }

    public Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        // Top section with back button and title
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(0,0,20,0));

        Button backButton = new Button("←");
        backButton.setAlignment(Pos.CENTER_LEFT);
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Registra Paziente");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        topBox.getChildren().addAll(backButton, titleLabel);

        // Input fields
        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        nomeField = new TextField();
        nomeField.setPromptText("Nome");
        cognomeField = new TextField();
        cognomeField.setPromptText("Cognome");
        dataNascitaPicker = new DatePicker();
        codiceFiscaleField = new TextField();
        codiceFiscaleField.setPromptText("Codice Fiscale");

        // Row 1
        Label l1=new Label("Nome:");
        l1.setStyle("-fx-font-weight: bold;");
        Label l2=new Label("Cognome:");
        l2.setStyle("-fx-font-weight: bold;");
        inputGrid.addRow(0, l1, nomeField, l2, cognomeField);

        // Row 2
        Label l3=new Label("Data di nascita:");
        l3.setStyle("-fx-font-weight: bold;");
        Label l4=new Label("Codice fiscale:");
        l4.setStyle("-fx-font-weight: bold;");
        inputGrid.addRow(1, l3, dataNascitaPicker, l4, codiceFiscaleField);

        // ColoreBox
        VBox coloreBox = new VBox(10);
        coloreBox.setAlignment(Pos.CENTER_LEFT);

        codiceColoreGroup = new ToggleGroup();

        // Creazione dei pulsanti di opzione
        RadioButton whiteButton = new RadioButton("bianco");
        RadioButton greenButton = new RadioButton("verde");
        RadioButton blueButton = new RadioButton("azzurro");
        RadioButton yellowButton = new RadioButton("arancione");
        RadioButton redButton = new RadioButton("rosso");

        whiteButton.setToggleGroup(codiceColoreGroup);
        greenButton.setToggleGroup(codiceColoreGroup);
        blueButton.setToggleGroup(codiceColoreGroup);
        yellowButton.setToggleGroup(codiceColoreGroup);
        redButton.setToggleGroup(codiceColoreGroup);

     // Creazione dei rettangoli colorati
        Rectangle whiteRect = new Rectangle(15, 15, Color.WHITE);
        Rectangle greenRect = new Rectangle(15, 15, Color.GREEN);
        Rectangle blueRect = new Rectangle(15, 15, Color.BLUE);
        Rectangle yellowRect = new Rectangle(15, 15, Color.ORANGE);
        Rectangle redRect = new Rectangle(15, 15, Color.RED);

        // Creazione di HBox per ogni colore con pulsante e rettangolo
        HBox whiteBox = new HBox(5, whiteRect, whiteButton);
        HBox greenBox = new HBox(5, greenRect, greenButton);
        HBox blueBox = new HBox(5, blueRect, blueButton);
        HBox yellowBox = new HBox(5, yellowRect, yellowButton);
        HBox redBox = new HBox(5, redRect, redButton);

        // Aggiunta di tutti i box colorati al coloreBox
        Label l5=new Label("Codice Colore:");
        l5.setStyle("-fx-font-weight: bold;");
        coloreBox.getChildren().addAll(l5, whiteBox, greenBox, blueBox, yellowBox, redBox);

        //aggiunta della descrizione
        VBox descrizioneBox = new VBox(10);
        descrizioneBox.setAlignment(Pos.CENTER_LEFT);
        descrizioneArea = new TextArea();
        descrizioneArea.setPromptText("Descrizione");
        VBox.setVgrow(descrizioneArea, Priority.ALWAYS);
        Label descrizioneLabel = new Label("Descrizione:");
        descrizioneLabel.setStyle("-fx-font-weight: bold;");
        descrizioneBox.getChildren().addAll(descrizioneLabel, descrizioneArea);

        // Register button
        Button registraButton = new Button("Registra");
        registraButton.setStyle("-fx-font-weight: bold;");
        registraButton.setStyle("-fx-background-color: #8FBC8F;");
        registraButton.setOnAction(this::registraPaziente);

        // Label per mostrare il Codice Identificativo Paziente
        codiceIdentificativoLabel = new Label();
        codiceIdentificativoLabel.setStyle("-fx-font-weight: bold;");

        // Aggiunta di tutti i componenti al VBox principale
        vbox.getChildren().addAll(topBox, inputGrid, coloreBox, descrizioneBox, registraButton, codiceIdentificativoLabel);
        VBox.setVgrow(vbox, Priority.ALWAYS);

        return vbox;
    }


    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }

    private void registraPaziente(javafx.event.ActionEvent event) {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        LocalDate dataNascita = dataNascitaPicker.getValue();
        String codiceFiscale = codiceFiscaleField.getText();
        String codiceColore = ((RadioButton) codiceColoreGroup.getSelectedToggle()).getText();
        String descrizione = descrizioneArea.getText();
        
        CodiceColore colore = null;
        switch (codiceColore) {
            case "bianco":
                colore = CodiceColore.BIANCO;
                break;
            case "verde":
                colore = CodiceColore.VERDE;
                break;
            case "azzurro":
                colore = CodiceColore.AZZURRO;
                break;
            case "arancione":
                colore = CodiceColore.ARANCIONE;
                break;
            case "rosso":
                colore = CodiceColore.ROSSO;
                break;
        }

        // Registra il paziente e ottieni il Codice Identificativo Paziente
        String codiceIdentificativo = infermiere.registraPaziente(nome, cognome, codiceFiscale, dataNascita, descrizione, colore);

        // Mostra il Codice Identificativo Paziente nel label
        codiceIdentificativoLabel.setText("Codice Identificativo Paziente: " + codiceIdentificativo);

        // Clear the input fields after registering the patient
        nomeField.clear();
        cognomeField.clear();
        dataNascitaPicker.setValue(null);
        codiceFiscaleField.clear();
        codiceColoreGroup.selectToggle(null);
        descrizioneArea.clear();
    }
}
