/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import baseDatos.ModeloDefaultTableCampoPosicion;
import java.util.ArrayList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author proyectosbeta
 */
public class VentanaCrearTexto extends javax.swing.JFrame implements TableModelListener {
    ////////////////////////////////////////////////////////////////////////////
    // Variables de clase.
    ////////////////////////////////////////////////////////////////////////////
    private panelDibujoTexto panelDibujoTexto;
    private static String rutaImagenTemporal;
    private static String[] nombreColumnas;
    private static ArrayList<ArrayList> arrayFilasSeleccionadas;
    private ModeloDefaultTableCampoPosicion modeloTablaCamposPosiciones;
    
    /**
     * Creates new form VentanaCrearTexto
     */
    public VentanaCrearTexto(String rutaImagenTemporal, String[] nombreColumnas,
            ArrayList<ArrayList> arrayFilasSeleccionadas) {
        /*
         * Se guarda la ruta de la imagen temporal para luego usar,
         * al crear un Texto con registros de la base de datos.
         */
        VentanaCrearTexto.rutaImagenTemporal = rutaImagenTemporal;
        
        /*
         * Se guarda el nombre de las columnas.
         * Obs: que viene la columna Imprimir, y deberia suprimirse
         * para no colocar en el combobox.
         */
        VentanaCrearTexto.nombreColumnas = nombreColumnas;
        
        /**
         * Se guardan las filas seleccionadas con sus campos correspondientes.
         */
        VentanaCrearTexto.arrayFilasSeleccionadas = arrayFilasSeleccionadas;
        
        initComponents();

        // Modelo para jTableCamposPosiciones.
        modeloTablaCamposPosiciones = new ModeloDefaultTableCampoPosicion(); 
        
        // Se agrega a la tabla (jTableCamposPosiciones) el modelo.
        jTableCamposPosiciones.setModel(modeloTablaCamposPosiciones);
        
        cargarDatosTabla();
        
        /*
         * Guarda en un array las posiciones de los Texto (campos) que
         * el usuario habia seleccionado anteriormente.
         */
        ArrayList<String> arrayPosicionesTexto = crearArrayPosicionesTexto();
        
        // Centrar la VentanaCrearTexto.
        setLocationRelativeTo(null);

        // Deshabilitar la opcion de Maximizar ventana.
        setResizable(false);
        
        /*
         * Para que el canvas contenga 350 de ancho se tiene que poner la ventana
         * VentanaCrearTexto a 487 de ancho. Para que el canvas contenga 700 de alto 
         * se tiene que poner la ventana VentanaCrearTexto a 977 de alto. 
         */
        setSize(1075, 487);
        
        // Crea el objeto de Mesa de Dibujo.
        panelDibujoTexto = new panelDibujoTexto(rutaImagenTemporal, arrayFilasSeleccionadas, 
            nombreColumnas, arrayPosicionesTexto);
        
        /*
         * Establece un esquema para la mesa de dibujo y agrega a la
         * VentanaCrearTexto
         */ 
        getContentPane().add(panelDibujoTexto, java.awt.BorderLayout.CENTER);
        //setContentPane(panelDibujoTexto);
        
        // No se puede mover las columnas de posicion de jTableCamposPosiciones.
        jTableCamposPosiciones.getTableHeader().setReorderingAllowed(false); 
        
        // Escuchador del modeloTablaCamposPosiciones.
        modeloTablaCamposPosiciones.addTableModelListener(this);
        
        pack();
    }
    
    /**
     * Metodo privado que carga datos al modelo (modeloTablaCamposPosiciones).
     */
    private void cargarDatosTabla(){
        // Variables.
        int x = 100;
        int y = 50;
        
        // Agregar columnas al modeloTablaCamposPosiciones.
        modeloTablaCamposPosiciones.addColumn("Campos");
        modeloTablaCamposPosiciones.addColumn("Posición(x,y)");
        
        /**
         * Como el array de String trae el campo imprimir,
         * restamos la longitud total con 1, porque
         * no queremos usar en la tabla porque no es necesario.
         */
        for(int i = 0; i < nombreColumnas.length - 1; i++){ 
            x = x + 50;
            y = y + 25;
            Object nuevo[] = new Object[2];
            nuevo[0] = nombreColumnas[i];
            nuevo[1] = "" + x + "," + y;
            this.modeloTablaCamposPosiciones.addRow(nuevo); 
        } 
    }

    /**
     * 
     */
    private ArrayList<String> crearArrayPosicionesTexto(){
        // Objetos.
        ArrayList<String> arrayPosicionesTexto = new ArrayList<String>();
        DefaultTableModel modeloTabla_temp = (DefaultTableModel)jTableCamposPosiciones.getModel();
        
        for(int i = 0; i < modeloTabla_temp.getRowCount(); i++){   
            arrayPosicionesTexto.add((String)modeloTabla_temp.getValueAt(i, 1).toString());
        } 
        return arrayPosicionesTexto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelOpciones = new javax.swing.JPanel();
        jPanelTablaPosiciones = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCamposPosiciones = new javax.swing.JTable();
        jPanelBotones = new javax.swing.JPanel();
        jButtonInsertarImagen = new javax.swing.JButton();
        jButtonImprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Imprimir imagen dinámica");

        jPanelOpciones.setLayout(new java.awt.BorderLayout());

        jPanelTablaPosiciones.setPreferredSize(new java.awt.Dimension(300, 295));

        jTableCamposPosiciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Campos", "Posiciones (x,y)"
            }
        ));
        jScrollPane1.setViewportView(jTableCamposPosiciones);

        javax.swing.GroupLayout jPanelTablaPosicionesLayout = new javax.swing.GroupLayout(jPanelTablaPosiciones);
        jPanelTablaPosiciones.setLayout(jPanelTablaPosicionesLayout);
        jPanelTablaPosicionesLayout.setHorizontalGroup(
            jPanelTablaPosicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaPosicionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTablaPosicionesLayout.setVerticalGroup(
            jPanelTablaPosicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTablaPosicionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jPanelOpciones.add(jPanelTablaPosiciones, java.awt.BorderLayout.NORTH);

        jButtonInsertarImagen.setText("Imagen");
        jButtonInsertarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertarImagenActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonInsertarImagen);

        jButtonImprimir.setText("Imprimir");
        jButtonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImprimirActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonImprimir);

        jPanelOpciones.add(jPanelBotones, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelOpciones, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInsertarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertarImagenActionPerformed
        // Busca una imagen para luego insertar en el panelDibujoTexto.
        panelDibujoTexto.abrirImagen();
    }//GEN-LAST:event_jButtonInsertarImagenActionPerformed

    private void jButtonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImprimirActionPerformed
        panelDibujoTexto.prepararImagenesTemporales();
        
        // Se cierra VentanaCrearTexto.
        //this.dispose();
    }//GEN-LAST:event_jButtonImprimirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCrearTexto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaCrearTexto(rutaImagenTemporal, nombreColumnas,
                    arrayFilasSeleccionadas).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonImprimir;
    private javax.swing.JButton jButtonInsertarImagen;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelOpciones;
    private javax.swing.JPanel jPanelTablaPosiciones;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCamposPosiciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public void tableChanged(TableModelEvent e) {
        // Variables.
        int fila = e.getFirstRow();
	int columna = e.getColumn();

        System.out.println("La fila es: " + fila);
        System.out.println("La columna es: " + columna);
        
	String valorCeldaCambiada = String.valueOf(jTableCamposPosiciones.getValueAt(fila, columna));
        panelDibujoTexto.actualizarPosicionTexto(fila, valorCeldaCambiada);
        
        panelDibujoTexto.repaint();

        /*
	JOptionPane.showMessageDialog(this, "Value at (" + fila + "," + columna + ") "
            + "changed to " + "\'" + valorCeldaCambiada + "\'");
            * */
    }
}