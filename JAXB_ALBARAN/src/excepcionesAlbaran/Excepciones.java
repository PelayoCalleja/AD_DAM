/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepcionesAlbaran;

/**
 *
 * @author Annie
 */
public class Excepciones {

    /*EXCEPCIONES UN/MARSHALIZADOR*/
    /**
     * Excepcion al Unmarshalizar
     */
    public static class UnmarshalExcepcion extends Throwable {

        public UnmarshalExcepcion(String string) {
            super(string);
        }
    }

    /**
     * Excepción al marshalizar
     */
    public static class MarshalExcepcion extends Throwable {

        public MarshalExcepcion(String string) {
            super(string);
        }
    }

    /*EXCEPCIONES ARTICULO*/
    /**
     * Excepción Si el campo  va vacío
     */
    public static class NombreIncorrectoException extends Throwable {

        public NombreIncorrectoException(String mensaje) {
            super(mensaje);
        }
    }

    /**
     * Excepción si la cantiad del producto es menor que 0 y mayor que 150
     */
    public static class CantidadProductoInvalidaException extends Throwable {

        public CantidadProductoInvalidaException(String string) {
            super(string);
        }
    }

    /**
     * Excepción si el precio es 0.0 o menor
     */
    public static class PrecioIncorrectoException extends Throwable {

        public PrecioIncorrectoException(String string) {
            super(string);
        }
    }

    /**
     * Excepción Si el formato de fecha es incorrecto
     */
    public static class FechaIncorrectaException extends Throwable {

        public FechaIncorrectaException(String string) {
            super(string);
        }
    }

    /**
     * Excepción si no va un código en el articulo
     */
    public static class CodigoVacioException extends Throwable {

        public CodigoVacioException(String string) {
            super(string);
        }
    }

    /*EXCEPCIONES DIRECCION*/
    /**
     * Excepción si codigo postal no tiene el formato correcto
     */
    public static class CodigoPostalExcepcion extends Throwable {

        public CodigoPostalExcepcion(String string) {
            super(string);
        }
    }
    /**
     * Excepción Si la suma total del importe da negativo
     */
     public static class ImporteIncorrectoException extends Throwable {

        public ImporteIncorrectoException(String string) {
            super(string);
        }

   
    
}}
