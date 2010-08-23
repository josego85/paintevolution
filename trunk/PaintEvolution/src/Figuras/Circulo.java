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
 *
 * Decalaracion de la clase Circulo que extiende de Figura
 */
public class Circulo extends FiguraConRelleno{
    // Variables de clase
    private double radio;      // el radio del circulo

    // Constructor
    public Circulo(Point2D punto, double radio, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, colorBorde, colorRelleno, tamanhio);
        setRadio(radio);
    }

    // Constructor
    public Circulo(int x, int y, double radio, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, colorBorde, colorRelleno, tamanhio);
        setRadio(radio);
    }

    // Setters y getters
     /**
     * Devuelve el radio del circulo
     *
     * @return radio el radio del circulo
     */
    public double getRadio() {
        return radio;
    }

    /**
     * Establece el radio del circulo
     *
     * @param radio el radio del circulo
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    // Metodos varios
    // Sobre escribe el metodo dibujar
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
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 05:16
    */
    /**
     * Devuelve true si el punto está contenido dentro del circulo,
     * false en caso contrario
     *
     * @param x x del punto a ver si esta dentro.
     * @param y y del punto a ver si esta dentro.
     *
     * @return true si esta dentro.
     */
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
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     * Fecha: 21/07/2010 05:16
    */
    /**
     * Fija el centro del circulo
     *
     * @param x nueva x del centro.
     * @param y nueva y del centro.
     */
    public void setPosicion(int x, int y){
        setInicio(x, y);
    }
}