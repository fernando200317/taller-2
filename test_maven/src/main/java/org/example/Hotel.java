package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Hotel {
    private List<Reserva> reservas;

    public Hotel() {
        reservas = new ArrayList<>();
        cargarReservasDeJSON();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Bienvenido al Hotel");
                System.out.println("-------------------");
                System.out.println("1. Hacer una nueva reserva");
                System.out.println("2. Ver reservas");
                System.out.println("3. Salir");
                System.out.println("4. Editar una reserva");
                System.out.println("5. Eliminar una reserva");
                System.out.print("Ingrese el número de la opción deseada: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        hacerNuevaReserva(scanner);
                        break;
                    case 2:
                        verReservas();
                        break;
                    case 3:
                        salir = true;
                        break;
                    case 4:
                        editarReserva(scanner);
                        break;
                    case 5:
                        eliminarReserva(scanner);
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }

                System.out.println("-------------------");
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void hacerNuevaReserva(Scanner scanner) {
        try {
            System.out.println("Sistema de Reserva de habitación");
            System.out.println("--------------------------------");
            System.out.print("Ingrese el nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            String rutCliente = "";
            boolean rutValido = false;

            Validador validador = new Validador();

            while (!rutValido) {
                System.out.print("Ingrese el RUT del cliente (Formato XXXXXXX-X): ");
                rutCliente = scanner.nextLine();

                if (validador.validaRut(rutCliente)) {
                    rutValido = true;
                    System.out.println("RUT válido");
                } else {
                    System.out.println("RUT inválido. Por favor, ingrese un RUT válido.");
                }
            }

            System.out.print("Ingrese el día de llegada al hotel: ");
            String diaLlegada = scanner.nextLine();
            System.out.print("Ingrese la duración de la estadía (en días): ");
            int duracionEstadia = scanner.nextInt();

            int opcionHabitacion = 0;
            boolean habitacionValida = false;
            double costoHabitacion = 0;
            while (!habitacionValida) {
                System.out.println("Opciones de habitaciones:");
                System.out.println("1. Habitación individual - $100");
                System.out.println("2. Habitación doble - $150");
                System.out.println("3. Habitación suite - $300");
                System.out.print("Ingrese el número de la habitación elegida: ");
                opcionHabitacion = scanner.nextInt();

                switch (opcionHabitacion) {
                    case 1:
                        costoHabitacion = 100.0;
                        habitacionValida = true;
                        break;
                    case 2:
                        costoHabitacion = 150.0;
                        habitacionValida = true;
                        break;
                    case 3:
                        costoHabitacion = 300.0;
                        habitacionValida = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            }

            System.out.print("¿Desea agregar servicios adicionales? (S/N): ");
            String respuestaServicios = scanner.next();

            double costoServicios = 0.0;
            List<Integer> serviciosSeleccionados = new ArrayList<>();

            if (respuestaServicios.equalsIgnoreCase("S")) {
                boolean agregarServicios = true;
                while (agregarServicios) {
                    System.out.println("Lista de servicios adicionales:");
                    System.out.println("1. Servicio de limpieza - $20");
                    System.out.println("2. Desayuno incluido - $10");
                    System.out.println("3. Acceso al gimnasio - $15");
                    System.out.println("4. Salir");
                    System.out.print("Ingrese el número del servicio adicional elegido (4 para salir): ");
                    int opcionServicio = scanner.nextInt();

                    if (opcionServicio == 4) {
                        agregarServicios = false;
                    } else if (serviciosSeleccionados.contains(opcionServicio)) {
                        System.out.println("Ya ha seleccionado ese servicio. Por favor, elija otro.");
                    } else if (opcionServicio >= 1 && opcionServicio <= 3) {
                        serviciosSeleccionados.add(opcionServicio);
                        switch (opcionServicio) {
                            case 1:
                                costoServicios += 20.0;
                                break;
                            case 2:
                                costoServicios += 10.0;
                                break;
                            case 3:
                                costoServicios += 15.0;
                                break;
                        }
                    } else {
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    }
                }
            }

            double costoTotal = calcularCostoTotal(costoHabitacion, duracionEstadia, costoServicios, scanner);

            System.out.println("-------------------");
            System.out.println("Resumen de la reserva");
            System.out.println("-------------------");
            System.out.println("Cliente: " + nombreCliente);
            System.out.println("RUT del cliente: " + rutCliente);
            System.out.println("Duración de la estadía: " + duracionEstadia + " días");
            System.out.println("Día de llegada al hotel: " + diaLlegada);
            System.out.println("Habitación elegida: " + opcionHabitacion);
            System.out.println("Costo de la habitación: $" + costoHabitacion);
            System.out.println("Costo de los servicios adicionales: $" + costoServicios);
            System.out.println("Costo total: $" + costoTotal);

            Reserva reserva = new Reserva(nombreCliente, rutCliente, duracionEstadia, diaLlegada, opcionHabitacion, costoHabitacion, costoServicios, costoTotal);
            reservas.add(reserva);

            guardarReservasEnJSON();
        } catch (Exception e) {
            System.out.println("Error al hacer la reserva: " + e.getMessage());
        }
    }

    private double calcularCostoTotal(double costoHabitacion, int duracionEstadia, double costoServicios, Scanner scanner) {
        double costoTotal = costoHabitacion * duracionEstadia + costoServicios;
        try {
            System.out.print("Ingrese la edad del cliente: ");
            int edadCliente = scanner.nextInt();

            if (edadCliente >= 65) {
                double descuento = costoTotal * 0.1;
                costoTotal -= descuento;
                System.out.println("Se aplicó un descuento del 10% para personas mayores de 65 años.");
            }

            if (duracionEstadia > 7) {
                double descuento = costoTotal * 0.05;
                costoTotal -= descuento;
                System.out.println("Se aplicó un descuento del 5% para estadías de más de 7 días.");
            }
        } catch (Exception e) {
            System.out.println("Error al calcular el costo total: " + e.getMessage());
        }

        return costoTotal;
    }

    private void verReservas() {
        System.out.println("");
        System.out.println("Reservas existentes:");
        System.out.println("-------------------");

        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private void editarReserva(Scanner scanner) {
        try {
            System.out.println("\nEditar Reserva Existente");
            System.out.println("------------------------");
            verReservas();

            System.out.print("Ingrese el RUT del cliente de la reserva a editar: ");
            String rutCliente = scanner.nextLine();

            Reserva reservaAEditar = buscarReservaPorRut(rutCliente);

            if (reservaAEditar != null) {
                System.out.println("Datos actuales de la reserva:");
                System.out.println(reservaAEditar);

                boolean continuarEditando = true;
                while (continuarEditando) {
                    System.out.println("\n¿Qué desea editar?");
                    System.out.println("1. Día de llegada");
                    System.out.println("2. Duración de la estadía");
                    System.out.println("3. Habitación");
                    System.out.println("4. Terminar edición");
                    System.out.print("Ingrese la opción: ");

                    int opcion = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcion) {
                        case 1:
                            System.out.print("Nuevo día de llegada: ");
                            String nuevoDiaLlegada = scanner.nextLine();
                            reservaAEditar.setDiaLlegada(nuevoDiaLlegada);
                            break;
                        case 2:
                            System.out.print("Nueva duración de la estadía (en días): ");
                            int nuevaDuracion = scanner.nextInt();
                            scanner.nextLine();
                            reservaAEditar.setDuracionEstadia(nuevaDuracion);
                            break;
                        case 3:
                            System.out.print("Nueva habitación (1: Individual, 2: Doble, 3: Suite): ");
                            int nuevaHabitacion = scanner.nextInt();
                            scanner.nextLine();
                            if (nuevaHabitacion >= 1 && nuevaHabitacion <= 3) {
                                reservaAEditar.setOpcionHabitacion(nuevaHabitacion);
                                reservaAEditar.setCostoHabitacion(nuevaHabitacion == 1 ? 100.0 : nuevaHabitacion == 2 ? 150.0 : 300.0);
                            } else {
                                System.out.println("Opción de habitación inválida.");
                            }
                            break;
                        case 4:
                            continuarEditando = false;
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }


                    reservaAEditar.calcularCostoTotal();
                }

                System.out.println("\nReserva actualizada:");
                System.out.println(reservaAEditar);

                guardarReservasEnJSON();
            } else {
                System.out.println("No se encontró ninguna reserva con ese RUT.");
            }
        } catch (Exception e) {
            System.out.println("Error al editar la reserva: " + e.getMessage());
        }
    }

    private void eliminarReserva(Scanner scanner) {
        try {
            System.out.println("\nEliminar Reserva Existente");
            System.out.println("------------------------");
            verReservas();

            System.out.print("Ingrese el RUT del cliente de la reserva a eliminar: ");
            String rutCliente = scanner.nextLine();

            Reserva reservaAEliminar = buscarReservaPorRut(rutCliente);

            if (reservaAEliminar != null) {
                System.out.println("Datos de la reserva a eliminar:");
                System.out.println(reservaAEliminar);

                System.out.print("¿Está seguro que desea eliminar esta reserva? (S/N): ");
                String confirmacion = scanner.nextLine();

                if (confirmacion.equalsIgnoreCase("S")) {
                    reservas.remove(reservaAEliminar);
                    guardarReservasEnJSON();
                    System.out.println("Reserva eliminada exitosamente.");
                } else {
                    System.out.println("Eliminación cancelada.");
                }
            } else {
                System.out.println("No se encontró ninguna reserva con ese RUT.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    private Reserva buscarReservaPorRut(String rutCliente) {
        for (Reserva reserva : reservas) {
            if (reserva.getRutCliente().equals(rutCliente)) {
                return reserva;
            }
        }
        return null;
    }

    private void guardarReservasEnJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(reservas);

        try {
            FileWriter writer = new FileWriter("reservas.json");
            writer.write(json);
            writer.close();
            System.out.println("Reserva guardada en el archivo reservas.json");
            System.out.println("");
            System.out.println("MUCHAS GRACIAS POR SU PREFERENCIA");
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva en el archivo.");
        }
    }

    private void cargarReservasDeJSON() {
        try {
            File file = new File("reservas.json");
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                StringBuilder jsonContent = new StringBuilder();

                while (scanner.hasNextLine()) {
                    jsonContent.append(scanner.nextLine());
                }

                Gson gson = new Gson();
                Reserva[] reservasArray = gson.fromJson(jsonContent.toString(), Reserva[].class);
                for (Reserva reserva : reservasArray) {
                    reservas.add(reserva);
                }

                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de reservas.");
        } catch (Exception e) {
            System.out.println("Error al cargar las reservas: " + e.getMessage());
        }
    }

}

