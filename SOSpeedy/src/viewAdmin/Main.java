package viewAdmin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Login;
import javafx.geometry.*;
import controller.Admin;
import java.util.*;
import java.util.Map.Entry;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione dell'interfaccia Home
        Admin admin = new Admin();
        Map<String,Scene> scenes = new HashMap<String,Scene>();
        Login login = new Login(scenes);
        scenes.put("login",new Scene(login.createContent(),800,450));
        Home home = new Home(admin, scenes);
        scenes.put("home", new Scene(home.createContent(),800,450));
        GestioneVisite gestioneVisite = new GestioneVisite(admin,scenes,primaryStage);
        scenes.put("gestioneVisite", new Scene(gestioneVisite.createContent(),800,450));
        AggiungiVisita aggVisita = new AggiungiVisita(admin,scenes);
        scenes.put("aggiungiVisite", new Scene(aggVisita.createContent(),800,450));
        GestioneTurno gestTurno = new GestioneTurno(admin,scenes,primaryStage);
        scenes.put("gestioneTurni", new Scene(gestTurno.createContent(),800,450));
        for(Entry<String,Scene> e: scenes.entrySet()) {
        	e.getValue().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        }


        primaryStage.setTitle("Admin");
        primaryStage.setScene(scenes.get("login"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
