package viewAdmin;

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
import model.Visita;
import controller.Admin;
import java.util.*;
import javafx.geometry.*;

public class GestioneVisite {
    public Admin admin;
    public Map<String, Scene> scenes;
    public TextField searchField;

    public GestioneVisite(Admin admin, Map<String, Scene> scenes, Stage stage) {
        this.admin = admin;
        this.scenes = scenes;
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
        Label titleLabel = new Label("Gestione Visite");
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

        Button addButton = new Button("Aggiungi Visita");
        addButton.setStyle("-fx-background-color: #8FBC8F;");
        addButton.setOnAction(this::goAggiungiVisita);

        vbox.getChildren().addAll(topBox, searchBox, visitsBox, addButton);

        return vbox;
    }

    private void initializeListView(VBox visitsBox) {
        List<HBoxVisita> list = new ArrayList<>();
        for (Visita visita : admin.visite) {
            list.add(new HBoxVisita(visita, this.admin, this.scenes));
        }

        ObservableList<HBoxVisita> observableListVisite = FXCollections.observableList(list);
        ListView<HBoxVisita> listView = new ListView<>(observableListVisite);
        visitsBox.getChildren().add(listView);

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
            observableListVisite.add(new HBoxVisita(visita, this.admin, this.scenes));
        }
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }

    private void goAggiungiVisita(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        AggiungiVisita aggVisita = new AggiungiVisita(admin, scenes);
        stage.setScene(new Scene(aggVisita.createContent(), 800, 450));
    }
}
