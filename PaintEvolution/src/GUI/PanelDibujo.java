/*
 * PanelDibujo.java
 *
 * Created on 18/03/2010, 09:50:38 AM
 */

package GUI;

import Auxiliar.Constantes;
import Auxiliar.FiltroArchivo;
import Auxiliar.Text;
import figurasGeometricas.InterfaceFigura;
import figurasGeometricas.Rectangulo;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import paint.Coordinate;
import paint.StepInfo;

/**
 *
 * @author fires
 */
public class PanelDibujo extends javax.swing.JPanel implements Serializable, Printable{
    // Constantes
    private final static int LINE = 1, SQUARE = 2, OVAL = 3, POLYGON = 4,
            ROUND_RECT = 5, FREE_HAND = 6, SOLID_SQUARE = 22, SOLID_OVAL = 33, 
            SOLID_POLYGON = 44, SOLID_ROUND_RECT = 55, ERASER = 60, CIRCULO = 70,
            SOLID_CIRCULO = 71, TEXT = 72;

    // Objetos
    private static Vector vectorLinea, vectorRectangulo, vectorOvalo, vectorPolygono,
            vectorRoundRect, vectorManoAlzada,vectorRellenoRectangulo, vectorRellenoOvalo,
            vectorRellenoPolygono, vectorRellenoRoundRect, vectorFile, vectorCirculo,
            vectorRellenoCirculo, vectorTexto;
    private BufferedImage imagen;


    /**
     * Lista de figuras a dibujar.
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     */
    private LinkedList<InterfaceFigura> listaFiguras = new LinkedList<InterfaceFigura>();

    // La imagen que se mostrará
    private Image imagenActual;
    private Cursor cursorActual;
    private Text ventanaTexto;                           // Ventana Texto
    private int tipoFuente;
    private int estiloFuente;
    private int tamañoFuente;
    private String texto;
    private boolean objetoTexto;

    private Stack desHacerPila, reHacerPila;
    private File nombreArchivo;
    private Color colorFondoPantallaDibujo;
    private Color colorSeleccion;
    private int coordenadasInicioX, coordenadasFinX;
    private int coordenadasInicioY, coordenadasFinY;
    private int linex1, linex2, liney1, liney2, drawMode;
    private int drawModeAnterior;
    private boolean relleno;
    private float tamañoBorde;
    private boolean initialEraser;
    private int longitudBorrador;
    GraphicsEnvironment ge;
    GraphicsDevice gd;

    // La ubicacion donde se colocará la imagen, si es null, se colocará en el centro
    private Point2D ubicacionDeImagen;

    // Escala por defecto de la imagen
    private double escala = 1.0;


    /** Creates new form PanelDibujo */
    public PanelDibujo() {
        nombreArchivo = null;
        ventanaTexto = null;
        imagen = null;
        objetoTexto = false;
        texto = "prueba1";
        relleno = false;
        tamañoBorde = 1.0f;
        imagenActual = null;
        cursorActual = null;
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();

        initialEraser = true;

        drawMode = 0;
        drawModeAnterior = 0;
        longitudBorrador = 5;
                
        vectorLinea                 = new Vector();
	vectorRectangulo            = new Vector();
	vectorOvalo                 = new Vector();
	vectorPolygono              = new Vector();
	vectorRoundRect             = new Vector();
	vectorManoAlzada            = new Vector();
	vectorRellenoRectangulo     = new Vector();
	vectorRellenoOvalo          = new Vector();
	vectorRellenoPolygono       = new Vector();
	vectorRellenoRoundRect      = new Vector();
	vectorFile                  = new Vector();
        vectorCirculo               = new Vector();
        vectorRellenoCirculo        = new Vector();
        vectorTexto                 = new Vector();

        colorFondoPantallaDibujo    = Color.WHITE;          // De color blanco
        colorSeleccion              = Color.BLACK;          // De color negro


        desHacerPila = new Stack();
	reHacerPila = new Stack();
        initComponents();
        this.setBackground(colorFondoPantallaDibujo);
        jFrameColor.pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrameColor = new javax.swing.JFrame();
        jColorChooserSeleccionarColor = new javax.swing.JColorChooser();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        jFrameColor.getContentPane().setLayout(null);
        jFrameColor.getContentPane().add(jColorChooserSeleccionarColor);
        jColorChooserSeleccionarColor.setBounds(0, 0, 429, 337);

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setToolTipText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jFrameColor.getContentPane().add(jButtonAceptar);
        jButtonAceptar.setBounds(0, 0, 71, 23);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jFrameColor.getContentPane().add(jButtonCancelar);
        jButtonCancelar.setBounds(0, 0, 75, 23);

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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setCoordenadasInicioX(evt.getX());
        setLinex1(evt.getX());
        setLinex2(evt.getX());
        setCoordenadasInicioY(evt.getY());
        setLiney1(evt.getY());
        setLiney2(evt.getY());
        GUI_principal.jLabelCoordenadasPuntero.setText("x:" + evt.getX() +
                "   y:" + evt.getY());
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        setCoordenadasFinX(evt.getX());
        setCoordenadasFinY(evt.getY());
                  
        // Mano alzada
        if (drawMode == PanelDibujo.FREE_HAND) {
            drawModeAnterior = drawMode;
            setLinex1(getLinex2());
            setLiney1(getLiney2());
            setLinex2(getCoordenadasFinX());
            setLiney2(getCoordenadasFinY()); 
               
            vectorManoAlzada.add(new Coordinate(getLinex1(), getLiney1(), getLinex2(),
                    getLiney2(), getColorSeleccion(), getTamañoBorde()));
            desHacerPila.push(new StepInfo(FREE_HAND, new Coordinate(getLinex1(),
                    getLiney1(), getLinex2(), getLiney2(), getColorSeleccion(),
                    getTamañoBorde())));
        }
        if(drawMode == PanelDibujo.ERASER ){
            eraserOperation(evt);
        }
        GUI_principal.jLabelCoordenadasPuntero.setText("x:" + evt.getX() +
                "   y:" + evt.getY());
        repaint();
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(final java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (drawMode == PanelDibujo.LINE){
            vectorLinea.add(new Coordinate(getCoordenadasInicioX(), getCoordenadasInicioY(),
                    evt.getX(), evt.getY(), getColorSeleccion(), getTamañoBorde()));

            desHacerPila.push(new StepInfo(LINE ,new Coordinate(getCoordenadasInicioX(), 
                    getCoordenadasInicioY(), evt.getX(), evt.getY(), getColorSeleccion(),
                    getTamañoBorde())));
        }

        if (drawMode == PanelDibujo.SQUARE){
            drawModeAnterior = drawMode;

            addFigura(new Rectangulo(Math.min(getCoordenadasInicioX(), getCoordenadasFinX()),
                        Math.min(getCoordenadasInicioY(), getCoordenadasFinY()),
                        Math.abs(getCoordenadasInicioX() - getCoordenadasFinX()),
                        Math.abs(getCoordenadasInicioY() - getCoordenadasFinY()), 
                        colorSeleccion, (int)tamañoBorde));
            
            /*
            if(relleno){
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoRectangulo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_SQUARE,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                } // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoRectangulo.add(new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(), 
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_SQUARE,
                            new Coordinate(evt.getX(), evt.getY(), getCoordenadasInicioX(),
                            getCoordenadasInicioY(), getColorSeleccion(),
                            getTamañoBorde())));
           	}// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRellenoRectangulo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_SQUARE,
                            new Coordinate(getCoordenadasInicioX(), getCoordenadasInicioY(),
                            evt.getX(), evt.getY(), getColorSeleccion(),
                            getTamañoBorde())));
           	}// Dibuja en el cuarto cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRellenoRectangulo.add(new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_SQUARE,
                            new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde())));
                }
            }else{
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRectangulo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SQUARE,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                } // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRectangulo.add(new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(), 
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SQUARE,
                            new Coordinate(evt.getX(), evt.getY(), getCoordenadasInicioX(),
                            getCoordenadasInicioY(), getColorSeleccion(),
                            getTamañoBorde())));
           	}// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRectangulo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SQUARE,
                            new Coordinate(getCoordenadasInicioX(), getCoordenadasInicioY(),
                            evt.getX(), evt.getY(), getColorSeleccion(),
                            getTamañoBorde())));
           	}// Dibuja en el cuarto cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRectangulo.add(new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SQUARE,
                            new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde())));
                }
           }
             *
             */
        }
        
        if (drawMode == PanelDibujo.OVAL){
            drawModeAnterior = drawMode;
            if(relleno){
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoOvalo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(), 
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_OVAL, 
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(), 
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoOvalo.add(new Coordinate(evt.getX(), evt.getY(), 
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_OVAL, new Coordinate(evt.getX(), 
                            evt.getY(), getCoordenadasInicioX(),
                            getCoordenadasInicioY(), getColorSeleccion(), getTamañoBorde())));
                }// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() >  evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRellenoOvalo.add(new Coordinate(evt.getX(), getCoordenadasInicioY(), 
                            getCoordenadasInicioX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_OVAL, 
                            new Coordinate(evt.getX(), getCoordenadasInicioY(), 
                            getCoordenadasInicioX(), evt.getY(), 
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el cuarto cuadrante
                else if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorRellenoOvalo.add(new Coordinate(getCoordenadasInicioX(), 
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_OVAL,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
            }else{
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorOvalo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(OVAL,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorOvalo.add(new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(OVAL, new Coordinate(evt.getX(),
                            evt.getY(), getCoordenadasInicioX(),
                            getCoordenadasInicioY(), getColorSeleccion(),
                            getTamañoBorde())));
                }// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() >  evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorOvalo.add(new Coordinate(evt.getX(), getCoordenadasInicioY(),
                            getCoordenadasInicioX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(OVAL,
                            new Coordinate(evt.getX(), getCoordenadasInicioY(),
                            getCoordenadasInicioX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el cuarto cuadrante
                else if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() < evt.getY()){
                    vectorOvalo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(OVAL,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
           }
        }

        if (drawMode == PanelDibujo.CIRCULO){
            drawModeAnterior = drawMode;
            if(relleno){
                vectorRellenoCirculo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_CIRCULO,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
            }else{
                vectorCirculo.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(CIRCULO,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
           }
        }

        if (drawMode == PanelDibujo.ROUND_RECT){
            drawModeAnterior = drawMode;
            if(relleno){
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoRoundRect.add(new Coordinate(getCoordenadasInicioX(), 
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.add(new StepInfo(SOLID_ROUND_RECT, 
                            new Coordinate(getCoordenadasInicioX(), 
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRellenoRoundRect.add(new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.add(new StepInfo(SOLID_ROUND_RECT, 
                            new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde())));
          	}// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() < getCoordenadasFinX() && getCoordenadasInicioY() < getCoordenadasFinY()){
                    vectorRellenoRoundRect.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_ROUND_RECT, 
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }// Dibuja en el cuarto cuadrante
               else if(getCoordenadasInicioX() > getCoordenadasFinX() && getCoordenadasInicioY() < getCoordenadasFinY()){
                    vectorRellenoRoundRect.add(new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(SOLID_ROUND_RECT, 
                            new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde())));
               }
           }else{
                // Dibuja en el primer cuadrante
                if(getCoordenadasInicioX() < evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRoundRect.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.add(new StepInfo(ROUND_RECT,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }
                // Dibuja en el segundo cuadrante
                else if(getCoordenadasInicioX() > evt.getX() && getCoordenadasInicioY() > evt.getY()){
                    vectorRoundRect.add(new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.add(new StepInfo(ROUND_RECT,
                            new Coordinate(evt.getX(), evt.getY(),
                            getCoordenadasInicioX(), getCoordenadasInicioY(),
                            getColorSeleccion(), getTamañoBorde())));
          	}// Dibuja en el tercer cuadrante
                else if(getCoordenadasInicioX() < getCoordenadasFinX() && getCoordenadasInicioY() < getCoordenadasFinY()){
                    vectorRoundRect.add(new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(ROUND_RECT,
                            new Coordinate(getCoordenadasInicioX(),
                            getCoordenadasInicioY(), evt.getX(), evt.getY(),
                            getColorSeleccion(), getTamañoBorde())));
                }// Dibuja en el cuarto cuadrante
               else if(getCoordenadasInicioX() > getCoordenadasFinX() && getCoordenadasInicioY() < getCoordenadasFinY()){
                    vectorRoundRect.add(new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde()));
                    desHacerPila.push(new StepInfo(ROUND_RECT,
                            new Coordinate(evt.getX(),
                            getCoordenadasInicioY(), getCoordenadasInicioX(),
                            evt.getY(), getColorSeleccion(), getTamañoBorde())));
               }
           }
        }

        if(drawMode == PanelDibujo.ERASER ){
            releasedEraser();
        }
   //     setCoordenadasInicioX(0);
      //  setLinex1(0);
     //   setCoordenadasFinX(0);
   //     setLinex2(0);

     //   setCoordenadasInicioY(0);
     //   setLiney1(0);
   //     setCoordenadasFinY(0);
   //     setLiney2(0);
 
        //coordenadasInicioX=linex1=coordenadasFinX=linex2=0;
        //coordenadasInicioY=liney1=coordenadasFinY=liney2=0;
        GUI_principal.jLabelCoordenadasPuntero.setText("x:" + evt.getX() +
                "   y:" + evt.getY());
    }//GEN-LAST:event_formMouseReleased

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        setColorSeleccion(jColorChooserSeleccionarColor.getColor());
        jFrameColor.setVisible(false);
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        jFrameColor.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
       GUI_principal.jLabelCoordenadasPuntero.setText("x:" + evt.getX() +
                "   y:" + evt.getY());
    }//GEN-LAST:event_formMouseMoved

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        GUI_principal.jLabelCoordenadasPuntero.setText("");
    }//GEN-LAST:event_formMouseExited

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setCursor(getCursorActual());
    }//GEN-LAST:event_formMouseEntered

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if(esObjetoTexto()){
            System.out.println("1- paso");
            mostrarVentanaTexto();
            System.out.println("2- paso");
            this.setTexto(ventanaTexto.getTexto());
            System.out.println("4- paso");
            setObjetoTexto(false);
        }
    }//GEN-LAST:event_formMouseClicked

    ////////////////////////////////////////////////////////////////////////////
    // Metodos SETTERS y GETTERS
    ////////////////////////////////////////////////////////////////////////////
    public float getTamañoBorde() {
        return tamañoBorde;
    }

    /*----------------------------------------------------------------------------*/
    public void setTamañoBorde(float tamañoBorde) {
        if(tamañoBorde >= Constantes.MINIMO_GROSOR_BORDE
                &&  tamañoBorde <= Constantes.MAXIMO_GROSOR_BORDE){
           this.tamañoBorde = tamañoBorde;
        }
    }

    /*----------------------------------------------------------------------------*/
    public Color getColorSeleccion() {
        return colorSeleccion;
    }

    /*----------------------------------------------------------------------------*/
    public void setColorSeleccion(Color colorSeleccion) {
        this.colorSeleccion = colorSeleccion;
    }

    /*----------------------------------------------------------------------------*/
    public Color getColorFondoPantallaDibujo() {
        return colorFondoPantallaDibujo;
    }

    /*----------------------------------------------------------------------------*/
    public void setColorFondoPantallaDibujo(Color colorFondoPantallaDibujo) {
        this.colorFondoPantallaDibujo = colorFondoPantallaDibujo;
    }

    /*----------------------------------------------------------------------------*/
    public int getCoordenadasFinX() {
        return coordenadasFinX;
    }

    /*----------------------------------------------------------------------------*/
    public void setCoordenadasFinX(int coordenadasFinX) {
        if(coordenadasFinX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasFinX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
           this.coordenadasFinX = coordenadasFinX;
        }
    }

    /*----------------------------------------------------------------------------*/
    public int getCoordenadasFinY() {
        return coordenadasFinY;
    }

    /*----------------------------------------------------------------------------*/
    public void setCoordenadasFinY(int coordenadasFinY) {
        if(coordenadasFinY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasFinY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasFinY = coordenadasFinY;
        }
    }

    /*----------------------------------------------------------------------------*/
    public int getCoordenadasInicioX() {
        return coordenadasInicioX;
    }

    /*----------------------------------------------------------------------------*/
    public void setCoordenadasInicioX(int coordenadasInicioX) {
        if(coordenadasInicioX >= Constantes.MINIMO_LARGO_PANTALLA_DIBUJO
                && coordenadasInicioX <= Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO){
            this.coordenadasInicioX = coordenadasInicioX;
        }
    }

    /*----------------------------------------------------------------------------*/
    public int getCoordenadasInicioY() {
        return coordenadasInicioY;
    }

    /*----------------------------------------------------------------------------*/
    public void setCoordenadasInicioY(int coordenadasInicioY) {
        if(coordenadasInicioY >= Constantes.MINIMO_ANCHO_PANTALLA_DIBUJO
                && coordenadasInicioY <= Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO){
            this.coordenadasInicioY = coordenadasInicioY;
        }
    }

    /*----------------------------------------------------------------------------*/
    public int getLinex1() {
        return linex1;
    }

    /*----------------------------------------------------------------------------*/
    public void setLinex1(int linex1) {
        this.linex1 = linex1;
    }

    /*----------------------------------------------------------------------------*/
    public int getLinex2() {
        return linex2;
    }

    /*----------------------------------------------------------------------------*/
    public void setLinex2(int linex2) {
        this.linex2 = linex2;
    }

    /*----------------------------------------------------------------------------*/
    public int getLiney1() {
        return liney1;
    }

    /*----------------------------------------------------------------------------*/
    public void setLiney1(int liney1) {
        this.liney1 = liney1;
    }

    /*----------------------------------------------------------------------------*/
    public int getLiney2() {
        return liney2;
    }

    /*----------------------------------------------------------------------------*/
    public void setLiney2(int liney2) {
        this.liney2 = liney2;
    }

    /*----------------------------------------------------------------------------*/
    public void setDrawMode(int mode){
        drawMode = mode;
    }

    /*----------------------------------------------------------------------------*/
    public int getDrawMode(){
        return drawMode;
    }

    /*----------------------------------------------------------------------------*/
    public void setRelleno(Boolean inSolidMode){
        relleno = inSolidMode.booleanValue();
    }

    /*----------------------------------------------------------------------------*/
    public Boolean getRelleno(){
        return Boolean.valueOf(relleno);
    }

    /*----------------------------------------------------------------------------*/
    public static int getFREE_HAND() {
        return FREE_HAND;
    }

    /*----------------------------------------------------------------------------*/
    public static int getLINE() {
        return LINE;
    }

    /*----------------------------------------------------------------------------*/
    public static int getOVAL() {
        return OVAL;
    }

    /*----------------------------------------------------------------------------*/
    public static int getPOLYGON() {
        return POLYGON;
    }

    /*----------------------------------------------------------------------------*/
    public static int getROUND_RECT() {
        return ROUND_RECT;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSOLID_OVAL() {
        return SOLID_OVAL;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSOLID_POLYGON() {
        return SOLID_POLYGON;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSOLID_ROUND_RECT() {
        return SOLID_ROUND_RECT;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSOLID_SQUARE() {
        return SOLID_SQUARE;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSQUARE() {
        return SQUARE;
    }

    /*----------------------------------------------------------------------------*/
    public static int getCIRCULO() {
        return CIRCULO;
    }

    /*----------------------------------------------------------------------------*/
    public static int getSOLID_CIRCULO() {
        return SOLID_CIRCULO;
    }

    /*----------------------------------------------------------------------------*/
    // Retorna la imagen actual
    public Image getImagenActual(){
        return imagenActual;
    }

    /*----------------------------------------------------------------------------*/
    // Asigna la imagen actual
    public void setImagenActual(Image imagenActual){
        //Image old = this.getImagenActual();                     // Imagen anterior
        this.imagenActual = imagenActual;                       // Imagen actual
        setUbicacionDeImagen(null);                             // Se centra la imagen en el medio del panel
        redimensionar();                                        // Se escala la imagen para que quepe en el panel
        repaint();                                              // Se dibuja la nueva imagen
        //firePropertyChange("imagenActual", old, this.imagenActual);    // Se dispara un evento de cambio de propiedad
    }
    
    /*----------------------------------------------------------------------------*/
    // Asigna la imagen actual mediante un archivo
    public void setImagenActual(File file) throws IOException{
        setImagenActual(ImageIO.read(file));
    }

    /*----------------------------------------------------------------------------*/
    public Cursor getCursorActual() {
        return cursorActual;
    }

    /*----------------------------------------------------------------------------*/
    public void setCursorActual(Cursor cursorActual) {
        this.cursorActual = cursorActual;
    }

    /*----------------------------------------------------------------------------*/
    public int getEstiloFuente() {
        return estiloFuente;
    }

    /*----------------------------------------------------------------------------*/
    public void setEstiloFuente(int estiloFuente) {
        this.estiloFuente = estiloFuente;
    }

    /*----------------------------------------------------------------------------*/
    public int getTipoFuente() {
        return tipoFuente;
    }

    /*----------------------------------------------------------------------------*/
    public void setTipoFuente(int tipoFuente) {
        this.tipoFuente = tipoFuente;
    }

    /*----------------------------------------------------------------------------*/
    public int getTamañoFuente() {
        return tamañoFuente;
    }

    /*----------------------------------------------------------------------------*/
    public void setTamañoFuente(int tamañoFuente) {
        this.tamañoFuente = tamañoFuente;
    }

    /*----------------------------------------------------------------------------*/
    public String getTexto() {
        return texto;
    }

    /*----------------------------------------------------------------------------*/
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /*----------------------------------------------------------------------------*/
    public boolean esObjetoTexto(){
        return objetoTexto;
    }

    /*----------------------------------------------------------------------------*/
    public void setObjetoTexto(boolean objetoTexto){
        this.objetoTexto = objetoTexto;
    }

    /*----------------------------------------------------------------------------*/
    // Redimensiona la imagen para que quepe dentro del panel, si la imagen es mas pequeña
    // que el panel, la deja en su escala por defecto
    public void redimensionar(){
        if(imagen.getHeight(this) > tamañoVisor().getHeight()){
            double visor = tamañoVisor().getHeight();
            double imagen = this.imagen.getHeight(this);
            double escala = visor/imagen;// calculo de escala
            setEscala(escala);
        }else{
            setEscala(1.0);
        }
    }

    /*----------------------------------------------------------------------------*/
    // Retorna el tamaño del visor de imagenes
    private Dimension tamañoVisor(){
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int largo = d.width - 10;
        int ancho = d.height - 188;
        d.setSize(largo, ancho);
        return d;
    }

    /*----------------------------------------------------------------------------*/
    // Retorna la ubicacion actual de la imagen dentro del panel
    public Point2D getUbicacionDeImagen(){
        return ubicacionDeImagen;
    }

    /*----------------------------------------------------------------------------*/
    // Asigna la ubicacion de la imagen dentro del panel
    public void setUbicacionDeImagen(Point2D imageLocation){
        this.ubicacionDeImagen = imageLocation;
        repaint();              // Vuelve a dibujar la imagen en la ubicacion especificada
    }

    /*----------------------------------------------------------------------------*/
    // Retorna la escala actual de la imagen
    public double getEscala(){
        return escala;
    }

    /**
     * Añada una figura a la lista de figuras a dibujar
     *
     * @param figura Una nueva figura a dibujar
     *
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     */
    public void addFigura(InterfaceFigura figura){
        listaFiguras.add(figura);
    }

     /**
     * Quita la figura en la lista de figuras a dibujar.
     *
     * @param figura figura a quitar de la lista.
     * sitio: http://www.chuidiang.com/
     * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
     * Mi licencia (fires): BSD
     */
    public void removeFigura(InterfaceFigura figura){
        listaFiguras.remove(figura);
    }


    /*----------------------------------------------------------------------------*/
    // Asigna la escala actual de la imagen
    public void setEscala(double escala){
        if(getImagenActual() != null){
            //double anteriorEscala = this.escala;
        
            this.escala = escala;
           // this.firePropertyChange("escala", anteriorEscala, escala);
            repaint();
        }
    }

    /*----------------------------------------------------------------------------*/
    @Override
    // Sobreescritura del metodo paintComponent
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        if (getImagenActual() != null){
            Point2D center = new Point2D.Double(getWidth() / 2, getHeight() / 2);
            if (getUbicacionDeImagen() != null){
                center = getUbicacionDeImagen();
            }
            Point2D loc = new Point2D.Double();
            double width = getImagenActual().getWidth(null) * getEscala();
            double height = getImagenActual().getHeight(null) * getEscala();
            loc.setLocation(center.getX() - width / 2, center.getY() - height / 2);
            setColorFondoPantallaDibujo(getColorFondoPantallaDibujo());
            g.drawImage(getImagenActual(), (int) loc.getX(), (int) loc.getY(),(int) width, (int) height, null);
        }

       /**
        * sitio: http://www.chuidiang.com/
        * licencia original de Chuidiang: Esta obra está bajo una licencia de Creative Commons.
        * Mi licencia (fires): BSD
        */
        for (InterfaceFigura figura : listaFiguras){
            figura.dibujate(g);
        }


        /*
        if(imagen != null){
            setColorFondoPantallaDibujo(Color.WHITE);
            g2.drawImage(imagen, 0, 0, this);

            BufferedImage mImagen;

            Dimension d = getSize();
            int ancho = d.width, largo = d.height;
            mImagen = new BufferedImage(ancho, largo, BufferedImage.TYPE_INT_RGB);
            Graphics2D gOculta = mImagen.createGraphics();
            gOculta.drawImage(imagen, 0, 0, this);
            gOculta.setStroke(new BasicStroke(1.5f));
            gOculta.setPaint(Color.green);
            for (int i = -32; i < 40; i += 8) {
                gOculta.drawLine(i, i, ancho - i * 2, largo - i * 2);
                gOculta.rotate(0.05f);
            }
            g2.drawImage(mImagen, 0, 0, this);
        }
 *
 */

      	redrawVectorBuffer(g);
        this.setBackground(colorFondoPantallaDibujo);
        g.setColor(getColorSeleccion());

        
      	if (drawMode == LINE) {
            Line2D line2D = new Line2D.Float(getCoordenadasInicioX(),
                    getCoordenadasInicioY(), getCoordenadasFinX(), getCoordenadasFinY());
            g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorSeleccion());
            g2.setStroke(bordeFigura);
            g2.draw(line2D); 
      	}

      	if (drawMode == OVAL){
            if(relleno){
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
            bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorSeleccion());
            g2.setStroke(bordeFigura);
            g2.draw(e2);
        }

        if (drawMode == CIRCULO){
            double radio = Math.sqrt(Math.pow(getCoordenadasFinX() - getCoordenadasInicioX(),2) +
                        Math.pow(getCoordenadasFinY() - getCoordenadasInicioY(),2));
            if(relleno){
                 g.fillOval(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
                }
                Ellipse2D e2;
                Stroke bordeFigura;

                e2 = new Ellipse2D.Float(getCoordenadasInicioX() - (int)radio, getCoordenadasInicioY() - (int)radio,
                    (int)radio * 2 , (int)radio * 2);
                g2 = (Graphics2D)g;
                bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
                g2.setColor(getColorSeleccion());
                g2.setStroke(bordeFigura);
                g2.draw(e2);
        }

        if (drawMode == ROUND_RECT){
            if(relleno){
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
            bordeFigura = new BasicStroke(getTamañoBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorSeleccion());
            g2.setStroke(bordeFigura);
            g2.draw(rr2);
      	}

/*
      	if (drawMode == SQUARE){
            if(relleno){
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
            bordeFigura = new BasicStroke(getTamañoBorde(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorSeleccion());
            g2.setStroke(bordeFigura);
            g2.draw(r2);
      	}
 * 
 */

        if (drawMode == TEXT){
            System.out.println("7- paso");
            g2 = (Graphics2D)g;    
            g2.drawString(this.getTexto(), getCoordenadasInicioX(), getCoordenadasInicioY());
      	}


      	if (drawMode == FREE_HAND){
            Line2D line2D = new Line2D.Float(getLinex1(), getLiney1(), getLinex2(), getLiney2());
            g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(getTamañoBorde(),  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(getColorSeleccion());
            g2.setStroke(bordeFigura);
            g2.draw(line2D);
      	}
    }

    /*----------------------------------------------------------------------------*/
    private void redrawVectorBuffer(Graphics g){
        for (int i = 0; i < vectorManoAlzada.size(); i++){
            Line2D line2D = new Line2D.Float(((Coordinate)vectorManoAlzada.elementAt(i)).getX1(),
                ((Coordinate)vectorManoAlzada.elementAt(i)).getY1(),
                ((Coordinate)vectorManoAlzada.elementAt(i)).getX2(),
                ((Coordinate)vectorManoAlzada.elementAt(i)).getY2());
            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorManoAlzada.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorManoAlzada.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(line2D);
        }
       
        for (int i = 0; i < vectorLinea.size(); i++){
            Line2D line2D = new Line2D.Float(((Coordinate)vectorLinea.elementAt(i)).getX1(),
                    ((Coordinate)vectorLinea.elementAt(i)).getY1(),
                    ((Coordinate)vectorLinea.elementAt(i)).getX2(),
                    ((Coordinate)vectorLinea.elementAt(i)).getY2());
            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorLinea.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorLinea.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(line2D);
        }

	for (int i = 0; i < vectorOvalo.size(); i++){
            Ellipse2D e2 = new Ellipse2D.Float((Math.min((((Coordinate)vectorOvalo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorOvalo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorOvalo.elementAt(i)).getY1()),
                    (((Coordinate)vectorOvalo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorOvalo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorOvalo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorOvalo.elementAt(i)).getY1() -
                    ((Coordinate)vectorOvalo.elementAt(i)).getY2())));

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorOvalo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorOvalo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(e2);
      	}

        for (int i = 0; i < vectorCirculo.size(); i++){
            double radio = Math.sqrt(Math.pow(((Coordinate)vectorCirculo.elementAt(i)).getX2()
                    - ((Coordinate)vectorCirculo.elementAt(i)).getX1(), 2) +
                        Math.pow(((Coordinate)vectorCirculo.elementAt(i)).getY2()
                    - ((Coordinate)vectorCirculo.elementAt(i)).getY1(), 2));
            Ellipse2D e2 = new Ellipse2D.Float(((Coordinate)vectorCirculo.elementAt(i)).getX1()
                    - (int)radio, ((Coordinate)vectorCirculo.elementAt(i)).getY1()
                    - (int)radio, (int)radio * 2 , (int)radio * 2);

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorCirculo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorCirculo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(e2);
      	}

      	for (int i = 0; i < vectorRoundRect.size(); i++){
            RoundRectangle2D rr2 = new RoundRectangle2D.Float((Math.min((((Coordinate)vectorRoundRect.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRoundRect.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRoundRect.elementAt(i)).getY1()),
                    (((Coordinate)vectorRoundRect.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRoundRect.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRoundRect.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRoundRect.elementAt(i)).getY1() -
                    ((Coordinate)vectorRoundRect.elementAt(i)).getY2())), 25, 25);

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRoundRect.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRoundRect.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(rr2);
      	}

      	for (int i = 0; i < vectorRellenoOvalo.size(); i++){
            g.setColor(((Coordinate)vectorRellenoOvalo.elementAt(i)).colour());
            g.fillOval((Math.min((((Coordinate)vectorRellenoOvalo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRellenoOvalo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRellenoOvalo.elementAt(i)).getY1()),
                    (((Coordinate)vectorRellenoOvalo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRellenoOvalo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRellenoOvalo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRellenoOvalo.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoOvalo.elementAt(i)).getY2())));
            Ellipse2D e2 = new Ellipse2D.Float((Math.min((((Coordinate)vectorRellenoOvalo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRellenoOvalo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRellenoOvalo.elementAt(i)).getY1()),
                    (((Coordinate)vectorRellenoOvalo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRellenoOvalo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRellenoOvalo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRellenoOvalo.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoOvalo.elementAt(i)).getY2())));
            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRellenoOvalo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRellenoOvalo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(e2);
      	}

        for (int i = 0; i < vectorRellenoCirculo.size(); i++){
            double radio = Math.sqrt(Math.pow(((Coordinate)vectorRellenoCirculo.elementAt(i)).getX2()
                    - ((Coordinate)vectorRellenoCirculo.elementAt(i)).getX1(), 2) +
                        Math.pow(((Coordinate)vectorRellenoCirculo.elementAt(i)).getY2()
                    - ((Coordinate)vectorRellenoCirculo.elementAt(i)).getY1(), 2));
            g.setColor(((Coordinate)vectorRellenoCirculo.elementAt(i)).colour());
            g.fillOval(((Coordinate)vectorRellenoCirculo.elementAt(i)).getX1()
                    - (int)radio, ((Coordinate)vectorRellenoCirculo.elementAt(i)).getY1()
                    - (int)radio, (int)radio * 2 , (int)radio * 2);
            Ellipse2D e2 = new Ellipse2D.Float(((Coordinate)vectorRellenoCirculo.elementAt(i)).getX1()
                    - (int)radio, ((Coordinate)vectorRellenoCirculo.elementAt(i)).getY1()
                    - (int)radio, (int)radio * 2 , (int)radio * 2);

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRellenoCirculo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRellenoCirculo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(e2);
      	}

      	for (int i = 0; i < vectorRellenoRoundRect.size(); i++){
            g.setColor(((Coordinate)vectorRellenoRoundRect.elementAt(i)).colour());

            // Primer Cuadrante
            g.fillRoundRect(((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY2(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX2() -
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY2(), 25, 25);

            // Segundo, Tercer y Cuarto Cuadrante
            g.fillRoundRect(((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY1(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX2() -
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1(),
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY2() -
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY1(), 25, 25);

            // Primer, Segundo, Tercer y Cuarto Cuadrante
            RoundRectangle2D rr2 = new RoundRectangle2D.Float((Math.min((((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY1()),
                    (((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRellenoRoundRect.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoRoundRect.elementAt(i)).getY2())), 25, 25);
            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRellenoRoundRect.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRellenoRoundRect.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(rr2);
      	}

      	for (int i = 0; i < vectorRectangulo.size(); i++){
            Rectangle2D r2 = new Rectangle2D.Float((Math.min((((Coordinate)vectorRectangulo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRectangulo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRectangulo.elementAt(i)).getY1()),
                    (((Coordinate)vectorRectangulo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRectangulo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRectangulo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRectangulo.elementAt(i)).getY1() -
                    ((Coordinate)vectorRectangulo.elementAt(i)).getY2())));

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRectangulo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRectangulo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(r2);
      	}

      	for (int i = 0; i < vectorRellenoRectangulo.size(); i++){
            g.setColor(((Coordinate)vectorRellenoRectangulo.elementAt(i)).colour());
            g.fillRect((Math.min((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY1()),
                    (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY2())));

            Rectangle2D r2 = new Rectangle2D.Float((Math.min((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX1()
                    ), (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX2()))),
                    (Math.min((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY1()),
                    (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY2()))),
                    (Math.abs((((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX1()) -
                    (((Coordinate)vectorRellenoRectangulo.elementAt(i)).getX2()))),
                    (Math.abs(((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY1() -
                    ((Coordinate)vectorRellenoRectangulo.elementAt(i)).getY2())));

            Graphics2D g2 = (Graphics2D)g;
            Stroke bordeFigura = new BasicStroke(((Coordinate)vectorRellenoRectangulo.elementAt(i)).getTamañoBorde(),
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
            g2.setColor(((Coordinate)vectorRellenoRectangulo.elementAt(i)).colour());
            g2.setStroke(bordeFigura);
            g2.draw(r2);
      	}
    }

    /*----------------------------------------------------------------------------*/
    public void deshacer(){
        StepInfo tempInfo;

        if(desHacerPila.isEmpty())
            JOptionPane.showMessageDialog(null, "Ya no se puede deshacer",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);
        else{
            tempInfo = (StepInfo)desHacerPila.pop();

            switch(tempInfo.getStepType()){
                case 1:	vectorLinea.remove(vectorLinea.size() - 1);
                    break;
                case 2:	vectorRectangulo.remove(vectorRectangulo.size() - 1);
                    break;
                case 3:	vectorOvalo.remove(vectorOvalo.size() - 1);
                    break;
                case 4:	vectorPolygono.remove(vectorPolygono.size()-1);
                    break;
                case 5:	vectorRoundRect.remove(vectorRoundRect.size() - 1);
                    break;
                case 6:	vectorManoAlzada.remove(vectorManoAlzada.size() - 1);
                    break;
                case 22: vectorRellenoRectangulo.remove(vectorRellenoRectangulo.size() - 1);
                    break;
                case 33: vectorRellenoOvalo.remove(vectorRellenoOvalo.size() - 1);
                    break;
                case 44: vectorRellenoPolygono.remove(vectorRellenoPolygono.size() - 1);
                    break;
                case 55: vectorRellenoRoundRect.remove(vectorRellenoRoundRect.size() - 1);
                    break;
            }
            reHacerPila.push(tempInfo);
        }
        repaint();
    }

    /*----------------------------------------------------------------------------*/
    public void rehacer(){
        StepInfo tempInfo;

        if(reHacerPila.isEmpty())
            JOptionPane.showMessageDialog(null,"No hay algo que rehacer",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.INFORMATION_MESSAGE);
        else{
            tempInfo = (StepInfo)reHacerPila.pop();

            switch(tempInfo.getStepType()){
                case 1:	vectorLinea.add(tempInfo.getStepCoordinate());
                    break;
                case 2:	vectorRectangulo.add(tempInfo.getStepCoordinate());
                    break;
                case 3:	vectorOvalo.add(tempInfo.getStepCoordinate());
                    break;
                case 4:	vectorPolygono.add(tempInfo.getStepCoordinate());
                    break;
                case 5:	vectorRoundRect.add(tempInfo.getStepCoordinate());
                    break;
                case 6:	vectorManoAlzada.add(tempInfo.getStepCoordinate());
                    break;
                case 22: vectorRellenoRectangulo.add(tempInfo.getStepCoordinate());
                    break;
                case 33: vectorOvalo.add(tempInfo.getStepCoordinate());
                    break;
                case 44: vectorRellenoPolygono.add(tempInfo.getStepCoordinate());
                    break;
                case 55: vectorRellenoRoundRect.add(tempInfo.getStepCoordinate());
                    break;
            }
            desHacerPila.push(tempInfo);
        }
        repaint();
    }

    /*----------------------------------------------------------------------------*/
    // Metodo que borra todos los elementos de la pantalla
    public void borrarTodo(){
        vectorManoAlzada.removeAllElements();
        vectorLinea.removeAllElements();
        vectorOvalo.removeAllElements();
        vectorPolygono.removeAllElements();
        vectorRoundRect.removeAllElements();
        vectorRellenoOvalo.removeAllElements();
        vectorRellenoPolygono.removeAllElements();
        vectorRellenoRoundRect.removeAllElements();
        vectorRellenoRectangulo.removeAllElements();
        vectorRectangulo.removeAllElements();
        vectorCirculo.removeAllElements();
        vectorRellenoCirculo.removeAllElements();
        desHacerPila.clear();
        reHacerPila.clear();
        imagenActual = null;
        
        GUI_principal.jToggleButtonBorrarIndividual.doClick();
        //drawMode = drawModeAnterior;

        repaint();
    }

    /*----------------------------------------------------------------------------*/
    /*
        Method will emulate a eraser graphic.
        By drawing a filled rectangle of background color,
        with the current mouse coordinates being the center
        of the rectangle. This is done until the mouse has
        been release from dragmode
    */
    public void eraserOperation(MouseEvent e){
        Graphics g  = this.getGraphics();

        /*
          In initial state setup default values
          for mouse coordinates
        */
        if (initialEraser){
           setGraphicalDefaults(e);
           initialEraser = false;
           g.setColor(Color.white);
           g.fillRect(coordenadasFinX - longitudBorrador, coordenadasFinY
                   - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);
           g.setColor(Color.black);
           g.drawRect(coordenadasFinX - longitudBorrador, coordenadasFinY
                   - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);
           coordenadasInicioX = coordenadasFinX;
           coordenadasInicioY = coordenadasFinY;
        }



        /*
          Make sure that the mouse has actually
          moved from its previous position.
        */
        if (mouseHasMoved(e)){

           g.setColor(Color.white);
           g.drawRect(coordenadasInicioX - longitudBorrador, coordenadasInicioY - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);

           /* Get current mouse coordinates */
           coordenadasFinX  = e.getX();
           coordenadasFinY  = e.getY();

           /* Draw eraser block to panel */
           g.setColor(Color.white);
           g.fillRect(coordenadasFinX - longitudBorrador, coordenadasFinY - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);
           g.setColor(Color.black);
           g.drawRect(coordenadasFinX - longitudBorrador, coordenadasFinY - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);
           coordenadasInicioX = coordenadasFinX;
           coordenadasInicioY = coordenadasFinY;
        }
    }

    /*----------------------------------------------------------------------------*/
    /*
        Method is invoked when mouse has been released
        and current operation is Eraser
    */
    public void releasedEraser(){
        initialEraser = true;
        Graphics g  = this.getGraphics();
        g.setColor(Color.white);
        g.drawRect(coordenadasFinX - longitudBorrador, coordenadasFinY - longitudBorrador, longitudBorrador * 2, longitudBorrador * 2);
 }

    /*----------------------------------------------------------------------------*/
     /*
        Method determines weather the mouse has moved
        from its last recorded position.
        If mouse has deviated from previous position,
        the value returned will be true, otherwise
        the value that is returned will be false.
    */
    public boolean mouseHasMoved(MouseEvent e){
        return (coordenadasFinX != e.getX() || coordenadasFinY != e.getY());
    }

    /*----------------------------------------------------------------------------*/
    /*
        Method sets all the drawing varibles to the default
        state which is the current position of the mouse cursor
        Also the height and width varibles are zeroed off.
     */
    public void setGraphicalDefaults(MouseEvent e){
        coordenadasInicioX   = e.getX();
        coordenadasInicioY   = e.getY();
        coordenadasFinX      = e.getX();
        coordenadasFinY      = e.getY();
    }
    
    /*----------------------------------------------------------------------------*/
    public void guardarImagen(){
        if(nombreArchivo != null){
            vectorFile.removeAllElements();
            vectorFile.addElement(vectorManoAlzada);
            vectorFile.addElement(vectorLinea);
            vectorFile.addElement(vectorOvalo);
            vectorFile.addElement(vectorPolygono);
            vectorFile.addElement(vectorRoundRect);
            vectorFile.addElement(vectorRellenoOvalo);
            vectorFile.addElement(vectorRellenoPolygono);
            vectorFile.addElement(vectorRellenoRoundRect);
            vectorFile.addElement(vectorRellenoRectangulo);
            vectorFile.addElement(vectorRectangulo);
            vectorFile.addElement(new Color(getColorSeleccion().getRGB()));

            RenderedImage rendImage = crearImagen();
            
            try{
                JOptionPane.showMessageDialog(null,"Archivo guardado",
                        Constantes.TITULO_PROGRAMA,
                        JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

            try{
                File file = new File(nombreArchivo.toString() + ".png");
                ImageIO.write(rendImage, "png", file);
            }catch (IOException e) {
                 System.out.println(e.getMessage());
            }
	}
	else{
            guardarComoImagen();
	}
	repaint();
    }

    /*----------------------------------------------------------------------------*/
    public void guardarComoImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileSelectionMode(0);
        fileChooser.setFileFilter(new FiltroArchivo());
        int resultado = fileChooser.showSaveDialog(null);
        
        if(resultado == JFileChooser.CANCEL_OPTION)
            return;
        nombreArchivo = fileChooser.getSelectedFile();

        if(nombreArchivo == null || nombreArchivo.getName().equals(""))
            JOptionPane.showMessageDialog(null,"Nombre de archivo inválido",
                    Constantes.TITULO_PROGRAMA,
                    JOptionPane.ERROR_MESSAGE);
        else{
            vectorFile.removeAllElements();
            vectorFile.addElement(vectorManoAlzada);
            vectorFile.addElement(vectorLinea);
            vectorFile.addElement(vectorOvalo);
            vectorFile.addElement(vectorPolygono);
            vectorFile.addElement(vectorRoundRect);
            vectorFile.addElement(vectorRellenoOvalo);
            vectorFile.addElement(vectorRellenoPolygono);
            vectorFile.addElement(vectorRellenoRoundRect);
            vectorFile.addElement(vectorRellenoRectangulo);
            vectorFile.addElement(vectorRectangulo);
            vectorFile.addElement(new Color(getColorSeleccion().getRGB()));

            RenderedImage rendImagen = crearImagen();

            try{
                JOptionPane.showMessageDialog(null,"Archivo Guardado",Constantes.TITULO_PROGRAMA,
                        JOptionPane.INFORMATION_MESSAGE);

            }catch(Exception e){
                System.out.println(e.getMessage());
            }

             try {
                File file = new File(nombreArchivo.toString() + ".png");
                ImageIO.write(rendImagen, "png", file);
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
	repaint();
    }

    /*----------------------------------------------------------------------------*/
    public void abrirImagen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(new FiltroArchivo());
        

        int result = fileChooser.showOpenDialog(null);
        if(result == JFileChooser.CANCEL_OPTION)
            return;

        nombreArchivo = fileChooser.getSelectedFile();
        
        if(nombreArchivo != null){
            try{
                /*
                FileInputStream fis = new FileInputStream(nombreArchivo);
                ObjectInputStream ois = new ObjectInputStream(fis);
                vectorFile = (Vector) ois.readObject();

                this.borrarTodo();
                vectorManoAlzada            = (Vector)vectorFile.elementAt(0);
                vectorLinea                 = (Vector)vectorFile.elementAt(1);
                vectorOvalo                 = (Vector)vectorFile.elementAt(2);
                vectorPolygono              = (Vector)vectorFile.elementAt(3);
                vectorRoundRect             = (Vector)vectorFile.elementAt(4);
                vectorRellenoOvalo          = (Vector)vectorFile.elementAt(5);
                vectorRellenoPolygono       = (Vector)vectorFile.elementAt(6);
                vectorRellenoRoundRect      = (Vector)vectorFile.elementAt(7);
                vectorRellenoRectangulo     = (Vector)vectorFile.elementAt(8);
                vectorRectangulo            = (Vector)vectorFile.elementAt(9);
                setColorSeleccion((Color)vectorFile.elementAt(10));
                * 
                */

                BufferedImage image = ImageIO.read(nombreArchivo);
                imagen = ImageIO.read(nombreArchivo);
                setImagenActual(nombreArchivo);

                //this.borrarTodo();
                Graphics g = image.getGraphics();
                setColorFondoPantallaDibujo(Color.WHITE);
                g.drawImage(image, 0, 0, this);
                //repaint();
            }catch(Exception exp){
                JOptionPane.showMessageDialog(null,"No se puede abrir el archivo",
                        Constantes.TITULO_PROGRAMA,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            nombreArchivo = null;
	}
        repaint();
    }

    /*----------------------------------------------------------------------------*/
    // Metodo que crea la imagen
    public RenderedImage crearImagen() {
        // Variables
        int largo = Constantes.MAXIMO_LARGO_PANTALLA_DIBUJO;
        int ancho = Constantes.MAXIMO_ANCHO_PANTALLA_DIBUJO;

        // Objetos
        imagen = new BufferedImage(largo, ancho, BufferedImage.TYPE_INT_RGB);

        Graphics g = imagen.createGraphics();
        g.setColor(colorFondoPantallaDibujo);
        g.fillRect(0, 0, largo, ancho);
        redrawVectorBuffer(g);
        g.dispose();
        return imagen;
        //return imagenActual;
    }

    /*----------------------------------------------------------------------------*/
    // Metodo para acerca la Imagen Actual
    public void acercar(){
        setEscala(getEscala() * 1.09);
    }

    /*----------------------------------------------------------------------------*/
    // Metodo para alejar la Imagen Actual
    public void alejar(){
        setEscala(getEscala() * 0.9174311926605505);
    }

    /*----------------------------------------------------------------------------*/
    // Este es el metodo que se encarga de la impresion
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if( pageIndex >= 1 ) {
            return( Printable.NO_SUCH_PAGE );
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.translate( pageFormat.getImageableX(), pageFormat.getImageableY() );
        paint( g2 );
        return( Printable.PAGE_EXISTS );
    }

    /*----------------------------------------------------------------------------*/
    // Metodo que muestra la ventana Texto
    public void mostrarVentanaTexto() {
        if (ventanaTexto == null) {
            ventanaTexto = new Text();
            //ventanaTexto.setTexto(getTexto());
            ventanaTexto.setLocationRelativeTo(this);
        }
        ventanaTexto.setVisible(true);
    }

    /*----------------------------------------------------------------------------*/
    /*----------------------------------------------------------------------------*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JColorChooser jColorChooserSeleccionarColor;
    private javax.swing.JFrame jFrameColor;
    // End of variables declaration//GEN-END:variables
}