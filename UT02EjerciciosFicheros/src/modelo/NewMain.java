/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author alumnop
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MisExcepciones.NoEsDirectorio {
        // TODO code application logic here
        OperacionesFicheros of = new OperacionesFicheros();
        of.listaDirectorio("/home/alumnop/");
        System.out.println(of.listado);
        
    }
    
}
