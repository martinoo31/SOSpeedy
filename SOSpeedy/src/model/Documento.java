package model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

public class Documento {
    private int idDocumento;
    private String titolo;
    private LocalDateTime data;
    private File contenuto;

    public Documento() {
        this.idDocumento = -1;
        this.titolo = "";
        this.data = null;
        this.contenuto = null;
    }

    public Documento(int idDocumento, String titolo, LocalDateTime data, File contenuto) {
        this.idDocumento = idDocumento;
        this.titolo = titolo;
        this.data = data;
        this.contenuto = contenuto;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public File getContenuto() {
        return contenuto;
    }

    public void setContenuto(File contenuto) {
        this.contenuto = contenuto;
    }

    public int hashCode() {
        return Objects.hash(idDocumento, titolo, data, contenuto);
    }

   
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Documento documento = (Documento) obj;
        return idDocumento == documento.idDocumento &&
                Objects.equals(titolo, documento.titolo) &&
                Objects.equals(data, documento.data) &&
                Objects.equals(contenuto, documento.contenuto);
    }

    //implemento il metodo compareTo in base alla data
    
    public int compareTo(Documento documento) {
        return this.data.compareTo(documento.data);
    }
}