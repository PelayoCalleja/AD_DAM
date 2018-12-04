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
import modelo.PalabraNumero;

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
        int totalApariciones = 0;

        while ((lineaLeida = inputStream.readLine()) != null) {

            for (StringTokenizer stringTokenizer = new StringTokenizer(lineaLeida); stringTokenizer.hasMoreTokens();) {
                String palabra = stringTokenizer.nextToken();
                String replaced;

                replaced = palabra.replace(".", "");
                replaced = palabra.replace(",", "");
                replaced = palabra.replace(";", "");

                replaced = replaced.trim().toLowerCase();
                if (replaced.contains(palabraBuscada.toLowerCase())) {
                    totalApariciones++;

                }
            }
        }
        return totalApariciones;
    }

    public static int numeroLetras(String rutaFichero) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(rutaFichero));
        int numTotalLetras = 0;

        String lineaLeida;

        while ((lineaLeida = inputStream.readLine()) != null) {

            for (int i = 0; i < lineaLeida.length(); i++) {

                char c = lineaLeida.charAt(i);
                if (Character.isAlphabetic(c)) {
                    numTotalLetras++;
                }
            }

        }

        return numTotalLetras;
    }

    public static String LineasAlReves(String rutaFichero) throws FileNotFoundException, IOException {

        BufferedReader inputStream = new BufferedReader(new FileReader(rutaFichero));
        String lineaLeida, textoADevolver = "", reves = null;
        StringBuilder b;

        while ((lineaLeida = inputStream.readLine()) != null) {
            b = new StringBuilder(lineaLeida);
            reves = b.reverse().toString();
            textoADevolver = textoADevolver + reves + "\n";

        }

        return textoADevolver;
    }

    
    public static String contarPalabras (String rutaFichero) throws FileNotFoundException, IOException{
    
     BufferedReader inputStream = new BufferedReader(new FileReader(rutaFichero));
        String lineaLeida;
        int totalApariciones = 1;
        ArrayList<PalabraNumero> listado= new ArrayList<>();
        PalabraNumero pn = null;
        String replaced = null;

        while ((lineaLeida = inputStream.readLine()) != null) {

            for (StringTokenizer stringTokenizer = new StringTokenizer(lineaLeida); stringTokenizer.hasMoreTokens();) {
                String palabra = stringTokenizer.nextToken();
     


                replaced = palabra.replace(".", "");
                replaced = palabra.replace(",", "");
                replaced = palabra.replace(";", "");

                replaced = replaced.trim().toLowerCase();
            }
 
            for (int i = 0; i <listado.size(); i++) {
                    
                
                if(listado.get(i).getPalabra().equalsIgnoreCase(replaced)){
                    totalApariciones = pn.getNumero();
                    pn.setNumero(totalApariciones+1);
                     listado.get(i).setNumero(totalApariciones+1);
                 
                 }else{
                    pn.setPalabra(replaced);
                    pn.setNumero(1);
                    listado.add(pn);
                    
                 }
                
               
                
               
                }
                
                }
           
       
    return listado.toString();
    }
            
    
    
}
