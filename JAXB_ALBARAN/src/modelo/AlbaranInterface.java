/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepcionesAlbaran.Excepciones;
import java.io.File;
import javax.xml.bind.JAXBElement;
import jaxb.albaran.PedidoType;
import excepcionesAlbaran.Excepciones.CodigoPostalExcepcion;
import excepcionesAlbaran.Excepciones.ImporteIncorrectoException;
import excepcionesAlbaran.Excepciones.MarshalExcepcion;
import excepcionesAlbaran.Excepciones.UnmarshalExcepcion;
import java.util.List;
import jaxb.albaran.Articulos;
import jaxb.albaran.Direccion;

/**
 *
 * @author Annie
 */
public interface AlbaranInterface {

    public JAXBElement unMarshalizar(String ficheroEntrada) throws UnmarshalExcepcion;

    public boolean marshalizar(JAXBElement jaxbElement, File ficheroSalida) throws MarshalExcepcion;

    public Articulos.Articulo anhadirArticulo(PedidoType pedido, int cantidad, String codigo, String nombreProducto, double precio, int dia, int mes, int anio) throws Excepciones.FechaIncorrectaException, Excepciones.CodigoVacioException;

    public Direccion modificarDireccionPedido(PedidoType pedido, String nombre, String calle, String ciudad, String provincia, long codigoPostal, String pais) throws Excepciones.CodigoPostalExcepcion;

    public double calcularImportePedido(PedidoType pedido) throws ImporteIncorrectoException;

    public List borrarArticuloPorNombre(PedidoType pedido, String nombreArticulo);

}
