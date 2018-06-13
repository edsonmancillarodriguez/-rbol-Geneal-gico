/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodo;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Es el Nodo principal para crear el Arbol Geneologico Eg. (padre)hombre=Adan,
 * (madre)mujer=Eva Estos iniciaran con una lista de hijos vacia Eg.
 * ArrayList<Nodo> hijos=null Por cada hijo que tenga la pareja se creara un
 * nuevo nodo y se insertara en la lista hijos de izquierda a derecha
 *
 * @author EDSON
 */
public class Nodo {

    private String hombre;
    private String mujer;
    private int nHijos;
    private ArrayList<Nodo> hijos;
    
    public int getnHijos() {
        return nHijos;
    }

    public void setnHijos(int nHijos) {
        this.nHijos = nHijos;
    }
   

    /**
     * Crea un Nodo con el nombre del marido y de la esposa,Eg.(Adan,Eva) La
     * lista hijos sera hijos=null
     *
     * @param hombre
     * @param mujer
     */
    public Nodo(String hombre, String mujer,int nHijos) {
        this.hombre = hombre;
        this.mujer = mujer;
        this.nHijos=nHijos;
        this.hijos = new ArrayList();
    }

    /**
     * Retorna el nombre del hombre el Nodo siempre tendra un nombre de hombre
     *
     * @return String
     */
    public String getHombre() {
        return hombre;
    }

    /**
     * Modifica el nombre del hombre
     *
     * @param hombre
     */
    public void setHombre(String hombre) {
        this.hombre = hombre;
    }

    /**
     * Retorna el nombre de la esposa
     *
     * @return String retorna null si el hombre NO esta casado
     */
    public String getMujer() {
        return mujer;
    }

    /**
     * Modifica el nombre de la esposa
     *
     * @param mujer
     */
    public void setMujer(String mujer) {
        this.mujer = mujer;
    }

    /**
     * Devuelve la lista hijos de tipo ArrayList
     *
     * @return boolean retorna la lista con todos los hijos del Nodo retorna
     * null si no existen hijos
     */
    public ArrayList<Nodo> getHijos() {
        return hijos;
    }

    /**
     * Modifica la lista de hijos del Nodo
     *
     * @param hijos
     */
    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
    }

    /**
     * Retorna la cantidad de hijos que tiene el Nodo
     *
     * @return
     */
    public int NumHijos() {
        return hijos.size();
    }

    @Override
    public String toString() {
        return null;
    }
}
