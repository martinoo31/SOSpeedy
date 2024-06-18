package viewAdmin;

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
import model.Turno;

public class HBoxTurno extends HBox {
    private Turno turno;
    private Button modifica;
    private Button elimina;
    private Admin admin;
    private Map<String, Scene> scenes;

    public HBoxTurno(Turno turno, Admin admin, Map<String, Scene> scenes) {
        super();
        this.turno = turno;
        this.modifica = new Button("Modifica");
        this.elimina = new Button("Elimina");
        this.admin = admin;
        this.scenes = scenes;

        Label nomeMedicoLabel = new Label(this.turno.getMedico().toString());
        Label nomeVisitaLabel = new Label(this.turno.getVisita().getNomeVisita());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.elimina.setOnAction(this::delete);
        this.modifica.setOnAction(this::modifica);

        this.setPadding(new Insets(10)); // Optional, to add padding around the HBox
        this.setSpacing(10); // Space between elements

        this.getChildren().addAll(nomeMedicoLabel, nomeVisitaLabel, spacer, this.modifica, this.elimina);
    }

    private void delete(ActionEvent event) {
        this.admin.eliminaTurno(turno);
    }

    private void modifica(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        ModificaTurno modTurno = new ModificaTurno(admin, this.scenes, this.turno);
        stage.setScene(new Scene(modTurno.createContent(), 400, 300));
    }
}
