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
    /**
     * La primera columna es boolean, las demas Object.
     * @param columna
     * @return 
     */
    
    public ModeloDefaultTableModel(String[] nombreColumnas, int numeroColumnas){
        super(nombreColumnas, numeroColumnas);
    }
    
    public Class getColumnClass(int columna){
        if (columna == 0){
            return Integer.class;
        }
        return Object.class;
    }
}