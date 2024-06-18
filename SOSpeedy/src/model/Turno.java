package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.*;

public class Turno implements Serializable{
    private int idTurno;
    private LocalDateTime inizioProgrammazione;
    private LocalDateTime fineProgrammazione;
    private List<TurnoEccezioni> turnoEccezioni;
    private List<Periodo> periodo;
    private Medico medico;
    private Visita visita;

    public Turno() {
        this.idTurno = -1;
        this.inizioProgrammazione = null;
        this.fineProgrammazione = null;
    }

    public Turno(int idTurno, LocalDateTime inizioProgrammazione, LocalDateTime fineProgrammazione, Medico medico, Visita visita) {
        this.idTurno = idTurno;
        this.inizioProgrammazione = inizioProgrammazione;
        this.fineProgrammazione = fineProgrammazione;
        this.turnoEccezioni = new ArrayList<TurnoEccezioni>();
        this.periodo = new ArrayList<Periodo>();
        this.medico = medico;
        this.visita = visita;
    }

    public Turno(int idTurno, LocalDateTime inizioProgrammazione, LocalDateTime fineProgrammazione, List<TurnoEccezioni> turnoEccezioni, List<Periodo> periodo, Medico medico, Visita visita) {
        this.idTurno = idTurno;
        this.inizioProgrammazione = inizioProgrammazione;
        this.fineProgrammazione = fineProgrammazione;
        this.turnoEccezioni = turnoEccezioni;
        this.periodo = periodo;
        this.medico = medico;
        this.visita = visita;
    }

    
    // Getters and Setters
    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public LocalDateTime getInizioProgrammazione() {
        return inizioProgrammazione;
    }

    public void setInizioProgrammazione(LocalDateTime inizioProgrammazione) {
        this.inizioProgrammazione = inizioProgrammazione;
    }

    public LocalDateTime getFineProgrammazione() {
        return fineProgrammazione;
    }

    public void setFineProgrammazione(LocalDateTime fineProgrammazione) {
        this.fineProgrammazione = fineProgrammazione;
    }

    public List<TurnoEccezioni> getTurnoEccezioni() {
        return turnoEccezioni;
    }

    public Visita getVisita() {
		return visita;
	}

	public void setVisita(Visita visita) {
		this.visita = visita;
	}

	public void setTurnoEccezioni(List<TurnoEccezioni> turnoEccezioni) {
        this.turnoEccezioni = turnoEccezioni;
    }

    public List<Periodo> getPeriodo() {
        return periodo;
    }

    public void setPeriodo(List<Periodo> periodo) {
        this.periodo = periodo;
    }

    public void addTurnoEccezioni(TurnoEccezioni turnoEccezioni) {
        this.turnoEccezioni.add(turnoEccezioni);
    }

    public void removeTurnoEccezioni(TurnoEccezioni turnoEccezioni) {
        this.turnoEccezioni.remove(turnoEccezioni);
    }

    public void addPeriodo(Periodo periodo) {
        this.periodo.add(periodo);
    }

    public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public void removePeriodo(Periodo periodo) {
        this.periodo.remove(periodo);
    }

    // HashCode and Equals overrides
    @Override
    public int hashCode() {
        return Objects.hash(idTurno, inizioProgrammazione, fineProgrammazione, turnoEccezioni, periodo);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Turno turno = (Turno) obj;
        return idTurno == turno.idTurno && Objects.equals(inizioProgrammazione, turno.inizioProgrammazione) && Objects.equals(fineProgrammazione, turno.fineProgrammazione) && Objects.equals(turnoEccezioni, turno.turnoEccezioni) && Objects.equals(periodo, turno.periodo);
    }
}