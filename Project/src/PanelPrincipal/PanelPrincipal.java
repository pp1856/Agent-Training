package PanelPrincipal;

import Animacion.Escenario;
import Animacion.Premios;
import GestionDistancias.GestionDistancias;
import GestionDistancias.GestionaArchivos;
import MenuInicio.MenuPrincipal;
import Personaje_Enemigo.Enemigo;
import Personaje_Jugador.Jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelPrincipal extends JFrame {

    private final TablaDistancias TablaDatos = new TablaDistancias();
    private final GestionDistancias CalculaDistancia = new GestionDistancias();
    public static Premios Pokeball = new Premios();
    private final Escenario Cuadro;
    public static Jugador Player;
    public static Enemigo Rival;
    private final GestionaArchivos Participantes;
    
    public static Tabla tabla;

    public PanelPrincipal(boolean TipoJugador) {
        Player = new Jugador(CalculaDistancia, Pokeball, TablaDatos, TipoJugador);
        Rival  = new Enemigo(CalculaDistancia, Player, TablaDatos, TipoJugador);
        Cuadro = new Escenario(Player, Rival, Pokeball, CalculaDistancia);
        Participantes = new GestionaArchivos(Player, Rival, CalculaDistancia, TablaDatos, TipoJugador);
        
        tabla = new Tabla();
        tabla.load();

        this.setEstructura();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(1920, 561));
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("Proyecto de Título - Universidad del Bío-Bío");
        this.addKeyListener(Player);
        Player.setCuadro(Cuadro);
        this.setFocusable(true);
        Cuadro.IniciarPintado(1);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Sprite_Utileria/ubb.jpg"));
        this.setIconImage(icon);
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setMaximizedBounds(env.getMaximumWindowBounds());
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
    }

    private void setEstructura() {

        this.setLayout(new GridBagLayout());  

        GridBagConstraints Ubicador = new GridBagConstraints();

        Ubicador.gridx = 0;
        Ubicador.gridy = 0;
        Ubicador.gridwidth = 1;
        Ubicador.gridheight = 1;
        Ubicador.weightx = 0;
        Ubicador.weighty = 0;
        Ubicador.anchor = GridBagConstraints.CENTER;

        JPanel Fondo = new JPanel();
        Fondo.setBackground(Color.BLACK);
        Fondo.setLayout(new GridBagLayout());
        Fondo.add(Cuadro, Ubicador);

        Ubicador.weightx = 1;
        Ubicador.weighty = 1;
        Ubicador.fill = GridBagConstraints.BOTH;
        this.add(Fondo, Ubicador);

        Ubicador.gridx = 1;
        Ubicador.gridy = 0;
        Ubicador.gridwidth = 1;
        Ubicador.gridheight = 1;
        Ubicador.weightx = 6;
        Ubicador.weighty = 1;
        Ubicador.fill = GridBagConstraints.BOTH;
        //this.add(TablaDatos.getScroll(), Ubicador);
        this.add(tabla, Ubicador);
        
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MenuPrincipal formulario=new MenuPrincipal();
                formulario.setVisible(true);
            }
        });
    }
}
