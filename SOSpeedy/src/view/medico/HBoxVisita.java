package view.medico;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Map;

import controller.Admin;
import controller.Medico;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.PazienteInCoda;
import model.Visita;

public class HBoxVisita extends HBox {
    private Visita visita;
//    private Button modifica;
    private Button assegna;
    private Medico medico;
    private Map<String, Scene> scenes;
    private Stage stage;

    public HBoxVisita(Visita visita, Map<String, Scene> scenes, Stage stage, Medico m) {
        super();
        this.visita = visita;
//        this.modifica = new Button("Modifica");
        this.assegna = new Button("Assegna");
        this.medico = m;
        this.scenes = scenes;
        this.stage = stage;

//        this.modifica.setStyle("-fx-background-color: #F0E68C;");
//        this.assegna.setStyle("-fx-background-color: #F08080;");

        Label nomeVisitaLabel = new Label(this.visita.getNomeVisita());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.assegna.setOnAction(this::assegna);
//        this.modifica.setOnAction(this::modifica);

        this.setPadding(new Insets(10)); // Optional, to add padding around the HBox
        this.setSpacing(10); // Space between buttons

        this.getChildren().addAll(nomeVisitaLabel, spacer, this.assegna);
    }

    private void assegna(ActionEvent event) {
//        this.admin.eliminaVisita(visita);
    	// salvataggio nei file di bin e riporto a presa in carico
    	this.medico.pazientiInCoda.add(new PazienteInCoda(this.medico.p, LocalDateTime.now()));
    	try (ObjectOutputStream pazientiInCodaStream = new ObjectOutputStream(new FileOutputStream("pazientiInCoda.bin"))) {
    		pazientiInCodaStream.writeObject(this.medico.pazientiInCoda);
    		System.out.println("Assegnata nuova visita, aggiungo paziente in coda");
    		pazientiInCodaStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    	
        this.stage.setScene(scenes.get("home"));
    }

//    private void modifica(ActionEvent event) {
//        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//        ModificaVisita aggVisita = new ModificaVisita(admin, this.scenes, this.visita);
//        stage.setScene(new Scene(aggVisita.createContent(), 400, 300));
//    }
}
