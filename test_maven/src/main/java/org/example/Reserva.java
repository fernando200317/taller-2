package org.example;

/**
 * Representa una reserva de hotel, almacenando información del cliente, detalles de la estadía y costos asociados.
 */
public class Reserva {

    private String nombreCliente;
    private String rutCliente;
    private int duracionEstadia;
    private String diaLlegada;
    private int opcionHabitacion;
    private double costoHabitacion;
    private double costoServicios;
    private double costoTotal;

    /**
     * Constructor para crear una nueva reserva.
     *
     * @param nombreCliente    Nombre del cliente.
     * @param rutCliente       RUT del cliente.
     * @param duracionEstadia   Duración de la estadía en días.
     * @param diaLlegada       Fecha de llegada (formato a definir, por ejemplo, "dd/MM/yyyy").
     * @param opcionHabitacion  Opción de habitación seleccionada.
     * @param costoHabitacion  Costo por día de la habitación.
     * @param costoServicios   Costo de servicios adicionales.
     * @param costoTotal      Costo total de la reserva (puede ser calculado posteriormente).
     */
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

    /**
     * Calcula el costo total de la reserva basándose en la duración de la estadía, el costo de la habitación y los servicios.
     */
    public void calcularCostoTotal() {
        costoTotal = costoHabitacion * duracionEstadia + costoServicios;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public int getDuracionEstadia() {
        return duracionEstadia;
    }

    public String getDiaLlegada() {
        return diaLlegada;
    }

    public int getOpcionHabitacion() {
        return opcionHabitacion;
    }

    public double getCostoHabitacion() {
        return costoHabitacion;
    }

    public double getCostoServicios() {
        return costoServicios;
    }

    public double getCostoTotal() {
        return costoTotal;
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

    public void setCostoServicios(double costoServicios) {
        this.costoServicios = costoServicios;
    }

    /**
     * Devuelve una representación en cadena de la reserva con todos sus detalles.
     *
     * @return Cadena con la información de la reserva.
     */
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
