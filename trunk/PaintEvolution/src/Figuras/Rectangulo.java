/**
 * @(#)Rectangulo.java
 *
 * @author fires.
 * @version 1.00 2010/5/23
 * @since 1.6
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
 * Clase Rectangulo que extiende de la clase abstracta FiguraConRelleno y se pueden 
 * manipular objetos rectangulo.
 * @since 1.6
 */
public class Rectangulo extends FiguraConRelleno{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Ancho del rectangulo
     * @since 1.6
     */
    private int ancho;

    /**
     * Alto del rectangulo
     * @since 1.6
     */
    private int alto;           


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye un rectangulo.
     *
     * @param punto La coordenada inicial (x, y)
     * @param ancho El ancho
     * @param alto El alto
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Rectangulo(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, colorBorde, colorRelleno, tamanhio);
        setAncho(ancho);
        setAlto(alto);
    }

    /**
     * Construye un rectangulo.
     *
     * @param x La coordenada x
     * @param y La coordenada y
     * @param ancho El ancho
     * @param alto El alto
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Rectangulo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, colorBorde, colorRelleno, tamanhio);
        setAncho(ancho);
        setAlto(alto);
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve el alto del rectangulo.
     *
     * @return alto El alto del rectangulo
     * @since 1.6
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece el alto del rectangulo.
     *
     * @param alto El alto del rectangulo
     * @since 1.6
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

     /**
     * Devuelve el ancho del rectangulo.
     *
     * @return alto El ancho del rectangulo
     * @since 1.6
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Establece el ancho del rectangulo.
     *
     * @param ancho El ancho del rectangulo
     * @since 1.6
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Dibuja el rectangulo.
     * @param g El objeto Graphics
     * @since 1.6
     */
    @Override
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
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Debe devolver true si <b>x</b> e <b>y</b> están dentro del rectangulo;
     * false, en caso contrario.
     *
     * @param x La coordenada x del rectangulo
     * @param y La coordenada y del rectangulo
     *
     * @return True si esta dentro.
     * @since 1.6
     */
    @Override
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
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 04:52
    */
    /**
     * Establece la posición en la que se debe dibujar el rectangulo.
     *
     * @param x La coordenada x del rectangulo
     * @param y La coordenada y del rectangulo
     * @since 1.6
     */
    @Override
    public void setPosicion(int x, int y){
        setInicio(x, y);
    }
}