package PanelPrincipal;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaDistancias {

    private JTable TablaDatos;
    private DefaultTableModel TablaDefault = new DefaultTableModel();
    private JScrollPane Scroll;
    private int row;

    public TablaDistancias() {

        this.TablaDatos = new JTable(TablaDefault);
        TablaDatos.setEnabled(false);
        TablaDatos.setFocusable(false);
        TablaDefault.addColumn("JugEnem");
        TablaDefault.addColumn("JugPre");
        TablaDefault.addColumn("EnePre");
        TablaDefault.addColumn("Protección");
        TablaDefault.addColumn("Direccion");
        TablaDefault.addColumn("Premio");
        TablaDatos.setPreferredScrollableViewportSize(new Dimension(200, 200));
        this.Scroll = new JScrollPane(TablaDatos);
    }

    public int getCelda(int i, int j) {
        return (int) TablaDefault.getValueAt(i, j);
    }

    public String getCeldaDireccion(int i, int j) {
        return TablaDefault.getValueAt(i, j).toString();
    }

    public String getCeldaPremio(int i, int j) {
        return TablaDefault.getValueAt(i, j).toString();
    }
    
    public void setFila(int Jug_Enem, int Prem_Jug, int Prem_Enem, int Murallas, String Direccion, String Premio) {
        Object[] Fila = {Jug_Enem, Prem_Jug, Prem_Enem, Murallas, Direccion, Premio};
        TablaDefault.addRow(Fila);
        row = TablaDatos.getRowCount() - 1;
        Rectangle rect = TablaDatos.getCellRect(row, 0, true);
        TablaDatos.scrollRectToVisible(rect);
        TablaDatos.clearSelection();
        TablaDatos.setRowSelectionInterval(row, row);
        DefaultTableModel modelo = (DefaultTableModel) TablaDatos.getModel();
        modelo.fireTableDataChanged();
    }

    public JScrollPane getScroll() {
        return Scroll;
    }

    public int totalFilas() {
        return row;
    }
}
