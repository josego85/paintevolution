/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import java.awt.image.BufferedImage;

/**
 * Clase Imagen.
 * @author proyectosbeta
 */
public class Imagen {
    // Variables de clase.
    private String nombre;
    private int coordenadaX;
    private int coordenadaY;
    private BufferedImage imagen;
    
    /**
     * Constructor con 4 parametros.
     * @param nombre
     * @param coordenadaX
     * @param coordenadaY
     * @param imagen 
     */
    public Imagen(String nombre, int coordenadaX, int coordenadaY, BufferedImage imagen){
        setNombre(nombre);
        setCoordenadaX(coordenadaX);
        setCoordenadaY(coordenadaY);
        setImagen(imagen);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
}