/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeerCSV_Tokenizar_Cargar_Coleccion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Jordy
 */
public class ejer1_Integer_numeros {

    public static void main(String[] args) {

        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        Integer num;
        List<Integer> colNum;

        try {
            fr = new FileReader("numeros.csv");
            //leemos lineas del fichero
            br = new BufferedReader(fr);
            colNum = new ArrayList<Integer>();

            //bucle lee lineas y va tokenizando
            linea = br.readLine();
            StringTokenizer tokens = new StringTokenizer(linea, ",");
            System.out.println("Linea para cargar coleccion: " + linea);
            System.out.println("");

            while (tokens.hasMoreTokens()) {

                num = Integer.parseInt(tokens.nextToken().trim());
                colNum.add(num);
            }

            //mostramos la coleccion cargada y ordenada con objetos Integer         
            Collections.sort(colNum);
            Iterator it = colNum.iterator();

            //mostramos la coleccion con  el iterator 
            System.out.println("Coleccion ArrayList ordenada: ");
            while (it.hasNext()) {
                System.out.println(it.next());
            }
            System.out.println("Fin de fichero");

        } catch (FileNotFoundException ex) {
            System.out.println("error el fichero no existe");
        } catch (IOException ex) {
            System.out.println("Error en la lectura");
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException ex) {
                System.out.println("Error lectura");
            }
        }

    }
}
