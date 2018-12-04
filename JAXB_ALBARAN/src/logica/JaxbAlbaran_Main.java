/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import excepcionesAlbaran.Excepciones;
import java.io.File;
import javax.xml.bind.JAXBElement;
import jaxb.albaran.PedidoType;
import excepcionesAlbaran.Excepciones.MarshalExcepcion;
import excepcionesAlbaran.Excepciones.UnmarshalExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Metodos;

/**
 *
 * @author Annie
 */
public class JaxbAlbaran_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Excepciones.FechaIncorrectaException, Excepciones.CodigoVacioException, Excepciones.CodigoPostalExcepcion, Excepciones.ImporteIncorrectoException {
        Metodos metodos = new Metodos();
        try {
            //UNMARSHALIZAR
            JAXBElement jaxbElement = metodos.unMarshalizar("albaran.xml");//metodo unmarshalizador
            
          PedidoType pedido = (PedidoType) jaxbElement.getValue();//castear al tipo del nodo "raíz"
            
            
            
            //OPERACIONES
        metodos.anhadirArticulo(pedido, 3, "x3s", "Naranjas", 34, 20, 11, 2018);//añadir articulo
            
        metodos.modificarDireccionPedido(pedido, "Pc Componentes", "Las Cuestas, 13", "Langreo", "Asturias", 33900, "España");
            
        double importeTotal=metodos.calcularImportePedido(pedido);
        System.out.println("El importe total es " + importeTotal);
        
        metodos.borrarArticuloPorNombre(pedido, "Alfombra");
            
        
        //MARSHALIZAR
            File ficheroSalida = new File("miXMLmodificado.xml");//xml de salida nuevo modificado
            metodos.marshalizar(jaxbElement, ficheroSalida);//metodo marshalizador
        } catch (UnmarshalExcepcion ex) {
            Logger.getLogger(JaxbAlbaran_Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MarshalExcepcion ex) {
            Logger.getLogger(JaxbAlbaran_Main.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
}
