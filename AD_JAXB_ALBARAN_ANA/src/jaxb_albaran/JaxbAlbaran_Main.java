/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb_albaran;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import jaxb.albaran.Articulos;
import jaxb.albaran.PedidoType;
import jaxb_albaran.Excepciones.MarshalExcepcion;
import jaxb_albaran.Excepciones.UnmarshalExcepcion;

/**
 *
 * @author Annie
 */
public class JaxbAlbaran_Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   
         try {
        //Descomenta algún método para probar
        pruebaCrearXML();
        //pruebaAnyadirArticulo();
        //pruebaModificarDireccion();
        //pruebaCalcularImportePedido();
       // pruebaBorrarArticuloNombre();
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
        
    }
      private static void pruebaCrearXML() throws UnmarshalExcepcion, MarshalExcepcion {
          
        JAXBElement elementoPadre =Metodos.getINSTANCE().unMarshalizar(new File("albaran.xml"));
        if (Metodos.getINSTANCE().marshalizar(elementoPadre, new File("miAlbaranModificado.xml"))) {
            System.out.println("Fichero creado");
        } else {
            System.out.println("Error durante la grabación");
        }

    }
    
private static void pruebaAnyadirArticulo() throws UnmarshalExcepcion, MarshalExcepcion, Excepciones.FechaIncorrectaException, Excepciones.CodigoVacioException {
        try {
            JAXBElement elementoPadre = Metodos.getINSTANCE().unMarshalizar(new File("albaran.xml"));
            PedidoType pedido = (PedidoType) elementoPadre.getValue();
            Articulos.Articulo articulo = new Articulos.Articulo();
            articulo.setNombreProducto("Portatil");
            articulo.setCantidad(2);
            articulo.setPrecio(BigDecimal.valueOf(599.99));
            articulo.setComentario("Windows 10");
            articulo.setFechaEnvio(DatatypeFactory.newInstance().newXMLGregorianCalendar(new SimpleDateFormat("YYYY-MM-dd").format(new Date())));
            articulo.setCodigo("777-A");
            Metodos.getINSTANCE().anhadirArticulo(pedido, articulo.getCantidad(), articulo.getCodigo(), articulo.getNombreProducto(),articulo.getPrecio(), articulo.getFechaEnvio().getDay(), articulo.getFechaEnvio().getMonth(), articulo.getFechaEnvio().getYear());
            Metodos.getINSTANCE().marshalizar(elementoPadre, new File("miAlbaranModificado.xml"));
        } catch (DatatypeConfigurationException ex) {
            System.out.println("Algún problema con el formateo de fecha al usar la factoría.");;
        }
    }


    private static void pruebaModificarDireccion() throws UnmarshalExcepcion, MarshalExcepcion {
        JAXBElement elementoPadre = Metodos.getINSTANCE().unMarshalizar(new File("albaran.xml"));
        PedidoType pedido = (PedidoType) elementoPadre.getValue();
        Metodos.getINSTANCE().modificarDireccionPedido(pedido, "PC Componentes", "Av. Laviana, 49", "Langreo", "Asturias", 33900, "España");
        Metodos.getINSTANCE().marshalizar(elementoPadre, new File("miAlbaranModificado.xml"));
    }

    private static void pruebaCalcularImportePedido() throws UnmarshalExcepcion {
        JAXBElement elementoPadre = Metodos.getINSTANCE().unMarshalizar(new File("albaran.xml"));
        PedidoType pedido = (PedidoType) elementoPadre.getValue();
        float resultado = Metodos.getINSTANCE().calcularImportePedido(pedido);
        System.out.println("El importe total es " + resultado);
    }

    private static void pruebaBorrarArticuloNombre() throws UnmarshalExcepcion, MarshalExcepcion {
        JAXBElement elementoPadre = Metodos.getINSTANCE().unMarshalizar(new File("albaran.xml"));
        PedidoType pedido = (PedidoType) elementoPadre.getValue();
        Metodos.getINSTANCE().borrarArticuloPorNombre(pedido, "Portatil");
        Metodos.getINSTANCE().marshalizar(elementoPadre, new File("miAlbaranModificado.xml"));
    }

         
}
