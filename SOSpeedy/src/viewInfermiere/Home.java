package viewInfermiere;

import controller.Infermiere;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        root.setPadding(new Insets(15,15,15,15));

        Button registraPazienteButton = new Button("Registra Paziente");
        Button deregistraPazienteButton = new Button("Deregistra Paziente");

        registraPazienteButton.setMaxWidth(Double.MAX_VALUE); // make the button as wide as its parent
        deregistraPazienteButton.setMaxWidth(Double.MAX_VALUE); // make the button as wide as its parent

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
        
        root.getChildren().addAll(registraPazienteButton, deregistraPazienteButton);
        return root;
    }
}