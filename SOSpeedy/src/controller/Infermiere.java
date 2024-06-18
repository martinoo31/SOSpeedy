package controller;


import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import model.*;

public class Infermiere {

    public List<Paziente> pazienti;
    public ObservableList<Paziente> oPazienti;
    public List<PazienteInCoda> pazientiInCoda;
    public ObservableList<PazienteInCoda> oPazientiInCoda;
    public Set<String> IDs;
    
    private String generateIdentificativoPaziente() {
        Random random = new Random();
        String randomString = "";
        do {
        	// Genera una lettera maiuscola casuale (da 'A' a 'Z')
        	char randomLetter = (char) ('A' + random.nextInt(26));
        	
        	// Genera tre cifre casuali
        	int randomDigit1 = random.nextInt(10);
        	int randomDigit2 = random.nextInt(10);
        	int randomDigit3 = random.nextInt(10);
        	
        	// Combina la lettera e le cifre in una stringa
        	randomString = ""+randomLetter + randomDigit1 + randomDigit2 + randomDigit3;
        
        }while(this.IDs.contains(randomString));
        
        this.IDs.add(randomString);
       
        return randomString;
    }

    public Infermiere() {
    	this.IDs=new HashSet<>();
    	
    	Paziente paziente1 = new Paziente(1,"Federico",
    			"Hrvatin","HRVFRC02R19A944W", 
    			this.generateIdentificativoPaziente(), "Via Marzocchi 16, Caselecchio",
    			LocalDate.of(2002, 9, 3), "Frattura del polso evidente",
    			CodiceColore.VERDE);
    	Paziente paziente2 = new Paziente(2,"Francesco",
    			"Giordani","GRDFNC02P03A944J", 
    			this.generateIdentificativoPaziente(), "Via Saragozza 135, Bologna",
    			LocalDate.of(2002, 9, 3), "Strana aritmia",
    			CodiceColore.AZZURRO);
    	
    	PazienteInCoda pazienteInCoda1= new PazienteInCoda(paziente1, LocalDateTime.of(2024, 06, 19, 8, 45) );
    	PazienteInCoda pazienteInCoda2= new PazienteInCoda(paziente1, LocalDateTime.of(2024, 06, 19, 8, 55) );
    	
    	try {
            ObjectInputStream pazientiStream = new ObjectInputStream(new FileInputStream("pazienti.bin"));
            pazienti = (List<Paziente>) pazientiStream.readObject();
            pazientiStream.close();
        } catch (Exception e) {
            pazienti = new ArrayList<>();          
            pazienti.add(paziente1);
            pazienti.add(paziente2);
        }
    	
    	try {
            ObjectInputStream pazientiInCodaStream = new ObjectInputStream(new FileInputStream("C:\\Users\\ZBook 15\\OneDrive\\Desktop\\pazientiInCoda.bin"));
            pazientiInCoda = (List<PazienteInCoda>) pazientiInCodaStream.readObject();
            pazientiInCodaStream.close();
        } catch (Exception e) {
            pazientiInCoda = new ArrayList<>();          
            pazientiInCoda.add(pazienteInCoda1);
            pazientiInCoda.add(pazienteInCoda2);
        }
    	
    	
    	
        
        this.oPazienti =  FXCollections.observableList(pazienti);
        this.oPazientiInCoda =  FXCollections.observableList(pazientiInCoda);
       
        
            
        }
    
    public String registraPaziente( String nome, String cognome, String codiceFiscale, 
			LocalDate dataNascita, String descrizione, CodiceColore codiceColore) {
    	//this.visite.add(v);
    	String id=this.generateIdentificativoPaziente();
    	Paziente p= new Paziente(1,nome,cognome,codiceFiscale, id , 
    			"indirizzo fittizio", dataNascita, descrizione, codiceColore);
    	p.setVisita(new Visita(0, "Visita generale",null));
    	this.oPazienti.add(p);
    	PazienteInCoda pInCoda=new PazienteInCoda(p, LocalDateTime.now());
    	this.oPazientiInCoda.add(pInCoda);
    	
    	try (ObjectOutputStream pazientiStream = new ObjectOutputStream(new FileOutputStream("pazienti.bin"))) {
    		pazientiStream.writeObject(this.pazienti);
    		System.out.println("Salvataggio registrazione paziente in corso");
    		pazientiStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	try (ObjectOutputStream pazientiInCodaStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ZBook 15\\OneDrive\\Desktop\\pazientiInCoda.bin"))) {
    		pazientiInCodaStream.writeObject(this.pazientiInCoda);
    		System.out.println("Salvataggio registrazione paziente messo in coda in corso");
    		pazientiInCodaStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return id;
    	
    }
    
    public void deregistraPaziente(PazienteInCoda pInCoda) {
    	
    	if(this.oPazientiInCoda.contains(pInCoda)) {	
    		this.oPazientiInCoda.remove(pInCoda);
    		this.oPazienti.remove(pInCoda);
    	}
    	try (ObjectOutputStream pazientiInCodaStream = new ObjectOutputStream(new FileOutputStream("C:\\Users\\ZBook 15\\OneDrive\\Desktop\\pazientiInCoda.bin"))) {
    		pazientiInCodaStream.writeObject(this.pazientiInCoda);
    		System.out.println("Salvataggio eliminazione dai pazienti in coda in corso");
    		pazientiInCodaStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try (ObjectOutputStream pazientiStream = new ObjectOutputStream(new FileOutputStream("pazienti.bin"))) {
    		pazientiStream.writeObject(this.pazienti);
    		System.out.println("Salvataggio eliminazione dai pazienti in corso");
    		pazientiStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
