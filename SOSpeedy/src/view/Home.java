package view;

import controller.Admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.*;
import java.util.*;

public class  Home {

    public Admin admin;
    public Map<String,Scene> scenes;

    public Home(Admin admin, Map<String,Scene> scenes) {
        this.admin = admin;
        this.scenes = scenes;
    }

    public VBox createContent() {
        // Creazione dell'interfaccia Home
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER); // center the buttons
        root.setPadding(new Insets(15,15,15,15));

        Button gestioneVisiteButton = new Button("Gestione Visite");
        Button gestioneTurniButton = new Button("Gestione Turni");
        Button logButton = new Button("Log");

        gestioneVisiteButton.setMaxWidth(Double.MAX_VALUE); // make the button as wide as its parent
        gestioneTurniButton.setMaxWidth(Double.MAX_VALUE); // make the button as wide as its parent
        logButton.setMaxWidth(Double.MAX_VALUE); // make the button as wide as its parent

        VBox.setMargin(gestioneVisiteButton, new Insets(10, 0, 10, 0)); // add margin to the button
        VBox.setMargin(gestioneTurniButton, new Insets(10, 0, 10, 0)); // add margin to the button
        VBox.setMargin(logButton, new Insets(10, 0, 10, 0)); // add margin to the button

        gestioneVisiteButton.setOnAction(event -> {
            // Navigazione verso la schermata Gestione Visite
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scenes.get("gestioneVisite"));
        });
        
        gestioneTurniButton.setOnAction(event -> {
        	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        	stage.setScene(scenes.get("gestioneTurni"));
        });

        root.getChildren().addAll(gestioneVisiteButton, gestioneTurniButton, logButton);
        return root;
    }
}