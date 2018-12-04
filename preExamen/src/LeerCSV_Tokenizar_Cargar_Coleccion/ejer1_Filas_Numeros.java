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
public class ejer1_Filas_Numeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        ListaNum objLista;
        Integer num;
        List<ListaNum> colNum;

        try {
            fr = new FileReader("numeros_ejer2.csv");
            //leemos lineas del fichero
            br = new BufferedReader(fr);
            colNum = new ArrayList<ListaNum>();

            //bucle lee lineas y va tokenizando
            linea = br.readLine();
            while (linea != null) {
                StringTokenizer tokens = new StringTokenizer(linea, ",");
                System.out.println("Linea para cargar coleccion: " + linea);
                System.out.println("");

                objLista = new ListaNum();
                while (tokens.hasMoreTokens()) {
                    num = Integer.parseInt(tokens.nextToken().trim());

                    objLista.getNumeros().add(num);

                }
                colNum.add(objLista);
                linea = br.readLine();
            }

            //mostramos la coleccion cargada y ordenada con objetos Integer         
            Iterator at = colNum.iterator();
            //mostramos la coleccion con  el iterator 
            System.out.println("Coleccion ArrayList ordenada: ");
            while (at.hasNext()) {
                System.out.println(at.next().toString());
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
