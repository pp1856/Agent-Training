package Animacion;

import GestionDistancias.GestionDistancias;
import Personaje_Enemigo.Enemigo;
import Personaje_Jugador.Jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JPanel;
import PanelPrincipal.Mapa;
import javax.swing.ImageIcon;

public class Escenario extends JPanel {
    
    private Thread HiloPintado;
    private final Image Montaje;
    private final ComposicionEscena Composicion;
    private int Nivel;
    private Jugador Player;
    private final AffineTransform Translacion= new AffineTransform();
    private Enemigo Rival;
    private Premios Premios=new Premios();
    private ArrayList<Point>ListaPremios;

    
    //constructor recibe la creacion del jugador y el enemigo para poder pintarlos en el escenario
    /**
     * Constructor de clase Escenario
     * @param Jugador, recibe un dato de tipo Jugador, para poder ubicar y pintar al persoje,
     * para pdoer ubicarlo y pintarlo en el Escenario.
     * @param Enemigo recibe un dato de tipo Enemigo, para poder ubicar y pintar al persoje,
     * para pdoer ubicarlo y pintarlo en el Escenario.
     * @param Premios, recibe un dato de tipo premios, de donde se obtendra una lista que contiene las posiciones de los
     * premios en el mapa, para poder pintarlos.
     * @param 
     * @return Valor representa si encontro o no 1 - Encontro 0 - No Encontro
     */
    public Escenario(Jugador Player,Enemigo Rival,Premios PokePremio, GestionDistancias Distancias){    
        this.setPreferredSize(new Dimension(900, 500));
        this.setBackground(Color.black);
        Montaje     =createImage(getWidth(),getHeight());
        Composicion = new ComposicionEscena(this);
        this.Player=Player;
        //se agrega el metodo para interactuar el movimiento del jugador con el teclado
        this.addKeyListener(Player);
        this.Rival=Rival;
        ListaPremios=PokePremio.getListaPremios();
        //this.GestionDistancias=GestionDistancias;
    }

    // se pinta todo el escenario con sus componentes, piso, arboles, jugador, enemigo y premios
    @Override
    public void paint(Graphics g){
        

     g.drawImage(Composicion.getComposicionBosque(),0,0, this);
        for (int i = 0; i < ListaPremios.size(); i++) {
                 g.drawImage(Premios.getPremio(),ListaPremios.get(i).x*30+Mapa.CENTRAR_X,
                         ListaPremios.get(i).y*30+Mapa.CENTRAR_Y, this);
        }

     Translacion.setToTranslation(Player.getPixelX()+Mapa.CENTRAR_X,Player.getPixelY()+Mapa.CENTRAR_Y);
     ((Graphics2D)g).drawImage(Player.getSpriteAccion(), Translacion, this);
     
     Translacion.setToTranslation(Rival.getPixelX()+Mapa.CENTRAR_X,Rival.getPixelY()+Mapa.CENTRAR_Y);
     ((Graphics2D)g).drawImage(Rival.getSpriteAccion(), Translacion, this);             
     //super.repaint();
    }
    
    
    public void IniciarPintado(int Nivel){
      if(HiloPintado!=null && HiloPintado.isAlive())return;
      this.Nivel=Nivel;
      this.ConstruirComposicion();
      HiloPintado=new Thread("HiloPintado"){
            @Override
            public void run() {

                while(true){
                    try{
                     repaint();
                     Thread.sleep(10);
                    } catch (InterruptedException e) {
                    break;
                    }
                }  
            }            
        };
      HiloPintado.setPriority(Thread.MAX_PRIORITY);
      HiloPintado.start();
    }
    

    private void ConstruirComposicion(){
    Composicion.ConstruirBosque(Nivel);
    }
   
}

