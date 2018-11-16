/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ut02quijote;

import java.util.StringTokenizer;

/**
 *
 * @author alumnop
 */
public class UT02Quijote {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (StringTokenizer stringTokenizer = new StringTokenizer("cadena"); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();
            
        }
        
        

        for (StringTokenizer stringTokenizer = new StringTokenizer("cadena   ,cadena."); stringTokenizer.hasMoreTokens();) {
            String token = stringTokenizer.nextToken();


            String replaced;            
            
            replaced = token.replace(".", "");
            replaced = token.replace(",", "");
            replaced = token.replace(";", "");

            replaced.trim().toLowerCase();

            System.out.println(replaced);
        }

    }

}
