/**
 * @(#)Ovalo.java
 *
 * @author fires.
 * @version 1.00 2010/7/22
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
 * Declaracion de la clase Ovalo que extiende de Rectangulo
 */
public class Ovalo extends Rectangulo{

    // Constructor
    public Ovalo(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, ancho, alto, colorBorde, colorRelleno, tamanhio);
    }

    // Constructor
    public Ovalo(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
    }

    // Metodos varios
    // Sobre escribe el metodo dibujar
    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Ellipse2D e2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillOval((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        }
        e2 = new Ellipse2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(e2);
    }
}