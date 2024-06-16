package model;

public enum CodiceColore {
    ROSSO(1),
    ARANCIONE(2),
    AZZURRO(3),
    VERDE(4),
    BIANCO(5);
	
	private final int priorita;
	
	CodiceColore(int priorita) {
        this.priorita = priorita;
    }

    public int getPriorita() {
        return priorita;
    }
}
