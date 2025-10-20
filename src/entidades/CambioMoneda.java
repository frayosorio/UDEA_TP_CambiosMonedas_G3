package entidades;

import java.time.LocalDate;

public class CambioMoneda {

    private String moneda;
    private LocalDate fecha;
    private double cambio;
    
    public CambioMoneda(String moneda, LocalDate fecha, double cambio) {
        this.moneda = moneda;
        this.fecha = fecha;
        this.cambio = cambio;
    }

    public String getMoneda() {
        return moneda;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getCambio() {
        return cambio;
    }

}
