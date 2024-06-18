package viewAdmin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.TurnoEccezioni;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ProgrammazioneGiornaliera {
	Map<LocalDate,List<TimeBox>> mappaTimeBox;
    List<TimeBox> listaTimeBox;
    DatePicker datePicker;
    VBox periodi;
    Parent contenuto;
    List<TurnoEccezioni> eccezioni;

    public ProgrammazioneGiornaliera() {
        this.listaTimeBox = new ArrayList<>();
        this.listaTimeBox.add(new TimeBox());
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        this.mappaTimeBox = new TreeMap<>();
        this.mappaTimeBox.put(this.datePicker.getValue(), this.listaTimeBox);
        this.eccezioni = new ArrayList<>();
        creaParente();
    }

    public Parent createContent() {
        return contenuto;
    }
    
    private void creaParente() {
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

           
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                System.out.println("Data cambiata");
                cambiaPeriodo(oldValue,newValue);
                
            }
        });

        // Open the popup when the DatePicker is focused
        datePicker.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                datePicker.show();
            }
        });

        HBox labelBox = new HBox();
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(10);

        HBox progGiornalieraAlta = new HBox();

        VBox scegliGiorno = new VBox();
        scegliGiorno.getChildren().addAll(new Label("Data"), datePicker);

        labelBox.getChildren().add(new Label("Orario di Inizio"));
        labelBox.getChildren().add(new Label("Orario di Fine"));

        periodi = new VBox();
        periodi.getChildren().add(labelBox);
        periodi.getChildren().add(this.listaTimeBox.get(0));

        Button saveButton = new Button("Salva Eccezioni");
        saveButton.setOnAction(this::saveDay);
        Button removeButton = new Button("Rimuovi Eccezioni");
        removeButton.setOnAction(this::removeGiornata);
        Button addEccezioni = new Button("Aggiungi Eccezione");
        addEccezioni.setOnAction(this::aggiungiPeriodo);
        Button removeEccezioni = new Button("Cancella Eccezione");
        removeEccezioni.setOnAction(this::cancellaEccezione);
        Button disabilitaGiorno = new Button("Disabilita Giorno");
      

        VBox tastiPeriodo = new VBox();
        tastiPeriodo.getChildren().addAll(removeButton,addEccezioni,removeEccezioni,disabilitaGiorno);

        progGiornalieraAlta.getChildren().addAll(scegliGiorno, periodi, tastiPeriodo);

        vbox.getChildren().addAll(topBox, schedulingBox, progGiornalieraAlta, saveButton);

        // Show the popup initially
        datePicker.show();

        contenuto= vbox;
    }

    private void removeGiornata(ActionEvent event) {
        System.out.println("Rimuovo Giornata");
        this.periodi.getChildren().removeAll(this.listaTimeBox);
        this.listaTimeBox = new ArrayList<>();
        this.listaTimeBox.add(new TimeBox());
        this.periodi.getChildren().addAll(this.listaTimeBox);
        this.mappaTimeBox.put(this.datePicker.getValue(), listaTimeBox);
    }

    private void saveDay(ActionEvent event) {
    	save();      
        
    }
    
    private void save() {
    	for(Entry<LocalDate,List<TimeBox>> e : this.mappaTimeBox.entrySet()) {
    		var list = e.getValue();
    		LocalDate giorno = e.getKey();
    		for(TimeBox t : list) {
    			LocalTime start;
    			LocalTime end;
    			try {
    				start = LocalTime.parse(t.getStartTime().getText());
    				end = LocalTime.parse(t.getEndTime().getText());
    			}catch(DateTimeParseException ex) {
    				continue;
    			}
    			LocalDateTime startDate = LocalDateTime.of(giorno, start);
    			LocalDateTime endDate;
    			if(start.isAfter(end)) {
    				var giornoDopo = giorno.plusDays(1);
    				endDate = LocalDateTime.of(giornoDopo, end);
    			}
    			else {
    				endDate = LocalDateTime.of(giorno, end);
    			}
    			this.eccezioni.add(new TurnoEccezioni(10,startDate,endDate,false));
    		}
    	}
    }
    
    private void cancellaEccezione (ActionEvent event) {
    	TimeBox last=this.listaTimeBox.get(this.listaTimeBox.size()-1);
    	this.periodi.getChildren().remove(last);
    	this.listaTimeBox.remove(last);
    }
    
    private void cambiaPeriodo(LocalDate oldValue, LocalDate newValue) {
    	this.periodi.getChildren().removeAll(this.listaTimeBox);
    	List<TimeBox> list = this.mappaTimeBox.get(newValue);
    	if(list == null) {
    		list = new ArrayList<TimeBox>();
    		list.add(new TimeBox());
    		this.mappaTimeBox.put(newValue, list);
    	}
    	this.listaTimeBox = list;
    	this.periodi.getChildren().addAll(list);
    }

    private void aggiungiPeriodo(ActionEvent event) {
        var temp = new TimeBox();
        this.listaTimeBox.add(temp);
        periodi.getChildren().add(temp);
    }
    
    public List<TurnoEccezioni> getListEccezioni(){
    	return this.eccezioni;
    }
}
