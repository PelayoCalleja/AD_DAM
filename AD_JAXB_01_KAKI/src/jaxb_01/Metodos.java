/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_01;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.albaran.Articulos;
import jaxb.albaran.Articulos.Articulo;
import jaxb.albaran.Direccion;
import jaxb.albaran.PedidoType;
import jaxb_01.MisExcepciones.ExcepcionFecha;
import jaxb_01.MisExcepciones.ExcepcionNoExiste;

/**
 *
 * @author alumnop
 */
public class Metodos implements Interface {

    @Override
    public void anadirArticulo(PedidoType pedidoType, int cantidad,
            String codigo, String nombreProducto, int precio, int dia, int mes, int ano) throws ExcepcionFecha {

        if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12) || (ano < 1989 || ano > 2030)) {
            throw new ExcepcionFecha("Fecha introducida no válida");
        }

        Articulos.Articulo nuevoArticulo = new Articulos.Articulo();
        nuevoArticulo.setCantidad(cantidad);
        nuevoArticulo.setCodigo(codigo);
        nuevoArticulo.setNombreProducto(nombreProducto);
        nuevoArticulo.setPrecio(new BigDecimal(precio));

        try {
            XMLGregorianCalendar fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar();

            fecha.setDay(dia);
            fecha.setMonth(mes);
            fecha.setYear(ano);
            nuevoArticulo.setFechaEnvio(fecha);
        } catch (DatatypeConfigurationException ex) {
            ex.printStackTrace();
        }

        Articulos articulos = pedidoType.getArticulos();
        List<Articulos.Articulo> listaArticulos = articulos.getArticulo();

        listaArticulos.add(nuevoArticulo);

    }

    @Override
    public void modificarDireccion(PedidoType pedidoType, String nombre, String calle, String ciudad, String provincia, int codigoPostal, String pais) throws ExcepcionNoExiste {

        if (pedidoType.getFacturarA().getNombre().equalsIgnoreCase(nombre)) {
            Direccion d = new Direccion();
            d.setNombre(nombre);
            d.setCalle(calle);
            d.setCiudad(ciudad);
            d.setProvincia(provincia);
            BigDecimal codigoPostalBigDecimal = BigDecimal.valueOf((int) codigoPostal);
            d.setCodigoPostal(codigoPostalBigDecimal);
            d.setPais(pais);

            pedidoType.setFacturarA(d);
        } else {
            throw new ExcepcionNoExiste("No hay ninguna direccion con ese nombre, imposible modificar.");
        }

    }

    @Override
    public double calcularImportePedido(PedidoType pedidoType) {
        double total = 0;
        double subtotal;
        for (Iterator<Articulos.Articulo> iterator = pedidoType.getArticulos().getArticulo().iterator(); iterator.hasNext();) {
            Articulos.Articulo next = iterator.next();
            int cantidad = next.getCantidad();
            BigDecimal bd = next.getPrecio();
            subtotal = bd.doubleValue() * cantidad;
            total += subtotal;

        }
        return total;
    }

    @Override
    public int borrarArticulo(PedidoType pedidoType, String nombreProducto) throws ExcepcionNoExiste {

        /*
        List listaArticulos = pedidoType.getArticulos().getArticulo();
        
         */
        int contador = 0;

        if (nombreProducto == null) {
            throw new ExcepcionNoExiste("No se ha introducido un nombre para el producto.");
        }

        for (Iterator<Articulos.Articulo> iterator = pedidoType.getArticulos().getArticulo().iterator(); iterator.hasNext();) {
            Articulos.Articulo next = iterator.next();
            if (next.getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                iterator.remove();
            }

        }
        /*
        
        for (Iterator iterator = listaArticulos.iterator(); iterator.hasNext();) {
            Articulos.Articulo next = (Articulo) iterator.next();
            if (next.getNombreProducto().equalsIgnoreCase(nombreProducto)) {
                listaArticulos.remove(next);
                contador++;
            }
        }
         */
        return contador;
    }

    @Override
    public void marshallizar(JAXBElement jaxbElement) throws JAXBException {
        javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
        Marshaller m;
        m = jaxbCtx.createMarshaller();

        // El método setProperty(String nombrePropiedad, Object value) recibe en este
        // caso la propiedad "jaxb.formatted.output". Esta propiedad controla si al
        // realizar un marshal, debe formatear el resultado XML con saltos de linea
        // e indentaciones para que las personas podamos leerlo cómodamente. Por defecto
        // su valor es falso es decir el XML creado no está formateado
        // El argumento value en este caso tiene que ser concretamente de tipo Boolean
        // para indicar si queremos que el resultado XML esté formateado o no
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // El método marshall(Object elementoJAXB, OutputStream os) recibe un objeto
        // de tipo JAXBElement para que su contenido lo muestre en la salida estándar
        // debido a que este método está sobrecargo, si miramos la documentación de
        //la API podemos ver como podemos mostrar o escribir el resultado XML de
        //diferentes maneras
        m.marshal(jaxbElement, System.out);
    }

    @Override
    public JAXBElement unmarshallizar() {
        JAXBElement jaxbElement = null;
        javax.xml.bind.JAXBContext jaxbCtx = null;

        try {
            jaxbCtx = javax.xml.bind.JAXBContext.newInstance("jaxb.albaran");
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            jaxbElement = (JAXBElement) unmarshaller.unmarshal(new java.io.File("albaran.xml")); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        return jaxbElement;
    }

}
