/**
 * @(#)Rectangulo.java
 * @author Chuidiang
 * @co-author Modyfy fires
 * sitio: http://www.chuidiang.com/
 * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
 * Mi licencia (fires): BSD
 * Fecha: 8/06/07 18:02
 */

package figurasGeometricas;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Rectangulo para ser arrastrado en un PanelDibujo
 */
public class Rectangulo implements InterfaceFigura{

    // Variables de clase
    /**
     * x del rectangulo (esquina superior izquierda)
     */
    private int x;

    /**
     * y del rectangulo (esquina superior izquierda)
     */
    private int y;

    /**
     * ancho del rectangulo
     */
    private int ancho;

    /**
     * alto del rectangulo
     */
    private int alto;

    /**
     * color del rectangulo
     */
    private Color color;

    /**
     *  tamanhio borde
     */
    private int tamanhioBorde;


    /**
     * Crea un nuevo objeto Rectangulo.
     *
     * @param x de la esquina superior izquierda
     * @param y de la esquina superior izquierda
     * @param alto del rectangulo
     * @param ancho del rectangulo
     * @param color de relleno del rectangulo
     * @param tamanhioBorde del rectangulo
     */
    public Rectangulo(int x, int y, int alto, int ancho, Color color, int tamanhioBorde){
        setX(x);
        setY(y);
        setAncho(ancho);
        setAlto(alto);
        setColor(color);
        setTamanhioBorde(tamanhioBorde);
    }

    // Los setters y getters
    /**
     * Devuelve la x de la esquina superior izquierda del rectangulo
     *
     * @return x de la esquina superior izquierda del rectangulo
     */
    public int getX(){
        return x;
    }

    /**
     * Establece la x de la esquina superior izquierda del rectangulo
     *
     * @param x de la esquina superior izquierda del rectangulo
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Devuelve la y de la esquina superior izquierda del rectangulo
     *
     * @return y de la esquina superior izquierda del rectangulo
     */
    public int getY(){
        return y;
    }

    /**
     * Establece la y de la esquina superior izquierda del rectangulo
     *
     * @param y de la esquina superior izquierda del rectangulo
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Devuelve el alto del rectangulo
     *
     * @return alto del rectangulo
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece el alto del rectangulo
     *
     * @param alto del rectangulo
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Devuelve el ancho del rectangulo
     *
     * @return ancho del rectangulo
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Establece el ancho del rectangulo
     *
     * @param ancho del rectangulo
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Devuelve el color del relleno del rectangulo
     *
     * @return color del relleno del rectangulo
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del relleno del rectangulo
     *
     * @param color del relleno del rectangulo
     */
    public void setColor(Color color) {
        this.color = color;
    }

     /**
     * Devuelve el tamanhio del borde del rectangulo
     *
     * @return tamanhio del borde del rectangulo
     */
    public int getTamanhioBorde() {
        return tamanhioBorde;
    }

     /**
     * Establece el tamanhio del borde del rectangulo
     *
     * @param tamanhio del borde del rectangulo
     */
    public void setTamanhioBorde(int tamanhioBorde) {
        this.tamanhioBorde = tamanhioBorde;
    }


    // Metodos varios
    /**
     * Dibuja el rectangulo en el Graphics que se le pasa
     *
     * @param g Graphics en el que dibujar
     */
    public void dibujate(Graphics g){
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getAncho(), getAlto());
    }

    /**
     * Devuelve true si x, y esta dentro del rectangulo, false en caso contrario
     *
     * @param x del punto que se quiere saber si esta dentro del rectangulo
     * @param y del punto que se quiere saber si esta dentro del rectangulo
     *
     * @return true si x,y esta dentro del rectangulo
     */
    public boolean estaDentro(int x, int y){
        if((x > getX()) && (x < (getX() + getAncho())) && (y > getY()) &&
                (y < (getY() + getAlto()))){
            return true;
        }
        return false;
    }

    /**
     * Fija la esquina superior izquierda del rectangulo
     *
     * @param x
     * @param y
     */
    public void setPosicion(int x, int y){
        setX(x);
        setY(y);
    }
}