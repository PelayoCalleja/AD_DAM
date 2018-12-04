/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author alumnop
 */
public class ClaseFiltros {

    FilenameFilter filtroFotos = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".jpg")||name.endsWith(".png")|| name.endsWith(".gif")||name.endsWith(".tiff");
        }

    };
    
    FilenameFilter filtroVideos = new FilenameFilter() {

        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".avi")||name.endsWith(".mp4")|| name.endsWith(".mkv");
        }

    };
    
  
}
