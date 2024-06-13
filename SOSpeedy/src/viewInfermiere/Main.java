package viewInfermiere;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Infermiere;
import java.util.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Creazione dell'interfaccia Home
        Infermiere infermiere = new Infermiere();
        
        Map<String,Scene> scenes = new HashMap<String,Scene>();
        
    	Home home = new Home(infermiere, scenes);
    	scenes.put("home", new Scene(home.createContent(), 800, 450));
   
    	RegistraPaziente regPaziente = new RegistraPaziente(infermiere,scenes);
        scenes.put("registraPaziente", new Scene(regPaziente.createContent(),800,450));
 
        DeregistraPaziente deregistraPaziente = new DeregistraPaziente(infermiere,scenes);
        scenes.put("deregistraPaziente", new Scene(deregistraPaziente.createContent(), 800, 450));
   
        primaryStage.setTitle("Infermiere");
        primaryStage.setScene(scenes.get("home"));
        primaryStage.show();
       
    }

    public static void main(String[] args) {
        launch(args);
    }
}
