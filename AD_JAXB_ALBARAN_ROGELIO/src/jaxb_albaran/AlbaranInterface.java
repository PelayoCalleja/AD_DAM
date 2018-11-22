/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_albaran;

import java.io.File;
import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import jaxb.albaran.PedidoType;
import jaxb_albaran.Excepciones.CodigoPostalExcepcion;
import jaxb_albaran.Excepciones.ImporteIncorrectoException;
import jaxb_albaran.Excepciones. ListaVaciaException;
import jaxb_albaran.Excepciones.MarshalExcepcion;
import jaxb_albaran.Excepciones.UnmarshalExcepcion;

/**
 *
 * @author Annie
 */
public interface AlbaranInterface {
    
        public JAXBElement unMarshalizar(File ficheroEntrada) throws UnmarshalExcepcion;
        public boolean marshalizar(JAXBElement jaxbElement, File ficheroSalida) throws MarshalExcepcion ;
        public boolean anhadirArticulo(PedidoType pedido, int cantidad, String codigo, String nombreProducto, BigDecimal precio, int dia, int mes, int anio) throws Excepciones.FechaIncorrectaException, Excepciones.CodigoVacioException;
        public boolean modificarDireccionPedido(PedidoType pedido, String nombre, String calle, String ciudad, String provincia, int codigoPostal, String pais) throws CodigoPostalExcepcion;
        public float calcularImportePedido(PedidoType pedido) throws ImporteIncorrectoException;
        public boolean borrarArticuloPorNombre(PedidoType pedido, String nombreArticulo) throws ListaVaciaException;



    
}
