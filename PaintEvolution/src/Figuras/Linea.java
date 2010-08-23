package Figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * @(#)Linea.java
 *
 * @author fires.
 * @version 1.00 2010/8/22
 */
/**
 *
 * Decalaracion de la clase Linea que extiende de Figura
 */
public class Linea extends Figura{
    // Variables de clase
    private Point2D fin;                                // Fin de las coordenadas del Punto(x,y) de la linea

    public Linea(){
        super();
        setFin(new Point2D.Double(0, 0));               // Punto final (0, 0)
    }

    public Linea(Point2D puntoInicio, Point2D puntoFin, Color colorBorde, int tamanhio){
        super(puntoInicio, colorBorde, tamanhio);
        setFin(puntoFin);               // Punto final
    }

    public Linea(int inicioX, int inicioY, int finX, int finY, Color colorBorde, int tamanhio){
        super(inicioX, inicioY, colorBorde, tamanhio);
        setFin(new Point2D.Double(finX, finY));        // Punto final
    }


    // Setters y Getters
    /**
     * Devuelve la coordenada final de Point 2D(x, y)
     *
     * @return la coordenada final de Point 2D(x, y)
     */
    public Point2D getFin() {
        return fin;
    }

    /**
     * Establece la coordenada final de Point 2D(x, y))
     *
     * @param la coordenada final de Point 2D(x, y)
     */
    public void setFin(Point2D fin) {
        this.fin = fin;
    }

    /**
     * Establece la coordenada final de Point 2D(finX, finY)
     *
     * @param x y
     */
    public void setFin(int finX, int finY) {
        Point2D finCoordenada = new Point2D.Double(finX, finY);
        this.fin = finCoordenada;
    }
 

    // Metodos varios
    // Sobre escribe el metodo dibujar
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Line2D line2D;
        Stroke bordeFigura;

        line2D = new Line2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                (int)getFin().getX(), (int)getFin().getY());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(line2D);
    }

     @Override
    public boolean estaDentro(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPosicion(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}