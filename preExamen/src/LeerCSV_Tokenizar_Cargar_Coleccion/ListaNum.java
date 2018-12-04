/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeerCSV_Tokenizar_Cargar_Coleccion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumno
 */
public class ListaNum {

    private List<Integer> numeros;

    public ListaNum() {
        numeros = new ArrayList<Integer>();
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public String toString() {
        return "ListaNum{" + "numeros=" + numeros + '}';
    }

}
