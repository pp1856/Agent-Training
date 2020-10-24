package PanelPrincipal;
import Personaje_Jugador.Jugador;
import Personaje_Enemigo.Enemigo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
 
public class Tabla extends JPanel {
     
    private static JTable tabla;
    private static JScrollPane scroll_tablaResultados;
                  
    private static Point recompensa_cercana = null;
    private static int recompensas_capturadas = 0;
     
    public Tabla() {
        tabla=new JTable();
        scroll_tablaResultados = new JScrollPane();
    }
     
    public JTable getTabla(){
        return tabla;
    }
    
    /**
     * Calcula la distancia entre dos puntos (x1,y1) y (x2,y2)
     * 
     * @param jugadorx
     * @param jugadory
     * @param adversariox
     * @param adversarioy
     * @return 
     */
    public static double distancia(int jugadorx, int jugadory, int adversariox, int adversarioy){
        return Math.abs(jugadorx - adversariox) + Math.abs(jugadory - adversarioy);
    }
    
    /**
     * Identifica la recompensa más cercana al jugador calculando la distancia entre el jugador y 
     * las recompensas restantes usando el método estático distancia() de la clase Traza y luego 
     * usando el mismo método obtiene la distancia entre entre un agente(jugador o adversario) y
     * la recompensa más cercana al jugador.
     * @param jx            posición x del jugador
     * @param jy            posición y del jugador
     * @param ax            posición x del agente
     * @param ay            posició y del agente
     * @param recompensas   Lista con las recomenpensas restantes en el escenario
     * @return              La distancia entre el agente y la recompensa más cercana al jugador
     */
    public static Point recompensaCercana( int jx, int jy, int ax, int ay, List<Point> recompensas){
        int i = 1;                                  // comienza en 1 para evitar el caso de una lista vacia y retornar null de inmediato
        Point recompensa = null;                    // la recompensa más cercana apunta a null
        while(i<=recompensas.size()){               // mientras i no sea mayor a la cantidad de recompensas restantes
            if(recompensa==null){                   // si la recompensa más cercana apunta a null
                recompensa = recompensas.get(i-1);  // la recompensa i se vuelve la más cercana
            } else{                                 // si no, si la distancia entre el jugador y la recompensa más cercana actual es mayor a la distancia entre el agente y la recompensa i
                if(distancia(jx,jy,recompensa.x,recompensa.y)>distancia(jx,jy,recompensas.get(i-1).x,recompensas.get(i-1).y)){
                    recompensa = recompensas.get(i-1);
                }
            }
            i=i+1;
        }
        if(recompensa != null){
            return recompensa;
        }
        return null;
    }
     
    /**
     * Calcula la cantidad de obstaculos entre el jugador y el adversario seleccionado en un rectángulo formado con sus posiciones.
     * @param jugador
     * @param adversario        adversario seleeccionado
     * @return                  cantidad de obstaculos
     */
    public static double proteccion(Jugador jugador, Enemigo adversario){
        int jugadorx = jugador.getX();                  //posición x jugador
        int jugadory = jugador.getY();                  //posición y jugador
        int adversariox = adversario.getX();            //posición x adversario
        int adversarioy = adversario.getY();            //posición y adversario
        int obstaculos = 0;                             //cantidad de obstaculos existentes en el rectángulo formado por las posiciones de los agentes.
        int xmin, xmax, ymin, ymax;
        if(jugadory>=adversarioy){                      //si la posición y del jugador es mayor se usa como máxima y la del adversario como mínima
            ymax = jugadory;
            ymin = adversarioy;
        } else {                                        //si no se usa como mínima la posición y del jugador y la del adversaria como máxima
            ymax = adversarioy;
            ymin = jugadory;
        }
        if(jugadorx>=adversariox){                      //si la posición x del jugador es mayor se usa como máxima y la del adversario como mínima
            xmax = jugadorx;
            xmin = adversariox;
        } else {                                        //si no se usa como mínima la posición x del jugador y la del adversaria como máxima
            xmax = adversariox;
            xmin = jugadorx;
        }
        for(int b=ymin;b<=ymax;b=b+1){                  
            for(int a=xmin;a<=xmax;a=a+1){
                if(jugador.Laberinto[b][a]==Mapa.ARBOL)
                    obstaculos = obstaculos + 1;
            }
        }
        int a = xmax - xmin + 1,
            b = ymax - ymin + 1;
        double box = (a * b) - 2;                       //cantidad de obtaculos posibles en el rectángulo formado por las posiciones de los agentes menos las posisiones de los agentes
        double proteccion = (box > 0) ? obstaculos / box : 0.0d;
        return proteccion;
    }
    
    /**
     * Este metodo termina las operaciones del jugador y adversarios, 
     * se asegura de que tabla este actualizada, luego crea la traza
     */
    @SuppressWarnings("unchecked")
    public static void detenereImprimir(){
        SwingUtilities.invokeLater(() -> {
            new Thread(() -> {
                final Jugador jugador = PanelPrincipal.Player;
                synchronized (jugador) {
                    try {
                        jugador.wait();                                             // detiene las tareas del jugador
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            new Thread(() -> {
                final Enemigo adversario = PanelPrincipal.Rival;
                synchronized (adversario) {
                    try {
                        adversario.wait();                                          // detiene las tareas del adversario
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            
            TableModel dataModel = tabla.getModel();
            String[][] data = new String[dataModel.getRowCount()][dataModel.getColumnCount()];
            for (int row = 0; row < dataModel.getRowCount(); row++){
                for (int column = 0; column < dataModel.getColumnCount(); column++){
                    data[row][column] = dataModel.getValueAt(row, column).toString();
                }
            }
            
            Traza traza = new Traza(data);                                          // se crea la traza
            traza.trazadeEjecucion();
            traza.datosaTexto();
            traza.trazadeEjecucionlinguistica();

            final Jugador jugador = PanelPrincipal.Player;
            synchronized (jugador) {
                jugador.notify();                                                   // continúa las tareas del jugador
            }
            final Enemigo adversario = PanelPrincipal.Rival;
            synchronized (adversario) {
                adversario.notify();                                                // continúa las tareas del adversario
            }
        });
    }
    
    /**
     * Este método retorna la ultima fila de la tabla, se penso para ser usado 
     * desde el hilo que controla al jugador.
     * @return Array con la ultima fila de la tabla, de no haber filas se retorna un array vacio.
     */
    public static String[] getLatestRow() {
        String[] latestRow = null;
        if (PanelPrincipal.Rival != null) {
            new Thread(() -> {
                final Enemigo adversario = PanelPrincipal.Rival;
                synchronized (adversario) {
                    try {
                        adversario.wait();                                          // detiene las tareas del adversario
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            TableModel dataModel = (tabla != null) ? tabla.getModel() : null;
            if (dataModel != null) {
                int rowCount = dataModel.getRowCount();
                if (rowCount > 1) {
                    int columnCount = dataModel.getColumnCount();
                    latestRow = new String[columnCount];
                    int row = rowCount-1;
                    for (int column = 0; column < columnCount; column++)
                        latestRow[column] = dataModel.getValueAt(row, column).toString();
                }
            }
            final Enemigo adversario = PanelPrincipal.Rival;
            synchronized (adversario) {
                adversario.notify();                                                // continúa las tareas del adversario
            }
        }
        return latestRow;
    }
    
    public void load() {
        this.setLayout(new BorderLayout());
        
        String[] cabeceras = 
                new String[] {      "Jugador", 
                                    "Adversario", 
                                    "Recompensa", 
                                    "protecAdv", 
                                    "distanciaJ-A", 
                                    "distanciaA-R", 
                                    "distanciaJ-R", 
                                    "RecObtenida", 
                                    "Tiempo", 
                                    "Agente"};
        
        Object[][] datos =  new Object[0][cabeceras.length];
        
        tabla.setModel(new DefaultTableModel(datos,cabeceras) {
            Class[] tipoColumn = {  java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class, 
                                    java.lang.String.class};

            boolean[] editColum = { false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false, 
                                    false};

            @Override
            public Class getColumnClass(int indColum) {
               return tipoColumn[indColum];
            }

            @Override
            public boolean isCellEditable(int indFila, int indColum) {
                return editColum[indColum];
            }
        });
        
        scroll_tablaResultados.setViewportBorder(new BevelBorder(BevelBorder.RAISED));
        scroll_tablaResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll_tablaResultados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll_tablaResultados.setBackground(Color.ORANGE);
        scroll_tablaResultados.setViewportView(tabla);
        
        this.add(scroll_tablaResultados);
        this.setVisible(true);
    }
    
    public static void insertarFila(int         jugadorx, 
                                    int         jugadory, 
                                    int         adversariox, 
                                    int         adversarioy, 
                                    double      proteccionA1, 
                                    List<Point> recompensas,
                                    int         recompensa,
                                    long        tiempo, 
                                    char        agente
                                    )
    {
        SwingUtilities.invokeLater(() -> {
            if(recompensa == 0)
                recompensa_cercana = (!recompensas.isEmpty()) ? recompensaCercana(jugadorx, jugadory, jugadorx, jugadory, recompensas) : recompensa_cercana;
            recompensas_capturadas = recompensas_capturadas + recompensa;
            int i = 0;
            Object[]fila=new Object[tabla.getColumnCount()];
            fila[i++]=""+jugadorx+","+jugadory;
            fila[i++]=""+adversariox+","+adversarioy;
            fila[i++]=""+recompensa_cercana.x+","+recompensa_cercana.y;
            fila[i++]=""+proteccionA1;
            fila[i++]=""+distancia(jugadorx,    jugadory,    adversariox,          adversarioy);   
            fila[i++]=""+distancia(adversariox, adversarioy, recompensa_cercana.x, recompensa_cercana.y);
            fila[i++]=""+distancia(jugadorx,    jugadory,    recompensa_cercana.x, recompensa_cercana.y);
            fila[i++]=""+recompensa;
            fila[i++]=""+tiempo;
            fila[i++]=""+agente;
             
            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.addRow(fila);
             
             
            Dimension tamanhoTabla = tabla.getSize();
            Point p = new Point(0,tabla.getHeight());
            scroll_tablaResultados.getViewport().setViewPosition(p);
        });
 
    }
     
}