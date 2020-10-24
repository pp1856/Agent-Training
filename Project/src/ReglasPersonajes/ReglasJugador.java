package ReglasPersonajes;

import GestionDistancias.GestionDistancias;
import java.awt.Point;
import java.util.ArrayList;
import PanelPrincipal.Mapa;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

public class ReglasJugador {
    private ArrayList <Point> Pokeball;
    private boolean bandera=false;
    private GestionDistancias  distancia;
    private boolean TipoJugador;
    /**
     * 
     * @param Premios Lista de los Premios
     * @param Distancia Referencia a la Clases GestionDistancias
     */
    public ReglasJugador(ArrayList<Point> Premios, GestionDistancias Distancia, boolean TipoJugador){
        this.Pokeball=Premios;
        this.distancia=Distancia;
        this.TipoJugador=TipoJugador;
    }

    /**
     * Reglas para que los personajes se puedan mover por el escenario,
     * siempre que haya un camino disponible.
     * @param Laberinto Nivel del Escenario
     * @param Y Posición del Jugador en el Eje Y
     * @param X Posición del Jugador en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoArriba(byte Laberinto[][], int Y, int X)
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
     * @param Y Posición del Jugador en el Eje Y
     * @param X Posición del Jugador en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoAbajo(byte Laberinto[][], int Y, int X)
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
     * @param Y Posición del Jugador en el Eje Y
     * @param X Posición del Jugador en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoIzquierda(byte Laberinto[][], int Y, int X)
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
     * @param Y Posición del Jugador en el Eje Y
     * @param X Posición del Jugador en el Eje X
     * @return True, si es posible moverse. False, si no es posible moverse.
     */
    public boolean getCaminoDerecha(byte Laberinto[][], int Y, int X)
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
     * Metodo que Elimina los premios cuando el jugador pasar por la posicion donde estan pintados
     * @param Y Posición del Jugador en el eje Y
     * @param X Posición del Jugador en el eje X
     * @return true si se elimina un premio, false en caso contrario
     */
    public boolean EliminarPremio(int Y, int X) {
        boolean result = false;
        for (int k = 0; k <Pokeball.size(); k++) {
            if(Pokeball.get(k).x==X && Pokeball.get(k).y==Y && bandera==false){
//                        System.out.println("Tamaño: "+Pokeball.size());
                    Pokeball.remove(k);
                    distancia.setPremio(true);
                /*if(Pokeball.size()==2){
                    Pokeball.clear();
                    distancia.setPremio(true);
                }*/
                result = true;
            }
            if(Pokeball.isEmpty() && bandera==false){
                Pokeball.add(new Point(22, 4));
                bandera=true;
            }
            if(Pokeball.get(0).x==X && Pokeball.get(0).y==Y && bandera==true){
                Pokeball.remove(0);
                distancia.setSeMueve(false);
                distancia.setPremio(true);
                distancia.setMeta(false);
                if(TipoJugador)
                    JOptionPane.showMessageDialog(null, "Ganaste");
                result = true;
            }
        }
        return result;
    }
      
//    public boolean esMeta( int X, int Y)
//    {
//        if(X==23 && Y==4){
//            System.out.println("meta");
//            JOptionPane.showMessageDialog(null, "Ganaste");
//            return false;}
//        return true;
//    }
    
    /**
     * 
     * @param X Posicion en Pixel en el eje X del Enemigo.
     * @param Y Posicion en Pixel en el eje Y del Enemigo.
     * @param X1 Posicion en Pixel en el eje X del Jugador.
     * @param Y1 Posicion en Pixel en el eje X del Jugador.
     */
     public boolean Perder(double X, double Y, double X1, double Y1)
    {
        Rectangle r1 = new Rectangle((int)X,(int)Y,25,25);
        Rectangle r2 = new Rectangle((int)X1,(int)Y1,30,30);
        
        if(r1.intersects(r2)){
            distancia.setMeta(false);
            distancia.setSeMueve(false);
            System.out.println("perdio jugador");
            JOptionPane.showMessageDialog(null, "Perdiste");
            return true;
        }
        
        return false;
    }
}
