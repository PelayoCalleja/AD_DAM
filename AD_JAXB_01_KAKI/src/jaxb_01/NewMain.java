/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import jaxb.albaran.PedidoType;

/**
 *
 * @author pelayo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MisExcepciones.ExcepcionFecha, JAXBException, MisExcepciones.ExcepcionNoExiste {
       
        Metodos m = new Metodos();
        JAXBElement jaxbElement = m.unmarshallizar();
        PedidoType pedidoType = (PedidoType) jaxbElement.getValue();
       
        
      
        m.marshallizar(jaxbElement);
       
        
    }
    
}
