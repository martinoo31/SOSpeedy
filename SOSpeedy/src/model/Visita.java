package model;

import java.util.Objects;
import java.util.TreeSet;

public class Visita {
    private int idVisita;
    private String nomeVisita;
    private TreeSet<Paziente> coda;

    public Visita() {
        this.idVisita = -1;
        this.nomeVisita = "";
        this.coda = new TreeSet<>();
    }

    public Visita(int idVisita, String nomeVisita, TreeSet<Paziente> coda) {
        this.idVisita = idVisita;
        this.nomeVisita = nomeVisita;
        this.coda = coda;
    }

    // Getters and Setters
    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getNomeVisita() {
        return nomeVisita;
    }

    public void setNomeVisita(String nomeVisita) {
        this.nomeVisita = nomeVisita;
    }

    public TreeSet<Paziente> getCoda() {
        return coda;
    }

    public void setCoda(TreeSet<Paziente> coda) {
        this.coda = coda;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return Objects.equals(idVisita, visita.idVisita) &&
                Objects.equals(nomeVisita, visita.nomeVisita) &&
                Objects.equals(coda, visita.coda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVisita, nomeVisita, coda);
    }
}
