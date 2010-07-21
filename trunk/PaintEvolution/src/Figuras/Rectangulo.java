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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * Decalaracion de la clase Rectangulo que extiende de Figura
 */
public class Rectangulo extends Figura{
    // Variables de clase
    private int ancho;          // ancho del rectangulo
    private int alto;           // alto del rectangulo

    // Constructor
    public Rectangulo(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, colorBorde, colorRelleno, tamanhio);
        setAncho(ancho);
        setAlto(alto);
    }

    public Rectangulo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, colorBorde, colorRelleno, tamanhio);
        setAncho(ancho);
        setAlto(alto);
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
            g.fillRect((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        }
        r2 = new Rectangle2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(r2);
    }

    /**
     * @(#)Rectangulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Devuelve true si x, y esta dentro del rectangulo, false en caso contrario
     *
     * @param x del punto que se quiere saber si esta dentro del rectangulo
     * @param y del punto que se quiere saber si esta dentro del rectangulo
     *
     * @return true si x,y esta dentro del rectangulo
     */
    public boolean estaDentro(int x, int y){
        if((x > getInicio().getX()) && (x < (getInicio().getX() + getAncho())) &&
                (y > getInicio().getY()) && (y < (getInicio().getY() + getAlto()))){
            return true;
        }
        return false;
    }

    /**
     * @(#)Rectangulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Fija la esquina superior izquierda del rectangulo
     *
     * @param x
     * @param y
     */
    public void setPosicion(int x, int y){
        setInicio(x, y);
    }
}