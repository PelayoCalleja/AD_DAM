/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Annie
 */
public class misExcepciones {

    public static class NoEsDirectorio extends Exception {

        public NoEsDirectorio(String message) {
            super("No es un directorio no se puede listar");
        }
    }

    public static class carpetaVacia extends Exception {

        public carpetaVacia(String message) {
            super("Es una carpeta vacia");
        }
    }
      public static class RutaNoExiste extends Exception {

        public RutaNoExiste() {
            super("No exite la ruta del fichero");
        }

    }

    public static class ExisteDirectorio extends Exception {

        public ExisteDirectorio() {
            super("El directorio si existe");
        }

    }
}
