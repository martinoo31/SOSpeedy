package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DayBox extends VBox{
	private Label label;
	private Label selected;
	
	public DayBox(String giorno) {
		this.label = new Label(giorno);
		this.selected = new Label("X");
		this.selected.setVisible(true);
		this.getChildren().addAll(this.label,this.selected);
	}
	
	public void cambiaStato() {
		this.selected.setVisible(!this.selected.isVisible());
	}
	
	public void attiva() {
		this.getChildren().remove(this.selected);
		this.selected.setVisible(true);
		this.getChildren().add(this.selected);
		this.requestLayout();
	}

}
