package Figuras;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * @(#)FiguraConRelleno.java
 *
 * @author fires.
 * @version 1.00 2010/8/22
 */
/**
 *
 * Decalaracion de la clase abstracta Figura con relleno.
 * Esta clase extiende de figura pero agrega color de relleno
 */
public abstract class FiguraConRelleno extends Figura{
    // Variables
    private Color colorRelleno;         // Color del relleno de la figura

    // Constructor Predeterminado
    public FiguraConRelleno(){
        super();
        setColorRelleno(Color.WHITE);           // ColorRelleno blanco
    }

    public FiguraConRelleno(Point2D punto, Color colorBorde, Color colorRelleno,
             int tamanhio){
        super(punto, colorBorde, tamanhio);
        setColorRelleno(colorRelleno);  // ColorRelleno
    }

    public FiguraConRelleno(int x, int y, Color colorBorde, Color colorRelleno,
             int tamanhio){
        super(x, y, colorBorde, tamanhio);
        setColorRelleno(colorRelleno);              // ColorRelleno
    }

    // Setters y Getters
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
}