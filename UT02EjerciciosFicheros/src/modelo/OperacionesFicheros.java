/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author alumnop
 */
public class OperacionesFicheros {

    List listado = new ArrayList();
    
    public List listaDirectorio(String ruta)  throws MisExcepciones.NoEsDirectorio {
        File file = new File(ruta);
        
        if (!file.isDirectory()) {
                throw new MisExcepciones.NoEsDirectorio("No existe directorio");
        }
        
        
        File[] listFiles = file.listFiles();
        List<File> listaficheros = Arrays.asList(listFiles);
        listado.addAll(listaficheros);
        
        for(int x = 0; x<listaficheros.size(); x++){
                    if(listaficheros.get(x).isDirectory())
                        listaDirectorio(listaficheros.get(x).getPath());   
                    
                    
        
        return listado;
        }
        
        
      
 
        
        
                
      /*  Comparator comparator = new Comparator() {
            public int compare(File fichero1, File fichero2) {
                if (fichero1.length() < fichero2.length()) {
                    
                }
                
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int compare(Object o1, Object o2) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            
            
        };
      
        /*        String[] listaFicheros = file.list();
        Arrays.sort(listaFicheros);
        return listaFicheros;*/        
        return null;
       
    }
} 
