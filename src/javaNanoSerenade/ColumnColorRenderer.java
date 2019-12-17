/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaNanoSerenade;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author aliay
 */
public class ColumnColorRenderer extends DefaultTableCellRenderer {
   Color backgroundColor, foregroundColor;
   public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
      super();
      this.backgroundColor = backgroundColor;
      this.foregroundColor = foregroundColor;
      this.setBorder(BorderFactory.createCompoundBorder());
   }
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      cell.setBackground(backgroundColor);
      cell.setForeground(foregroundColor);
      this.setBorder(BorderFactory.createEtchedBorder());
      return cell;
   }
    
}
