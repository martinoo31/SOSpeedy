package viewMedico;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CodiceColore;
import model.Paziente;
import model.PazienteInCoda;
import controller.Medico;
import javafx.scene.layout.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Map;

public class GestioneVisita {
    private Medico medico;
    private Map<String, Scene> scenes;
    private Stage stage;
    private Paziente p;
    private Scene scena;
    
    private TextField nomeField;
    private TextField cognomeField;
    private DatePicker dataNascitaPicker;
    private TextField codiceFiscaleField;
    private ToggleGroup codiceColoreGroup;
    private TextArea descrizioneArea;
    private Label codiceIdentificativoLabel; // Label per il Codice Identificativo Paziente

    public GestioneVisita(Medico medico, Map<String, Scene> scenes, Stage stage, PazienteInCoda p) {
        this.medico = medico;
        this.scenes = scenes;
        this.stage = stage;
        this.p = new Paziente(p);
        this.scena = new Scene(this.createContent(), 300, 400);
    }

    public Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        // Top section with back button and title
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(0,0,20,0));

        Label titleLabel = new Label("Gestione Visita");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        topBox.getChildren().addAll(titleLabel);

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
        
//        Paziente p = medico.p;

        // Row 1
        Label l1=new Label("Paziente: ");
        l1.setStyle("-fx-font-weight: bold;");
        // ma la visita come e' collegata col paziente??
        Label l2=new Label(p.getNome() + " " + p.getCognome() + ", " + p.getCodiceFiscale());
//        l2.setStyle("-fx-font-weight: bold;");
        Button info = new Button("Informazioni");
        info.setOnAction(e -> showAlert());
        inputGrid.addRow(0, l1, l2, info);

        // Row 2
        Label l3=new Label("Visita:");
        l3.setStyle("-fx-font-weight: bold;");
//        Label l4=new Label("Codice fiscale:");
//        l4.setStyle("-fx-font-weight: bold;");
        inputGrid.addRow(1, l3);

        // ColoreBox
        HBox coloreBox = new HBox(10);
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
        
        switch (p.getCodiceColore()) {
        case BIANCO:
            whiteButton.setSelected(true);
            break;
        case VERDE:
            greenButton.setSelected(true);
            break;
        case AZZURRO:
            blueButton.setSelected(true);
            break;
        case ARANCIONE:
            yellowButton.setSelected(true);
            break;
        case ROSSO:
            redButton.setSelected(true);
            break;
        default:
            // Gestisci il caso in cui il codice colore non è riconosciuto
            break;
    }
        
        

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
        coloreBox.setPadding(new Insets(20,0,0,0));

        //aggiunta della descrizione
//        HBox cc = new HBox(10);
//        cc.setAlignment(Pos.CENTER_LEFT);
        Label l6 = new Label("Cartella Clinica: ");
        l6.setStyle("-fx-font-weight: bold;");
        Button ccb = new Button("Visualizza");
//        cc.getChildren().addAll(l6, ccb);
        inputGrid.addRow(2, l6, ccb);
        
//        VBox descrizioneBox = new VBox(10);
//        descrizioneBox.setAlignment(Pos.CENTER_LEFT);
//        descrizioneArea = new TextArea();
//        descrizioneArea.setPromptText("Descrizione");
//        VBox.setVgrow(descrizioneArea, Priority.ALWAYS);
//        Label descrizioneLabel = new Label("Descrizione:");
//        descrizioneLabel.setStyle("-fx-font-weight: bold;");
//        descrizioneBox.getChildren().addAll(descrizioneLabel, descrizioneArea);

        // Register button
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        Button assegna = new Button("Assegna Nuova Visita");
        assegna.setStyle("-fx-font-weight: bold;");
        assegna.setStyle("-fx-background-color: #8FBC8F; -fx-text-fill: white;");
        assegna.setOnAction(this::assegna);
        
        Button deregistra = new Button("Deregistra Paziente");
        deregistra.setStyle("-fx-font-weight: bold;");
        deregistra.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        deregistra.setOnAction(this::deregistra);
        
        buttons.getChildren().addAll(assegna, deregistra);
        buttons.setPadding(new Insets(100, 0, 0, 0));

//        // Label per mostrare il Codice Identificativo Paziente
//        codiceIdentificativoLabel = new Label();
//        codiceIdentificativoLabel.setStyle("-fx-font-weight: bold;");

        // Aggiunta di tutti i componenti al VBox principale
        vbox.getChildren().addAll(topBox, inputGrid, coloreBox, buttons);
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
        String codiceIdentificativo = medico.registraPaziente(nome, cognome, codiceFiscale, dataNascita, descrizione, colore);

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
    
    private void showAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informazioni paziente");
        alert.setHeaderText("Altre Informazioni");
        alert.setContentText(p.toString());

        alert.showAndWait();
    }
    
    private void assegna(ActionEvent event) {
    	AssegnaVisita assegna = new AssegnaVisita(this.scenes, this.stage, this.p, this.medico);
        this.stage.setScene(assegna.getScene());
    }
    
    private void deregistra(ActionEvent event) {
    	// deregistrare paziente dai file bin
    	
    	this.medico.pazienti.remove(this.p);
    	
    	try (ObjectOutputStream pazientiStream = new ObjectOutputStream(new FileOutputStream("pazienti.bin"))) {
    		pazientiStream.writeObject(this.medico.pazienti);
    		System.out.println("Rimozione da pazientiInCoda del paziente preso in carico");
    		pazientiStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
        
    	this.stage.setScene(scenes.get("home"));
    }
    
    public Scene getScene() {
        return scena;
    }
}