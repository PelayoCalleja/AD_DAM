/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Annie
 */
public class OperacionesFicheros {

    public static List<File> ListarFicherosOrdenados(String ruta, boolean ordenadosTamano, boolean soloDirectorios) throws misExcepciones.carpetaVacia, misExcepciones.NoEsDirectorio {

        File ficheroRuta = new File(ruta);

    //Mirar si la ruta está vacía, buscará en la raíz en funcion del SO
        if (ruta == null || ruta.equals("")) {
            ficheroRuta = new File(System.getProperty("user.dir"));

        } else {
            ficheroRuta = new File(ruta);

        }

        //si "ordenadosPorTamaño" es true, retornará los nombres y 
        //tamaño ordenados por tamaño
        if (ordenadosTamano) {
            Comparator<File> comparatorFicheros = new Comparator<File>() {
                @Override//convertimos el array a una coleccion
                public int compare(File fichero1, File fichero2) {
                    if (fichero1.length() < fichero2.length()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }

            };
            File[] arrayFicheros = ficheroRuta.listFiles();
            List<File> listaFicheros = Arrays.asList(arrayFicheros);
            listaFicheros.sort(comparatorFicheros);
            return listaFicheros;
        }

        File[] arrayFicheros = ficheroRuta.listFiles();
        List<File> listaFicheros = Arrays.asList(arrayFicheros);
        return listaFicheros;

    }

    int crearDirectorios(File rutaOrigen, ArrayList<String> listaNombresAlumnos) throws  misExcepciones.RutaNoExiste, misExcepciones.ExisteDirectorio {//existeDirectorio

        if (!rutaOrigen.isDirectory()) {
            throw new misExcepciones.RutaNoExiste();
        }
        int totalDirectoriosCreados = 0;

        for (String nombreAlumno : listaNombresAlumnos) {
            String rutaAlumno = rutaOrigen.toString() + "/" + nombreAlumno; //FALTA
            File fileAlumno = new File(rutaAlumno);

            if (fileAlumno.exists()) {
                throw new misExcepciones.ExisteDirectorio();
            }
            fileAlumno.mkdir();
            totalDirectoriosCreados++;
        }

        return totalDirectoriosCreados;
    }

    int cambiarExtensionFicheros(File ficheroOrigen, String extensionAntigua, String extensionNueva) throws misExcepciones.NoEsDirectorio, IOException {
        if (!ficheroOrigen.isDirectory()) {
            throw new misExcepciones.NoEsDirectorio("No es dir");
        }
        File[] arrayFiles = ficheroOrigen.listFiles();
        
        for (File fichero : arrayFiles) {
           
            String canonicalPath = fichero.getCanonicalPath();
            

            System.out.println("Name--->"+fichero.getCanonicalPath());
             if (canonicalPath.endsWith(extensionAntigua)) {
                //  System.out.print("XXXXXXXXXXXX");
                String nuevoCanonicalPath = canonicalPath.replace(extensionAntigua, extensionNueva);
                // System.out.println(nuevoCanonicalPath);
                File nuevoFile = new File(nuevoCanonicalPath);
               
                //ficheroOrigen.renameTo(fichero);//no funciona. xq??no lo se!!
             }
        }

        return 1;
    }
    public void listarFicherosRecursivos(String ruta) throws IOException{
        Files.walk(Paths.get(ruta)).filter(Files::isRegularFile).forEach(System.out::println);
    }
    
}
