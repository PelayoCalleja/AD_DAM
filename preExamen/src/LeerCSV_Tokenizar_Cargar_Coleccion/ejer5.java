/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeerCSV_Tokenizar_Cargar_Coleccion;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class ejer5 {
   


    public static void main(String[] args) {

        ListaNum p;

        FileWriter fw = null;
        try {
            fw = new FileWriter("ejer5.csv");
            BufferedWriter fsalida = new BufferedWriter(fw);
            //BufferedReader registro = new BufferedReader(new FileReader("c:/ficheros/libros.csv"));
            //leemos el primer registro
            List<Integer> numeros;
            numeros = new ArrayList<Integer>();
            numeros.add(1);
            numeros.add(2);
            numeros.add(6);
            numeros.add(3);
            numeros.add(5);
            numeros.add(9);
           
            p = new ListaNum ();
            p.setNumeros(numeros);
            System.out.println(p.getNumeros().get(0));
            String cadena = p.getNumeros().get(0).toString();
            int i;
            for (i=1; i< numeros.size(); i++)
            cadena += ","+p.getNumeros().get(i).toString();
            fsalida.write(cadena+"\n");
            
            fsalida.write("\n");
            fsalida.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(ejer5.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

