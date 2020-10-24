package ReglasPersonajes;

import GestionDistancias.GestionDistancias;
import PanelPrincipal.Mapa;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReglasEnemigo{
    private GestionDistancias distancia;
    private ArrayList <Point> Pokeball;
    
    public ReglasEnemigo(GestionDistancias distancia)
    {
    this.distancia=distancia;
    }
    //reglas para que los personajes se puedan mover por el escenario,
    //siempre que haya un camino disponible
    
    
        /**
     * Reglas para que los personajes se puedan mover por el escenario,
     * siempre que haya un camino disponible.
     * @param Laberinto Nivel del Escenario
     * @param Y Posición del Enemigo en el Eje Y
     * @param X Posición del Enemigo en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoArribaEnemigo(byte Laberinto[][], int Y, int X)
    {
        for(int i=0; i<Laberinto.length;i++)
            for (int j = 0; j < Laberinto[0].length; j++) {
                if(Laberinto[Y-1][X]==Mapa.CAMINO || Laberinto[Y-1][X]==Mapa.PERSONAJE 
                        || Laberinto[Y-1][X]==Mapa.PREMIO || Laberinto[Y-1][X]==Mapa.META || Laberinto[Y-1][X]==Mapa.ENEMIGO)
                    return true;
            }
    return false;
    
    }
    
    /**
     * Reglas para que los personajes se puedan mover por el escenario,
     * siempre que haya un camino disponible.
     * @param Laberinto Nivel del Escenario
     * @param Y Posición del Enemigo en el Eje Y
     * @param X Posición del Enemigo en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoAbajoEnemigo(byte Laberinto[][], int Y, int X)
    {
        for(int i=0; i<Laberinto.length;i++)
            for (int j = 0; j < Laberinto[0].length; j++) {
                if(Laberinto[Y+1][X]==Mapa.CAMINO || Laberinto[Y+1][X]==Mapa.PERSONAJE 
                        || Laberinto[Y+1][X]==Mapa.PREMIO || Laberinto[Y+1][X]==Mapa.META|| Laberinto[Y+1][X]==Mapa.ENEMIGO)
                    return true;
            }
    return false;
    
    }
    
    /**
     * Reglas para que los personajes se puedan mover por el escenario,
     * siempre que haya un camino disponible.
     * @param Laberinto Nivel del Escenario
     * @param Y Posición del Enemigo en el Eje Y
     * @param X Posición del Enemigo en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoIzquierdaEnemigo(byte Laberinto[][], int Y, int X)
    {
        for(int i=0; i<Laberinto.length;i++)
            for (int j = 0; j < Laberinto[0].length; j++) {
                if(Laberinto[Y][X-1]==Mapa.CAMINO || Laberinto[Y][X-1]==Mapa.PERSONAJE 
                        || Laberinto[Y][X-1]==Mapa.PREMIO|| Laberinto[Y][X-1]==Mapa.ENEMIGO)
                    return true;
            }
    return false;
    
    }
    
    /**
     * Reglas para que los personajes se puedan mover por el escenario,
     * siempre que haya un camino disponible.
     * @param Laberinto Nivel del Escenario
     * @param Y Posición del Enemigo en el Eje Y
     * @param X Posición del Enemigo en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoDerechaEnemigo(byte Laberinto[][], int Y, int X)
    {
        for(int i=0; i<Laberinto.length;i++)
            for (int j = 0; j < Laberinto[0].length; j++) {
                if(Laberinto[Y][X+1]==Mapa.CAMINO || Laberinto[Y][X+1]==Mapa.PERSONAJE 
                        || Laberinto[Y][X+1]==Mapa.PREMIO || Laberinto[Y][X+1]==Mapa.META|| Laberinto[Y][X+1]==Mapa.ENEMIGO)
                    return true;
            }
    return false;
    
    }
   /**
     * Metodo que ayuda a detener al jugador si hay colisión entre el Enemigo y el Jugador.
     * @param X Posicion en Pixel en el eje X del Enemigo.
     * @param Y Posicion en Pixel en el eje Y del Enemigo.
     * @param X1 Posicion en Pixel en el eje X del Jugador.
     * @param Y1 Posicion en Pixel en el eje X del Jugador.
     */
    public void Perder(double X, double Y, double X1, double Y1)
    {
        Rectangle r1 = new Rectangle((int)X,(int)Y,25,25);
        Rectangle r2 = new Rectangle((int)X1,(int)Y1,28,28);
        
        if(r1.intersects(r2)){
            distancia.setMeta(false);
            distancia.setSeMueve(false);
            System.out.println("perdio enemigo");
            JOptionPane.showMessageDialog(null, "Perdiste");
//            JOptionPane.showMessageDialog(null, "Game Over", "GameO ver", JOptionPane.YES_NO_OPTION);
        }


    }

}
