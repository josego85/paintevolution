
package Auxiliar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * @author gaby
 */
public class Texto {
    // Variables
    private String contenidoTexto;
    private String tipo;
    private int estilo;
    private int tamanio;
    private int posicionInicialX;
    private int posicionInicialY;
    private Color color;


    // Constructor con todos los valores
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

    // Constructor sin las posiciones iniciales X e Y
    // Se coloca las posicionInicialX e posicionInicialY con valor 0
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


    // Metodos get y set
    public String getContenidoTexto() {
        return contenidoTexto;
    }

    public void setContenidoTexto(String contenidoTexto) {
        this.contenidoTexto = contenidoTexto;
    }

    public int getEstilo() {
        return estilo;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPosicionInicialX() {
        return posicionInicialX;
    }

    public void setPosicionInicialX(int posicionInicialX) {
        this.posicionInicialX = posicionInicialX;
    }

    public int getPosicionInicialY() {
        return posicionInicialY;
    }

    public void setPosicionInicialY(int posicionInicialY) {
        this.posicionInicialY = posicionInicialY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    // Metodos varios
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
        layout.draw( g2,getPosicionInicialX(), getPosicionInicialY());
    }
}