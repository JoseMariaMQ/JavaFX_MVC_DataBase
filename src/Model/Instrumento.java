package Model;

import java.time.LocalDate;

public class Instrumento {
    private int numSerie;
    private int numInstrumento;
    private int peso;
    private LocalDate fechaRecepcion;
    private String transportista;
    private String observaciones;

    public Instrumento(int numSerie, int numInstrumento, int peso, LocalDate fechaRecepcion, String transportista, String observaciones) {
        this.numSerie = numSerie;
        this.numInstrumento = numInstrumento;
        this.peso = peso;
        this.fechaRecepcion = fechaRecepcion;
        this.transportista = transportista;
        this.observaciones = observaciones;
    }

    public int getNumSerie() {
        return numSerie;
    }
    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }
    public int getNumInstrumento() {
        return numInstrumento;
    }
    public void setNumInstrumento(int numInstrumento) {
        this.numInstrumento = numInstrumento;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }
    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
    public String getTransportista() {
        return transportista;
    }
    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }
    public String getObservaciones() {
        return observaciones;
    }
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
