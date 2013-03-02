/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author proyectosbeta
 */
public class ModeloTabla extends AbstractTableModel{
    // Objetos
    private String[] columnNames = {"Nombre",
                         "Apellido",
                         "Pasatiempo",
                         "Años de Practica",
                         "Soltero(a)"};
    private Object[][] data = {
        {"Mary", "Campione",
                 "Esquiar", new Integer(5), new Boolean(false)},
        {"Lhucas", "Huml",
                   "Patinar", new Integer(3), new Boolean(true)},
        {"Kathya", "Walrath",
                  "Escalar", new Integer(2), new Boolean(false)},
        {"Marcus", "Andrews",
                 "Correr", new Integer(7), new Boolean(true)},
        {"Angela", "Lalth",
                   "Nadar", new Integer(4), new Boolean(false)}
    };  
        
    /**
     * Metodo publico que retorna el numero de elementos del array de datos.
     * @return 
     */
    @Override
    public int getRowCount() {
        return this.data.length;
    }

    /**
     * Metodo publico que retorna el numero de elementos del
     * array de los nombres de las columnas.
     * @return 
     */
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    //
    /**
     * Metodo publico que retorna el elemento indicado.
     * @param col
     * @return 
     */
    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    /**
     * Metodo publico que retorna el valor indicado.
     * @param row
     * @param col
     * @return 
     */
    @Override
    public Object getValueAt(int row, int col) {
        return this.data[row][col];
    }
    
    /*
     * No tienes que implementar este método a menos que
     * los datos de tu tabla cambien
     */
    @Override
    public void setValueAt(Object value, int row, int col) {
         this.data[row][col] = value;
         fireTableCellUpdated(row, col);
    }
    
    /*
     * No tienes que implementar este método a menos que
     * las celdas de tu tabla sean Editables.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
         return true;
    }

   /*
    * Metodo publico que sirve para determinar el editor predeterminado
    * para cada columna de celdas.
    */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }



}
