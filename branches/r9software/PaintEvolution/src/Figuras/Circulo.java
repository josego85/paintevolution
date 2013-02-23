/**
 * @(#)Circulo.java
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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Clase Circulo que extiende de la clase abstracta Figura y se pueden manipular
 * objetos circulo.
 * @since 1.6
 */
public class Circulo extends FiguraConRelleno{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * El radio del circulo.
     * @since 1.6
     */
    private double radio;      


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye un circulo.
     *
     * @param punto Las coordenadas de inicio (x, y) del circulo
     * @param radio El radio del circulo
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Circulo(Point2D punto, double radio, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, colorBorde, colorRelleno, tamanhio);
        setRadio(radio);
    }

    /**
     * Construye un circulo.
     * 
     * @param x La coordenada x del circulo
     * @param y La coordenada y del circulo
     * @param radio El radio del circulo
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Circulo(int x, int y, double radio, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, colorBorde, colorRelleno, tamanhio);
        setRadio(radio);
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
     /**
     * Devuelve el radio del circulo.
     *
     * @return El radio del circulo
     * @since 1.6
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Establece el radio del circulo.
     *
     * @param radio El radio del circulo
     * @since 1.6
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Dibuja el circulo.
     * @param g El objeto Graphics
     * @since 1.6
     */
    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Ellipse2D e2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillOval((int)getInicio().getX() - (int)radio, (int)getInicio().getY()
                    - (int)radio, (int)radio * 2 , (int)radio * 2);
        }
        e2 = new Ellipse2D.Float((int)getInicio().getX() - (int)radio, (int)getInicio().getY()
                    - (int)radio, (int)radio * 2 , (int)radio * 2);
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(e2);
    }


    /**
     * @(#)Circulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 05:16
    */
    /**
     * Debe devolver true si <b>x</b> e <b>y</b> están dentro del circulo; false, en caso contrario.
     *
     * @param x La coordenada x del circulo
     * @param y La coordenada y del circulo
     *
     * @return True si esta dentro.
     * @since 1.6
     */
    @Override
    public boolean estaDentro(int x, int y){
        if(Math.sqrt(((getInicio().getX() - x) * (getInicio().getX() - x)) +
                ((getInicio().getY() - y) * (getInicio().getY() - y))) < getRadio()){
            return true;
        }
        return false;
    }


    /**
     * @(#)Circulo.java
     * @author Chuidiang
     * @co-author Modyfy fires
     * Sitio: http://www.chuidiang.com/
     * Licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 05:16
    */
    /**
     * Establece la posición en la que se debe dibujar el circulo.
     *
     * @param x La coordenada x del circulo
     * @param y La coordenada y del circulo
     * @since 1.6
     */
    @Override
    public void setPosicion(int x, int y){
        setInicio(x, y);
    }
}