package view;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import model.Periodo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class ProgrammazioneSettimanale {
	List<TimeBox>[] ArraylistaTimeBox;
	
	
	ComboBox<DayOfWeek> dayOfWeekComboBox;
	VBox periodi;
	List<Periodo> listaPeriodi;
	CheckBox[] dayCheckBoxes;
	
	public ProgrammazioneSettimanale() {
		this.ArraylistaTimeBox = (List<TimeBox>[])new List[7];
		for(int i=0;i<7;i++) {
			this.ArraylistaTimeBox[i] = new ArrayList<>();
			this.ArraylistaTimeBox[i].add(new TimeBox());
		}
		this.listaPeriodi = new ArrayList<>();
	}

    public Parent createContent() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(15, 15, 15, 15));
        vbox.setSpacing(20);

        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER_LEFT);
        topBox.setSpacing(10);

        HBox schedulingBox = new HBox();
        schedulingBox.setAlignment(Pos.CENTER);
        schedulingBox.setSpacing(10);

        dayOfWeekComboBox = new ComboBox<>();
        dayOfWeekComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(DayOfWeek.values())));
        dayOfWeekComboBox.setValue(DayOfWeek.MONDAY);

        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(10);
        
        HBox progSettimanaleAlta = new HBox();
        
        VBox scegliGiorno = new VBox();
        scegliGiorno.getChildren().addAll(new Label("Giorno della settimana"),dayOfWeekComboBox);
        
        labelBox.getChildren().add(new Label("Orario di Inizio"));
        labelBox.getChildren().add(new Label("Orario di Fine"));
        
        periodi = new VBox();
        periodi.getChildren().add(labelBox);
        periodi.getChildren().add(this.ArraylistaTimeBox[this.dayOfWeekComboBox.getValue().getValue()].get(0));
        
        Button saveButton = new Button("Salva Giornata");
        saveButton.setOnAction(this::saveDay);
        Button removeButton = new Button("Rimuovi Giornata");
        Button addPeriodButton = new Button("Aggiungi Periodo");
        addPeriodButton.setOnAction(this::aggiungiPeriodo);
        
        VBox tastiPeriodo = new VBox();
        tastiPeriodo.getChildren().addAll(saveButton,removeButton,addPeriodButton);
        
        progSettimanaleAlta.getChildren().addAll(scegliGiorno,periodi,tastiPeriodo);

        TextField endTimeField1 = new TextField();
        endTimeField1.setPromptText("19:00");

        
        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);
        dateBox.setSpacing(10);

        TextField startDateField = new TextField();
        startDateField.setPromptText("25/05/2024");

        TextField endDateField = new TextField();
        endDateField.setPromptText("25/09/2024");

        dateBox.getChildren().addAll(new Label("Data di Inizio:"), startDateField, new Label("Data di Fine:"), endDateField);

        dayCheckBoxes = new CheckBox[7];
        HBox dayCheckBoxBox = new HBox();
        dayCheckBoxBox.setAlignment(Pos.CENTER);
        dayCheckBoxBox.setSpacing(10);
        
        HBox progSettimanaleSotto = new HBox();
        progSettimanaleSotto.getChildren().addAll(dayCheckBoxBox,dateBox);

        for (int i = 0; i < 7; i++) {
            dayCheckBoxes[i] = new CheckBox();
            dayCheckBoxBox.getChildren().add(dayCheckBoxes[i]);
        }

        vbox.getChildren().addAll(topBox, schedulingBox,progSettimanaleAlta , progSettimanaleSotto);

        return vbox;
    }

    private void saveDay(ActionEvent event) {
        // Implementa la logica di salvataggio qui
        System.out.println("Giornata salvata!");
    }
    
    private void aggiungiPeriodo(ActionEvent event) {
    	var temp = new TimeBox();
    	this.ArraylistaTimeBox[this.dayOfWeekComboBox.getValue().getValue()].add(temp);
    	periodi.getChildren().add(temp);
    }
    
    private void salvaGiornata(ActionEvent event) {
    	DayOfWeek tempDayOfWeek = this.dayOfWeekComboBox.getValue();
    	List<TimeBox> listaTimeBox = this.ArraylistaTimeBox[tempDayOfWeek.getValue()];
    	for(TimeBox t: listaTimeBox) {
    		LocalTime inizio =  LocalTime.parse(t.getStartTime().getText());
    		LocalTime fine = LocalTime.parse(t.getEndTime().getText());
    		this.listaPeriodi.add(new Periodo(10,tempDayOfWeek,inizio,fine));
    	}
    	
    	
    }

}
