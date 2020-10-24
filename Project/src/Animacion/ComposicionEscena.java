package Animacion;

import java.awt.Image;
import javax.swing.JPanel;
import PanelPrincipal.Mapa;

public class ComposicionEscena {
    
    private Image ComposicionBosque;
    private Escenografia Recursos=new Escenografia();
    private JPanel Escena;
    
    //constructor recibe un jpanel
    /**
     * Constructor de ComposicionEscena
     * @param JPanel Este parametro representa el panel donde se píntara parte de la escenografia del juego
     */
    public ComposicionEscena(JPanel Escena){
    this.Escena=Escena;
                                           }
    
    // metodo que arma al escenario antes de pintarlo
    /**
     * Metodo que Arma el escenario y ubica las imagenes en JPanel
     * @param Nivel Este valor representa un nivel de los laberintos, como solo hay un nivel implementado
     * se debe entregar el valor 1.
     */
    public void ConstruirBosque(int Nivel){
     ComposicionBosque  = Escena.createImage(Escena.getWidth(),Escena.getHeight());
     ComposicionBosque.getGraphics().fillRect(0,0,Escena.getWidth(),Escena.getHeight());
     byte Laberinto[][] = Mapa.getLaberinto(Nivel);
     
     for(int i=0;i<Laberinto.length;i++)
         for(int j=0;j<Laberinto[0].length;j++){
             ComposicionBosque.getGraphics().drawImage(Recursos.getPiso() ,(j*30)+Mapa.CENTRAR_X,(i*30)+Mapa.CENTRAR_Y,null);
              if(Laberinto[i][j]==Mapa.ARBOL)
             ComposicionBosque.getGraphics().drawImage(Recursos.getArbol(),(j*30)+Mapa.CENTRAR_X,(i*30)+Mapa.CENTRAR_Y,null);
              if(Laberinto[i][j]==Mapa.META)
             ComposicionBosque.getGraphics().drawImage(Recursos.getMeta(),(j*30)+Mapa.CENTRAR_X,(i*30)+Mapa.CENTRAR_Y, null);
                                                }
    }
    
    //metodo que retornar el escenario armado para poder pintarlo posteriormente
    public Image getComposicionBosque(){ return ComposicionBosque;}
    
}

