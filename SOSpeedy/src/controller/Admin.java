package controller;


import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import model.*;

public class Admin {

    public List<Medico> medici;
    public List<Visita> visite;
    public List<Turno> turni;
    
    public ObservableList<Medico> oMedici;
    public ObservableList<Visita> oVisite;
    public ObservableList<Turno> oTurni;

    public Admin() {
    	try {
    		ObjectInputStream visiteStream = new ObjectInputStream(new FileInputStream("visite.bin"));
    		 visite = (List<Visita>) visiteStream.readObject();
    		 visiteStream.close();
    	}catch(Exception e) {
    		visite = new ArrayList<>();
    	}
    	
    	try {
            ObjectInputStream mediciStream = new ObjectInputStream(new FileInputStream("medici.bin"));
            medici = (List<Medico>) mediciStream.readObject();
            mediciStream.close();
        } catch (Exception e) {
            medici = new ArrayList<>();          
            Medico medico1 = new Medico(1,"Martino","Manaresi");
            Medico medico2 = new Medico(2,"Francesco","Giordani");
            medici.add(medico1);
            medici.add(medico2);
        }
    	
    	try {
    		ObjectInputStream turniStream = new ObjectInputStream(new FileInputStream("turni.bin"));
    		 visite = (List<Visita>) turniStream.readObject();
    		 turniStream.close();
    	}catch(Exception e) {
    		turni = new ArrayList<>();    		
    		Turno turno1 = new Turno(1,LocalDateTime.of(2024, 6, 1, 10, 0),LocalDateTime.of(2024, 6, 1, 10, 0),this.medici.get(0),this.visite.get(0));
    		Turno turno2 = new Turno(2,LocalDateTime.of(2024, 6, 1, 10, 0),LocalDateTime.of(2024, 6, 1, 10, 0),this.medici.get(1),this.visite.get(1));
        	turni.add(turno1);
        	turni.add(turno2);
    	}
    	
        
        this.oMedici =  FXCollections.observableList(medici);
        this.oTurni = FXCollections.observableList(turni);
        this.oVisite = FXCollections.observableList(visite);
        
            
        }
    
    public void add(Visita v) {
    	//this.visite.add(v);
    	this.oVisite.add(v);
    	try (ObjectOutputStream visiteStream = new ObjectOutputStream(new FileOutputStream("visite.bin"))) {
    		visiteStream.writeObject(this.visite);
    		System.out.println("Salvataggio aggiunta in corso");
    		visiteStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void eliminaVisita(Visita v) {
    	this.oVisite.remove(v);
    	try (ObjectOutputStream visiteStream = new ObjectOutputStream(new FileOutputStream("visite.bin"))) {
    		visiteStream.writeObject(this.visite);
    		System.out.println("Salvataggio eliminazione in corso");
    		visiteStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void modificaVisita(Visita v) {
    	this.oVisite.remove(v);
    	this.oVisite.add(v);
    	try (ObjectOutputStream visiteStream = new ObjectOutputStream(new FileOutputStream("visite.bin"))) {
    		visiteStream.writeObject(this.visite);
    		System.out.println("Salvataggio eliminazione in corso");
    		visiteStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void eliminaTurno(Turno turno) {
		// TODO Auto-generated method stub
		
	}

}
