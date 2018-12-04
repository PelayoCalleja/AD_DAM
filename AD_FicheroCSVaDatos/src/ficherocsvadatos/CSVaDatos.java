/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherocsvadatos;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author alumnop
 */
public class CSVaDatos {

    public static void cambio(String rutaCSV) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(rutaCSV));
        String line= br.readLine();
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream( new FileOutputStream("google.dat"))); 
       
        while (line != null) {
            
            
            for (StringTokenizer stringTokenizer = new StringTokenizer(line); stringTokenizer.hasMoreTokens();) {
                String date = stringTokenizer.nextToken();
                double open = Double.parseDouble(stringTokenizer.nextToken());
                double high = Double.parseDouble(stringTokenizer.nextToken());
                double low = Double.parseDouble(stringTokenizer.nextToken());
                double close = Double.parseDouble(stringTokenizer.nextToken());
                double adjClose = Double.parseDouble(stringTokenizer.nextToken());
                int volume = Integer.parseInt(stringTokenizer.nextToken());
                
           
                out.writeUTF(date);
                out.writeDouble(open);
                out.writeDouble(high);
                out.writeDouble(low);
                out.writeDouble(close);
                out.writeDouble(adjClose);
                out.writeInt(volume);

            }

        }
            out.close();
    }



public void abrirDat (String rutaFichero){





}






}
