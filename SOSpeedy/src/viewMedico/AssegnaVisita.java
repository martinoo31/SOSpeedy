package viewMedico;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Paziente;
import model.PazienteInCoda;
import model.Visita;
import controller.Admin;
import controller.Medico;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;
import javafx.geometry.*;

public class AssegnaVisita {
    public Map<String, Scene> scenes;
    public TextField searchField;
    public Scene scena;
    public Paziente p;
    private Stage stage;
    private List<Visita> visite;
    private Medico m;
    private Admin a;

    public AssegnaVisita(Map<String, Scene> scenes, Stage stage, Paziente p, Medico m) {
        this.scenes = scenes;
        this.stage = stage;
        this.p = p;
        this.m = m;
        this.a = new Admin();
        
        try {
    		ObjectInputStream visiteStream = new ObjectInputStream(new FileInputStream("visite.bin"));
    		 visite = (List<Visita>) visiteStream.readObject();
    		 visiteStream.close();
    	}catch(Exception e) {
    		System.out.println("Errore Lettura Visite");
    		visite = new ArrayList<>();
    		Visita visita = new Visita("temp1");
    		Visita visita2 = new Visita("temp2");
    		visite.add(visita);
    		visite.add(visita2);
    	}
    	if(this.visite == null || this.visite.size()<2) {
    		
    		Visita visita = new Visita("temp3");
    		Visita visita2 = new Visita("temp4");
    		visite.add(visita);
    		visite.add(visita2);
    	}
    	
        this.scena = new Scene(this.createContent(), 300, 400);
        
    }

    public Parent createContent() {
    	
    	
    	
    	
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setSpacing(10);

        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(10);

        Button backButton = new Button("←");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Assegnazione Prossima Visita");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(backButton, titleLabel);

        HBox searchBox = new HBox();
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchBox.setSpacing(10);
        Label searchLabel = new Label("Ricerca Visita:");
        searchField = new TextField();
        Button searchButton = new Button("🔍");
        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);

        VBox visitsBox = new VBox();
        initializeListView(visitsBox);

//        Button addButton = new Button("Aggiungi Visita");
//        addButton.setStyle("-fx-background-color: #8FBC8F;");
//        addButton.setOnAction(this::goAggiungiVisita);

        vbox.getChildren().addAll(topBox, searchBox, visitsBox);

        return vbox;
    }

    private void initializeListView(VBox visitsBox) {
        List<HBoxVisita> list = new ArrayList<>();
        for (Visita visita : a.visite) {
            list.add(new HBoxVisita(visita, this.scenes, this.stage, this.m));
        }

        ObservableList<HBoxVisita> observableListVisite = FXCollections.observableList(list);
        ListView<HBoxVisita> listView = new ListView<>(observableListVisite);
        visitsBox.getChildren().add(listView);

        FXCollections.observableList(visite).addListener((ListChangeListener<Visita>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateListView(observableListVisite);
                }
            }
        });
    }

    private void updateListView(ObservableList<HBoxVisita> observableListVisite) {
        observableListVisite.clear();
        for (Visita visita : a.visite) {
            observableListVisite.add(new HBoxVisita(visita, this.scenes, this.stage, this.m));
        }
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneVisita"));
    }
    
    public List<Visita> readVisite() {
    	List<Visita> visite;
    	try {
    		ObjectInputStream visiteStream = new ObjectInputStream(new FileInputStream("visite.bin"));
    		 visite = (List<Visita>) visiteStream.readObject();
    		 visiteStream.close();
    	}catch(Exception e) {
    		visite = new ArrayList<>();
    		Visita visita = new Visita("temp1");
    		Visita visita2 = new Visita("temp2");
    		visite.add(visita);
    		visite.add(visita2);
    	}
    	
    	return visite;
    }
    
    public Scene getScene() {
        return scena;
    }
}
