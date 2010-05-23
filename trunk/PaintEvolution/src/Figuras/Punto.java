/**
 * @(#)Punto.java
 *
 * @author fires.
 * @version 1.00 2010/5/23
 */

package Figuras;

/**
 *
 * Declaracion de la clase Punto
 * Contiene las coordenadas x e y
 */
public class Punto {
    // Variables de clase
    private int coordenadaX;      // Coordenada x
    private int coordenadaY;      // Coordenada y

    // Constructor sin argumentos
    /**
     * Crea un nuevo Punto
     * El valor predeterminado de x e y es 0
     */
    public Punto(){
        setCoordenadaX(this.coordenadaX);
        setCoordenadaY(this.coordenadaY);
    }

    /**
     * Crea un nuevo Punto
     *
     * @param x coordenada x
     * @param y coordenada y
     */
    public Punto(int coordenadaX, int coordenadaY){
        setCoordenadaX(coordenadaX);
        setCoordenadaY(coordenadaY);
    }

    // Setters y Getters
    /**
     * Devuelve la coordenada x
     *
     * @return x la coordenada x
     */
    public int getCoordenadaX(){
        return coordenadaX;
    }

    /**
     * Establece la coordenada x
     *
     * @param x la coordenada x
     */
    public void setCoordenadaX(int coordenadaX){
        this.coordenadaX = coordenadaX;
    }

    /**
     * Devuelve la coordenada y
     *
     * @return y la coordenada y
     */
    public int getCoordenadaY(){
        return coordenadaY;
    }

    /**
     * Establece la coordenada y
     *
     * @param y la coordenada y
     */
    public void setCoordenadaY(int coordenadaY){
        this.coordenadaY = coordenadaY;
    }
}