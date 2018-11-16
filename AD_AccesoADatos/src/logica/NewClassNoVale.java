/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Annie
 */
public class NewClassNoVale {

    public static void ListarFicheros(String ruta) {
        File directorio = new File(ruta);
        File[] arrayFicheros = directorio.listFiles();
        List<File> listaFicheros = Arrays.asList(arrayFicheros);
        Comparator<File> comparatorFicheros = new Comparator<File>() {
            @Override
            public int compare(File fichero1, File fichero2) {
                if (fichero1.length() < fichero2.length()) {
                    return 1;
                }
                if (fichero1.length() > fichero2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        listaFicheros.sort(comparatorFicheros);
        //return listaFicheros;
    }
}
    