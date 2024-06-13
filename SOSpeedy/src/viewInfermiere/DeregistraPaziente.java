package viewInfermiere;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.PazienteInCoda;
import model.Visita;
import view.HBoxVisita;
import controller.Infermiere;
import javafx.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeregistraPaziente {
    private Infermiere infermiere;
    private Map<String, Scene> scenes;
    private TextField searchField;
    private ListView<PazienteItem> pazientiListView;

    public DeregistraPaziente(Infermiere infermiere, Map<String, Scene> scenes) {
        this.infermiere = infermiere;
        this.scenes = scenes;
    }

    public Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));

        // Top section with back button and title
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER_LEFT);
        Button backButton = new Button("Indietro");
        backButton.setOnAction(this::goBack);
        Label titleLabel = new Label("Deregistra Paziente");
        topBox.getChildren().addAll(backButton, titleLabel);

        // Search field
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);
        searchField = new TextField();
        searchField.setPromptText("Ricerca paziente");
        Button searchButton = new Button("🔍");
        searchButton.setOnAction(this::searchPaziente);
        searchBox.getChildren().addAll(new Label("Ricerca paziente:"), searchField, searchButton);

        // ListView for patients
        VBox pazientiBox = new VBox();
        initializeListView(pazientiBox);

        vbox.getChildren().addAll(topBox, searchBox, new Label("Elenco dei pazienti:"), pazientiBox);

        return vbox;
    }

    private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("home"));
    }

    private void searchPaziente(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase();
        ObservableList<PazienteItem> filteredList = FXCollections.observableArrayList();
        for (PazienteItem item : pazientiListView.getItems()) {
            if (item.getNome().toLowerCase().contains(searchText) || 
                item.getCognome().toLowerCase().contains(searchText)) {
                filteredList.add(item);
            }
        }
        pazientiListView.setItems(filteredList);
    }

    private void populateListView() {
        ObservableList<PazienteItem> pazienti = FXCollections.observableArrayList();
        // Populate with sample data or real data from the model
        pazienti.add(new PazienteItem("Martino", "Manaresi", "A131", "Tac", "yellow"));
        pazienti.add(new PazienteItem("Federico", "Hrvatin", "B408", "Lastra", "green"));
        pazientiListView.setItems(pazienti);
    }
    
    private void initializeListView(VBox visitsBox) {
        List<HBoxPazienteInCoda> list = new ArrayList<>();
        for (PazienteInCoda p : infermiere.pazientiInCoda) {
            list.add(new HBoxPazienteInCoda(p, this.infermiere, this.scenes));
        }

        ObservableList<HBoxPazienteInCoda> observableListVisite = FXCollections.observableList(list);
        ListView<HBoxPazienteInCoda> listView = new ListView<>(observableListVisite);
        visitsBox.getChildren().add(listView);

        infermiere.oPazientiInCoda.addListener((ListChangeListener<PazienteInCoda>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateListView(observableListVisite);
                }
            }
        });
    }

    private void updateListView(ObservableList<HBoxPazienteInCoda> observableListVisite) {
        observableListVisite.clear();
        for (PazienteInCoda p : infermiere.pazientiInCoda) {
            observableListVisite.add(new HBoxPazienteInCoda(p, this.infermiere, this.scenes));
        }
    }

    public static class PazienteItem extends HBox {
        private String nome;
        private String cognome;
        private String codice;
        private String tipoVisita;
        private String coloreCodice;

        public PazienteItem(String nome, String cognome, String codice, String tipoVisita, String coloreCodice) {
            super(10);
            this.nome = nome;
            this.cognome = cognome;
            this.codice = codice;
            this.tipoVisita = tipoVisita;
            this.coloreCodice = coloreCodice;
            createItem();
        }

        private void createItem() {
            Label nomeLabel = new Label(nome + " " + cognome);
            Label codiceLabel = new Label(codice);
            codiceLabel.setStyle("-fx-background-color: " + coloreCodice + "; -fx-padding: 2px 4px;");
            Label tipoVisitaLabel = new Label(tipoVisita);
            Button infoButton = new Button("i");
            Button deregistraButton = new Button("Deregistra");
            deregistraButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            deregistraButton.setOnAction(event -> deregistraPaziente());

            this.getChildren().addAll(nomeLabel, codiceLabel, tipoVisitaLabel, infoButton, deregistraButton);
        }

        private void deregistraPaziente() {
            // Implement the logic to deregister the patient
            // Example: infermiere.deregistraPaziente(this.codice);
            this.setVisible(false);
        }

        public String getNome() {
            return nome;
        }

        public String getCognome() {
            return cognome;
        }
    }
}
