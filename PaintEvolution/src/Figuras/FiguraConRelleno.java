package Figuras;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * @(#)FiguraConRelleno.java
 *
 * @author fires.
 * @version 1.00 2010/8/22
 * @since 1.6
 */
/**
 *
 * Clase abstracta Figura con relleno que extiende de Figura.
 * Agrega color de relleno.
 */
public abstract class FiguraConRelleno extends Figura{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Color del relleno de FiguraConRelleno.
     * @since 1.6
     */
    private Color colorRelleno;         


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Es una clase abstracta y no se puede instanciar.<br>
     * Su color de relleno predeterminado es blanco.
     * @since 1.6
     */
    public FiguraConRelleno(){
        super();
        setColorRelleno(Color.WHITE);           
    }

    /**
     * Es una clase abstracta y no se puede instanciar.<br>
     *
     * @param punto Las coordenadas de inicio (x, y) del figura con relleno
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public FiguraConRelleno(Point2D punto, Color colorBorde, Color colorRelleno,
             int tamanhio){
        super(punto, colorBorde, tamanhio);
        setColorRelleno(colorRelleno);  
    }

    /**
     * Es una clase abstracta y no se puede instanciar.<br>
     *
     * @param x La coordenada x de la figura con relleno
     * @param y La coordenada y de la figura con relleno
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public FiguraConRelleno(int x, int y, Color colorBorde, Color colorRelleno,
             int tamanhio){
        super(x, y, colorBorde, tamanhio);
        setColorRelleno(colorRelleno);              
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve el color del relleno.
     *
     * @return colorRelleno El color del relleno
     * @since 1.6
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Establece el color del relleno.
     *
     * @param colorRelleno El color del relleno
     * @since 1.6
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }
}