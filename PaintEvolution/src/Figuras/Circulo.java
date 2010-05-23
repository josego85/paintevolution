/**
 * @(#)Circulo.java
 *
 * @author fires.
 * @version 1.00 2010/5/23
 */
package Figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

/**
 *
 * Decalaracion de la clase Circulo que extiende de Figura
 */
public class Circulo extends Figura{
    // Variables de clase
    private double radio;      // el radio del circulo

    // Constructores
    public Circulo(double radio){
        super();
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
    // Sobre escribe el metodo
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        Ellipse2D e2;
        Stroke bordeFigura;

        g.setColor(getColorRelleno());
        g.fillOval(getInicio().getCoordenadaX() - (int)radio,
                getInicio().getCoordenadaY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
        e2 = new Ellipse2D.Float(getInicio().getCoordenadaX() - (int)radio,
                getInicio().getCoordenadaY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
        g2 = (Graphics2D)g;
        bordeFigura = new BasicStroke(getTamanhio(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2.setColor(getColorBorde());
        g2.setStroke(bordeFigura);
        g2.draw(e2);
    }
}
