package GUI;

import Auxiliar.Constantes;
import Auxiliar.FiltroArchivo;
import Auxiliar.Texto;
import Figuras.Circulo;
import Figuras.Figura;
import Figuras.Linea;
import Figuras.Ovalo;
import Figuras.Rectangulo;
import Figuras.RectanguloConCurvasRedondas;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *@(#)PanelDibujo.java
 * Creado 23/07/2010, 12:22:44 AM
 *
 * @author fires
 * @version 1.00
 * @since 1.6
 */
/**
 * Clase PanelDibujo donde se dibujan todos los objetos Figura y Texto del programa.
 * @since 1.6
 */
public class PanelDibujo extends javax.swing.JPanel implements Serializable, Printable{
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Constantes:
     * - Circulo: 1
     * - Rectandulo: 2
     * - Ovalo: 3
     * - Rectangulo con curvas redondeadas: 4
     * - Pincel: 5
     * - Linea: 6
     * - Lapiz: 7
     * - Texto: 8
     * - Borrador: 9
     * - Arrastrar: 10
     * - Nulo: 0
     * @since 1.6
     */
    private final static int CIRCULO = 1, RECTANGULO = 2, OVALO = 3,
        RECTANGULO_CON_CURVAS_REDONDAS = 4, PINCEL = 5, LINEA = 6, LAPIZ = 7,
        TEXTO = 8, BORRADOR = 9, ARRASTRAR = 10, NULO = 0;

    /**
     * Las coordenadas de inicio y fin de "x".
     * @since 1.6
     */
    private int coordenadasInicioX, coordenadasFinX;

    /**
     * Las coordenadas de inicio y fin de "y".
     * @since 1.6
     */
    private int coordenadasInicioY, coordenadasFinY;

    /**
     * Para saber que objeto o texto se va a dibujar.
     * @since 1.6
     */
    private int modoDibujar;

    /**
     * Guarda las coordenadas iniciales y finales de "x" e "y" que usan el
     * lapiz y pincel.
     * @since 1.6
     */
    private int lineaX1, lineaX2, lineaY1, lineaY2;

    /**
     * El color del fondo de la pantalla de dibujo.
     * @since 1.6
     */
    private Color colorFondoPantallaDibujo;

    /**
     * El color del borde.
     * @since 1.6
     */
    private Color colorBorde;

    /**
     * El color de relleno.
     * @since 1.6
     */
    private Color colorRelleno;

    /**
     * El tamanhio del borde.
     * @since 1.6
     */
    private float tamanioBorde;

    /**
     * El nombre del archivo que se usa para guardar la imagen.
     * @since 1.6
     */
    private File nombreArchivo;

    /**
     * La longitud del tamanhio del borrador.
     * @since 1.6
     */
    private int longitudBorrador;

    /**
     * La ventana Texto.
     * * @since 1.6
     */
    private VentanaTexto ventanaTexto;                      

    /**
     * True es para habilitar si se va a dibujar texto.
     * @since 1.6
     */
    private boolean habilitarDibujarTexto;

    /**
     * Donde se manipula el objeto texto con sus atributos.
     * @since 1.6
     */
    private Texto texto;

    /**
     * Para saber si las Figuras van a contener color de relleno o no.
     * Valor predeterminado es false.
     * @since 1.6
     */
    private boolean conRelleno = false;

    /**
     * La imagen actual que se mostrará.
     * @since 1.6
     */
    private BufferedImage imagen;

    /**
     * Escala por defecto de la imagen.
     * Su valor prederminado es 1.0.
     * @since 1.6
     */
    private double escala = 1.0;

    /**
     * El cursor actual que se esta usando.
     * @since 1.6
     */
    private Cursor cursorActual;
    
    /**
     * Lista de figuras a dibujar.
     * @since 1.6
     */
    private LinkedList<Figura> listaFiguras = new LinkedList<Figura>();

    /**
     * Lista de Texto a dibujar.
     * @since 1.6
     */
    private LinkedList<Texto> listaTexto = new LinkedList<Texto>();

    /**
     * Si actualmente se está arrastrando o no una Figura.
     * @since 1.6
     */
    private Figura figuraArrastrandose;

    /**
     * Indica para borrar un objeto Individual.
     * @since 1.6
     */
    private Figura eliminarObjetoIndivial;

     /**
      * La coordenada x en la que estaba anteriormente el ratón.
      * @since 1.6
      */
    private int xAnteriorRaton;

    /**
     * La coordenada y en la que estaba anteriormente el ratón.
     * @since 1.6
     */
    private int yAnteriorRaton;

    /**
     * Dos pilas para hacer y deshacer las opciones de dibujo.
     * @since 1.6
     */
    private Stack desHacerPila;

    /**
     * La ubicacion donde se colocará la imagen, si es null, se colocará en el centro.
     * * @since 1.6
     */
    private Point2D ubicacionDeImagen;

    /**
     * Valor boleano para saber si es la ultima version del archivo generado.
     *
     * @since 1.6
     */
    boolean archivoGuardadoUltimaVersion;

    /**
     * La imagen de fondo.
     */
    private BufferedImage imagenFondo;
    
    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye un Panel Dibujo.
     * @since 1.6
     */
    public PanelDibujo() {
        initComponents();
        setModoDibujar(NULO);
        setTamanioBorde(1.0f);
        longitudBorrador = 5;
        habilitarDibujarTexto = false;
        archivoGuardadoUltimaVersion = false;
        ubicacionDeImagen = null;
        nombreArchivo = null;
        ventanaTexto = null;
        texto = null;
        figuraArrastrandose = null;
        eliminarObjetoIndivial = null;
        desHacerPila = new Stack();
        colorFondoPantallaDibujo    = Color.WHITE;          // De color blanco
        colorBorde                  = Color.BLACK;          // De color negro
        colorRelleno                = null;                 // Sin relleno    
        setBackground(getColorFondoPantallaDibujo());
        
        // Tamaño del PanelDibujo.
        setSize(Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO, 
            Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO);
        //repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     * @since 1.6
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setCoordenadasInicioX(evt.getX());
        setLineaX1(evt.getX());
        setLineaX2(evt.getX());
        setCoordenadasInicioY(evt.getY());
        setLineaY1(evt.getY());
        setLineaY2(evt.getY());
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(modoDibujar == getLINEA()){
            Linea linea = new Linea(getCoordenadasInicioX(), getCoordenadasInicioY(),
                    getCoordenadasFinX(), getCoordenadasFinY(), getColorBorde(), 
                    (int)getTamanioBorde());    
            agregarFigura(linea);
            desHacerPila.push(linea);
        }else if (modoDibujar == getCIRCULO()){
            double radio = Math.sqrt(Math.pow(getCoordenadasFinX() - getCoordenadasInicioX(),2) +
                        Math.pow(getCoordenadasFinY() - getCoordenadasInicioY(),2));
            Circulo circulo =  new Circulo(getCoordenadasInicioX(), 
                    getCoordenadasInicioY(), (int)radio, getColorBorde(),
                    getColorRelleno(), (int)getTamanioBorde());
            agregarFigura(circulo);
            desHacerPila.push(circulo);
        }else if(modoDibujar == getRECTANGULO()){
            Rectangulo rectangulo = new Rectangulo(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                        getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
            agregarFigura(rectangulo);
            desHacerPila.push(rectangulo);
        }else if(modoDibujar == getOVALO()){
            Ovalo ovalo = new Ovalo(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                        getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
            agregarFigura(ovalo);
            desHacerPila.push(ovalo);
        }else if(modoDibujar == getRECTANGULO_CON_CURVAS_REDONDAS()){
            RectanguloConCurvasRedondas rectanguloConCurvasRedondas = new RectanguloConCurvasRedondas(
                    Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                    Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                    Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                    Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()),
                    getColorBorde(), getColorRelleno(), (int)getTamanioBorde());
            agregarFigura(rectanguloConCurvasRedondas);
            desHacerPila.push(rectanguloConCurvasRedondas);
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        //repaint();
    }//GEN-LAST:event_formMouseReleased

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        archivoGuardadoUltimaVersion = false;
        setCoordenadasFinX(evt.getX());
        setCoordenadasFinY(evt.getY());
        
         if (modoDibujar == getLAPIZ()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            setTamanioBorde((int)1);
            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamanioBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }else if (modoDibujar == getPINCEL()) {
            setLineaX1(getLineaX2());
            setLineaY1(getLineaY2());
            setLineaX2(getCoordenadasFinX());
            setLineaY2(getCoordenadasFinY());

            Linea linea = new Linea(getLineaX1(), getLineaY1(), getLineaX2(),
                    getLineaY2(), getColorBorde(), (int)getTamanioBorde());
            agregarFigura(linea);
            desHacerPila.push(linea);
        }
        /**
         * Método al que se llama cuando se arrastra el ratón.
         * Se comprueba con el atributo arrastrando si está empezando el arrastre o
         * ya se esta en medio del mismo.
         * Si se comienza el arrastre, se guardan las coordenadas del ratón que
         * vienen en el evento MouseEvent y se cambia el valor del atributo arrastrando.
         * Si se está en medio de un arrastre, se calcula la nueva posición del
         * rectángulo y se llama al método repaint() para que se pinte.
         *
         * @param e Evento del ratón
         * @since 1.6
        */
        if(modoDibujar == getARRASTRAR()){
            // Si comienza el arrastre
            if (figuraArrastrandose == null){
                // Se guardan las posiciones del ratón
                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                // y se marca que ha comenzado el arrastre.
                figuraArrastrandose = dameFigura(evt);
            }else{
                // Si ya había empezado el arrastre, se calculan las nuevas
                // coordenadas del rectángulo
                figuraArrastrandose.setPosicion((int)figuraArrastrandose.getInicio().getX()
                        + (evt.getX() - xAnteriorRaton), (int)figuraArrastrandose.getInicio().getY()
                        + (evt.getY() - yAnteriorRaton));

                // Se guarda la posición del ratón para el siguiente cálculo
                xAnteriorRaton = evt.getX();
                yAnteriorRaton = evt.getY();

                // y se manda repintar el Canvas
                repaint();
            }
        }
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * El ratón se mueve sin arrastrar. Se marca fin de arrastre.
     *
     * @param e MouseEvent
     * @since 1.6
     */
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        figuraArrastrandose = null;
        GUI_Principal.jLabelCoordenadasPuntero.setText("x: " + evt.getX() + "   y: " + evt.getY());
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        GUI_Principal.jLabelCoordenadasPuntero.setText("");
    }//GEN-LAST:event_formMouseExited

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setCursor(getCursorActual());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(modoDibujar == getTEXTO()){
            mostrarVentanaTexto();
            if(ventanaTexto.isDibujaTexto()){
                texto = ventanaTexto.getTexto();
                setHabilitarDibujarTexto(true);
                desHacerPila.push(texto);
                agregarTexto(texto);
            }else{
                setHabilitarDibujarTexto(false);
            }
        }
        if(modoDibujar == getBORRADOR()){
            // Se busca si existe el objeto seleccionado
            eliminarObjetoIndivial = dameFigura(evt);
            eliminarFigura(eliminarObjetoIndivial);
        }
        repaint();
    }//GEN-LAST:event_formMouseClicked


    ////////////////////////////////////////////////////////////////////////////
    // Metodos SETTERS y GETTERS
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Devuelve la coordenada final de x.
     *
     * @return La coordenada final de x
     * @since 1.6
     */
    public int getCoordenadasFinX() {
        return coordenadasFinX;
    }

    /**
     * Establece la coordenada final de x.
     *
     * @param coordenadasFinX La coordenada final de x
     * @since 1.6
     */
    public void setCoordenadasFinX(int coordenadasFinX) {
        if(coordenadasFinX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasFinX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
           this.coordenadasFinX = coordenadasFinX;
        }
    }

    /**
     * Devuelve la coordenada final de y.
     *
     * @return La coordenada final de y
     * @since 1.6
     */
    public int getCoordenadasFinY() {
        return coordenadasFinY;
    }

    /**
     * Establece la coordenada final de y.
     *
     * @param coordenadasFinY La coordenada final de y
     * @since 1.6
     */
    public void setCoordenadasFinY(int coordenadasFinY) {
        if(coordenadasFinY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasFinY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasFinY = coordenadasFinY;
        }
    }

    /**
     * Devuelve la coordenada inicial de x.
     *
     * @return La coordenada inicial de x
     * @since 1.6
     */
    public int getCoordenadasInicioX() {
        return coordenadasInicioX;
    }

    /**
     * Establece la coordenada inicial de x.
     *
     * @param coordenadasInicioX La coordenada inicial de x
     * @since 1.6
     */
    public void setCoordenadasInicioX(int coordenadasInicioX) {
        if(coordenadasInicioX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasInicioX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
            this.coordenadasInicioX = coordenadasInicioX;
        }
    }

    /**
     * Devuelve la coordenada inicial de y.
     *
     * @return La coordenada inicial de y
     * @since 1.6
     */
    public int getCoordenadasInicioY() {
        return coordenadasInicioY;
    }

    /**
     * Establece la coordenada inicial de y.
     *
     * @param coordenadasInicioY La coordenada inicial de y
     * @since 1.6
     */
    public void setCoordenadasInicioY(int coordenadasInicioY) {
        if(coordenadasInicioY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasInicioY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasInicioY = coordenadasInicioY;
        }
    }

    /**
     * Devuelve el color del borde.
     *
     * @return El color del borde
     * @since 1.6
     */
    public Color getColorBorde() {
        return colorBorde;
    }

    /**
     * Establece el color del borde.
     *
     * @param colorBorde El color del borde
     * @since 1.6
     */
    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    /**
     * Devuelve el color del fondo de pantalla del dibujo.
     *
     * @return El color del fondo de pantalla del dibujo
     * @since 1.6
     */
    public Color getColorFondoPantallaDibujo() {
        return colorFondoPantallaDibujo;
    }

    /**
     * Establece el color del fondo de pantalla del dibujo.
     *
     * @param colorFondoPantallaDibujo El color del fondo de pantalla del dibujo
     * @since 1.6
     */
    public void setColorFondoPantallaDibujo(Color colorFondoPantallaDibujo) {
        this.colorFondoPantallaDibujo = colorFondoPantallaDibujo;
    }

    /**
     * Devuelve el color de relleno.
     *
     * @return El color de relleno
     * @since 1.6
     */
    public Color getColorRelleno() {
        return colorRelleno;
    }

    /**
     * Establece el color de relleno.
     *
     * @param colorRelleno El color de relleno
     * @since 1.6
     */
    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    /**
     * Devuelve el tamanhio del borde.
     *
     * @return El tamanhio del borde
     * @since 1.6
     */
    public float getTamanioBorde() {
        return tamanioBorde;
    }

    /**
     * Establece el tamanhio del borde.
     *
     * @param tamanioBorde El tamanhio del borde
     * @since 1.6
     */
    public void setTamanioBorde(float tamanioBorde) {
        if(tamanioBorde >= Constantes.MINIMO_GROSOR_BORDE
                &&  tamanioBorde <= Constantes.MAXIMO_GROSOR_BORDE){
           this.tamanioBorde = tamanioBorde;
        }
    }

    /**
     * Devuelve la escala actual de la imagen.
     *
     * @return La escala actual de la imagen
     * @since 1.6
     */
    public double getEscala(){
        return escala;
    }

    /**
     * Establece la escala actual de la imagen.
     *
     * @param escala La escala actual de la imagen
     * @since 1.6
     */
    public void setEscala(double escala){
        if(imagen != null){
            double anteriorEscala = this.escala;

            this.escala = escala;
            this.firePropertyChange("escala", anteriorEscala, escala);
            repaint();
        }
    }

    /**
     * Devuelve el cursor actual.
     *
     * @return El cursor actual
     * @since 1.6
     */
    public Cursor getCursorActual() {
        return cursorActual;
    }

    /**
     * Establece el cursor actual.
     *
     * @param cursorActual El cursor actual
     * @since 1.6
     */
    public void setCursorActual(Cursor cursorActual) {
        this.cursorActual = cursorActual;
    }

    /**
     * Devuelve la ubicacion actual de la imagen dentro del panel.
     *
     * @return La ubicacion actual de la imagen dentro del panel
     * @since 1.6
     */
    public Point2D getUbicacionDeImagen(){
        return ubicacionDeImagen;
    }

    /**
     * Establece la ubicacion de la imagen dentro del panel.
     *
     * @param imageLocation La ubicacion actual de la imagen dentro del panel
     * @since 1.6
     */
    public void setUbicacionDeImagen(Point2D imageLocation){
        this.ubicacionDeImagen = imageLocation;
        repaint();              // Vuelve a dibujar la imagen en la ubicacion especificada
    }

    /**
     * Devuelve la coordenada inicial de x para el lapiz y pincel.
     *
     * @return La coordenada inicial de x para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaX1() {
        return lineaX1;
    }

    /**
     * Establece la coordenada inicial de x para el lapiz y pincel.
     *
     * @param lineaX1 La coordenada inicial de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX1(int lineaX1) {
        this.lineaX1 = lineaX1;
    }

    /**
     * Devuelve la coordenada final de x para el lapiz y pincel.
     *
     * @return La coordenada final de x para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaX2() {
        return lineaX2;
    }

    /**
     * Establece la coordenada final de x para el lapiz y pincel.
     *
     * @param lineaX2 La coordenada final de x para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaX2(int lineaX2) {
        this.lineaX2 = lineaX2;
    }

    /**
     * Devuelve la coordenada inicial de y para el lapiz y pincel.
     *
     * @return La coordenada inicial de y para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaY1() {
        return lineaY1;
    }

    /**
     * Establece la coordenada inicial de y para el lapiz y pincel.
     *
     * @param lineaY1 La coordenada inicial de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY1(int lineaY1) {
        this.lineaY1 = lineaY1;
    }

    /**
     * Devuelve la coordenada final de y para el lapiz y pincel.
     *
     * @return La coordenada final de y para el lapiz y pincel
     * @since 1.6
     */
    public int getLineaY2() {
        return lineaY2;
    }

    /**
     * Establece la coordenada final de y para el lapiz y pincel.
     *
     * @param lineaY2 La coordenada final de y para el lapiz y pincel
     * @since 1.6
     */
    public void setLineaY2(int lineaY2) {
        this.lineaY2 = lineaY2;
    }

    /**
     * Devuelve true si tiene relleno.
     *
     * @return True si tiene relleno
     * @since 1.6
     */
    public boolean isConRelleno() {
        return conRelleno;
    }

    /**
     * Establece true si tiene relleno, false sin relleno.
     *
     * @param conRelleno True si tiene relleno, false sin relleno
     * @since 1.6
     */
    public void setConRelleno(boolean conRelleno) {
        this.conRelleno = conRelleno;
    }

    /**
     * Devuelve el numero del modo a dibujar.
     *
     * @return El numero del modo a dibujar
     * @since 1.6
     */
    public int getModoDibujar() {
        return modoDibujar;
    }

    /**
     * Establece el numero del modo a dibujar.
     *
     * @param modoDibujar El numero del modo a dibujar
     * @since 1.6
     */
    public void setModoDibujar(int modoDibujar) {
        this.modoDibujar = modoDibujar;
    }

    /**
     * Devuelve la imagen actual.
     *
     * @return La imagen actual
     * @since 1.6
     */
    public BufferedImage getImagen(){
        return imagen;
    }

    /**
     * Establece la imagen actual.
     *
     * @param imagen La imagen actual
     * @since 1.6
     */
    public void setImagen(Image imagen){
        this.imagen = (BufferedImage) imagen;                   // Imagen actual
        setUbicacionDeImagen(null);                             // Se centra la imagen en el medio del panel
        repaint();                                              // Se dibuja la nueva imagen
    }

    /**
     * Asigna la imagen actual mediante un archivo.
     *
     * @param file El archivo de entrada para guardar la imagen.
     * @throws IOException Error del archivo de entrada para guardar la imagen.
     * @since 1.6
     */
    public void setImagen(File file) throws IOException{
        setImagen(ImageIO.read(file));
        repaint();                                              // Se dibuja la nueva imagen
    }

    /**
     * Devuelve el numero de la constante ARRASTRAR.
     *
     * @return El numero de la constante ARRASTRAR
     * @since 1.6
     */
    public static int getARRASTRAR() {
        return ARRASTRAR;
    }

    /**
     * Devuelve el numero de la constante CIRCULO.
     *
     * @return El numero de la constante CIRCULO
     * @since 1.6
     */
    public static int getCIRCULO() {
        return CIRCULO;
    }

    /**
     * Devuelve el numero de la constante LAPIZ.
     *
     * @return El numero de la constante LAPIZ
     * @since 1.6
     */
    public static int getLAPIZ() {
        return LAPIZ;
    }

    /**
     * Devuelve el numero de la constante LINEA.
     *
     * @return El numero de la constante LINEA
     * @since 1.6
     */
    public static int getLINEA() {
        return LINEA;
    }

    /**
     * Devuelve el numero de la constante OVALO.
     *
     * @return El numero de la constante OVALO
     * @since 1.6
     */
    public static int getOVALO() {
        return OVALO;
    }

    /**
     * Devuelve el numero de la constante PINCEL.
     *
     * @return El numero de la constante PINCEL
     * @since 1.6
     */
    public static int getPINCEL() {
        return PINCEL;
    }

    /**
     * Devuelve el numero de la constante RECTANGULO.
     *
     * @return El numero de la constante RECTANGULO
     * @since 1.6
     */
    public static int getRECTANGULO() {
        return RECTANGULO;
    }

    /**
     * Devuelve el numero de la constante RECTANGULO CON CURVAS REDONDAS.
     *
     * @return El numero de la constante RECTANGULO CON CURVAS REDONDAS
     * @since 1.6
     */
    public static int getRECTANGULO_CON_CURVAS_REDONDAS() {
        return RECTANGULO_CON_CURVAS_REDONDAS;
    }

    /**
     * Devuelve el numero de la constante TEXTO.
     *
     * @return El numero de la constante TEXTO
     * @since 1.6
     */
    public static int getTEXTO() {
        return TEXTO;
    }

    /**
     * Devuelve el numero de la constante BORRADOR.
     *
     * @return El numero de la constante BORRADOR
     * @since 1.6
     */
    public static int getBORRADOR() {
        return BORRADOR;
    }

    /**
     * Devuelve el numero de la longitud del borrador.
     *
     * @return El numero de la longitud del borrador
     * @since 1.6
     */
    public int getLongitudBorrador() {
        return longitudBorrador;
    }

    /**
     * Establece el numero de la longitud del borrador.
     *
     * @param longitudBorrador El numero de la longitud del borrador
     * @since 1.6
     */
    public void setLongitudBorrador(int longitudBorrador) {
        this.longitudBorrador = longitudBorrador;
    }

    /**
     * Devuelve para saber si se puede dibujar (true) o no (false) un texto.
     *
     * @return True cuando se puede dibujar un texto; caso contrario, false
     * @since 1.6
     */
    public boolean isHabilitarDibujarTexto() {
        return habilitarDibujarTexto;
    }

    /**
     * Establece en true para poder dibujar texto; false para no dibujar texto.
     *
     * @param habilitarDibujarTexto En true para poder dibujar texto; false para no dibujar texto
     * @since 1.6
     */
    public void setHabilitarDibujarTexto(boolean habilitarDibujarTexto) {
        this.habilitarDibujarTexto = habilitarDibujarTexto;
    }

    /**
     * Establece el nombre del archivo.
     *
     * @param nombreArchivo El nombre del archivo
     * @since 1.6
     */
    public void setNombreArchivo(File nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Devuelve para saber si se ha guardado la ultima version de la imagen(true),
     * caso contrario, en false.
     *
     * @return True cuando se ha guardado la última versión de la imagen; caso contrario, false
     * @since 1.6
     */
    public boolean isArchivoGuardadoUltimaVersion() {
        return archivoGuardadoUltimaVersion;
    }

    /**
     * Establece en True para guardar la ultima version de la imagen;
     * caso contrario, en false.
     *
     * @param archivoGuardadoUltimaVersion En true saber si se ha guardado la ultima version de la imagen(true), caso contrario, en false.
     * @since 1.6
     */
    public void setArchivoGuardadoUltimaVersion(boolean archivoGuardadoUltimaVersion) {
        this.archivoGuardadoUltimaVersion = archivoGuardadoUltimaVersion;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Añada una figura a la lista de figuras a dibujar.
     *
     * @param figura Una nueva figura a dibujar
     * @since 1.6
     */
    public void agregarFigura(Figura figura){
        listaFiguras.add(figura);
    }

    /**
     * Quita la figura en la lista de figuras a dibujar.
     *
     * @param figura La figura a quitar de la lista
     * @since 1.6
     */
    public void eliminarFigura(Figura figura){
        listaFiguras.remove(figura);
    }

    /**
     * Añada un objeto Texto a la lista de texto a dibujar.
     *
     * @param texto Una nuevo objeto Texto a dibujar
     * @since 1.6
     */
    public void agregarTexto(Texto texto){
        listaTexto.add(texto);
    }

    /**
     * Quita el texto en la lista de textos a dibujar.
     *
     * @param texto Un texto a quitar de la lista
     * @since 1.6
     */
    public void eliminarTexto(Texto texto){
        listaTexto.remove(texto);
    }

    /**
     * Para ver si el ratón está dentro del rectángulo.
     * Se utiliza para poder comenzar un arrastre o eliminar un objeto individual
     *
     * @param e El evento de ratón
     *
     * @return True si el ratón está dentro del rectángulo
     * @since 1.6
     */
    private Figura dameFigura(MouseEvent e){
        for (Figura figura : listaFiguras){
            if (figura.estaDentro(e.getX(), e.getY())){
                return figura;
            }
        }
        return null;
    }

    /**
     * Dibuja todos los componentes en la pantalla.
     *
     * @param g Graphics con el que dibujar
     * @since 1.6
     */

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        setBackground(getColorFondoPantallaDibujo());

        // Dibuja la imagen de fondo.
        if (getImagenFondo() != null){
            g2.drawImage(getImagenFondo(), 0, 0, this.getWidth(), this.getHeight(), null); 
        }
        
        if (getImagen() != null){
            Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
            if (getUbicacionDeImagen() != null){
                center = getUbicacionDeImagen();
            }
            Point2D loc = new Point2D.Double();
            double width = imagen.getWidth() * getEscala();
            double height = imagen.getHeight() * getEscala();
            loc.setLocation(center.getX() - width / 2, center.getY() - height / 2);
            setColorFondoPantallaDibujo(getColorFondoPantallaDibujo());
            g.drawImage(getImagen(), (int) loc.getX(), (int) loc.getY(),(int) width, (int) height, null); // centra la imagen
        }
        dibujarFiguras(g);
        dibujarTexto(g);
  
        this.setBackground(getColorFondoPantallaDibujo());
        g.setColor(getColorBorde());      

        if(modoDibujar != getARRASTRAR()){
            if (modoDibujar == getLINEA()) {
                Line2D line2D = new Line2D.Float(getCoordenadasInicioX(),
                    getCoordenadasInicioY(), getCoordenadasFinX(), getCoordenadasFinY());
                g2 = (Graphics2D)g;
                Stroke bordeFigura = new BasicStroke(getTamanioBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(line2D);
            }

            if (modoDibujar == getOVALO()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillOval(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                }
                Ellipse2D e2;
                Stroke bordeFigura;

                e2 = new Ellipse2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamanioBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(e2);
            }

            if (modoDibujar == getCIRCULO()){
                double radio = Math.sqrt(Math.pow(getCoordenadasFinX() - getCoordenadasInicioX(),2) +
                        Math.pow(getCoordenadasFinY() - getCoordenadasInicioY(),2));
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillOval(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                        (int)radio * 2 , (int)radio * 2);
                }
                Ellipse2D e2;
                Stroke bordeFigura;

                e2 = new Ellipse2D.Float(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamanioBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(e2);
            }

            if (modoDibujar == getRECTANGULO_CON_CURVAS_REDONDAS()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillRoundRect(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()), 25, 25);
                }
                RoundRectangle2D rr2;
                Stroke bordeFigura;
                rr2 = new RoundRectangle2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()), 25, 25);
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamanioBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(rr2);
            }


            if (modoDibujar == getRECTANGULO()){
                if(conRelleno){
                    g.setColor(getColorRelleno());
                    g.fillRect(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                            Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                            Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                            Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                }
                Rectangle2D r2;
                Stroke bordeFigura;
                r2 = new Rectangle2D.Float(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()));
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamanioBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorBorde());
                g2.setStroke(bordeFigura);
                g2.draw(r2);
            }

            if (modoDibujar == getTEXTO() && isHabilitarDibujarTexto()
                    && ventanaTexto.isDibujaTexto()){
                texto.setPosicionInicialX(coordenadasInicioX);
                texto.setPosicionInicialY(coordenadasInicioY);

                FontRenderContext contextoFuente = g2.getFontRenderContext();
                Font fuente = new Font(texto.getTipo(), texto.getEstilo(), texto.getTamanio());
                TextLayout layout = new TextLayout( texto.getContenidoTexto(), fuente, contextoFuente );
                g2.setColor( texto.getColor());
                layout.draw( g2, getCoordenadasInicioX(), getCoordenadasInicioY());
                setHabilitarDibujarTexto(false);
            }
        }
    }

    /**
     * Deshace una opcion que se realizo al dibujar en la mesa de trabajo.
     *
     * @since 1.6
     */
    public void deshacer(){
        modoDibujar = 0;
        if(desHacerPila.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ya no se puede deshacer",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            // Compara si el ultimo elemento de la Pila es de tipo Figura
            if(desHacerPila.lastElement() instanceof Figura){
                Figura objeto = (Figura) desHacerPila.pop();
                eliminarFigura(objeto);
            }else{
                Texto objeto = (Texto) desHacerPila.pop();
                eliminarTexto(objeto);
            }
        }
        repaint();
    }
   
    /**
     * Metodo que borra todos los elementos de la pantalla.
     * @since 1.6
     */
    public void borrarTodo(){
        listaFiguras.clear();
        listaTexto.clear();
        desHacerPila.clear();
        imagen = null;
        setModoDibujar(0);
        repaint();
    }

    /**
     * Devuelve true si se pudo guardar la imagen; false, en caso contrario.
     *
     * @return True si se pudo guardar la imagen; false, en caso contrario.
     * @since 1.6
     */
    public boolean guardarImagen(){
        if(nombreArchivo == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileSelectionMode(0);
            fileChooser.setFileFilter(new FiltroArchivo());
            int resultado = fileChooser.showSaveDialog(null);

            if(resultado == JFileChooser.CANCEL_OPTION) {
                return false;
            }
            nombreArchivo = fileChooser.getSelectedFile();

            if(nombreArchivo == null || nombreArchivo.getName().equals("")){
                JOptionPane.showMessageDialog(null,"Nombre de archivo inválido",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if(getImagen() == null){
                crearImagen();
            }
	}
        actualizarImagen();
        
        try{
            JOptionPane.showMessageDialog(null, "Archivo Guardado",
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO
                + Constantes.TITULO_PROGRAMA,
                JOptionPane.INFORMATION_MESSAGE);   
        }catch(Exception e){
             System.out.println(e.getMessage());
        }

        try {
            File file;
            // Compara si tiene una extension .png
            if(nombreArchivo.toString().endsWith(".png")){
                 file = new File(nombreArchivo.toString());
            }else{
                 file = new File(nombreArchivo.toString() + ".png");
            }
          if(imagen == null){
                crearImagen();
            }
            ImageIO.write(getImagen(), "png", file);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // vaciar las dos listas y el deshacer
        listaFiguras.clear();
        listaTexto.clear();
        desHacerPila.clear();

        archivoGuardadoUltimaVersion = true;
        setModoDibujar(NULO);
        
        //setEscala(1.0);
	repaint();
        return true;
    }

    /**
     * Abre la imagen con formato png.
     *
     * @since 1.6
     */
    public void abrirImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION){
            return;
        }  

        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo != null){
            try{
                borrarTodo();
                BufferedImage image = ImageIO.read(nombreArchivo);
                imagen = ImageIO.read(nombreArchivo);
                setImagen(nombreArchivo);

                Graphics g = image.getGraphics();
                setColorFondoPantallaDibujo(Color.WHITE);
                g.drawImage(image, 0, 0, this);
            }catch(Exception exp){
                JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        archivoGuardadoUltimaVersion = true;
        repaint();
    }
    
    /**
     * Inserta una imagen de fondo.
     *
     * @since 1.6
     */
    public void insertarImagenFondo(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION){
            return;
        }  

        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo != null){
            try{
                BufferedImage image = ImageIO.read(nombreArchivo);
                imagenFondo = ImageIO.read(nombreArchivo);
                setImagenFondo(nombreArchivo);

                Graphics g = image.getGraphics();
                setColorFondoPantallaDibujo(Color.WHITE);
                
                /* 
                 * Dibuja la imagen que se inserta.
                 * En este caso seria una imagen de fondo.
                 */ 
                g.drawImage(image, 0, 0, this); 
            }catch(Exception exp){
                JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                        "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                        Constantes.TITULO_PROGRAMA, JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        archivoGuardadoUltimaVersion = true;
        repaint();
    }
    
    /**
     * Establece una imagen de fondo.
     *
     * @param imagen La imagen de fondo.
     * @since 1.6
     */
    public void setImagenFondo(Image imagenFondo){
        this.imagenFondo = (BufferedImage)imagenFondo;          // Imagen Fondo.
        repaint();                                              // Se dibuja la imagen insertada.
    }
    
    /**
     * Asigna la imagen de fondo mediante un archivo.
     *
     * @param file El archivo de entrada para guardar la imagen de fondo.
     * @throws IOException Error del archivo de entrada para guardar la imagen de fondo.
     * @since 1.6
     */
    public void setImagenFondo(File file) throws IOException{
        setImagenFondo(ImageIO.read(file));
        repaint();                                              // Se dibuja la nueva imagen.
    }

    /**
     * Devuelve la imagen de fondo.
     *
     * @return La imagen de fondo.
     * @since 1.6
     */
    public BufferedImage getImagenFondo(){
        return imagenFondo;
    }

    /**
     * Metodo que crea la imagen.
     * @since 1.6
     */
    public void crearImagen() {
        imagen = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = imagen.createGraphics();
        g2.setColor(getColorFondoPantallaDibujo());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        // Dibuja la imagen de fondo.
        if (getImagenFondo() != null){
            // Dibuja la imagenFondo.
            g2.drawImage(getImagenFondo(), 0, 0, this.getWidth(), this.getHeight(), null); 
        }
        
        dibujarFiguras(g2);
        dibujarTexto(g2);
        setImagen(imagen);
        g2.dispose();
        setImagen(imagen);
    }
    
    /**
     * Metodo publico que crea una imagen temporal de la imagen creada
     * en el panelDibujo.
     * @return 
     */
    public String crearImagenTemporal(){
        // Creamos una imagen temporal.
        File imagenTemporal = null; 
        
        try {
            imagenTemporal = File.createTempFile("ImagenTemporal.png", null);
            
            // Se crea la ultima instancia de lo dibujado en el PanelDibujo.
            crearImagen();
            
            ImageIO.write(getImagen(), "png", imagenTemporal);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return imagenTemporal.getAbsolutePath();
    }

    /**
     * Metodo para acercar la imagen actual.
     * @since 1.6
     */
    public void acercar(){
        setEscala(getEscala() * 1.09);
    }

    /**
     * Metodo para alejar la imagen actual.
     * @since 1.6
     */
    public void alejar(){
        setEscala(getEscala() * 0.9174311926605505);
    }

    /**
     * Dibuja todas las figuras de la lista.
     *
     * @param g Dibuja todas las figuras de la lista
     * @since 1.6
     */
    public void dibujarFiguras(Graphics g){
        for (Figura figura : listaFiguras){
            figura.dibujar(g);
        }
    }

    /**
     * Dibuja todos los objetos texto de la lista.
     *
     * @param g Dibuja todos los objetos texto de la lista
     * @since 1.6
     */
    public void dibujarTexto(Graphics g){
        for (Texto texto_temp : listaTexto){
            texto_temp.dibujar(g);
        }
    }


    /**
     * Actualiza la imagen actual con la imagen nueva.
     *
     * @since 1.6
     */
    public void actualizarImagen(){
        if(getImagen() != null){
            Graphics g = getImagen().getGraphics();
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(getColorFondoPantallaDibujo());
            g2.drawImage(getImagen(), 0, 0, null);
            dibujarFiguras(g2);
            dibujarTexto(g2);
            setImagen(getImagen());
            g2.dispose();
        }
    }

    /**
     * Este es el metodo que se encarga de la impresion.
     *
     * @param g El contexto en el que se dibuja la página
     * @param pageFormat El tamaño y la orientación de la página
     * @param pageIndex El indice de la pagina
     * 
     * @return Si la página se representa con éxito PAGE_EXISTS o si pageIndex se
     *         especifica una página que no existe NO_SUCH_PAGE.
     * @throws PrinterException Se lanza cuando termina la impresion
     * @since 1.6
     */
    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if( pageIndex >= 1 ) {
            return( Printable.NO_SUCH_PAGE );
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.translate( pageFormat.getImageableX(), pageFormat.getImageableY() );
        paint(g2);
        return( Printable.PAGE_EXISTS );
    }

    /**
     * Metodo privado que muestra la ventana Texto.
     * @since 1.6
     */
    private void mostrarVentanaTexto() {
        if (ventanaTexto == null) {
            ventanaTexto = new VentanaTexto(null, true);
            ventanaTexto.setLocationRelativeTo(this);
        }
        ventanaTexto.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}