package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Paziente implements Comparable, Serializable{
    private int idPaziente;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String codiceIdentificativo;
    private String indirizzo;
    private LocalDate dataNascita;
    private String descrizione;
    private CodiceColore codiceColore;
    

    public Paziente() {
        this.idPaziente = -1;
        this.codiceIdentificativo = "";
        this.codiceColore = CodiceColore.BIANCO;
        this.nome = "";
        this.cognome = "";
        this.codiceFiscale = "";
    }

    

    public Paziente(int idPaziente, String nome, String cognome, String codiceFiscale, String codiceIdentificativo,
			String indirizzo, LocalDate dataNascita, String descrizione, CodiceColore codiceColore) {
		super();
		this.idPaziente = idPaziente;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.codiceIdentificativo = codiceIdentificativo;
		this.indirizzo = indirizzo;
		this.dataNascita = dataNascita;
		this.descrizione = descrizione;
		this.codiceColore = codiceColore;
	}
    
    public Paziente(PazienteInCoda pInC) {
    	super();
    	this.idPaziente = pInC.getIdPaziente();
    	this.nome = pInC.getNome();
    	this.cognome = pInC.getCognome();
    	this.codiceFiscale = pInC.getCodiceFiscale();
    	this.codiceIdentificativo = pInC.getCodiceIdentificativo();
    	this.indirizzo = pInC.getIndirizzo();
    	this.dataNascita = pInC.getDataNascita();
    	this.descrizione = pInC.getDescrizione();
    	this.codiceColore = pInC.getCodiceColore();
    }
    

	public int getIdPaziente() {
		return idPaziente;
	}



	public void setIdPaziente(int idPaziente) {
		this.idPaziente = idPaziente;
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



	public String getCodiceFiscale() {
		return codiceFiscale;
	}



	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}



	public String getCodiceIdentificativo() {
		return codiceIdentificativo;
	}



	public void setCodiceIdentificativo(String codiceIdentificativo) {
		this.codiceIdentificativo = codiceIdentificativo;
	}



	public String getIndirizzo() {
		return indirizzo;
	}



	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}



	public LocalDate getDataNascita() {
		return dataNascita;
	}



	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public CodiceColore getCodiceColore() {
		return codiceColore;
	}



	public void setCodiceColore(CodiceColore codiceColore) {
		this.codiceColore = codiceColore;
	}



	@Override
	public int hashCode() {
		return Objects.hash(codiceColore, codiceFiscale, codiceIdentificativo, cognome, dataNascita, descrizione,
				idPaziente, indirizzo, nome);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paziente other = (Paziente) obj;
		return codiceColore == other.codiceColore && Objects.equals(codiceFiscale, other.codiceFiscale)
				&& Objects.equals(codiceIdentificativo, other.codiceIdentificativo)
				&& Objects.equals(cognome, other.cognome) && Objects.equals(dataNascita, other.dataNascita)
				&& Objects.equals(descrizione, other.descrizione) && idPaziente == other.idPaziente
				&& Objects.equals(indirizzo, other.indirizzo) && Objects.equals(nome, other.nome);
	}



	@Override
    public int compareTo(Object o) {
        Paziente p = (Paziente) o;
        return this.codiceColore.compareTo(p.codiceColore);
    }
	
	
}