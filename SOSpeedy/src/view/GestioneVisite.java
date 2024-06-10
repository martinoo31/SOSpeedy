package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Visita;
import controller.Admin;
import java.util.*;
import javafx.geometry.*;

public class GestioneVisite {
    public Admin admin;
    public Map<String,Scene> scenes;
    public TextField searchField;
    

    public GestioneVisite(Admin admin, Map<String, Scene> scenes, Stage stage) {
        this.admin = admin;
        this.scenes = scenes;
//        addSceneChangeListener(stage);
    }

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15,15,15,15));

        HBox topBox = new HBox();
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Gestione Visite");
        topBox.getChildren().addAll(backButton, titleLabel);

        HBox searchBox = new HBox();
        Label searchLabel = new Label("Ricerca Visita:");
        searchField = new TextField();
        searchBox.getChildren().addAll(searchLabel, searchField);

        VBox visitsBox = new VBox();
        
        initializeListView(visitsBox);

        Button addButton = new Button("Aggiungi Visita");
        addButton.setOnAction(this::goAggiungiVisita);

        vbox.getChildren().addAll(topBox, searchBox, visitsBox, addButton);

        return vbox;
    }
    
    private void initializeListView(VBox visitsBox) {
        List<HBoxVisita> list = new ArrayList<>();
        for (Visita visita : admin.visite) {
            list.add(new HBoxVisita(visita,this.admin,this.scenes));
        }

        ObservableList<HBoxVisita> observableListVisite = FXCollections.observableList(list);
        ListView<HBoxVisita> listView = new ListView<>(observableListVisite);
        visitsBox.getChildren().add(listView);

        // Add listener to admin.visite to update the ListView when it changes
        admin.oVisite.addListener((ListChangeListener<Visita>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateListView(observableListVisite);
                }
            }
        });
    }
    
    private void updateListView(ObservableList<HBoxVisita> observableListVisite) {
        observableListVisite.clear();
        for (Visita visita : admin.visite) {
            observableListVisite.add(new HBoxVisita(visita,this.admin,this.scenes));
        }
    }


    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }
    
    private void goAggiungiVisita(ActionEvent event) {
    	Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
    	AggiungiVisita aggVisita = new AggiungiVisita(admin,scenes);
    	stage.setScene(new Scene(aggVisita.createContent(),400,300));
    }
    
//    private void addSceneChangeListener(Stage stage) {
//        stage.sceneProperty().addListener((observable, oldScene, newScene) -> {
//            if (newScene != null && newScene.equals(scenes.get("gestioneVisite"))) {
//                VBox patientsBox = (VBox) newScene.lookup("#patientsBox");
//                updateVisiteList(patientsBox);
//            }
//        });
//    }
}
