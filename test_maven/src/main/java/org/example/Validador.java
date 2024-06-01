package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase auxiliar para validar el formato de un RUT chileno (Rol Único Tributario).
 *
 * Proporciona métodos para verificar si un RUT tiene el formato correcto y si el dígito verificador es válido.
 */
public class Validador {

    /**
     * Valida el formato y el dígito verificador de un RUT chileno.
     *
     * @param rut El RUT a validar, en formato "XXXXXXXX-X" (sin puntos ni espacios).
     * @return true si el RUT es válido, false en caso contrario.
     */
    public boolean validaRut(String rut) {
        // Verifica el formato básico del RUT (números y guión)
        Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");
        Matcher matcher = pattern.matcher(rut);
        if (!matcher.matches()) {
            return false;  // El formato no es válido
        }

        // Separa el RUT en número y dígito verificador
        String[] stringRut = rut.split("-");

        // Calcula el dígito verificador esperado y lo compara con el proporcionado
        return stringRut[1].equalsIgnoreCase(dv(stringRut[0]));
    }

    /**
     * Calcula el dígito verificador (DV) de un RUT chileno.
     *
     * Este método implementa el algoritmo estándar de cálculo del DV del RUT.
     *
     * @param rut El número del RUT sin el dígito verificador (ej. "12345678").
     * @return El dígito verificador calculado, ya sea un número del 0 al 9 o la letra "k".
     */
    public static String dv(String rut) {
        int M = 0, S = 1, T = Integer.parseInt(rut);

        // Algoritmo de cálculo del DV
        for (; T != 0; T = (int) Math.floor(T /= 10)) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }

        return (S > 0) ? String.valueOf(S - 1) : "k"; // Retorna el DV
    }
}
