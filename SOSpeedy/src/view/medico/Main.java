package view.medico;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.Login;
import javafx.geometry.*;
import controller.Medico;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione dell'interfaccia Home
        Medico medico = new Medico();
        Map<String,Scene> scenes = new HashMap<String,Scene>();
        
        Login login = new Login(scenes);
        scenes.put("login", new Scene(login.createContent()));
        
        // La presa in carico la considero come home del Medico
        PresaInCarico presaInCarico = new PresaInCarico(scenes, primaryStage);
        scenes.put("home", new Scene(presaInCarico.createContent()));
        
//        
//        VisualizzaCC visualizzaCC = new VisualizzaCC(medico,scenes,primaryStage);
//        scenes.put("visualizzaCC", new Scene(visualizzaCC.createContent()));
//        
//        DeregistraPaziente derPaziente = new DeregistraPaziente(medico, scenes);
//        scenes.put("deregistraPaziente", new Scene(derPaziente.createContent()));

        primaryStage.setHeight(450);       
        primaryStage.setWidth(800);
        primaryStage.setTitle("SoSpeedy");
        primaryStage.setScene(scenes.get("login"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
