/**
 * @(#)Rectangulo.java
 *
 * @author fires.
 * @version 1.00 2010/5/23
 */
package Figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

/**
 *
 * Decalaracion de la clase Rectangulo que extiende de Figura
 */
public class Rectangulo extends Figura{
    // Variables de clase
    private int alto;           // alto del rectangulo
    private int ancho;          // ancho del rectangulo

    // Constructor
    public Rectangulo(Punto punto, int alto, int ancho, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, colorBorde, colorRelleno, tamanhio);
        setAlto(alto);
        setAncho(ancho);
    }

    // Setters y getters
    /**
     * Devuelve el alto del rectangulo
     *
     * @return alto el alto del rectangulo
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece el alto del rectangulo
     *
     * @param alto el alto del rectangulo
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

     /**
     * Devuelve el ancho del rectangulo
     *
     * @return alto el ancho del rectangulo
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Establece el ancho del rectangulo
     *
     * @param alto el ancho del rectangulo
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    // Metodos varios
    // Sobre escribe el metodo dibujar
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Rectangle2D r2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillRect(getAlto(), getAncho(), getInicio().getCoordenadaX(),
                getInicio().getCoordenadaY());
        }
        r2 = new Rectangle2D.Float(getAlto(), getAncho(), getInicio().getCoordenadaX(),
                getInicio().getCoordenadaY());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(r2);
    }
}