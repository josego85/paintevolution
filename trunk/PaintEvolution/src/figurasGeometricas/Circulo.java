/**
 * @(#)Circulo.java
 * @author Chuidiang
 * @co-author Modyfy fires
 * Fecha: 8/06/07 18:02
 */

package figurasGeometricas;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Figura Circulo para dibujarlo en un PanelDibujo
 */
public class Circulo implements InterfaceFigura {

    // Variables de clase
    /**
     * x del centro del Circulo
     */
    private int x;

    /**
     * y del centro del Circulo
     */
    private int y;

    /**
     * color del Circulo
     */
    private Color color;

    /**
     * radio del Circulo
     */
    private int radio;

    /**
     * tamanhio del borde del Circulo
     */
    private int tamanhioBorde;


    /**
     * Crea un nuevo objeto Circulo
     *
     * @param x del centro
     * @param y del centro
     * @param radio radio
     * @param color color
     */
    public Circulo(int x, int y, int radio, Color color, int tamanhioBorde){
        setX(x);
        setY(y);
        setRadio(radio);
        setColor(color);
        setTamanhioBorde(tamanhioBorde);
    }

    // Los setters y getters
    /**
     * Devuelve la x del centro.
     *
     * @return x del centro
     */
    public int getX(){
        return x;
    }

    /**
     * Establece la x del centro.
     *
     * @param x del centro
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Devuelve la y del centro.
     *
     * @return y del centro
     */
    public int getY(){
        return y;
    }

    /**
     * Establece la y del centro.
     *
     * @param y del centro
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Devuelve el color del relleno del circulo
     *
     * @return color del relleno del circulo
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del relleno del circulo
     *
     * @param color del relleno del circulo
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Devuelve el radio del circulo
     *
     * @return radio del circulo
     */
    public int getRadio() {
        return radio;
    }

    /**
     * Establece el radio del circulo
     *
     * @param radio del circulo
     */
    public void setRadio(int radio) {
        this.radio = radio;
    }

    /**
     * Devuelve el tamanhio del borde del circulo
     *
     * @return tamanhio del borde del circulo
     */
    public int getTamanhioBorde() {
        return tamanhioBorde;
    }

    /**
     * Establece el tamanhio del borde del circulo
     *
     * @param tamanhio del borde del circulo
     */
    public void setTamanhioBorde(int tamanhioBorde) {
        this.tamanhioBorde = tamanhioBorde;
    }

    
    // Metodos varios
    /**
     * Se dibuja un circulo en el Graphics que se le pasa
     *
     * @param g Graphics con el que dibujar
     */
    public void dibujate(Graphics g){
        g.setColor(getColor());
        g.drawOval(getX() - getRadio(), getY() - getRadio(), 2 * getRadio(),
                2 * getRadio());
    }

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
        if(Math.sqrt(((getX() - x) * (getX() - x)) +
                ((getY() - y) * (getY() - y))) < getRadio()){
            return true;
        }
        return false;
    }

    /**
     * Fija el centro del circulo
     *
     * @param x nueva x del centro.
     * @param y nueva y del centro.
     */
    public void setPosicion(int x, int y){
        setX(x);
        setY(y);
    }
}