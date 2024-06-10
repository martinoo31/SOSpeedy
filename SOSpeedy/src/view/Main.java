package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.*;
import controller.Admin;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione dell'interfaccia Home
        Admin admin = new Admin();
        Map<String,Scene> scenes = new HashMap<String,Scene>();
        Home home = new Home(admin, scenes);
        scenes.put("home", new Scene(home.createContent(), 400, 300));
        GestioneVisite gestioneVisite = new GestioneVisite(admin,scenes);
        scenes.put("gestioneVisite", new Scene(gestioneVisite.createContent(), 400, 300));


        primaryStage.setTitle("Admin");
        primaryStage.setScene(scenes.get("home"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
