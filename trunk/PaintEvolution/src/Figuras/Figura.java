package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 * @(#)Figura.java
 *
 * @author fires.
 * @version 1.00 2010/8/22
 */
/**
 *
 * Decalaracion de la clase abstracta Figura
 */
public abstract class Figura {
    // Variables de clase
    private Point2D inicio;             // Inicio de las coordenadas del Punto(x,y) de la figura
    private Color colorBorde;           // Color del borde de la figura
    private int tamanhio;               // Tamanhio de la figura

    // Constructor Predeterminado
    public Figura(){
        setInicio(new Point2D.Double(0, 0));    // Punto (0, 0)
        setColorBorde(Color.BLACK);             // ColorBorde negro
        setTamanhio(1);                         // Tamanhio 1
    }

    public Figura(Point2D punto, Color colorBorde, int tamanhio){
        setInicio(punto);               // Punto
        setColorBorde(colorBorde);      // ColorBorde
        setTamanhio(tamanhio);          // Tamanhio
    }

    public Figura(int x, int y, Color colorBorde, int tamanhio){
        setInicio(new Point2D.Double(x, y));        // Punto
        setColorBorde(colorBorde);                  // ColorBorde
        setTamanhio(tamanhio);                      // Tamanhio
    }

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
     * Devuelve la coordenada inicial de Point 2D(x, y)
     *
     * @return la coordenada inicial de Point 2D(x, y)
     */
    public Point2D getInicio() {
        return inicio;
    }

    /**
     * Establece la coordenada inicial de Point 2D(x, y))
     *
     * @param la coordenada inicial de Point 2D(x, y)
     */
    public void setInicio(Point2D inicio) {
        this.inicio = inicio;
    }

     /**
     * Establece la coordenada inicial de Point 2D(x, y))
     *
     * @param x y
     */
    public void setInicio(int x, int y) {
        Point2D inicioCoordenada = new Point2D.Double(x, y);
        this.inicio = inicioCoordenada;
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

    // Metodos varios
    // Dibuja dependiendo de la figura
    public void dibujar(Graphics g){}

     /**
     * @(#)InterfaceFigura.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 22/07/2010 03:38
    */
    /**
     * Debe devolver true si x,y está dentro de la figura, false en caso contrario
     *
     * @param x
     * @param y
     *
     * @return true si x,y está dentro de la figura
     */
    public abstract boolean estaDentro(int x, int y);

     /**
     * @(#)InterfaceFigura.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 22/07/2010 03:44
    */
    /**
     * Fija la posición en la que se debe dibujar la figura
     *
     * @param x
     * @param y
     */
    public abstract void setPosicion(int x, int y);
}