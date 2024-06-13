package viewInfermiere;

import controller.Admin;
import controller.Infermiere;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.Paziente;
import model.PazienteInCoda;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Map;

public class HBoxPazienteInCoda extends HBox {
    private PazienteInCoda pazienteInCoda;
    private Button deregistra;
    private Infermiere infermiere;
    private Map<String, Scene> scenes;

    public HBoxPazienteInCoda(PazienteInCoda pazienteInCoda, Infermiere infermiere, Map<String, Scene> scenes) {
        super();
        this.pazienteInCoda = pazienteInCoda;
        this.deregistra = new Button("Deregistra");
        this.infermiere = infermiere;
        this.scenes = scenes;

        this.deregistra.setStyle("-fx-background-color: #F08080;");

        Label nomeLabel = new Label(this.pazienteInCoda.getNome());
        Label cognomeLabel = new Label(this.pazienteInCoda.getCognome());
        Label codiceLabel = new Label(this.pazienteInCoda.getCodiceIdentificativo());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.deregistra.setOnAction(this::deregistra);

        this.setPadding(new Insets(10)); // Optional, to add padding around the HBox
        this.setSpacing(10); // Space between labels and button

        this.getChildren().addAll(nomeLabel, cognomeLabel, codiceLabel, spacer, this.deregistra);
    }

    private void deregistra(ActionEvent event) {
        this.infermiere.deregistraPaziente(pazienteInCoda);
    }
}
