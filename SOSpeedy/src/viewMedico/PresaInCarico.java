package viewMedico;

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
//import view.HBoxVisita;
import controller.Medico;
import javafx.event.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PresaInCarico {
    private Medico medico;
    private Map<String, Scene> scenes;
    private Stage stage;
    private ListView<PazienteItem> pazientiListView;

    public PresaInCarico(Map<String, Scene> scenes, Stage stage) {
        this.medico = new Medico();
        this.scenes = scenes;
        this.stage = stage;
    }
    

    public Parent createContent() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));

        // Top section with back button and title
        HBox topBox = new HBox(10);
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setPadding(new Insets(0,0,20,0));
        
        Label titleLabel = new Label("Presa in carico");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        topBox.getChildren().addAll(/*backButton,*/ titleLabel);

        // ListView for patients
        VBox pazientiBox = new VBox();
        initializeListView(pazientiBox, stage);
        
        Label l1=new Label("Coda dei pazienti in ordine di priorità:");
        l1.setPadding(new Insets(20,0,0,0));
        l1.setStyle("-fx-font-weight: bold;");
        vbox.getChildren().addAll(topBox, l1, pazientiBox);

        return vbox;
    }

    private void populateListView() {
        ObservableList<PazienteItem> pazienti = FXCollections.observableArrayList();
        // Populate with sample data or real data from the model
        pazienti.add(new PazienteItem("Martino", "Manaresi", "A131", "Tac", "yellow"));
        pazienti.add(new PazienteItem("Federico", "Hrvatin", "B408", "Lastra", "green"));
        pazientiListView.setItems(pazienti);
    }
    
    private void initializeListView(VBox visitsBox, Stage stage) {
        List<HBoxPazienteInCoda> list = new ArrayList<>();
        // L'ordinamento della lista per codiceColore l'ho inserito direttamente nel costruttore del medico
//        List<PazienteInCoda> pazienti = medico.pazientiInCoda;
//        pazienti.sort((p1, p2) -> Integer.compare(p1.getCodiceColore().getPriorita(), p2.getCodiceColore().getPriorita()));
        for (PazienteInCoda p : medico.pazientiInCoda) {
            list.add(new HBoxPazienteInCoda(p, this.medico, this.scenes, stage));
        }

        ObservableList<HBoxPazienteInCoda> observableListVisite = FXCollections.observableList(list);
        ListView<HBoxPazienteInCoda> listView = new ListView<>(observableListVisite);
        visitsBox.getChildren().add(listView);

        medico.oPazientiInCoda.addListener((ListChangeListener<PazienteInCoda>) c -> {
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved()) {
                    updateListView(observableListVisite);
                }
            }
        });
    }

    private void updateListView(ObservableList<HBoxPazienteInCoda> observableListVisite) {
        observableListVisite.clear();
        for (PazienteInCoda p : medico.pazientiInCoda) {
            observableListVisite.add(new HBoxPazienteInCoda(p, this.medico, this.scenes, this.stage));
        }
    }
    
    public Scene getScene() {
        return this.scenes.get("presaInCarico");
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
            // non ho capito a cosa serve questo button se lo mettiamo anche in HBoxPazienteInCoda
            Button prendiButton = new Button("Prendi In Carico");
            prendiButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
            prendiButton.setOnAction(event -> deregistraPaziente());

            this.getChildren().addAll(nomeLabel, codiceLabel, tipoVisitaLabel, infoButton, prendiButton);
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