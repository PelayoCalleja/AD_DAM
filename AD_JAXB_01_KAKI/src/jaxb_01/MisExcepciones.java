/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01;

/**
 *
 * @author alumnop
 */
public class MisExcepciones {

    public static class ExcepcionNoExiste extends Exception {

        public ExcepcionNoExiste(String msg) {
            super(msg);
        }
    }

    public static class ExcepcionFecha extends Exception {

        public ExcepcionFecha(String msg) {
            super(msg);
        }

        ExcepcionFecha() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

 
    }
    
}
