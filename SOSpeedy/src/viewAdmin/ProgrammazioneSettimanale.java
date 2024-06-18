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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ProgrammazioneSettimanale {
	List<TimeBox>[] ArraylistaTimeBox;
	
	
	ComboBox<DayOfWeek> dayOfWeekComboBox;
	VBox periodi;
	List<Periodo> listaPeriodi;
	DayBox[] dayCheckBoxes;
	HBox dayCheckBoxBox;
	Parent contenuto;
	DatePicker startDateField;
	DatePicker endDateField;	
	
	public ProgrammazioneSettimanale() {
		this.ArraylistaTimeBox = (List<TimeBox>[])new List[7];
		for(int i=0;i<7;i++) {
			this.ArraylistaTimeBox[i] = new ArrayList<>();
			this.ArraylistaTimeBox[i].add(new TimeBox());
		}
		this.listaPeriodi = new ArrayList<>();
		creaParente();
		this.contenuto.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
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

        dayOfWeekComboBox = new ComboBox<>();
        dayOfWeekComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(DayOfWeek.values())));
        dayOfWeekComboBox.setValue(DayOfWeek.MONDAY);
        dayOfWeekComboBox.valueProperty().addListener(new ChangeListener<DayOfWeek>() {


            @Override
            public void changed(ObservableValue<? extends DayOfWeek> observable, DayOfWeek oldValue, DayOfWeek newValue) {                
               cambiaVisualizzazionePeriodiGiorno(oldValue, newValue);
            }
        });
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
        periodi.getChildren().add(this.ArraylistaTimeBox[this.dayOfWeekComboBox.getValue().getValue()-1].get(0));
        
        Button saveButton = new Button("Salva Giornata");
        saveButton.setOnAction(this::saveDay);
        Button removeButton = new Button("Rimuovi Giornata");
        removeButton.setOnAction(this::removeGiornata);
        Button addPeriodButton = new Button("Aggiungi Periodo");
        addPeriodButton.setOnAction(this::aggiungiPeriodo);
        
        VBox tastiPeriodo = new VBox();
        tastiPeriodo.setSpacing(10);
        tastiPeriodo.getChildren().addAll(saveButton,removeButton,addPeriodButton);
        
        progSettimanaleAlta.getChildren().addAll(scegliGiorno,periodi,tastiPeriodo);
        progSettimanaleAlta.setSpacing(10);
        TextField endTimeField1 = new TextField();
        endTimeField1.setPromptText("19:00");

        
        HBox dateBox = new HBox();
        dateBox.setAlignment(Pos.CENTER);
        dateBox.setSpacing(10);

        startDateField = new DatePicker();
        startDateField.setValue(LocalDate.now());

        endDateField = new DatePicker();
        endDateField.setValue(LocalDate.now().plusDays(10));

        dateBox.getChildren().addAll(new Label("Data di Inizio:"), startDateField, new Label("Data di Fine:"), endDateField);

        dayCheckBoxes = new DayBox[7];
        dayCheckBoxBox = new HBox();
        dayCheckBoxBox.setAlignment(Pos.CENTER);
        dayCheckBoxBox.setSpacing(10);
        
        HBox progSettimanaleSotto = new HBox();
        progSettimanaleSotto.getChildren().add(dateBox);

        for (int i = 0; i < 7; i++) {
        	String giorno = switch(i) {
          case 0 ->  "L";
          case 1 -> "Ma";
          case 2 -> "Me";
          case 3 -> "G";
          case 4 -> "V";
          case 5 -> "S";
          case 6 -> "D";
          default -> "L";
          };
            dayCheckBoxes[i] = new DayBox(giorno);
            dayCheckBoxBox.getChildren().add(dayCheckBoxes[i]);      
        }

        vbox.getChildren().addAll(topBox, schedulingBox,progSettimanaleAlta , progSettimanaleSotto);

        this.contenuto = vbox;
    }
    
    private void removeGiornata(ActionEvent event) {
    	System.out.println("Rimuovo Giornata");
    	DayOfWeek tempDayOfWeek = this.dayOfWeekComboBox.getValue();
    	this.periodi.getChildren().removeAll(this.ArraylistaTimeBox[tempDayOfWeek.getValue()-1]);
        this.ArraylistaTimeBox[tempDayOfWeek.getValue()-1] = new ArrayList<>();
        this.ArraylistaTimeBox[tempDayOfWeek.getValue()-1].add(new TimeBox());
        this.periodi.getChildren().addAll( this.ArraylistaTimeBox[tempDayOfWeek.getValue()-1]);
        
    }
    
    private void cambiaVisualizzazionePeriodiGiorno(DayOfWeek oldValue, DayOfWeek newValue) {
    	this.periodi.getChildren().removeAll(this.ArraylistaTimeBox[oldValue.getValue()-1]);
    	this.periodi.getChildren().addAll(this.ArraylistaTimeBox[newValue.getValue()-1]);
    }

    private void saveDay(ActionEvent event) {
        System.out.println("Giornata salvata!");
    }
    
    private void aggiungiPeriodo(ActionEvent event) {
    	var temp = new TimeBox();
    	this.ArraylistaTimeBox[this.dayOfWeekComboBox.getValue().getValue()-1].add(temp);
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
    	this.dayCheckBoxes[tempDayOfWeek.getValue()- 1].cambiaStato();
    	this.dayCheckBoxBox.requestLayout();
    	
    }
    public List<Periodo> getListPeriodo(){
    	return this.listaPeriodi;
    }
    
    public LocalDate[] getEstremi() {
    	LocalDate[] estremi = new LocalDate[2];
    	estremi[0] = this.startDateField.getValue();
    	estremi[1] = this.endDateField.getValue();
    	return estremi;
    }

}
