package viewInfermiere;

import controller.Infermiere;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.util.*;

public class  Home {

    public Infermiere infermiere;
    public Map<String,Scene> scenes;

    public Home(Infermiere infermiere, Map<String,Scene> scenes) {
        this.infermiere = infermiere;
        this.scenes = scenes;
    }

    public VBox createContent() {
        // Creazione dell'interfaccia Home
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER); // center the buttons
        root.setPadding(new Insets(25,25,25,25));
        
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER);
        topBox.setPadding(new Insets(30));
        Label titleLabel = new Label("Benvenuto Infermiere");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(titleLabel);

        Button registraPazienteButton = new Button("Registra Paziente");
        Button deregistraPazienteButton = new Button("Deregistra Paziente");

        registraPazienteButton.setMaxWidth(200); // make the button as wide as its parent
        deregistraPazienteButton.setMaxWidth(200); // make the button as wide as its parent

        VBox.setMargin(registraPazienteButton, new Insets(10, 0, 10, 0)); // add margin to the button
        VBox.setMargin(deregistraPazienteButton, new Insets(10, 0, 10, 0)); // add margin to the button

        registraPazienteButton.setOnAction(event -> {
        // Navigazione verso la schermata Gestione Visite
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        
        stage.setScene(scenes.get("registraPaziente"));
        });
        
        deregistraPazienteButton.setOnAction(event -> {
        	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        	stage.setScene(scenes.get("deregistraPaziente"));
        });
        
        root.getChildren().addAll(topBox,registraPazienteButton, deregistraPazienteButton);
        return root;
    }
}