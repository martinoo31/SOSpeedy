package view.admin;

import java.util.Map;

import controller.Admin;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Visita;

public class HBoxVisita extends HBox {
    private Visita visita;
    private Button modifica;
    private Button elimina;
    private Admin admin;
    private Map<String, Scene> scenes;

    public HBoxVisita(Visita visita, Admin admin, Map<String, Scene> scenes) {
        super();
        this.visita = visita;
        this.modifica = new Button("Modifica");
        this.elimina = new Button("Elimina");
        this.admin = admin;
        this.scenes = scenes;

        this.modifica.setStyle("-fx-background-color: #F0E68C;");
        this.elimina.setStyle("-fx-background-color: #F08080;");

        Label nomeVisitaLabel = new Label(this.visita.getNomeVisita());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.elimina.setOnAction(this::delete);
        this.modifica.setOnAction(this::modifica);

        this.setPadding(new Insets(10)); // Optional, to add padding around the HBox
        this.setSpacing(10); // Space between buttons

        this.getChildren().addAll(nomeVisitaLabel, spacer, this.modifica, this.elimina);
    }

    private void delete(ActionEvent event) {
        this.admin.eliminaVisita(visita);
    }

    private void modifica(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ModificaVisita aggVisita = new ModificaVisita(admin, this.scenes, this.visita);
        stage.setScene(new Scene(aggVisita.createContent(), 400, 300));
    }
}
