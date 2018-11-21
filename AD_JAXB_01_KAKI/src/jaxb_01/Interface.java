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
 * @author Iñaki
 */
public interface Interface {
    
    public int borrarArticulo(PedidoType pedidoType, String nombreProducto) throws MisExcepciones.ExcepcionNoExiste;
    public double calcularImportePedido(PedidoType pedidoType);
    public void modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, int codigoPostal, String pais) throws MisExcepciones.ExcepcionNoExiste;
    public void anadirArticulo(PedidoType pedidoType, int cantidad, String codigo, String nombreProducto, int precio, int dia, int mes, int ano) throws MisExcepciones.ExcepcionFecha;
    public void marshallizar(JAXBElement jaxbElement) throws JAXBException;
    public JAXBElement unmarshallizar();
}