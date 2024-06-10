package model;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Objects;

public class Attesa {
    private int idAttesa;
    private LocalDateTime start;
    private LocalDateTime end;

    public Attesa() {
        this.idAttesa = 0;
        this.start = null;
        this.end = null;
    }

    public Attesa(int idAttesa, LocalDateTime start, LocalDateTime end) {
        this.idAttesa = idAttesa;
        this.start = start;
        this.end = end;
    }

    public int getIdAttesa() {
        return idAttesa;
    }

    public void setIdAttesa(int idAttesa) {
        this.idAttesa = idAttesa;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAttesa, start, end);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attesa attesa = (Attesa) obj;
        return idAttesa == attesa.idAttesa && Objects.equals(start, attesa.start) && Objects.equals(end, attesa.end);
    }
}