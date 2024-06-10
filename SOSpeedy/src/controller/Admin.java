package controller;


import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import model.*;

public class Admin {

    public List<Medico> medici;
    public List<Visita> visite;
    public List<Turno> turni;

    public Admin() {
        try {
            ObjectInputStream mediciStream = new ObjectInputStream(new FileInputStream("medici.bin"));
            ObjectInputStream visiteStream = new ObjectInputStream(new FileInputStream("visite.bin"));
            ObjectInputStream turniStream = new ObjectInputStream(new FileInputStream("turni.bin"));

            medici = (List<Medico>) mediciStream.readObject();
            visite = (List<Visita>) visiteStream.readObject();
            turni = (List<Turno>) turniStream.readObject();

            mediciStream.close();
            visiteStream.close();
            turniStream.close();
        } catch (Exception e) {
            medici = new ArrayList<>();
            visite = new ArrayList<>();
            turni = new ArrayList<>();
        }
        System.out.println("MOC");  
            Visita visita1 = new Visita(1, "Consulto Cardiologico",new TreeSet<Paziente>());
            Visita visita2 = new Visita(2, "Consulto Oculistico",new TreeSet<Paziente>());
            Visita visita3 = new Visita(3, "Consulto Ortopedico",new TreeSet<Paziente>());
    visite.add(visita1);
            visite.add(visita2);
            visite.add(visita3);
        }

}
