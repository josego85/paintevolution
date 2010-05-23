/**
 * @(#)Figura.java
 *
 * @author fires.
 * @version 1.00 2010/5/23
 */

package Figuras;

import java.awt.Color;

/**
 *
 * Decalaracion de la clase abstracta Figura
 */
public abstract class Figura {
    // Variables de clase
    private Punto inicio;               // Inicio de las coordenadas del Punto(x,y) de la figura
    private Color colorBorde;           // Color del borde de la figura
    private Color colorRelleno;         // Color del relleno de la figura
    private int tamanhio;               // Tamanhio de la figura

    // Setters y Getters
    /**
     * Devuelve el color del borde
     *
     * @return colorBorde el color del borde
     */
    public Color getColorBorde() {
        return colorBorde;
    }

    /**
     * Establece el color del borde
     *
     * @param colorBorde el color del borde
     */
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    /**
     * Devuelve el color del relleno
     *
     * @return colorRelleno el color del relleno
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Establece el color del relleno
     *
     * @param colorRelleno el color del relleno
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    /**
     * Devuelve el inicio del punto (x, y)
     *
     * @return inicioPunto el inicio del punto (x, y)
     */
    public Punto getInicio() {
        return inicio;
    }

    /**
     * Establece el inicio del punto (x, y)
     *
     * @param inicioPunto el inicio del punto (x, y)
     */
    public void setInicio(Punto inicio) {
        this.inicio = inicio;
    }

    /**
     * Devuelve el tamanhio de la figura
     *
     * @return tamanhio el tamanhio de la figura
     */
    public int getTamanhio() {
        return tamanhio;
    }

    /**
     * Establece el tamanhio de la figura
     *
     * @param tamanhio el tamanhio de la figura
     */
    public void setTamanhio(int tamanhio) {
        this.tamanhio = tamanhio;
    }
}