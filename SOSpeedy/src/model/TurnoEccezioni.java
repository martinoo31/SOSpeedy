package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class TurnoEccezioni {
    private int idTurnoEccezioni;
    private LocalDateTime inizio;
    private LocalDateTime fine;
    private boolean disabilitaGiornata;

    public TurnoEccezioni() {
        this.idTurnoEccezioni = -1;
        this.inizio = null;
        this.fine = null;
        this.disabilitaGiornata = false;
    }

    public TurnoEccezioni(int idTurnoEccezioni, LocalDateTime inizio, LocalDateTime fine, boolean disabilitaGiornata) {
        this.idTurnoEccezioni = idTurnoEccezioni;
        this.inizio = inizio;
        this.fine = fine;
        this.disabilitaGiornata = disabilitaGiornata;
    }

    public int getIdTurnoEccezioni() {
        return idTurnoEccezioni;
    }

    public void setIdTurnoEccezioni(int idTurnoEccezioni) {
        this.idTurnoEccezioni = idTurnoEccezioni;
    }

    public LocalDateTime getInizio() {
        return inizio;
    }

    public void setInizio(LocalDateTime inizio) {
        this.inizio = inizio;
    }

    public LocalDateTime getFine() {
        return fine;
    }

    public void setFine(LocalDateTime fine) {
        this.fine = fine;
    }

    public boolean isDisabilitaGiornata() {
        return disabilitaGiornata;
    }

    public void setDisabilitaGiornata(boolean disabilitaGiornata) {
        this.disabilitaGiornata = disabilitaGiornata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurnoEccezioni that = (TurnoEccezioni) o;
        return idTurnoEccezioni == that.idTurnoEccezioni &&
                disabilitaGiornata == that.disabilitaGiornata &&
                Objects.equals(inizio, that.inizio) &&
                Objects.equals(fine, that.fine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTurnoEccezioni, inizio, fine, disabilitaGiornata);
    }
}