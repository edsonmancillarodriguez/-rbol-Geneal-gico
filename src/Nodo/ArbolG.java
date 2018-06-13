/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodo;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Todos los metodos del arbol geneologico Indiferente de como ingresen las
 * variables, todas las cadenas seran almacenadas en Mayusculas
 *
 * @author EDSON
 */
public class ArbolG {

    Nodo Arbol;

    /**
     * Crea el arbol con una pareja por defecto Adan y Eva, Eg. Arbol=new
     * Nodo(ADAN,EVA)
     */
    public ArbolG() {
        Arbol = new Nodo("Adan".toUpperCase(), "eva".toUpperCase(),10);
    }
    
    /**
     * crea el arbol con una pareja inicial dada como variable Eg. Arbol=new
     * Nodo(marido,esposa)
     *
     * @param marido
     * @param esposa
     */
    public ArbolG(String marido, String esposa,int nHijos) {

        Arbol = new Nodo(marido.toUpperCase(), esposa.toUpperCase(),nHijos);
    }

    /**
     * verifica si el "nombre" ya existe en el arbol indistinto si es mayuscula
     * o minuscula
     *
     * @param nombre nombre de la persona indistinto si es hombre o mujer
     * @return true si existe en el arbol false si NO existe en el arbol
     */
    public boolean ExisteNombre(String nombre) {
        return ExisteNombre(nombre, Arbol);
    }

    /**
     * Parte privada del metodo ExisteNombre(String nombre){}
     *
     * @param nombre
     * @param Arbol este es Nodo inicial de la class ArbolG
     * @return true si existe en el arbol false si NO existe en el arbol
     */
    private boolean ExisteNombre(String nombre, Nodo Arbol) {
        if (!EstaCasado(Arbol) && Arbol.getHombre().toUpperCase().equals(nombre.toUpperCase())) {// si no esta casado y el hombre(nombre) es igual a nombre retorna true
            return true;
        }
        if (!EstaCasado(Arbol)) {
            return false;
        }
        if (Arbol.getHombre().toUpperCase().equals(nombre.toUpperCase()) || Arbol.getMujer().toUpperCase().equals(nombre.toUpperCase())) {// compara el nombre con el marido y la mujer
            return true;
        }
        if (!TieneHijos(Arbol)) {
            return false;
        }

        for (int i = 0; i < Arbol.NumHijos(); i++) {
            boolean flag = ExisteNombre(nombre, Arbol.getHijos().get(i));
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pregunta si la pareja tiene hijos
     *
     * @param Arbol
     * @return boolean true si tiene hijos false si NO tiene hijos
     */
    public boolean TieneHijos(Nodo Arbol) {
        if (!EstaCasado(Arbol)) {
            return false;
        }
        return Arbol.NumHijos()> 0;
    }

    /**
     * Retorna true si el Arbol.getHombre tiene esposa
     *
     * @param Arbol
     * @return boolean true si esta casado false si esta soltero (no esta
     * casado)
     */
    public boolean EstaCasado(Nodo Arbol) {
        return Arbol.getMujer() != null;
    }

    /**
     * Agrega un hijo a una pareja (hombre casado)
     *
     * @param progenitor Nombre del padre o la madre
     * @param hijo Nombre del hijo(varon), si este nombre se repite en el arbol
     * entonces no se agregara
     */
    public void AddHijo(String progenitor, String hijo) {

        Nodo desendiente = new Nodo(hijo.toUpperCase(), null,0);
        AddHijo(progenitor, desendiente, Arbol);
    }
    
    /**
     * Agrega un hijo a una pareja (hombre casado)
     *
     * @param progenitor Nombre del padre o madre
     * @param hijo Nombre del hijo
     * @param Arbol Nodo Arbol en el que se agregara al hijo
     */
    private void AddHijo(String progenitor, Nodo hijo, Nodo Arbol) {
        if (!ExisteNombre(progenitor) || ExisteNombre(hijo.getHombre())) {
            return;
        }

        if (!EstaCasado(Arbol)) {
            return;
        }

        if ((Arbol.getHombre().equals(progenitor.toUpperCase())) || (Arbol.getMujer().equals(progenitor.toUpperCase()))) {
            if (Arbol.NumHijos()<Arbol.getnHijos()) {
                Arbol.getHijos().add(hijo);    
                return;
            }
            JOptionPane.showMessageDialog(null, progenitor+" Ya no pueden tener mas hijos");
            return;
        }

        if (!TieneHijos(Arbol)) {
            return;
        }

        for (int i = 0; i < Arbol.NumHijos(); i++) {
            AddHijo(progenitor, hijo, Arbol.getHijos().get(i));
        }
    }

    /**
     * Si el hombre existe en el Arbol y La esposa no existe en el arbol,
     * Entonces se casan
     *
     * @param hombre
     * @param esposa
     */
    public void AddPareja(String hombre, String esposa,int nHijos) {
        AddPareja(hombre, esposa,nHijos, Arbol);
    }

    /**
     * Parte privada de AddPareja(String hombre,String esposa)
     *
     * @param hombre
     * @param esposa
     * @param Arbol
     */
    private void AddPareja(String hombre, String esposa,int nHijos, Nodo Arbol) {
        if (!ExisteNombre(hombre.toUpperCase()) || ExisteNombre(esposa.toUpperCase())) {//Si el hombre no esta en el arbol o La esposa ya esta casada con alguien, Entonces termina el proceso
            return;
        }
        if ((Arbol.getHombre().equals(hombre.toUpperCase())) && (Arbol.getMujer() == null)) {
            Arbol.setMujer(esposa.toUpperCase());
            Arbol.setnHijos(nHijos);
        }

        if (!TieneHijos(Arbol)) {
            return;
        }

        for (int i = 0; i < Arbol.NumHijos(); i++) {
            AddPareja(hombre, esposa,nHijos, Arbol.getHijos().get(i));
        }
    }
    public void guardarFichero(String nomFichero){
        File fichero=new File(nomFichero+".txt");
        String arbol=toString();
        try {
            FileWriter escribir=new FileWriter(fichero);
            escribir.write(arbol);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(ArbolG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String toString(){
        return toString(Arbol);
    }
    private String toString(Nodo Arbol){
        String M="";
        
        if (!EstaCasado(Arbol)) {
            return M;
        }
        M=M+Arbol.getHombre()+"::"+Arbol.getMujer();
        if (!TieneHijos(Arbol)) {
            M=M+"\n";
            return M;
        }
        for (int i = 0; i < Arbol.NumHijos(); i++) {
            if (i==0) {
                M=M+"{";
            }
            M=M+Arbol.getHijos().get(i).getHombre()+",";
        }
        M=M+"}\n";
        for (int i = 0; i < Arbol.NumHijos(); i++) {
            M=M+toString(Arbol.getHijos().get(i));
        }
        return M;
    }
    
}
