package view.admin;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TimeBox extends HBox{
	private TextField startTime;
	private TextField endTime;
	
	public TimeBox() {
		this.startTime = new TextField();
		this.startTime.setPromptText("HH:MM");
		
		this.endTime = new TextField();
		this.endTime.setPromptText("HH:MM");
		
		this.getChildren().addAll(this.startTime,this.endTime);
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
	}
	
	public String[] getValues() {
		String[] result = new String[4];
		result[0] = this.startTime.getText().split(":")[0];
		result[1] = this.startTime.getText().split(":")[1];
		result[2] = this.endTime.getText().split(":")[2];
		result[3] = this.endTime.getText().split(":")[3];
		
		return result;
	}

	public TextField getStartTime() {
		return startTime;
	}

	public void setStartTime(TextField startTime) {
		this.startTime = startTime;
	}

	public TextField getEndTime() {
		return endTime;
	}

	public void setEndTime(TextField endTime) {
		this.endTime = endTime;
	}
	
	
	

}
