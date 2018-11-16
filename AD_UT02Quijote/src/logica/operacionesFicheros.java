/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 *
 * @author alumnop
 */
public class operacionesFicheros {

    /**
     * Cuenta Numero Lineas
     *
     * @param rutaFichero
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static int contarNumeroLineas(String rutaFichero) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(rutaFichero));
        String lineaLeida;
        int totalLineas = 0;

        while ((lineaLeida = inputStream.readLine()) != null) {
            totalLineas++;
        }
        return totalLineas;
    }

    public static int contarPalabra(String rutaFichero, String palabraBuscada) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(rutaFichero));
        String lineaLeida;
        int totalLineas = 0;
        int totalApariciones = 0;

        while ((lineaLeida = inputStream.readLine()) != null) {

            for (StringTokenizer stringTokenizer = new StringTokenizer(lineaLeida); stringTokenizer.hasMoreTokens();) {
                String palabra = stringTokenizer.nextToken();
                String replaced;

                replaced = palabra.replace(".", "");
                replaced = palabra.replace(",", "");
                replaced = palabra.replace(";", "");

                replaced.trim().toLowerCase();

                if (replaced.contains(palabraBuscada)) {
                    totalApariciones++;

                }
            }

        }
        return totalApariciones;
    }

}
