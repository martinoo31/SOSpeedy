package viewInfermiere;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Login;
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
    	
    	Login login = new Login(scenes);
        scenes.put("login",new Scene(login.createContent(),800,450));
   
    	RegistraPaziente regPaziente = new RegistraPaziente(infermiere,scenes);
        scenes.put("registraPaziente", new Scene(regPaziente.createContent(),800,500));
 
        DeregistraPaziente deregistraPaziente = new DeregistraPaziente(infermiere,scenes);
        scenes.put("deregistraPaziente", new Scene(deregistraPaziente.createContent(), 800, 450));
   
        primaryStage.setTitle("Infermiere");
        primaryStage.setScene(scenes.get("login"));
        primaryStage.show();
       
    }

    public static void main(String[] args) {
        launch(args);
    }
}
