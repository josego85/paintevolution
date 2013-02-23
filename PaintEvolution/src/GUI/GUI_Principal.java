package GUI;

import Auxiliar.Constantes;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *@(#)GUI_Principal.java
 * Creado 22/07/2010, 10:14:37 PM
 *
 * @author fires
 * @version 1.00
 * @since 1.6
 */
/**
 * Clase GUI_Principal se ejecuta el programa y donde aparecen todos los objetos
 * Menus, mesa de trabajo, botones, barra de herramientas, etc.
 * @since 1.6
 */
public class GUI_Principal extends javax.swing.JFrame {
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Obtenemos la referencia al entorno de ventanas.
     * @since 1.6
     */
    private GraphicsEnvironment gEnv;

    /**
     * Obtenemos el dispositivo gráfico.
     * @since 1.6
     */
    private GraphicsDevice gd;


    /**
     * Mesa de Dibujo donde se dibujan todos los objetos que crea el usuario.
     * @since 1.6
     */
    private PanelDibujo mesaDeDibujo;
   
    /**
     * Ventana Ayuda.
     *
     * @since 1.6
     */
    private VentanaAyuda ventanaAyuda;

    /**
     * El color del borde de los objetos.
     *
     * @since 1.6
     */
    boolean colorBordeObjetos ;

    /**
     * El color de fondo de la pantalla.
     *
     * @since 1.6
     */
    boolean colorDeFondoPantalla;

    /**
     * El color de relleno.
     *
     * @since 1.6
     */
    boolean colorRelleno;

    /**
     * Valor boleano para saber si es la ultima version del archivo generado.
     *
     * @since 1.6
     */
    boolean archivoGuardadoUltimaVersion;

    /**
     * valor boleano para saber si la imagen sin guardar se ha modificado.
     *
     * @since 1.6
     */
    boolean modificarImagenSinGuardar;


    /**
     * Cursor predeterminado.
     * @since 1.6
     */
    private Cursor cursorPredeterminado;

    /**
     * Cursor actual.
     * @since 1.6
     */
    private Cursor cursorActual;

    /**
     * FALTA COMENTAR
     *
     * @since 1.6
     */
    private Toolkit toolKit;

    /**
     * Imagen para el cursor pencil.
     * @since 1.6
     */
     private Image pencilImg;

    /**
     * Imagen para el cursor eraser.
     * @since 1.6
     */
     private Image eraserImg;

    /**
     * Imagen para el cursor brush.
     * @since 1.6
     */
     private Image brushImg;

     /*
      * El icono de la aplicacion.
      * @since 1.6
      */
     private Image iconoAplicacion;
     
     /*
      * Ventana Opciones Imagen.
      */
     private VentanaOpcionesImagen ventanaOpcionesImagen;


    ////////////////////////////////////////////////////////////////////////////
    // Constructores
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Construye la GUI Principal.
     * @since 1.6
     */
    public GUI_Principal() {
        // Se elije el tema por defecto de la aplicación
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch(Exception e){
            // Look and Feel predeterminado de Java
            UIManager.getCrossPlatformLookAndFeelClassName();
        }
        modificarImagenSinGuardar = false;
        colorBordeObjetos = false;
        colorDeFondoPantalla = false;
        colorRelleno = false;
        archivoGuardadoUltimaVersion = false;

        gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();

        // Obtenemos el dispositivo gráfico primario (por defecto)
        gd = gEnv.getDefaultScreenDevice();

        initComponents();

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
       
        // Cambiar el titulo de la aplicacion
        setTitle(Constantes.TITULO_PROGRAMA);

        // Maximiza la aplicacion
        setExtendedState(GUI_Principal.MAXIMIZED_BOTH);
        
        // Crea el objeto de Mesa de Dibujo
        mesaDeDibujo = new PanelDibujo();

        ventanaAyuda = null;

        jLabelHerramientaSeleccionadaObjeto.setText("Lápiz");

        // Establece un esquema para la mesa de dibujo y agrega a la ventana
        // principal
        getContentPane().add(mesaDeDibujo, java.awt.BorderLayout.CENTER);

        // El boton Desactivar Pantalla Completa tiene que estar oculto
        jButtonDesactivarPantallaCompleta.setVisible(false);

        toolKit = Toolkit.getDefaultToolkit();
        iconoAplicacion = toolKit.getImage(getClass().getResource("/imagenes/iconos/paintEvolution.png"));
        this.setIconImage(iconoAplicacion);
        pencilImg = toolKit.getImage(getClass().getResource("/imagenes/cursores/pencil.gif"));
        eraserImg = toolKit.getImage(getClass().getResource("/imagenes/cursores/eraserSmall.gif"));
        brushImg = toolKit.getImage(getClass().getResource("/imagenes/cursores/brush.gif"));
        cursorPredeterminado =  toolKit.createCustomCursor(pencilImg, new Point(10,24),"Pencil Cursor");
        cursorActual = cursorPredeterminado;
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setModoDibujar(PanelDibujo.getLAPIZ());       // Lapiz como objeto predeterminado
        activarPropiedadesObjetos();
        //pack();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogColor = new javax.swing.JDialog();
        jColorChooserColores = new javax.swing.JColorChooser();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        buttonGroupObjetos = new javax.swing.ButtonGroup();
        jPanelBarraAccesoDirecto = new javax.swing.JPanel();
        jButtonNuevo = new javax.swing.JButton();
        jButtonAbrir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();
        jButtonDeshacer = new javax.swing.JButton();
        jButtonZoomIn = new javax.swing.JButton();
        jButtonZoomOut = new javax.swing.JButton();
        jButtonPantallaCompleta = new javax.swing.JButton();
        jButtonDesactivarPantallaCompleta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanelBarraDeHerramientas = new javax.swing.JPanel();
        jToolBarBarraDeHerramientas = new javax.swing.JToolBar();
        jPanel1 = new javax.swing.JPanel();
        jToggleButtonPincel = new javax.swing.JToggleButton();
        jToggleButtonLapiz = new javax.swing.JToggleButton();
        jToggleButtonLinea = new javax.swing.JToggleButton();
        jToggleButtonOvalo = new javax.swing.JToggleButton();
        jToggleButtonCirculo = new javax.swing.JToggleButton();
        jToggleButtonRectangulo = new javax.swing.JToggleButton();
        jToggleButtonRectanguloConCurvasRedondas = new javax.swing.JToggleButton();
        jToggleButtonTexto = new javax.swing.JToggleButton();
        jToggleButtonBorrador = new javax.swing.JToggleButton();
        jButtonBorraTodoLosObjetos = new javax.swing.JButton();
        jButtonColorDeFondoPantalla = new javax.swing.JButton();
        jButtonColorBordeObjetos = new javax.swing.JButton();
        jButtonColorRelleno = new javax.swing.JButton();
        jCheckBoxActivarRelleno = new javax.swing.JCheckBox();
        jLabelTamanioGrosor = new javax.swing.JLabel();
        jComboBoxGrosorBorde = new javax.swing.JComboBox();
        jToggleButtonArrastrarObjetos = new javax.swing.JToggleButton();
        jToggleInfoBorrado = new javax.swing.JToggleButton();
        jPanelInformacionExtra = new javax.swing.JPanel();
        jLabelCoordenadasPuntero = new javax.swing.JLabel();
        jLabelHerramientaSeleccionada = new javax.swing.JLabel();
        jLabelHerramientaSeleccionadaObjeto = new javax.swing.JLabel();
        jMenuBarArchivo = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemCerrar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jMenuItemGuardarComo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemImprimir = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItemDeshacer = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItemZoomIn = new javax.swing.JMenuItem();
        jMenuItemZoomOut = new javax.swing.JMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemIndice = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAcercaDe = new javax.swing.JMenuItem();

        jDialogColor.setTitle("Colores");
        jDialogColor.setMinimumSize(new java.awt.Dimension(450, 450));
        jDialogColor.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        jDialogColor.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        jDialogColor.setResizable(false);

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setToolTipText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setToolTipText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogColorLayout = new javax.swing.GroupLayout(jDialogColor.getContentPane());
        jDialogColor.getContentPane().setLayout(jDialogColorLayout);
        jDialogColorLayout.setHorizontalGroup(
            jDialogColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogColorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jDialogColorLayout.createSequentialGroup()
                        .addComponent(jButtonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jColorChooserColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialogColorLayout.setVerticalGroup(
            jDialogColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogColorLayout.createSequentialGroup()
                .addComponent(jColorChooserColores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialogColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar)))
        );

        jDialogColor.getAccessibleContext().setAccessibleParent(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelBarraAccesoDirecto.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/documentoNuevo_32px.png"))); // NOI18N
        jButtonNuevo.setText("Nuevo");
        jButtonNuevo.setToolTipText("Nuevo");
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonNuevo);

        jButtonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/documentoAbrir_32px.png"))); // NOI18N
        jButtonAbrir.setText("Abrir");
        jButtonAbrir.setToolTipText("Abrir");
        jButtonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonAbrir);

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/guardar_32px.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setToolTipText("Guardar");
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonGuardar);

        jButtonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/impresora_32px.png"))); // NOI18N
        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.setToolTipText("Imprimir");
        jButtonImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonImprimir);

        jButtonDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/desHacer_32px.png"))); // NOI18N
        jButtonDeshacer.setText("Deshacer");
        jButtonDeshacer.setToolTipText("Deshacer");
        jButtonDeshacer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDeshacer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeshacerActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonDeshacer);

        jButtonZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/zoom_in_32px.png"))); // NOI18N
        jButtonZoomIn.setText("Zoom In");
        jButtonZoomIn.setToolTipText("Zoom In");
        jButtonZoomIn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonZoomIn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZoomInActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonZoomIn);

        jButtonZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/zoom_out_32px.png"))); // NOI18N
        jButtonZoomOut.setText("Zoom Out");
        jButtonZoomOut.setToolTipText("Zoom Out");
        jButtonZoomOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonZoomOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZoomOutActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonZoomOut);

        jButtonPantallaCompleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/pantallaCompleta_32px.png"))); // NOI18N
        jButtonPantallaCompleta.setText("Pantalla Completa");
        jButtonPantallaCompleta.setToolTipText("Pantalla Completa");
        jButtonPantallaCompleta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPantallaCompleta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPantallaCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPantallaCompletaActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonPantallaCompleta);

        jButtonDesactivarPantallaCompleta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/AccesosDirectos/DesactivarPantallaCompleta_32px.png"))); // NOI18N
        jButtonDesactivarPantallaCompleta.setText("Desactivar Pantalla Completa");
        jButtonDesactivarPantallaCompleta.setToolTipText("Desactivar Pantalla Completa");
        jButtonDesactivarPantallaCompleta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDesactivarPantallaCompleta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonDesactivarPantallaCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDesactivarPantallaCompletaActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButtonDesactivarPantallaCompleta);

        jButton1.setText("Opciones Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelBarraAccesoDirecto.add(jButton1);

        getContentPane().add(jPanelBarraAccesoDirecto, java.awt.BorderLayout.NORTH);

        jPanelBarraDeHerramientas.setLayout(new javax.swing.BoxLayout(jPanelBarraDeHerramientas, javax.swing.BoxLayout.PAGE_AXIS));

        jToolBarBarraDeHerramientas.setRollover(true);

        jPanel1.setLayout(new java.awt.GridLayout(9, 2));

        buttonGroupObjetos.add(jToggleButtonPincel);
        jToggleButtonPincel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/brush_16_16.gif"))); // NOI18N
        jToggleButtonPincel.setToolTipText("Pincel");
        jToggleButtonPincel.setFocusable(false);
        jToggleButtonPincel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPincel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonPincel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPincelActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonPincel);

        buttonGroupObjetos.add(jToggleButtonLapiz);
        jToggleButtonLapiz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/pencil.png"))); // NOI18N
        jToggleButtonLapiz.setToolTipText("Lapiz");
        jToggleButtonLapiz.setFocusable(false);
        jToggleButtonLapiz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonLapiz.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonLapiz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLapizActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonLapiz);

        buttonGroupObjetos.add(jToggleButtonLinea);
        jToggleButtonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/linea.png"))); // NOI18N
        jToggleButtonLinea.setToolTipText("Linea");
        jToggleButtonLinea.setFocusable(false);
        jToggleButtonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLineaActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonLinea);

        buttonGroupObjetos.add(jToggleButtonOvalo);
        jToggleButtonOvalo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/ellipse.png"))); // NOI18N
        jToggleButtonOvalo.setToolTipText("Ovalo");
        jToggleButtonOvalo.setFocusable(false);
        jToggleButtonOvalo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonOvalo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonOvalo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonOvaloActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonOvalo);

        buttonGroupObjetos.add(jToggleButtonCirculo);
        jToggleButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/circulo_16px.png"))); // NOI18N
        jToggleButtonCirculo.setToolTipText("Círculo");
        jToggleButtonCirculo.setFocusable(false);
        jToggleButtonCirculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonCirculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCirculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCirculoActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonCirculo);

        buttonGroupObjetos.add(jToggleButtonRectangulo);
        jToggleButtonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/rectangulo.png"))); // NOI18N
        jToggleButtonRectangulo.setToolTipText("Rectangulo");
        jToggleButtonRectangulo.setFocusable(false);
        jToggleButtonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectangulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRectanguloActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonRectangulo);

        buttonGroupObjetos.add(jToggleButtonRectanguloConCurvasRedondas);
        jToggleButtonRectanguloConCurvasRedondas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/14_roundrect.png"))); // NOI18N
        jToggleButtonRectanguloConCurvasRedondas.setToolTipText("Rectangulo con curvas redondeadas");
        jToggleButtonRectanguloConCurvasRedondas.setFocusable(false);
        jToggleButtonRectanguloConCurvasRedondas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectanguloConCurvasRedondas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectanguloConCurvasRedondas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRectanguloConCurvasRedondasActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonRectanguloConCurvasRedondas);

        buttonGroupObjetos.add(jToggleButtonTexto);
        jToggleButtonTexto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/texto_16px.png"))); // NOI18N
        jToggleButtonTexto.setToolTipText("Texto");
        jToggleButtonTexto.setFocusable(false);
        jToggleButtonTexto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonTexto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonTextoActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonTexto);

        buttonGroupObjetos.add(jToggleButtonBorrador);
        jToggleButtonBorrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/eraser_16_16.gif"))); // NOI18N
        jToggleButtonBorrador.setToolTipText("Borrador");
        jToggleButtonBorrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonBorradorActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonBorrador);

        jButtonBorraTodoLosObjetos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/BarraHerramientas/eraser.png"))); // NOI18N
        jButtonBorraTodoLosObjetos.setToolTipText("Borra todos los objetos");
        jButtonBorraTodoLosObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBorraTodoLosObjetosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBorraTodoLosObjetos);

        jButtonColorDeFondoPantalla.setText("Fondo");
        jButtonColorDeFondoPantalla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorDeFondoPantallaActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonColorDeFondoPantalla);

        jButtonColorBordeObjetos.setText("Color Borde");
        jButtonColorBordeObjetos.setToolTipText("Color Borde");
        jButtonColorBordeObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorBordeObjetosActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonColorBordeObjetos);

        jButtonColorRelleno.setText("Color Relleno");
        jButtonColorRelleno.setEnabled(false);
        jButtonColorRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColorRellenoActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonColorRelleno);

        jCheckBoxActivarRelleno.setText("Relleno");
        jCheckBoxActivarRelleno.setToolTipText("Relleno");
        jCheckBoxActivarRelleno.setBorderPainted(true);
        jCheckBoxActivarRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jCheckBoxActivarRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActivarRellenoActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBoxActivarRelleno);

        jLabelTamanioGrosor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTamanioGrosor.setText("Grosor:");
        jPanel1.add(jLabelTamanioGrosor);

        jComboBoxGrosorBorde.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        jComboBoxGrosorBorde.setToolTipText("Grosor");
        jComboBoxGrosorBorde.setMaximumSize(new java.awt.Dimension(40, 18));
        jComboBoxGrosorBorde.setMinimumSize(new java.awt.Dimension(40, 18));
        jComboBoxGrosorBorde.setPreferredSize(new java.awt.Dimension(40, 18));
        jComboBoxGrosorBorde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGrosorBordeActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBoxGrosorBorde);

        buttonGroupObjetos.add(jToggleButtonArrastrarObjetos);
        jToggleButtonArrastrarObjetos.setText("Arrastrar");
        jToggleButtonArrastrarObjetos.setToolTipText("Arrastrar Objetos");
        jToggleButtonArrastrarObjetos.setFocusable(false);
        jToggleButtonArrastrarObjetos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonArrastrarObjetos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonArrastrarObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonArrastrarObjetosActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleButtonArrastrarObjetos);

        buttonGroupObjetos.add(jToggleInfoBorrado);
        jToggleInfoBorrado.setText("Info Borrado");
        jToggleInfoBorrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleInfoBorradoActionPerformed(evt);
            }
        });
        jPanel1.add(jToggleInfoBorrado);

        jToolBarBarraDeHerramientas.add(jPanel1);

        jPanelBarraDeHerramientas.add(jToolBarBarraDeHerramientas);

        getContentPane().add(jPanelBarraDeHerramientas, java.awt.BorderLayout.WEST);

        jPanelInformacionExtra.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanelInformacionExtra.add(jLabelCoordenadasPuntero);

        jLabelHerramientaSeleccionada.setText("Herramienta Seleccionada: ");
        jPanelInformacionExtra.add(jLabelHerramientaSeleccionada);
        jPanelInformacionExtra.add(jLabelHerramientaSeleccionadaObjeto);

        getContentPane().add(jPanelInformacionExtra, java.awt.BorderLayout.SOUTH);

        jMenuArchivo.setText("Archivo");
        jMenuArchivo.setToolTipText("Archivo");

        jMenuItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/documentoNuevo_16px.png"))); // NOI18N
        jMenuItemNuevo.setText("Nuevo");
        jMenuItemNuevo.setToolTipText("Nuevo");
        jMenuItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemNuevo);

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/documentoAbrir_16px.png"))); // NOI18N
        jMenuItemAbrir.setText("Abrir");
        jMenuItemAbrir.setToolTipText("Abrir");
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemAbrir);

        jMenuItemCerrar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/cerrar_16px.png"))); // NOI18N
        jMenuItemCerrar.setText("Cerrar");
        jMenuItemCerrar.setToolTipText("Cerrar");
        jMenuItemCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCerrarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemCerrar);
        jMenuArchivo.add(jSeparator1);

        jMenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/guardar_16px.png"))); // NOI18N
        jMenuItemGuardar.setText("Guardar");
        jMenuItemGuardar.setToolTipText("Guardar");
        jMenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemGuardar);

        jMenuItemGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/guardarComo_16px.png"))); // NOI18N
        jMenuItemGuardarComo.setText("Guardar Como");
        jMenuItemGuardarComo.setToolTipText("Guardar Como");
        jMenuItemGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarComoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemGuardarComo);
        jMenuArchivo.add(jSeparator2);

        jMenuItemImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/impresora_16px.png"))); // NOI18N
        jMenuItemImprimir.setText("Imprimir");
        jMenuItemImprimir.setToolTipText("Imprimir");
        jMenuItemImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemImprimirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemImprimir);
        jMenuArchivo.add(jSeparator3);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenuItemSalir.setToolTipText("Salir");
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBarArchivo.add(jMenuArchivo);

        jMenuEditar.setText("Editar");
        jMenuEditar.setToolTipText("Editar");

        jMenuItemDeshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/desHacer_16px.png"))); // NOI18N
        jMenuItemDeshacer.setText("Deshacer");
        jMenuItemDeshacer.setToolTipText("Deshacer");
        jMenuItemDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeshacerActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemDeshacer);
        jMenuEditar.add(jSeparator4);

        jMenuItemZoomIn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemZoomIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/zoom_in_16px.png"))); // NOI18N
        jMenuItemZoomIn.setText("Zoom In");
        jMenuItemZoomIn.setToolTipText("Zoom In");
        jMenuItemZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemZoomInActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemZoomIn);

        jMenuItemZoomOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemZoomOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/zoom_out_16px.png"))); // NOI18N
        jMenuItemZoomOut.setText("Zoom Out");
        jMenuItemZoomOut.setToolTipText("Zoom Out");
        jMenuItemZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemZoomOutActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemZoomOut);

        jMenuBarArchivo.add(jMenuEditar);

        jMenuAyuda.setText("Ayuda");
        jMenuAyuda.setToolTipText("Ayuda");

        jMenuItemIndice.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemIndice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/ayuda_16px.png"))); // NOI18N
        jMenuItemIndice.setText("Índice");
        jMenuItemIndice.setToolTipText("Índice");
        jMenuItemIndice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemIndiceActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jMenuItemIndice);
        jMenuAyuda.add(jSeparator5);

        jMenuItemAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iconos/Menus/acercaDe_16px.png"))); // NOI18N
        jMenuItemAcercaDe.setText("Acerca de");
        jMenuItemAcercaDe.setToolTipText("Acerca de");
        jMenuItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAcercaDeActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jMenuItemAcercaDe);

        jMenuBarArchivo.add(jMenuAyuda);

        setJMenuBar(jMenuBarArchivo);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPantallaCompletaActionPerformed
        if(soportaPantallaCompleta()){
            activarPantallaCompleta();
        }else{
            JOptionPane.showMessageDialog(this,"No soporta Pantalla Completa",
                Constantes.TITULO_PROGRAMA,
                JOptionPane.INFORMATION_MESSAGE);
        }
}//GEN-LAST:event_jButtonPantallaCompletaActionPerformed

    private void jButtonDesactivarPantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDesactivarPantallaCompletaActionPerformed
        desactivarPantallaCompleta();
    }//GEN-LAST:event_jButtonDesactivarPantallaCompletaActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jToggleButtonCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCirculoActionPerformed
        cursorActual = new Cursor(Cursor.CROSSHAIR_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonCirculoActionPerformed

    private void jToggleButtonRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloActionPerformed
        cursorActual = new Cursor(Cursor.CROSSHAIR_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonRectanguloActionPerformed

    private void jToggleButtonRectanguloConCurvasRedondasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloConCurvasRedondasActionPerformed
        cursorActual = new Cursor(Cursor.CROSSHAIR_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonRectanguloConCurvasRedondasActionPerformed

    private void jToggleButtonOvaloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonOvaloActionPerformed
        cursorActual = new Cursor(Cursor.CROSSHAIR_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonOvaloActionPerformed

    private void jToggleButtonArrastrarObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonArrastrarObjetosActionPerformed
        cursorActual = new Cursor(Cursor.DEFAULT_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setModoDibujar(PanelDibujo.getARRASTRAR());
    }//GEN-LAST:event_jToggleButtonArrastrarObjetosActionPerformed

    private void jButtonDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeshacerActionPerformed
        llamarObjetosDiferentes(evt);
    }//GEN-LAST:event_jButtonDeshacerActionPerformed

    private void jButtonBorraTodoLosObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBorraTodoLosObjetosActionPerformed
        mesaDeDibujo.borrarTodo();
        seleccionarObjetoPredeterminado();
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jButtonBorraTodoLosObjetosActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        if(colorDeFondoPantalla){
            mesaDeDibujo.setColorFondoPantallaDibujo(jColorChooserColores.getColor());
            mesaDeDibujo.repaint();
        }

        if(colorBordeObjetos){
            mesaDeDibujo.setColorBorde(jColorChooserColores.getColor());
        }
        
        if(colorRelleno){
            mesaDeDibujo.setColorRelleno(jColorChooserColores.getColor());
            activarRellenoDeFiguras();
        }       
        colorBordeObjetos = false;
        colorDeFondoPantalla = false;
        colorRelleno = false;
        jDialogColor.setVisible(false);
        //mesaDeDibujo.repaint();  
}//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        //jColorChooserColores.setColor(Color.WHITE);
        jDialogColor.setVisible(false);
}//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonColorDeFondoPantallaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorDeFondoPantallaActionPerformed
        // Centrar el JDialogColor
        jDialogColor.setLocationRelativeTo(this);

        activarPropiedadesObjetos();
        colorBordeObjetos = false;
        colorDeFondoPantalla = true;
        colorRelleno = false;
        jColorChooserColores.setColor(mesaDeDibujo.getColorFondoPantallaDibujo());
        jDialogColor.setTitle("" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO + 
                "Color Fondo de Pantalla");
        jDialogColor.setVisible(true);
    }//GEN-LAST:event_jButtonColorDeFondoPantallaActionPerformed

    private void jButtonColorBordeObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorBordeObjetosActionPerformed
        // Centrar el JDialogColor
        jDialogColor.setLocationRelativeTo(this);

        activarPropiedadesObjetos();
        colorBordeObjetos = true;
        colorDeFondoPantalla = false;
        colorRelleno = false;
        jColorChooserColores.setColor(mesaDeDibujo.getColorBorde());
        jDialogColor.setTitle("" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                "Color Borde");
        jDialogColor.setVisible(true);
    }//GEN-LAST:event_jButtonColorBordeObjetosActionPerformed

    private void jButtonColorRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorRellenoActionPerformed
        // Centrar el JDialogColor
        jDialogColor.setLocationRelativeTo(this);

        activarPropiedadesObjetos();
        colorBordeObjetos = false;
        colorDeFondoPantalla = false;
        colorRelleno = true;
        jColorChooserColores.setColor(mesaDeDibujo.getColorRelleno());
        jDialogColor.setTitle("" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                "Color Relleno");
        jDialogColor.setVisible(true);
    }//GEN-LAST:event_jButtonColorRellenoActionPerformed

    private void jMenuItemCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCerrarActionPerformed
        operacionNuevo();
    }//GEN-LAST:event_jMenuItemCerrarActionPerformed

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
        operacionNuevo();
    }//GEN-LAST:event_jMenuItemNuevoActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        operacionNuevo();
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jMenuItemIndiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemIndiceActionPerformed
        mostrarVentanaAyuda();
    }//GEN-LAST:event_jMenuItemIndiceActionPerformed

    private void jMenuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAcercaDeActionPerformed
        String codigoHTML = "<html><body><p><b>&nbsp;&nbsp;&nbsp;&nbsp;Autor:</b> " +
                "José González von Schmeling<br><b>&nbsp;&nbsp;&nbsp;&nbsp;Mail:</b> " +
                "josego85@gmail.com<br><b>&nbsp;&nbsp;&nbsp;&nbsp;Año:</b> 2010" +
                "<br><b>&nbsp;&nbsp;&nbsp;&nbsp;Paint Evolution v.1.0</b></body></html>";
        JOptionPane.showMessageDialog(this, codigoHTML,
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO + 
                Constantes.TITULO_PROGRAMA, JOptionPane.PLAIN_MESSAGE);
        mesaDeDibujo.repaint();
    }//GEN-LAST:event_jMenuItemAcercaDeActionPerformed

    private void jMenuItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeshacerActionPerformed
        llamarObjetosDiferentes(evt);
    }//GEN-LAST:event_jMenuItemDeshacerActionPerformed

    private void jComboBoxGrosorBordeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGrosorBordeActionPerformed
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
}//GEN-LAST:event_jComboBoxGrosorBordeActionPerformed

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        mesaDeDibujo.abrirImagen();
        archivoGuardadoUltimaVersion = true;
        seleccionarObjetoPredeterminado();
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        mesaDeDibujo.abrirImagen();
        archivoGuardadoUltimaVersion = true;
        seleccionarObjetoPredeterminado();
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jButtonZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZoomInActionPerformed
        acercarImagen();
    }//GEN-LAST:event_jButtonZoomInActionPerformed

    private void jMenuItemZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemZoomInActionPerformed
        acercarImagen();
    }//GEN-LAST:event_jMenuItemZoomInActionPerformed

    private void jMenuItemZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemZoomOutActionPerformed
        alejarImagen();
    }//GEN-LAST:event_jMenuItemZoomOutActionPerformed

    private void jButtonZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZoomOutActionPerformed
        alejarImagen();
    }//GEN-LAST:event_jButtonZoomOutActionPerformed

    private void jToggleButtonPincelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPincelActionPerformed
        Cursor cursorPincel = toolKit.createCustomCursor(brushImg,new Point(3, 23),"Brush Cursor");
	cursorActual = cursorPincel;
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        mesaDeDibujo.setCursorActual(cursorActual);
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonPincelActionPerformed

    private void jToggleButtonLapizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLapizActionPerformed
        Cursor pencilCursor = toolKit.createCustomCursor(pencilImg, new Point(10,24),"Pencil Cursor");
	cursorActual = pencilCursor;
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(1);
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonLapizActionPerformed

    private void jToggleButtonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLineaActionPerformed
        cursorActual = new Cursor(Cursor.CROSSHAIR_CURSOR);
        mesaDeDibujo.setCursorActual(cursorActual);
        mesaDeDibujo.setTamanioBorde(jComboBoxGrosorBorde.getSelectedIndex());
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonLineaActionPerformed

    private void jToggleButtonTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonTextoActionPerformed
        Cursor cursorLetra = new Cursor(Cursor.TEXT_CURSOR);
	cursorActual = cursorLetra;
        mesaDeDibujo.setCursorActual(cursorActual);
        activarPropiedadesObjetos();
        //mesaDeDibujo.setObjetoTexto(true);
        llamarObjetosDiferentes(evt);
    }//GEN-LAST:event_jToggleButtonTextoActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        if(mesaDeDibujo.guardarImagen()){
            archivoGuardadoUltimaVersion = true;
        }
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jMenuItemGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarComoActionPerformed
        mesaDeDibujo.setNombreArchivo(null);
        if(mesaDeDibujo.guardarImagen()){
            archivoGuardadoUltimaVersion = true;
        }
    }//GEN-LAST:event_jMenuItemGuardarComoActionPerformed

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        if(mesaDeDibujo.guardarImagen()){
            archivoGuardadoUltimaVersion = true;
        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        mostrarVentanaImpresora();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    private void jMenuItemImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemImprimirActionPerformed
        mostrarVentanaImpresora();
    }//GEN-LAST:event_jMenuItemImprimirActionPerformed

    private void jToggleButtonBorradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonBorradorActionPerformed
        // Icono del borrador
        Cursor borradorCursor = toolKit.createCustomCursor(eraserImg,
                new Point(10,24),"Eraser Cursor");
        cursorActual = borradorCursor;
        mesaDeDibujo.setCursorActual(cursorActual);
        llamarObjetosDiferentes(evt);
        activarPropiedadesObjetos();
    }//GEN-LAST:event_jToggleButtonBorradorActionPerformed

    private void jCheckBoxActivarRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarRellenoActionPerformed
        activarPropiedadesObjetos();
        if(mesaDeDibujo.getColorRelleno() == null){
            mesaDeDibujo.setColorRelleno(Color.BLACK);
        }

        if(jCheckBoxActivarRelleno.isSelected()){
            jButtonColorRelleno.setEnabled(true);
            mesaDeDibujo.setConRelleno(true);
            mesaDeDibujo.setColorRelleno(mesaDeDibujo.getColorRelleno());
        }else{
            jButtonColorRelleno.setEnabled(false);
            mesaDeDibujo.setConRelleno(false);
            mesaDeDibujo.setColorRelleno(null);
        }
    }//GEN-LAST:event_jCheckBoxActivarRellenoActionPerformed

    private void jToggleInfoBorradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleInfoBorradoActionPerformed
        String codigoHTML = "<html><body><p>Sólo se pueden eliminar individualmente" +
                " haciendo click sobre los objetos:<br><t>- Rectángulo<br><t>- " +
                "Óvalo<br><t>- Círculo<br><t>- Rectángulo con curvas redondas"
                + "</body></html>";
        JOptionPane.showMessageDialog(this, codigoHTML,
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                Constantes.TITULO_PROGRAMA, JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_jToggleInfoBorradoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        mostrarVentanaOpcionesImagen();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    ////////////////////////////////////////////////////////////////////////////
    // Metodos varios
    ////////////////////////////////////////////////////////////////////////////
    /**
     * Comprobar si tiene soporte a pantalla completa. True si lo tiene; caso
     * contrario, false.
     *
     * @return Comprobar si tiene soporte a pantalla completa. True si lo tiene; caso
     * contrario, false.
     * @since 1.6
     */
    public boolean soportaPantallaCompleta(){
        return gd.isFullScreenSupported();
    }

    /**
     * Activamos el modo a pantalla completa.
     * @since 1.6
     */
    public void activarPantallaCompleta(){
        gd.setFullScreenWindow(this);
        jButtonPantallaCompleta.setVisible(false);
        jButtonDesactivarPantallaCompleta.setVisible(true);
    }

    /**
     * Desactivamos el modo a pantalla completa.
     * @since 1.6
     */
    public void desactivarPantallaCompleta(){
       gd.setFullScreenWindow(null);
       jButtonPantallaCompleta.setVisible(true);
       jButtonDesactivarPantallaCompleta.setVisible(false);
    }

    /**
     * Metodo privado que llama a los diferentes objetos para asignarle opciones diferentes
     *
     * @param event El evento degenerado de un objeto.
     * @since 1.6
     */
    private void llamarObjetosDiferentes(ActionEvent event){
        if(event.getSource() == jToggleButtonLinea){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getLINEA());
            jLabelHerramientaSeleccionadaObjeto.setText("Linea");
        }
        if(event.getSource() == jToggleButtonRectangulo){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getRECTANGULO());
            jLabelHerramientaSeleccionadaObjeto.setText("Rectangulo");
        }
        if(event.getSource() == jToggleButtonOvalo){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getOVALO());
            jLabelHerramientaSeleccionadaObjeto.setText("Ovalo");
        }
         if(event.getSource() == jToggleButtonCirculo){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getCIRCULO());
            jLabelHerramientaSeleccionadaObjeto.setText("Círculo");
        }
        if(event.getSource() == jToggleButtonRectanguloConCurvasRedondas){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getRECTANGULO_CON_CURVAS_REDONDAS());
            jLabelHerramientaSeleccionadaObjeto.setText("Rectangulo Redondo");
        }
        if(event.getSource() == jToggleButtonLapiz){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getLAPIZ());
            jLabelHerramientaSeleccionadaObjeto.setText("Lápiz");
        }
        if(event.getSource() == jToggleButtonPincel){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getPINCEL());
            jLabelHerramientaSeleccionadaObjeto.setText("Pincel");
        }
        if(event.getSource() == jButtonDeshacer || event.getSource() == jMenuItemDeshacer){
            mesaDeDibujo.deshacer();
        }
        if(event.getSource() == jButtonBorraTodoLosObjetos){
            mesaDeDibujo.borrarTodo();
        }
        if(event.getSource() == jToggleButtonBorrador){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getBORRADOR());
            jLabelHerramientaSeleccionadaObjeto.setText("Borrador");
        }
        if(event.getSource() == jToggleButtonTexto){
            mesaDeDibujo.setModoDibujar(PanelDibujo.getTEXTO());
            jLabelHerramientaSeleccionadaObjeto.setText("Texto");
        }
    }

    /**
     * Metodo privado cuando se presiona la opcion Nuevo.
     * Se pone null el nombre del archivo.
     * Se borra todos objetos sean figura o texto en la mesa de trabajo.
     * Se selecciona el objeto prederterminado del programa.
     * Se cambian los siguientes colores a su valor por defecto:
     *  - Color fondo de pantalla: blanco
     *  - Color borde: negro
     *  - Color relleno: blanco
     * @since 1.6
     */
    private void operacionNuevo(){
        mesaDeDibujo.setNombreArchivo(null);
        mesaDeDibujo.borrarTodo();
        seleccionarObjetoPredeterminado();
        activarPropiedadesObjetos();
	mesaDeDibujo.setColorFondoPantallaDibujo(Color.WHITE);
	mesaDeDibujo.setColorBorde(Color.BLACK);
        mesaDeDibujo.setColorRelleno(Color.WHITE);
        jCheckBoxActivarRelleno.setSelected(false);
	mesaDeDibujo.repaint();
    }

    /**
     * Metodo que muestra la ventana Ayuda.
     * @since 1.6
     */
    public void mostrarVentanaAyuda() {
        this.setLocationRelativeTo(null);
        ventanaAyuda = new VentanaAyuda(this, rootPaneCheckingEnabled);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width - w)/2;
        int y = (dim.height - h)/2;
        ventanaAyuda.setSize(dim);
       // ventanaAyuda.setLocation(x, y);
       // ventanaAyuda.mostrarAyuda();
       // ventanaAyuda.setVisible(true);
        //ventanaAyuda.setLocationRelativeTo(this);
        ventanaAyuda.setLocationRelativeTo(this);
	ventanaAyuda.mostrarAyuda();
    }
    
    /**
     * Metodo que muestra la ventana opciones imagen.
     * @since 1.6
     */
    public void mostrarVentanaOpcionesImagen() {
        this.setLocationRelativeTo(null);
        ventanaOpcionesImagen = new VentanaOpcionesImagen(this, rootPaneCheckingEnabled);
        ventanaOpcionesImagen.setLocationRelativeTo(this);
	ventanaOpcionesImagen.mostrarOpcionesImagen();
    }

    /**
     * Metodo que muestra la ventana Impresora.
     * @since 1.6
     */
    public void mostrarVentanaImpresora() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable((Printable) mesaDeDibujo);

        // Dialogo de configuracion de impresora
        if( printJob.printDialog() ) {
            try {
                printJob.print();
            } catch(java.awt.print.PrinterException e){
                System.out.println("El servicio de impresion esta desactivado.");
            }
            catch( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que retorna:
     *  - true en caso que se pueda arrastrar los objetos
     *  - false en caso que no se pueda arrastrar los objetos
     * Solo se arrastran los objetos siguientes:
     *  - Rectangulo
     *  - Ovalo
     *  - Circulo
     *  - Rectangulo con curvas redondas
     *
     * @return True si se puede arrastrar el objeto; caso contrario, false
     * @since 1.6
     */
    public boolean esObjetoArrastrable(){
         if(jToggleButtonRectangulo.isSelected() || jToggleButtonOvalo.isSelected()
                || jToggleButtonCirculo.isSelected()
                || jToggleButtonRectanguloConCurvasRedondas.isSelected()){
            return true;
        }
       return false;
    }


    /**
     * Metodo que retorna:
     *  - true en caso que se pueda rellenar los objetos
     *  - false en caso que no se pueda rellenar los objetos
     * Solo se rellenan los objetos siguientes:
     *  - Rectangulo
     *  - Ovalo
     *  - Circulo
     *  - Rectangulo con curvas redondas
     *
     * @return True si se puede rellenar el objeto; caso contrario, false
     * @since 1.6
     */
    public boolean esObjetoRellenable(){
        if(jToggleButtonRectangulo.isSelected() || jToggleButtonOvalo.isSelected()
                || jToggleButtonCirculo.isSelected()
                || jToggleButtonRectanguloConCurvasRedondas.isSelected()){
            return true;
        }
       return false;
    }

    /**
     * Metodo que retorna:
     *  - true en caso que se tenga tamanhio en el borde de los objetos
     *  - false en caso que no se tenga tamanhio en el borde de los objetos
     * Solo tienen tamanhio en el borde los objetos siguientes:
     *  - Rectangulo
     *  - Ovalo
     *  - Circulo
     *  - Rectangulo con curvas redondas
     *
     * @return True si se tiene tamanhio en el borde el objeto; caso contrario, false
     * @since 1.6
     */
    public boolean tieneTamanhioBordeObjeto(){
        if(jToggleButtonRectangulo.isSelected() || jToggleButtonOvalo.isSelected()
                || jToggleButtonCirculo.isSelected()
                || jToggleButtonRectanguloConCurvasRedondas.isSelected()
                || jToggleButtonPincel.isSelected()
                || jToggleButtonLinea.isSelected()){
            return true;
        }
       return false;
    }

    /**
     * Activar el relleno de las figuras.
     * @since 1.6
     */
    public void activarRellenoDeFiguras(){
        if(colorRelleno){
            mesaDeDibujo.setConRelleno(true);
        }
    }

    /**
     * Activar las propiedades de los objetos.
     * @since 1.6
     */
    private void activarPropiedadesObjetos(){
        jToggleButtonArrastrarObjetos.setVisible(esObjetoArrastrable());
        jButtonColorRelleno.setVisible(esObjetoRellenable());
        jCheckBoxActivarRelleno.setVisible(esObjetoRellenable());
        jLabelTamanioGrosor.setVisible(tieneTamanhioBordeObjeto());
        jComboBoxGrosorBorde.setVisible(tieneTamanhioBordeObjeto());
    }

    /**
     * Se selecciona el objeto predeterminado que contiene propiedades estandares.
     * En este caso es el lapiz.
     * @since 1.6
     */
    private void seleccionarObjetoPredeterminado(){
        // Icono del lapiz
        Cursor pencilCursor = toolKit.createCustomCursor(pencilImg, new Point(10,24),"Pencil Cursor");
        cursorActual = pencilCursor;
        mesaDeDibujo.setCursorActual(cursorActual);

        jToggleButtonLapiz.setSelected(true);
        mesaDeDibujo.setModoDibujar(PanelDibujo.getLAPIZ());
        jLabelHerramientaSeleccionadaObjeto.setText("Lápiz");
    }

    /**
     * Sirve para determinar si la ultima version de la imagen creada fue guardada.
     * Devuelve true si se guardo; caso contrario en false.
     *
     * @return True si se guardo la ultima version de la imagen; caso contrario en false.
     * @since 1.6
     */
    public boolean archivoGuardadoUltimaVersion(){
        return archivoGuardadoUltimaVersion;
    }

    /**
     * Sirve para determinar si modifico la imagen sin guardar.
     * Devuelve true si se modifico; caso contrario en false.
     *
     * @return True si se modifico la imagen sin guardar; caso contrario en false
     * @since 1.6
     */
    public boolean modificarImagenSinGuardar(){
        if(modificarImagenSinGuardar){
            return false;
        }else{
            return true;
        }
    }

    /**
     * Se acerca la imagen. Zoom In.
     *
     * @since 1.6
     */
    private void acercarImagen(){
        if(archivoGuardadoUltimaVersion() && mesaDeDibujo.isArchivoGuardadoUltimaVersion()) {
            mesaDeDibujo.acercar();
        }else{
            String mensaje = "Primero guarde la imagen para luego usar el Zoom In";
            JOptionPane.showMessageDialog(this, mensaje,
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                Constantes.TITULO_PROGRAMA, JOptionPane.PLAIN_MESSAGE);
        mesaDeDibujo.repaint();
        }
    }

    /**
     * Se aleja la imagen. Zoom Out.
     *
     * @since 1.6
     */
    private void alejarImagen(){
        if(archivoGuardadoUltimaVersion() && mesaDeDibujo.isArchivoGuardadoUltimaVersion()) {
            mesaDeDibujo.alejar();
        }else{
            String mensaje = "Primero guarde la imagen para luego usar el Zoom Out";
            JOptionPane.showMessageDialog(this, mensaje,
                "" + Constantes.INCREMENTO_CANTIDAD_DE_ESPACIO_TITULO +
                Constantes.TITULO_PROGRAMA, JOptionPane.PLAIN_MESSAGE);
        mesaDeDibujo.repaint();
        }
    }

    
    /**
    * Donde se ejecuta la Ventana Color.
    *
    * @param args La linea de argumentos del comando
    * @since 1.6
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupObjetos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonBorraTodoLosObjetos;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonColorBordeObjetos;
    private javax.swing.JButton jButtonColorDeFondoPantalla;
    private javax.swing.JButton jButtonColorRelleno;
    private javax.swing.JButton jButtonDesactivarPantallaCompleta;
    private javax.swing.JButton jButtonDeshacer;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonPantallaCompleta;
    private javax.swing.JButton jButtonZoomIn;
    private javax.swing.JButton jButtonZoomOut;
    private javax.swing.JCheckBox jCheckBoxActivarRelleno;
    private javax.swing.JColorChooser jColorChooserColores;
    private javax.swing.JComboBox jComboBoxGrosorBorde;
    private javax.swing.JDialog jDialogColor;
    public static javax.swing.JLabel jLabelCoordenadasPuntero;
    private javax.swing.JLabel jLabelHerramientaSeleccionada;
    private javax.swing.JLabel jLabelHerramientaSeleccionadaObjeto;
    private javax.swing.JLabel jLabelTamanioGrosor;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarArchivo;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemAcercaDe;
    private javax.swing.JMenuItem jMenuItemCerrar;
    private javax.swing.JMenuItem jMenuItemDeshacer;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemGuardarComo;
    private javax.swing.JMenuItem jMenuItemImprimir;
    private javax.swing.JMenuItem jMenuItemIndice;
    private javax.swing.JMenuItem jMenuItemNuevo;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemZoomIn;
    private javax.swing.JMenuItem jMenuItemZoomOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelBarraAccesoDirecto;
    private javax.swing.JPanel jPanelBarraDeHerramientas;
    private javax.swing.JPanel jPanelInformacionExtra;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToggleButton jToggleButtonArrastrarObjetos;
    private javax.swing.JToggleButton jToggleButtonBorrador;
    private javax.swing.JToggleButton jToggleButtonCirculo;
    private javax.swing.JToggleButton jToggleButtonLapiz;
    private javax.swing.JToggleButton jToggleButtonLinea;
    private javax.swing.JToggleButton jToggleButtonOvalo;
    private javax.swing.JToggleButton jToggleButtonPincel;
    private javax.swing.JToggleButton jToggleButtonRectangulo;
    private javax.swing.JToggleButton jToggleButtonRectanguloConCurvasRedondas;
    private javax.swing.JToggleButton jToggleButtonTexto;
    private javax.swing.JToggleButton jToggleInfoBorrado;
    private javax.swing.JToolBar jToolBarBarraDeHerramientas;
    // End of variables declaration//GEN-END:variables

}