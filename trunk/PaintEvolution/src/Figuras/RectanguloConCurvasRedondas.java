/**
 * @(#)RectanguloConCurvasRedondas.java
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
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;


public class RectanguloConCurvasRedondas extends Rectangulo{
    private int curvaEsquinaRedondeada;

    // Constructor
    public RectanguloConCurvasRedondas(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, ancho, alto, colorBorde, colorRelleno, tamanhio);
        setCurvaEsquinaRedondeada(25);
    }

    // Constructor
    public RectanguloConCurvasRedondas(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
        setCurvaEsquinaRedondeada(25);
    }

    // Setters y getters
    /**
     * Devuelve el numero de curvatura de la esquina del rectangulo con curvas redondas
     *
     * @return CurvaEsquinaRedondeada el numero de curvatura de la esquina del rectangulo con curvas redondas
     */
    public int getCurvaEsquinaRedondeada() {
        return curvaEsquinaRedondeada;
    }

     /**
     * Establece el numero de curvatura de la esquina del rectangulo con curvas redondas
     *
     * @param CurvaEsquinaRedondeada el numero de curvatura de la esquina del rectangulo con curvas redondas
     */
    public void setCurvaEsquinaRedondeada(int curvaEsquinaRedondeada) {
        this.curvaEsquinaRedondeada = curvaEsquinaRedondeada;
    }


    // Metodos varios
    // Sobre escribe el metodo dibujar
    @Override
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        RoundRectangle2D rr2;
        Stroke bordeFigura;

        // Si el color del relleno es null significa que no tiene relleno
        if(getColorRelleno() != null){
            g.setColor(getColorRelleno());
            g.fillRoundRect((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto(), getCurvaEsquinaRedondeada(),
                    getCurvaEsquinaRedondeada());
        }
        rr2 = new RoundRectangle2D.Float((int)getInicio().getX(), (int)getInicio().getY(),
                    getAncho(), getAlto(), getCurvaEsquinaRedondeada(),
                    getCurvaEsquinaRedondeada());
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(rr2);
    }
}