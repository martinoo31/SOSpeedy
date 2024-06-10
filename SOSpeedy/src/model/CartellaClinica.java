package model;

import java.util.TreeSet;
import java.util.Objects;

public class CartellaClinica {
    private TreeSet<Documento> documenti;

    public CartellaClinica() {
        documenti = new TreeSet<Documento>();
    }

    public void aggiungiDocumento(Documento documento) {
        documenti.add(documento);
    }

    public void rimuoviDocumento(Documento documento) {
        documenti.remove(documento);
    }

    public TreeSet<Documento> getDocumenti() {
        return documenti;
    }
    public void setDocumenti(TreeSet<Documento> documenti) {
        this.documenti = documenti;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartellaClinica that = (CartellaClinica) o;
        return Objects.equals(documenti, that.documenti);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(documenti);
    }
}