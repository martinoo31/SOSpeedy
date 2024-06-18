package controller;


import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import model.Medico;
import model.Visita;
import model.Turno;
import model.TurnoEccezioni;

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
    		System.out.println("Errore Lettura Visite");
    		visite = new ArrayList<>();
    		Visita visita = new Visita("temp1");
    		Visita visita2 = new Visita("temp2");
    		visite.add(visita);
    		visite.add(visita2);
    	}
    	if(this.visite.size()<2) {
    		
    		Visita visita = new Visita("temp3");
    		Visita visita2 = new Visita("temp4");
    		visite.add(visita);
    		visite.add(visita2);
    	}
    	
    	try {
            ObjectInputStream mediciStream = new ObjectInputStream(new FileInputStream("medici.bin"));
            medici = (List<Medico>) mediciStream.readObject();
            mediciStream.close();
        } catch (Exception e) {
            medici = new ArrayList<>();   
            System.out.println("Errore Lettura Medici");
            Medico medico1 = new Medico(1,"Martino","Manaresi");
            Medico medico2 = new Medico(2,"Francesco","Giordani");
            Medico medico3 = new Medico(3,"Federido","Hrvatin");
            medici.add(medico1);
            medici.add(medico2);
            medici.add(medico3);
            try {
				ObjectOutputStream mediciStream = new ObjectOutputStream(new FileOutputStream("medici.bin"));
				mediciStream.writeObject(medici);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        }
    	if(this.medici.size()<2) {
    		Medico medico1 = new Medico(1,"Martino","Manaresi");
            Medico medico2 = new Medico(2,"Francesco","Giordani");
            medici.add(medico1);
            medici.add(medico2);
    	}
    	
    	try {
    		 ObjectInputStream turniStream = new ObjectInputStream(new FileInputStream("turni.bin"));
    		 turni = (List<Turno>) turniStream.readObject();
    		 turniStream.close();
    	}catch(Exception e) {
    		System.out.println("Errore Lettura Turni");
    		turni = new ArrayList<>();    		
    		Turno turno1 = new Turno(1,LocalDateTime.of(2024, 6, 1, 10, 0),LocalDateTime.of(2024, 6, 1, 10, 0),this.medici.get(0),this.visite.get(0));
    		Turno turno2 = new Turno(2,LocalDateTime.of(2024, 6, 1, 10, 0),LocalDateTime.of(2024, 6, 1, 10, 0),this.medici.get(1),this.visite.get(1));
        	turni.add(turno1);
        	turni.add(turno2);
    	}
    	
    	if(turni.size()<2) {
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
    
    public void aggiungiTurno (Turno turno) {
    	this.oTurni.add(turno);
    	try (ObjectOutputStream turnoStream = new ObjectOutputStream(new FileOutputStream("turni.bin"))) {
    		turnoStream.writeObject(this.turni);
    		System.out.println("Salvataggio tuno in corso");
    		turnoStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public void eliminaTurno(Turno turno) {
		this.oTurni.remove(turno);
		try (ObjectOutputStream turniStream = new ObjectOutputStream(new FileOutputStream("turni.bin"))) {
    		turniStream.writeObject(this.turni);
    		System.out.println("Salvataggio eliminazione in corso");
    		turniStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
