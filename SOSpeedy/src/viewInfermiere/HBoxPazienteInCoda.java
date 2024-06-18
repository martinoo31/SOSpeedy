package viewInfermiere;

import controller.Infermiere;
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
import model.PazienteInCoda;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Map;

public class HBoxPazienteInCoda extends HBox {
    private PazienteInCoda pazienteInCoda;
    private Button deregistra;
    private Infermiere infermiere;
    private Map<String, Scene> scenes;

    public HBoxPazienteInCoda(PazienteInCoda pazienteInCoda, Infermiere infermiere, Map<String, Scene> scenes) {
        super();
        this.pazienteInCoda = pazienteInCoda;
        this.deregistra = new Button("Deregistra");
        this.infermiere = infermiere;
        this.scenes = scenes;

        this.deregistra.setStyle("-fx-background-color: #F08080;");

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

        this.deregistra.setOnAction(this::deregistra);

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
        
        this.getChildren().addAll(inputGrid, spacer, this.deregistra);
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

    private void deregistra(ActionEvent event) {
        this.infermiere.deregistraPaziente(pazienteInCoda);
    }
}
