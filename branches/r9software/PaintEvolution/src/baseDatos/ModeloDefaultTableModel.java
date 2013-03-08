/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author proyectosbeta
 */
public class ModeloDefaultTableModel extends DefaultTableModel {
    // Objetos
    private int numeroColumnaBoolean;
    
    /**
     * 
     * @param nombreColumnas
     * @param numeroColumnas
     * @param columnaBoolean 
     */
    public ModeloDefaultTableModel(String[] nombreColumnas, int numeroColumnas){
        super(nombreColumnas, numeroColumnas);
        this.numeroColumnaBoolean = numeroColumnas;
    }
    
    /**
     * La primera columna es boolean, las demas Object.
     * @param columna
     * @return 
     */
    @Override
    public Class getColumnClass(int columna){
        if (columna == numeroColumnaBoolean){
            return Boolean.class;
        }
        return Object.class;
    }
    
    /**
     * Metodo publico que devuelve el numero de la columna
     * booleana.
     */
    public int getNumeroColumnaBoolean(){
        return this.numeroColumnaBoolean;
    }  
}