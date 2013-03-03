package Auxiliar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *@(#)Texto.java
 *
 * @author fires
 * @version 1.00
 * @since 1.6
 */
/**
 * Clase Texto para poder manipular texto.
 * @since 1.6
 */
public class Texto {
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * El contenido del texto.
     * @since 1.6
     */
    private String contenidoTexto;

    /**
     * El tipo de texto.
     * @since 1.6
     */
    private String tipo;

    /**
     * El estilo del texto.
     * @since 1.6
     */
    private int estilo;

    /**
     * El tamanhio del texto.
     * @since 1.6
     */
    private int tamanio;

    /**
     * La posicion inicial de la coordenada x.
     * @since 1.6
     */
    private int posicionInicialX;

    /**
     * La posicion inicial de la coordenada y.
     * @since 1.6
     */
    private int posicionInicialY;

    /**
     * El color del texto.
     * @since 1.6
     */
    private Color color;


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye un Texto.
     *
     * @param contenidoTexto El contenido
     * @param tipo El tipo
     * @param estilo El estilo
     * @param tamanio El tamanhio
     * @param color El color
     * @param posicionInicialX La coordenada inicial x
     * @param posicionInicialY La coordenada inicial y
     * @since 1.6
     */
    public Texto(String contenidoTexto, String tipo, int estilo, int tamanio,
            Color color, int posicionInicialX, int posicionInicialY){
        setContenidoTexto(contenidoTexto);
        setTipo(tipo);
        setEstilo(estilo);
        setTamanio(tamanio);
        setColor(color);
        setPosicionInicialX(posicionInicialX);
        setPosicionInicialY(posicionInicialX);
    }

    /**
     * Construye un Texto.<br>
     * Se coloca la posicion inicial x es igual a 0.<br>
     * Se coloca la posicion inicial y es igual a 0.<br>
     * 
     * @param contenidoTexto El contenido 
     * @param tipo El tipo
     * @param estilo El estilo
     * @param tamanio El tamanhio
     * @param color El color
     * @since 1.6
     */
    public Texto(String contenidoTexto, String tipo, int estilo, int tamanio,
            Color color){
        setContenidoTexto(contenidoTexto);
        setTipo(tipo);
        setEstilo(estilo);
        setTamanio(tamanio);
        setColor(color);
        setPosicionInicialX(0);
        setPosicionInicialY(0);
    }


    ////////////////////////////////////////////////////////////////////////////
    // Setters y Getters
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve el contenido del texto.
     *
     * @return El contenido del texto
     * @since 1.6
     */
    public String getContenidoTexto() {
        return contenidoTexto;
    }

    /**
     * Estabelce el contenido del texto.
     *
     * @param contenidoTexto El contenido del texto
     * @since 1.6
     */
    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    /**
     * Devuelve el estilo del texto.
     *
     * @return El estilo delo texto
     * @since 1.6
     */
    public int getEstilo() {
        return estilo;
    }

    /**
     * Establece el estilo del texto.
     *
     * @param estilo El estilo del texto
     * @since 1.6
     */
    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    /**
     * Devuelve el tamanhio del texto.
     *
     * @return El tamanhio del texto
     * @since 1.6
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Estblece el tamanhio del texto.
     *
     * @param tamanio El tamanhio del texto
     * @since 1.6
     */
    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * Devuelve el tipo del texto.
     *
     * @return El tipo de texto
     * @since 1.6
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de texto.
     *
     * @param tipo El tipo de texto
     * @since 1.6
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve la posicion inicial de x.
     *
     * @return La posicion inicial de x
     * @since 1.6
     */
    public int getPosicionInicialX() {
        return posicionInicialX;
    }

    /**
     * Establece la posicion inicial de x.
     *
     * @param posicionInicialX La posicion inicial de x
     * @since 1.6
     */
    public void setPosicionInicialX(int posicionInicialX) {
        this.posicionInicialX = posicionInicialX;
    }

    /**
     * Devuelve la posicion inicial de y.
     *
     * @return la posicion inicial de y
     * @since 1.6
     */
    public int getPosicionInicialY() {
        return posicionInicialY;
    }

    /**
     * Establece la posicion inicial de y.
     *
     * @param posicionInicialY La posicion inicial de y
     * @since 1.6
     */
    public void setPosicionInicialY(int posicionInicialY) {
        this.posicionInicialY = posicionInicialY;
    }

    /**
     * Devuelve el color del texto.
     *
     * @return El color del texto
     * @since 1.6
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color del texto.
     *
     * @param color El color del texto
     * @since 1.6
     */
    public void setColor(Color color) {
        this.color = color;
    }


    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Dibuja el texto.
     * @param g El objeto Graphics
     * @since 1.6
     */
    public void dibujar(Graphics g){
        // Objetos
        Graphics2D g2;
        FontRenderContext contextoFuente;
        Font fuente;
        TextLayout layout;

        g2 = (Graphics2D)g;
        contextoFuente = g2.getFontRenderContext();
        fuente = new Font(getTipo(), getEstilo(), getTamanio());
        layout = new TextLayout(getContenidoTexto(), fuente, contextoFuente );
        g2.setColor(getColor());
        layout.draw( g2, getPosicionInicialX(), getPosicionInicialY());
    }
}