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
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));

        // Top section with back button and title
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER_LEFT);
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Registra Paziente");
        topBox.getChildren().addAll(backButton, titleLabel);

        // Input fields
        HBox inputBox1 = new HBox(10);
        inputBox1.setAlignment(Pos.CENTER_LEFT);
        nomeField = new TextField();
        nomeField.setPromptText("Nome");
        cognomeField = new TextField();
        cognomeField.setPromptText("Cognome");
        inputBox1.getChildren().addAll(new Label("Nome:"), nomeField, new Label("Cognome:"), cognomeField);

        HBox inputBox2 = new HBox(10);
        inputBox2.setAlignment(Pos.CENTER_LEFT);
        dataNascitaPicker = new DatePicker();
        codiceFiscaleField = new TextField();
        codiceFiscaleField.setPromptText("Codice Fiscale");
        inputBox2.getChildren().addAll(new Label("Data di nascita:"), dataNascitaPicker, new Label("Codice fiscale:"), codiceFiscaleField);

        HBox coloreBox = new HBox(10);
        coloreBox.setAlignment(Pos.CENTER_LEFT);

        codiceColoreGroup = new ToggleGroup();

        // Creazione dei pulsanti di opzione e dei relativi rettangoli colorati
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
        HBox whiteBox = new HBox(5, whiteButton, whiteRect);
        HBox greenBox = new HBox(5, greenButton, greenRect);
        HBox blueBox = new HBox(5, blueButton, blueRect);
        HBox yellowBox = new HBox(5, yellowButton, yellowRect);
        HBox redBox = new HBox(5, redButton, redRect);

        // Aggiunta di tutti i box colorati al coloreBox
        coloreBox.getChildren().addAll(new Label("Codice Colore:"), whiteBox, greenBox, blueBox, yellowBox, redBox);

        // Description field
        HBox descrizioneBox = new HBox(10);
        descrizioneBox.setAlignment(Pos.CENTER_LEFT);
        descrizioneArea = new TextArea();
        descrizioneArea.setPromptText("Descrizione");
        descrizioneBox.getChildren().addAll(new Label("Descrizione:"), descrizioneArea);

        // Register button
        Button registraButton = new Button("Registra");
        registraButton.setOnAction(this::registraPaziente);

        // Label per mostrare il Codice Identificativo Paziente
        codiceIdentificativoLabel = new Label();
        codiceIdentificativoLabel.setStyle("-fx-font-weight: bold;");

        vbox.getChildren().addAll(topBox, inputBox1, inputBox2, coloreBox, descrizioneBox, registraButton, codiceIdentificativoLabel);

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
