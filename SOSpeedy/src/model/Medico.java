package model;

import java.util.*;
import java.time.*;




public class Medico {
    private int idMedico;
    private String nome;
    private String cognome;
    private List<Turno> turni;

    public Medico() {
        this.idMedico = -1;
        this.nome = "";
        this.cognome = "";
    }

    public Medico(int idMedico, String nome, String cognome) {
        this.idMedico = idMedico;
        this.nome = nome;
        this.cognome = cognome;
        this.turni = new ArrayList<Turno>();
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<Turno> getTurni() {
        return turni;
    }
    public void setTurni(List<Turno> turni) {
        this.turni = turni;
    }
    public void addTurno(Turno turno) {
        this.turni.add(turno);
    }
    public void removeTurno(Turno turno) {
        this.turni.remove(turno);
    }

    public boolean disponibile(LocalDateTime dataOraAttuale) {
        //algoritmo che verifica se il medico è disponibile andando a ciclare su suoi turni
        //se il medico è disponibile rin questo momento restituisce "Disponibile ora"
        //altrimenti restituisce "Non disponibile ora"
        for(Turno turno : turni) {
            //algoritmo che verifica se il medico è disponibile in questo momento
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMedico, nome, cognome, turni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Medico medico = (Medico) obj;
        return idMedico == medico.idMedico &&
                Objects.equals(nome, medico.nome) &&
                Objects.equals(cognome, medico.cognome) &&
                Objects.equals(turni, medico.turni);
    }
    
    @Override
    public String toString() {
    	return this.nome.charAt(0)+". "+this.cognome;
    }
}