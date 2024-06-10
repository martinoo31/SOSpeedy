package model;

import java.time.LocalDateTime;

public class PazienteInCoda extends Paziente {
    private LocalDateTime inizioAttesa;

    public PazienteInCoda(int idPaziente, 
                            String codiceIdentificativo, 
                            CodiceColore codiceColore, 
                            String nome, 
                            String cognome, 
                            String codiceFiscale, 
                            LocalDateTime inizioAttesa) 
        {
        super(idPaziente, codiceIdentificativo, codiceColore, nome, cognome, codiceFiscale);
        this.inizioAttesa = inizioAttesa;
    }
    
    public PazienteInCoda(){
        super();
        this.inizioAttesa = null;
    }

    public LocalDateTime getInizioAttesa() {
        return inizioAttesa;
    }

    public void setInizioAttesa(LocalDateTime inizioAttesa) {
        this.inizioAttesa = inizioAttesa;
    }
    
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((inizioAttesa == null) ? 0 : inizioAttesa.hashCode());
        return result;
    }

        public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PazienteInCoda other = (PazienteInCoda) obj;
        if (inizioAttesa == null) {
            if (other.inizioAttesa != null)
                return false;
        } else if (!inizioAttesa.equals(other.inizioAttesa))
            return false;
        return true;
    }

    //implemento il metodo compareTo in base al codice colore e poi in base alla data di inizio attesa. L'rdine di priorita del codice colore è ROSSO > ARANCIONE > AZZURRO > VERDE > BIANCO
    
    public int compareTo(PazienteInCoda o) {
        if(this.getCodiceColore().compareTo(o.getCodiceColore()) == 0){
            return this.getInizioAttesa().compareTo(o.getInizioAttesa());
        }
        else if(this.getCodiceColore().compareTo(o.getCodiceColore()) == 1){
            return -1;
        }
        else{
            return 1;
        }
    }
    
}