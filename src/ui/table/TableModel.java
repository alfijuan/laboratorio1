package ui.table;

import javax.swing.table.DefaultTableModel;

public class TableModel extends DefaultTableModel{
	
   public boolean isCellEditable (int row, int column){
	   return false;
   }
   
}
