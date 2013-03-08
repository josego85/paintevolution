/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Auxiliar;

import java.awt.Component;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Clase CabeceraCheckBox que se usa en la cabecera del ModeloDefaultTableModel
 * para que se pueda seleccionar todos los cheboz para imprimir.
 * @author proyectosbeta
 */
public class CabeceraCheckBox extends JCheckBox implements TableCellRenderer, MouseListener{
    // Objetos.
    protected CabeceraCheckBox rendererComponent;  
    protected int column;  
    protected boolean mousePressed = false;  
    
    /**
     * Constructor con un parametro.
     * @param itemListener 
     */
    public CabeceraCheckBox(ItemListener itemListener) {  
        rendererComponent = this;  
        //setOpaque(false);
        //setFont(header.getFont());
        //rendererComponent.setHorizontalAlignment(JLabel.CENTER);
        rendererComponent.addItemListener(itemListener);  
    } 
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (table != null) {  
            JTableHeader cabeceraTabla = table.getTableHeader();  
            if (cabeceraTabla != null) {  
                //rendererComponent.setForeground(cabeceraTabla.getForeground());  
                //rendererComponent.setBackground(cabeceraTabla.getBackground());  
                //rendererComponent.setFont(cabeceraTabla.getFont());  
                cabeceraTabla.addMouseListener(rendererComponent);  
            }  
        }  
        setColumn(column);  
        rendererComponent.setText("imprimir");  
        //setBorder(UIManager.getBorder("TableHeader.cellBorder"));  
        return rendererComponent;
    }
        
    protected void setColumn(int column) {  
        this.column = column;  
    } 
    
    public int getColumn() {  
        return column;  
    } 
    
    protected void handleClickEvent(MouseEvent e) {  
        if (mousePressed) {  
            mousePressed = false;  
            JTableHeader cabeceraTabla = (JTableHeader)(e.getSource());  
            JTable tableView = cabeceraTabla.getTable();  
            TableColumnModel columnModel = tableView.getColumnModel();  
            int viewColumn = columnModel.getColumnIndexAtX(e.getX());  
            int column = tableView.convertColumnIndexToModel(viewColumn);  

            if (viewColumn == this.column && e.getClickCount() == 1 && column != -1) {  
                doClick();  
            }  
        }  
    }  

    @Override
    public void mouseClicked(MouseEvent e) {
        handleClickEvent(e);  
        ((JTableHeader)e.getSource()).repaint(); 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    } 
}