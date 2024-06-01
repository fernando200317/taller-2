package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Clase principal para iniciar una aplicación de gestión de hotel.
 */
class Main {

    /**
     * El punto de entrada principal para la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta implementación).
     */
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.iniciar();  // Inicia el proceso de gestión del hotel.
    }
}



 

