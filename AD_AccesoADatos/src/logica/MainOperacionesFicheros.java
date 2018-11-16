/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annie
 */
public class MainOperacionesFicheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       
        OperacionesFicheros opfich=new OperacionesFicheros();
        File file = new File(".");
       /* try {
            opfich.cambiarExtensionFicheros(file, "txx", "txt");
        } catch (misExcepciones.NoEsDirectorio ex) {
            Logger.getLogger(MainOperacionesFicheros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainOperacionesFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    
        opfich.listarFicherosRecursivos(".");
        
    }
    
}
