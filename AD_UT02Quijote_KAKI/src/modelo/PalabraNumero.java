package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumnop
 */


public class PalabraNumero {
    
    String palabra;
    int numero;

    public PalabraNumero(String palabra, int numero) {
        this.palabra = palabra;
        this.numero = numero;
    }

    public PalabraNumero() {
    }

    
    
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return  palabra+" " + numero +"/n";
    }
    
    
    
}
