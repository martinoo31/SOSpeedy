package view.medico;

import controller.Medico;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.CodiceColore;
import model.Paziente;
import model.PazienteInCoda;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HBoxPazienteInCoda extends HBox {
    private PazienteInCoda pazienteInCoda;
    private Button prendi;
    private Medico medico;
    private Map<String, Scene> scenes;
    private Stage stage;

    public HBoxPazienteInCoda(PazienteInCoda pazienteInCoda, Medico medico, Map<String, Scene> scenes, Stage stage) {
        super();
        this.pazienteInCoda = pazienteInCoda;
        this.prendi = new Button("Prendi In Carico");
        this.medico = medico;
        this.scenes = scenes;
        this.stage = stage;

        this.prendi.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        Label nomeLabel = new Label(this.pazienteInCoda.getNome());
        Label cognomeLabel = new Label(this.pazienteInCoda.getCognome());
        Label codiceLabel = new Label(this.pazienteInCoda.getCodiceIdentificativo());

        Rectangle codiceRect = new Rectangle(15, 15, getColorFromCodiceColore(this.pazienteInCoda.getCodiceColore()));
        codiceRect.setStrokeWidth(1.5);
        if(this.pazienteInCoda.getCodiceColore().equals(CodiceColore.BIANCO)) {
        	codiceRect.setStroke(Color.LIGHTGRAY);        	
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        this.prendi.setOnAction(e -> {
        	// aggioranmento file binari (una volta che ho preso in carico un paziente lo tolgo dai pazienti in coda)
        	
        	this.medico.pazientiInCoda.remove(this.pazienteInCoda);
        	
        	try (ObjectOutputStream pazientiInCodaStream = new ObjectOutputStream(new FileOutputStream("pazientiInCoda.bin"))) {
        		pazientiInCodaStream.writeObject(this.medico.pazientiInCoda);
        		System.out.println("Rimozione da pazientiInCoda del paziente preso in carico");
        		pazientiInCodaStream.close();
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
        	// ....
        	
            if (this.pazienteInCoda != null) {
                // Passa alla scena delle informazioni del paziente
                GestioneVisita gestione = new GestioneVisita(this.medico, this.scenes, this.stage, this.pazienteInCoda);
                this.stage.setScene(gestione.getScene());
            }
        });

//        this.prendi.setOnAction(this::prendiInCarico);

        this.setPadding(new Insets(10)); // Optional, to add padding around the HBox
        this.setSpacing(10); // Space between labels and button

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(20);
        inputGrid.setVgap(10);
        
        int columnCount = 4; // Number of columns
        double columnWidth12 = 150; // Fixed width for each column in pixels
        double columnWidth34 = 50;
        for (int i = 0; i < columnCount; i++) {
        	if(i==0 || i==1) {
        		ColumnConstraints column = new ColumnConstraints(columnWidth12);
        		inputGrid.getColumnConstraints().add(column);        		
        	}
            else {
            	ColumnConstraints column = new ColumnConstraints(columnWidth34);
        		inputGrid.getColumnConstraints().add(column);
            }
        }

        inputGrid.addRow(0, nomeLabel, cognomeLabel, codiceRect, codiceLabel );
        
        this.getChildren().addAll(inputGrid, spacer, this.prendi);
    }

    private Color getColorFromCodiceColore(CodiceColore codiceColore) {
        switch (codiceColore) {
            case BIANCO:
                return Color.WHITE;
            case VERDE:
                return Color.GREEN;
            case AZZURRO:
                return Color.BLUE;
            case ARANCIONE:
                return Color.ORANGE;
            case ROSSO:
                return Color.RED;
            default:
                return Color.WHITE; // Colore di default, puoi scegliere quello che preferisci
        }
    }

//    private void prendiInCarico(ActionEvent event) {
//        this.medico.prendiInCarico(pazienteInCoda);
//        
//        // forward alla pagina successiva (gestione visita)
//        this.stage.setScene(scenes.get("gestioneVisita"));
//        this.stage.show();
//        
////    	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
////        stage.setScene(scenes.get("gestioneVisita"));
//    }
}