package viewAdmin;

import java.util.Map;

import controller.Admin;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Turno;

public class ModificaTurno {
	Map<String,Scene> scenes;

	public ModificaTurno(Admin admin, Map<String, Scene> scenes, Turno turno) {
		this.scenes = scenes;
	}

	public Parent createContent() {
		VBox v = new VBox();
		v.getChildren().add(new Label("Todo"));
		Button GoBackButton = new Button("Indietro");
		v.getChildren().add(GoBackButton);
		GoBackButton.setOnAction(this::goBack);
		return v;
		
	}
	
	private void goBack(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scenes.get("gestioneTurni"));
    }

}
