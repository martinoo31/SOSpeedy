package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class Periodo {
    
    private int idPeriodo;
    private DayOfWeek giorno;
    private LocalTime inizio;
    private LocalTime fine;

    public Periodo(int idPeriodo, DayOfWeek giorno, LocalTime inizio, LocalTime fine) {
        this.idPeriodo = idPeriodo;
        this.giorno = giorno;
        this.inizio = inizio;
        this.fine = fine;
    }

    public Periodo() {
        this.idPeriodo = -1;
        this.giorno = null;
        this.inizio = null;
        this.fine = null;
    }


	public int getId() {
        return idPeriodo;
    }

    public DayOfWeek getGiorno() {
        return giorno;
    }

    public void setGiorno(DayOfWeek giorno) {
        this.giorno = giorno;
    }

    public LocalTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalTime inizio) {
        this.inizio = inizio;
    }

    public LocalTime getFine() {
        return fine;
    }

    public void setFine(LocalTime fine) {
        this.fine = fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodo periodo = (Periodo) o;
        return idPeriodo == periodo.idPeriodo &&
                giorno == periodo.giorno &&
                Objects.equals(inizio, periodo.inizio) &&
                Objects.equals(fine, periodo.fine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeriodo, giorno, inizio, fine);
    }
}