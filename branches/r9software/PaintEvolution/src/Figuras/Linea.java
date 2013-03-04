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
 * @since 1.6
 */
/**
 * Clase Linea que extiende de la clase abstracta Figura y se pueden manipular
 * objetos linea.
 */
public class Linea extends Figura{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * La coordenada final del Punto (x, y) de la linea.
     * @since 1.6
     */
    private Point2D fin;                                

    
    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye una linea.
     * Su coordenada final predeterminada (0, 0).
     * @since 1.6
     */
    public Linea(){
        super();
        setFin(new Point2D.Double(0, 0));               
    }

    /**
     * Construye una linea.
     *
     * @param puntoInicio La coordenada inicial (x, y)
     * @param puntoFin La coordenada final (x, y)
     * @param colorBorde El color del borde
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Linea(Point2D puntoInicio, Point2D puntoFin, Color colorBorde, int tamanhio){
        super(puntoInicio, colorBorde, tamanhio);
        setFin(puntoFin);               
    }

    /**
     * Construye una linea.
     *
     * @param inicioX La coordenada inicial x
     * @param inicioY La coordenada inicial y
     * @param finX La coordenada final x
     * @param finY La coordenada final y
     * @param colorBorde El color del borde
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public Linea(int inicioX, int inicioY, int finX, int finY, Color colorBorde, int tamanhio){
        super(inicioX, inicioY, colorBorde, tamanhio);
        setFin(new Point2D.Double(finX, finY));        // Punto final
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve la coordenada final (x, y).
     *
     * @return La coordenada final (x, y)
     * @since 1.6
     */
    public Point2D getFin() {
        return fin;
    }

    /**
     * Establece la coordenada final (x, y).
     *
     * @param fin La coordenada final (x, y)
     * @since 1.6
     */
    public void setFin(Point2D fin) {
        this.fin = fin;
    }

    /**
     * Establece la coordenada final (finX, finY)
     *
     * @param finX La coordenada final de x
     * @param finY La coordenada final de y 
     * @since 1.6
     */
    public void setFin(int finX, int finY) {
        Point2D finCoordenada = new Point2D.Double(finX, finY);
        this.fin = finCoordenada;
    }
 

    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Dibuja la linea.
     * @param g El objeto Graphics
     * @since 1.6
     */
    @Override
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

    /**
     * Debe devolver true si <b>x</b> e <b>y</b> está dentro de la linea; false, en caso contrario.
     * No se va a arrastrar la linea, por lo tanto siempre lanza false.
     *
     * @param x La coordenada x de la linea
     * @param y La coordenada y de la linea
     *
     * @return True si esta dentro.
     * @since 1.6
     */
    @Override
    public boolean estaDentro(int x, int y) {
        return false; 
    }

    /**
     * Establece la posición en la que se debe dibujar la linea.
     * No posiciona la linea; o sea, no hace nada el metodo.
     *
     * @param x La coordenada x de la linea
     * @param y La coordenada y de la linea
     * @since 1.6
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void setPosicion(int x, int y) {
        ;
    }
}