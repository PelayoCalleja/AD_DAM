/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_albaran;

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
import jaxb_albaran.Excepciones.CantidadProductoInvalidaException;
import jaxb_albaran.Excepciones.FechaIncorrectaException;
import jaxb_albaran.Excepciones.MarshalExcepcion;
import jaxb_albaran.Excepciones.CampoVacioException;
import jaxb_albaran.Excepciones.ImporteIncorrectoException;
import jaxb_albaran.Excepciones.PrecioIncorrectoException;
import jaxb_albaran.Excepciones.UnmarshalExcepcion;

/**
 *
 * @author Pelayo
 */
public class Metodos implements AlbaranInterface {

    private static Metodos INSTANCE;

    public Metodos() {
    }

    
    public static Metodos getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Metodos();
        }
        return INSTANCE;
    }
    
    /**
     * Comprueba si existe el fichero que vamos a un/marshalizar.
     *
     * @param fichero
     * @return true si existe, false si no existe
     */
    public boolean comprobarFichero(File fichero) {
        if (fichero == null) {
            System.out.println("No existe el fichero que quieres unmarshalizar");
            return false;
        }
        if (!fichero.exists()) {
            System.out.println("No hay un fichero XML");
            return false;
        }
        if (!fichero.getName().endsWith("xml")) {
            System.out.println("No hay fichero XML");
            return false;
        }
        return true;
    }

    /**
     * Método unmarshalizador. Obtenemos un objeto de la etiqueta padre del XML,
     * obteniendo todos sus hijos (su contenido)
     *
     * @param ficheroEntrada (archivo XML)
     * @return Objeto del XML o null si da error
     * @throws jaxb_albaran.Excepciones.UnmarshalExcepcion
     */
    @Override
    public JAXBElement unMarshalizar(File ficheroEntrada) throws UnmarshalExcepcion {
        if (comprobarFichero(ficheroEntrada)) {
            try {
                JAXBElement jaxbElement = null;
                javax.xml.bind.JAXBContext jaxbCtx = null;
                jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
                javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();//Creando unmarshalizador

                return ((JAXBElement) unmarshaller.unmarshal(new java.io.File("albaran.xml")));//Objeto

            } catch (JAXBException ex) {
                throw new UnmarshalExcepcion("Problemas en el binding.");
            } catch (ClassCastException cce) {
                throw new UnmarshalExcepcion("El XML tiene errores o está incompleto.");
            }
        } else {
            return null;
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
    @Override
    public boolean marshalizar(JAXBElement jaxbElement, File ficheroSalida) throws MarshalExcepcion {
        if (jaxbElement == null || ficheroSalida == null) {
            throw new NullPointerException("No existe el JAXB");
        }
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            Marshaller marshaller;
            marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//por si algun xml no lleva cabecera
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);//Controla si hay que formatear el XML para leerlo mejor

            if (!ficheroSalida.exists()) {
                try {
                    ficheroSalida.createNewFile();
                    if (!comprobarFichero(ficheroSalida)) {
                        throw new MarshalExcepcion("El fichero de salida no existe, o no es un XML válido.");
                    }
                } catch (IOException ex) {
                    throw new MarshalExcepcion("No se ha podido crear el fichero de salida.");
                }
            }
            marshaller.marshal(jaxbElement, ficheroSalida);//Muestra el contenido del objeto en salida estandar
            return true;
        } catch (javax.xml.bind.JAXBException ex) {
            System.out.println("Algún conflicto con la marshalización.");
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
     * @return
     * @throws jaxb_albaran.Excepciones.FechaIncorrectaException
     * @throws jaxb_albaran.Excepciones.CodigoVacioException
     */
    @Override
    public boolean anhadirArticulo(PedidoType pedido, int cantidad, String codigo, String nombreProducto, BigDecimal precio, int dia, int mes, int anio) throws FechaIncorrectaException, Excepciones.CodigoVacioException {

        Articulos.Articulo nuevoArticulo = new Articulos.Articulo();

        nuevoArticulo.setCantidad(cantidad);
        nuevoArticulo.setCodigo(codigo);
        nuevoArticulo.setNombreProducto(nombreProducto);
        nuevoArticulo.setPrecio(precio);

        try {
            XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

            fecha.setDay(dia);
            fecha.setMonth(mes);
            fecha.setYear(anio);

            nuevoArticulo.setFechaEnvio(fecha);

        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }

//Establecemos las excepciones para el nombre, precio, codigo, cantidad y fecha.
        if (pedido == null) {
            throw new NullPointerException("No se han introducido parámetros correctos");
        }
        if (nuevoArticulo.getNombreProducto().isEmpty()) {
            try {
                throw new Excepciones.CampoVacioException("El nombre del producto no puede estar vacío");
            } catch (CampoVacioException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nuevoArticulo.getCantidad() <= 0 || nuevoArticulo.getCantidad() > 150) {
            try {
                throw new Excepciones.CantidadProductoInvalidaException("La cantidad de productos debe ser entre 0 y 150");
            } catch (CantidadProductoInvalidaException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (nuevoArticulo.getPrecio().floatValue() <= 0.0f) {
            try {
                throw new Excepciones.PrecioIncorrectoException("El precio no puede ser negativo o ser 0");
            } catch (PrecioIncorrectoException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (anio < 1990 || anio > 2025)) {
            throw new Excepciones.FechaIncorrectaException("Formato de fecha incorrecto");
        }

        if (nuevoArticulo.getCodigo() == null || nuevoArticulo.getCodigo().isEmpty()) {
            throw new Excepciones.CodigoVacioException("El código no puede estar vacío");
        }

        Articulos articulos = pedido.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

        listaArticulos.add(nuevoArticulo);
        return true;
    }

    /**
     * Si los campos no estan vacios, se sustituyen por lo que introduzcamos. Si
     * no se quiere rellenar alguno, se pone a null, 0 o false.
     *
     * @param pedido
     * @param nombre
     * @param calle
     * @param ciudad
     * @param provincia
     * @param codigoPostal
     * @param pais
     * @return true si todo es correcto, false si hay algun error
     */
    @Override
    public boolean modificarDireccionPedido(PedidoType pedido, String nombre, String calle, String ciudad, String provincia, int codigoPostal, String pais) {

        Direccion direccionExistente = pedido.getFacturarA();

        if (pedido == null) {
            throw new NullPointerException("No se han introducido parámetros correctos");
        }
        if (!(nombre == null || nombre.isEmpty())) {
            direccionExistente.setNombre(nombre);
        }
        if (!(calle == null || calle.isEmpty())) {
            direccionExistente.setCalle(calle);
        }
        if (!(ciudad == null || ciudad.isEmpty())) {
            direccionExistente.setCiudad(ciudad);
        }
        if (!(provincia == null || provincia.isEmpty())) {
            direccionExistente.setProvincia(provincia);
        }
        if (String.valueOf(codigoPostal).matches("[\\d]{4,5}")) {  //habilitamos de 4 a 5 digitos 
            direccionExistente.setCodigoPostal(new BigDecimal(codigoPostal));
        } else {
            try {
                throw new Excepciones.CodigoPostalExcepcion("El código postal no es válido.");
            } catch (Excepciones.CodigoPostalExcepcion ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!(pais == null || pais.isEmpty())) {
            direccionExistente.setPais(pais);
        }
        pedido.setFacturarA(direccionExistente);
        return true;
    }

    /**
     * Calculamos el iimporte total del pedido. Osea, sumamos todos los precios
     * de todos los articulos existentes en el XML.
     *
     * @param pedido
     * @return
     */
    @Override
    public float calcularImportePedido(PedidoType pedido) {

        if (pedido == null) {
            throw new NullPointerException("No se han introducido parámetros correctos");
        }
        float importeTotal = 0.0f;
        List<Articulos.Articulo> listaArticulos = pedido.getArticulos().getArticulo();
        if (!listaArticulos.isEmpty()) {
            for (Articulos.Articulo articulo : listaArticulos) {
                importeTotal += articulo.getPrecio().floatValue() * articulo.getCantidad();

            }
        }
        if (importeTotal < 0.0f) {
            try {
                throw new Excepciones.ImporteIncorrectoException("El importe no puede ser negativo");
            } catch (ImporteIncorrectoException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return importeTotal;
    }

    /**
     * Se borran los artículos cuyo nombre sea el mismo que introduzcamos
     *
     * @param pedido
     * @param nombreArticulo
     * @return
     */
    @Override
    public boolean borrarArticuloPorNombre(PedidoType pedido, String nombreArticulo) {

        if (pedido == null) {
            throw new NullPointerException("No se han introducido parámetros correctos");
        }

        List<Articulos.Articulo> listaArticulos = pedido.getArticulos().getArticulo();
        if (listaArticulos.isEmpty()) {
            try {
                throw new Excepciones.ListaVaciaException("La lista de artículos del pedido no puede estar vacía.");
            } catch (Excepciones.ListaVaciaException ex) {
                Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
            }
            Iterator<Articulos.Articulo> iterator = listaArticulos.iterator();
            Articulos.Articulo articulo = null;
            while (iterator.hasNext()) {
                articulo = iterator.next();
                if (articulo.getNombreProducto().equalsIgnoreCase(nombreArticulo)) {
                    iterator.remove();
                    return true;    // Suponiendo que todos los artículos tengan nombres únicos...
                }
            }

        }
        return false;
    }
}
