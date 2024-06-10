package model;

import java.io.Serializable;
import java.util.*;

public class Paziente implements Comparable, Serializable{
    private int idPaziente;
    private String codiceIdentificativo;
    private CodiceColore codiceColore;
    private String nome;
    private String cognome;
    private String codiceFiscale;

    public Paziente() {
        this.idPaziente = -1;
        this.codiceIdentificativo = "";
        this.codiceColore = CodiceColore.BIANCO;
        this.nome = "";
        this.cognome = "";
        this.codiceFiscale = "";
    }

    public Paziente(int idPaziente, String codiceIdentificativo, CodiceColore codiceColore, String nome, String cognome, String codiceFiscale) {
        this.idPaziente = idPaziente;
        this.codiceIdentificativo = codiceIdentificativo;
        this.codiceColore = codiceColore;
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
    }

    // Getters and setters
    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    public String getCodiceIdentificativo() {
        return codiceIdentificativo;
    }

    public void setCodiceIdentificativo(String codiceIdentificativo) {
        this.codiceIdentificativo = codiceIdentificativo;
    }

    public CodiceColore getCodiceColore() {
        return codiceColore;
    }

    public void setCodiceColore(CodiceColore codiceColore) {
        this.codiceColore = codiceColore;
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

    // Hash and equals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idPaziente;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Paziente other = (Paziente) obj;
        return idPaziente == other.idPaziente;
    }

    @Override
    public int compareTo(Object o) {
        Paziente p = (Paziente) o;
        return this.codiceColore.compareTo(p.codiceColore);
    }
}
