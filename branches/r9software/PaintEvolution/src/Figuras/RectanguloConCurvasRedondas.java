/**
 * @(#)RectanguloConCurvasRedondas.java
 *
 * @author fires.
 * @version 1.00 2010/7/22
 * @since 1.6
 */

package Figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Clase RectanguloConCurvasRedondas que extiende de la clase Rectangulo y se pueden
 * manipular objetos Rectangulo con curvas redondas.
 * @since 1.6
 */
public class RectanguloConCurvasRedondas extends Rectangulo{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Tamanhio de todas las curvas redondeadas de la esquina.
     */
    private int curvaEsquinaRedondeada;


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye Ravtangulos con curvas redondeadas. El valor de las curvas redondeadas
     * de la esquina es simpre 25.
     *
     * @param punto La coordenada inicial (x, y)
     * @param ancho El ancho
     * @param alto El alto
     * @param colorBorde El color del borde
     * @param colorRelleno El color del relleno
     * @param tamanhio El tamañio del borde
     * @since 1.6
     */
    public RectanguloConCurvasRedondas(Point2D punto, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(punto, ancho, alto, colorBorde, colorRelleno, tamanhio);
        setCurvaEsquinaRedondeada(25);
    }

    /**
     * Construye Ravtangulos con curvas redondeadas. El valor de las curvas redondeadas
     * de la esquina es simpre 25.
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
    public RectanguloConCurvasRedondas(int x, int y, int ancho, int alto, Color colorBorde, Color colorRelleno,
            int tamanhio){
        super(x, y, ancho, alto, colorBorde, colorRelleno, tamanhio);
        setCurvaEsquinaRedondeada(25);
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve el numero de curvatura de la esquina del rectangulo con curvas redondas.
     *
     * @return CurvaEsquinaRedondeada El numero de curvatura de la esquina del rectangulo con curvas redondas
     * @since 1.6
     */
    public int getCurvaEsquinaRedondeada() {
        return curvaEsquinaRedondeada;
    }

     /**
     * Establece el numero de curvatura de la esquina del rectangulo con curvas redondas.
     *
     * @param curvaEsquinaRedondeada El numero de curvatura de la esquina del rectangulo con curvas redondas
      * @since 1.6
     */
    public void setCurvaEsquinaRedondeada(int curvaEsquinaRedondeada) {
        this.curvaEsquinaRedondeada = curvaEsquinaRedondeada;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Dibuja el rectangulo de curvas redondeadas.
     * @param g El objeto Graphics
     * @since 1.6
     */
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