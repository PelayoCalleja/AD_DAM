/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import excepcionesAlbaran.Excepciones;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.albaran.Articulos;
import jaxb.albaran.Direccion;
import jaxb.albaran.PedidoType;
import excepcionesAlbaran.Excepciones.CantidadProductoInvalidaException;
import excepcionesAlbaran.Excepciones.FechaIncorrectaException;
import excepcionesAlbaran.Excepciones.MarshalExcepcion;
import excepcionesAlbaran.Excepciones.NombreIncorrectoException;
import excepcionesAlbaran.Excepciones.ImporteIncorrectoException;
import excepcionesAlbaran.Excepciones.PrecioIncorrectoException;
import excepcionesAlbaran.Excepciones.UnmarshalExcepcion;

/**
 *
 * @author Annie
 */
public class Metodos {

    private static Metodos INSTANCE;

    public Metodos() {

    }

    /**
     * Método unmarshalizador. Obtenemos un objeto de la etiqueta padre del XML,
     * obteniendo todos sus hijos (su contenido)
     *
     * @param ficheroEntrada (archivo XML)
     * @return Objeto del XML o null si da error
     * @throws excepciones.Excepciones.UnmarshalExcepcion
     * @throws jaxb_albaran.Excepciones.UnmarshalExcepcion
     */
    public JAXBElement unMarshalizar(String ficheroEntrada) throws UnmarshalExcepcion {

        try {
            JAXBElement jaxbElement = null;
            javax.xml.bind.JAXBContext jaxbCtx = null;
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();//Creando unmarshalizador
            return ((JAXBElement) unmarshaller.unmarshal(new java.io.File(ficheroEntrada)));//Objeto

        } catch (JAXBException ex) {
            throw new UnmarshalExcepcion("Problemas en el binding.");
        } catch (ClassCastException cce) {
            throw new UnmarshalExcepcion("El XML tiene errores o está incompleto.");
        }
    }

    /**
     * Método marshalizador. Genera un XML a partir del JAXB
     *
     * @param jaxbElement
     * @param ficheroSalida donde volcamos los datos
     * @return true si esta todo correcto; false si hay algun problema
     * @throws jaxb_albaran.Excepciones.MarshalExcepcion
     */
    public boolean marshalizar(JAXBElement jaxbElement, File ficheroSalida) throws MarshalExcepcion {

        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            Marshaller marshaller;
            marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//Controla si hay que formatear el XML para leerlo mejor      
            marshaller.marshal(jaxbElement, ficheroSalida);//Muestra el contenido del objeto en salida estandar
            return true;
        } catch (javax.xml.bind.JAXBException ex) {

            return false;
        }
    }

    /**
     * Añade un artículo nuevo al XML. Gestionamos las excepciones por si se
     * introducen datos incorrectos
     *
     * @param pedido
     * @param cantidad
     * @param codigo
     * @param nombreProducto
     * @param precio
     * @param dia
     * @param mes
     * @param anio
     * @return el nuevo articulo
     * @throws excepciones.Excepciones.FechaIncorrectaException
     * @throws excepciones.Excepciones.CodigoVacioException
     */
    public Articulos.Articulo anhadirArticulo(PedidoType pedido, int cantidad, String codigo, String nombreProducto, double precio, int dia, int mes, int anio) throws FechaIncorrectaException, Excepciones.CodigoVacioException {

        Articulos.Articulo nuevoArticulo = new Articulos.Articulo();

        nuevoArticulo.setCantidad(cantidad);
        nuevoArticulo.setCodigo(codigo);
        nuevoArticulo.setNombreProducto(nombreProducto);

        nuevoArticulo.setPrecio(BigDecimal.valueOf(precio));

        try {
            XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

            fecha.setDay(dia);
            fecha.setMonth(mes);
            fecha.setYear(anio);

            nuevoArticulo.setFechaEnvio(fecha);

        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }

//Establecemos las excepciones
        if (nuevoArticulo.getNombreProducto().contains("ñ")) {
            try {
                throw new Excepciones.NombreIncorrectoException(nuevoArticulo.getNombreProducto() + " no puede llevar -ñ-");
            } catch (NombreIncorrectoException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nuevoArticulo.getCantidad() < 0) {
            try {
                throw new Excepciones.CantidadProductoInvalidaException("Has introducido " + nuevoArticulo.getCantidad()
                        + " numero de productos. No puede ser negativo");
            } catch (CantidadProductoInvalidaException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (nuevoArticulo.getPrecio().doubleValue() <= 0) {
            try {
                throw new Excepciones.PrecioIncorrectoException("El precio no puede ser negativo o ser 0");
            } catch (PrecioIncorrectoException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (anio < 189 || anio > 2030)) {
            throw new Excepciones.FechaIncorrectaException("La fecha " + dia + "-" + mes + "-" + anio + " es incorrecta");
        }

        if (nuevoArticulo.getCodigo() == null || nuevoArticulo.getCodigo().isEmpty()) {
            throw new Excepciones.CodigoVacioException("El código no puede estar vacío");
        }
// FIN EXCEPCIONES
        Articulos articulos = pedido.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

        listaArticulos.add(nuevoArticulo);
        return nuevoArticulo;
    }

    /**
     * Modificar direccion del pedido. Modificamos los valores que hay en la
     * direccion del pedido
     *
     * @param pedido
     * @param nombre
     * @param calle
     * @param ciudad
     * @param provincia
     * @param codigoPostal
     * @param pais
     * @return direccionExistente modificada
     * @throws excepciones.Excepciones.CodigoPostalExcepcion
     */
    public Direccion modificarDireccionPedido(PedidoType pedido, String nombre, String calle, String ciudad, String provincia, long codigoPostal, String pais) throws Excepciones.CodigoPostalExcepcion {
        if (codigoPostal > 99999 || codigoPostal < 10000) {
            throw new Excepciones.CodigoPostalExcepcion("En codigo:" + String.valueOf(codigoPostal) + " no es válido");
        }
        Direccion direccionExistente = pedido.getFacturarA();
        direccionExistente.setCalle(calle);
        direccionExistente.setCiudad(ciudad);
        direccionExistente.setCodigoPostal(BigDecimal.valueOf(codigoPostal));
        direccionExistente.setNombre(nombre);
        direccionExistente.setPais(pais);
        direccionExistente.setProvincia(provincia);
        pedido.setFacturarA(direccionExistente);
        return direccionExistente;
    }

    /**
     * Calcular el importe total del pedido. Multiplicamos el precio de cada
     * pedido por la cantidad de productos que haya.
     *
     * @param pedido
     * @return importeTotal
     * @throws excepciones.Excepciones.ImporteIncorrectoException
     */
    public double calcularImportePedido(PedidoType pedido) throws ImporteIncorrectoException {

        Articulos articulos = pedido.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();
        double importeTotal = 0;

        for (Articulos.Articulo articulo : listaArticulos) {
            importeTotal += articulo.getPrecio().doubleValue() * articulo.getCantidad();

        }
        if (importeTotal < 0) {
            throw new Excepciones.ImporteIncorrectoException("El importe " + importeTotal + " no puede ser negativo");
        }
        return importeTotal;
    }

   /**
    * Borrar Articulo. Se borrará el artículo cuyo nombre hayamos introducido
    * @param pedido
    * @param nombreArticulo
    * @return la lista de artículos que queda
    */
    public List borrarArticuloPorNombre(PedidoType pedido, String nombreArticulo) {

        if (nombreArticulo.contains("ñ")) {
            try {
                throw new Excepciones.NombreIncorrectoException(nombreArticulo+ " no puede llevar -ñ-");
            } catch (NombreIncorrectoException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Articulos.Articulo> listaArticulos = pedido.getArticulos().getArticulo();
        Iterator<Articulos.Articulo> iterator = listaArticulos.iterator();
        Articulos.Articulo articulo = null;

        while (iterator.hasNext()) {
            articulo = iterator.next();
            if (articulo.getNombreProducto().equalsIgnoreCase(nombreArticulo)) {
                iterator.remove();
            }
        }
        return listaArticulos;
    }
}
