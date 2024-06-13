package viewInfermiere;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.Infermiere;

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
        RadioButton whiteButton = new RadioButton();
        RadioButton greenButton = new RadioButton();
        RadioButton blueButton = new RadioButton();
        RadioButton yellowButton = new RadioButton();
        RadioButton redButton = new RadioButton();
        whiteButton.setToggleGroup(codiceColoreGroup);
        greenButton.setToggleGroup(codiceColoreGroup);
        blueButton.setToggleGroup(codiceColoreGroup);
        yellowButton.setToggleGroup(codiceColoreGroup);
        redButton.setToggleGroup(codiceColoreGroup);
        coloreBox.getChildren().addAll(new Label("Codice Colore:"), whiteButton, greenButton, blueButton, yellowButton, redButton);

        // Description field
        HBox descrizioneBox = new HBox(10);
        descrizioneBox.setAlignment(Pos.CENTER_LEFT);
        descrizioneArea = new TextArea();
        descrizioneArea.setPromptText("Descrizione");
        descrizioneBox.getChildren().addAll(new Label("Descrizione:"), descrizioneArea);

        // Register button
        Button registraButton = new Button("Registra");
        registraButton.setOnAction(this::registraPaziente);

        vbox.getChildren().addAll(topBox, inputBox1, inputBox2, coloreBox, descrizioneBox, registraButton);

        return vbox;
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }

    private void registraPaziente(javafx.event.ActionEvent event) {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String dataNascita = dataNascitaPicker.getValue().toString();
        String codiceFiscale = codiceFiscaleField.getText();
        String codiceColore = ((RadioButton) codiceColoreGroup.getSelectedToggle()).getText();
        String descrizione = descrizioneArea.getText();

        // Implement the logic to register the patient using the input fields
        // Example: infermiere.registraPaziente(new Paziente(nome, cognome, dataNascita, codiceFiscale, codiceColore, descrizione));

        // Clear the input fields after registering the patient
        nomeField.clear();
        cognomeField.clear();
        dataNascitaPicker.setValue(null);
        codiceFiscaleField.clear();
        codiceColoreGroup.selectToggle(null);
        descrizioneArea.clear();
    }
}
