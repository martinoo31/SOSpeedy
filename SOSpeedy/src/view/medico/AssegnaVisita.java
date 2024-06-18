package view.medico;

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

    public AssegnaVisita(Map<String, Scene> scenes, Stage stage, Paziente p, Medico m) {
        this.scenes = scenes;
        this.stage = stage;
        this.p = p;
        this.scena = new Scene(this.createContent(), 300, 400);
        this.m = m;
        
        try {
    		ObjectInputStream visiteStream = new ObjectInputStream(new FileInputStream("visite.bin"));
    		 visite = (List<Visita>) visiteStream.readObject();
    		 visiteStream.close();
    	}catch(Exception e) {
    		e.printStackTrace();
//    		visite = new ArrayList<>();
//    		Visita visita = new Visita("temp1");
//    		Visita visita2 = new Visita("temp2");
//    		visite.add(visita);
//    		visite.add(visita2);
    	}
        
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
        for (Visita visita : visite) {
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
        for (Visita visita : visite) {
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
