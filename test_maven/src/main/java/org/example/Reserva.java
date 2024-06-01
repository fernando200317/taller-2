package org.example;

public class Reserva {
    private String nombreCliente;
    private String rutCliente;
    private int duracionEstadia;
    private String diaLlegada;
    private int opcionHabitacion;
    private double costoHabitacion;
    private double costoServicios;
    private double costoTotal;

    public Reserva(String nombreCliente, String rutCliente, int duracionEstadia, String diaLlegada, int opcionHabitacion, double costoHabitacion, double costoServicios, double costoTotal) {
        this.nombreCliente = nombreCliente;
        this.rutCliente = rutCliente;
        this.duracionEstadia = duracionEstadia;
        this.diaLlegada = diaLlegada;
        this.opcionHabitacion = opcionHabitacion;
        this.costoHabitacion = costoHabitacion;
        this.costoServicios = costoServicios;
        this.costoTotal = costoTotal;
    }

    public void calcularCostoTotal() {
        costoTotal = costoHabitacion * duracionEstadia + costoServicios;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setDiaLlegada(String diaLlegada) {
        this.diaLlegada = diaLlegada;
    }

    public void setDuracionEstadia(int duracionEstadia) {
        this.duracionEstadia = duracionEstadia;
    }

    public void setOpcionHabitacion(int opcionHabitacion) {
        this.opcionHabitacion = opcionHabitacion;
    }

    public void setCostoHabitacion(double costoHabitacion) {
        this.costoHabitacion = costoHabitacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", rutCliente='" + rutCliente + '\'' +
                ", duracionEstadia=" + duracionEstadia +
                ", diaLlegada='" + diaLlegada + '\'' +
                ", opcionHabitacion=" + opcionHabitacion +
                ", costoHabitacion=" + costoHabitacion +
                ", costoServicios=" + costoServicios +
                ", costoTotal=" + costoTotal +
                '}';
    }
}
