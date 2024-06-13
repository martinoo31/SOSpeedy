package view.admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.medico.Login;
import javafx.geometry.*;
import controller.Admin;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione dell'interfaccia Home
        Admin admin = new Admin();
        Map<String,Scene> scenes = new HashMap<String,Scene>();
        
        Login login = new Login(admin, scenes);
        scenes.put("login", new Scene(login.createContent()));
        
        Home home = new Home(admin, scenes);
        scenes.put("home", new Scene(home.createContent()));
        
        GestioneVisite gestioneVisite = new GestioneVisite(admin,scenes,primaryStage);
        scenes.put("gestioneVisite", new Scene(gestioneVisite.createContent()));
        
        AggiungiVisita aggVisita = new AggiungiVisita(admin,scenes);
        scenes.put("aggiungiVisite", new Scene(aggVisita.createContent()));
        
        GestioneTurno gestTurno = new GestioneTurno(admin,scenes,primaryStage);
        scenes.put("gestioneTurni", new Scene(gestTurno.createContent()));


        primaryStage.setTitle("SoSpeedy");
        primaryStage.setScene(scenes.get("login"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
