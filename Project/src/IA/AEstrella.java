package IA;

import PanelPrincipal.Mapa;
import java.awt.Point;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class AEstrella {
    
    private byte Laberinto[][];
    private Point Enemigo;
    private Point Jugador;
    
    private Queue<Estado> Estados = new PriorityQueue<Estado>();
    private HashMap<Point,Estado> Historial = new HashMap<Point,Estado>();
    
    /**
     * 
     * @param Buscador Posicion de quien desea que busque.
     * @param Objetivo Posioción de a que o quien desea que busque.
     */
    public AEstrella(Point Buscador,Point Objetivo){
        Laberinto= Mapa.getLaberinto(1);
        this.Enemigo=Buscador;
        this.Jugador=Objetivo;
    }
   
    
    public byte procesar(){
      
        Estados.clear();
        Historial.clear();
        
        Point Jugador = (Point)this.Jugador.clone();
        Point Enemigo = (Point)this.Enemigo.clone();

        Estados.add(new Estado(null,Enemigo,0));
        Historial.put(Enemigo,new Estado(null,Enemigo,0));
                
        while(!Estados.isEmpty())
         if(this.generarEstados(Estados.poll(),Jugador))
           break;
        
        //No hay camino
        if(Estados.isEmpty())return -1;
        return obtenerRespuesta(Enemigo,Jugador);
    
    }
    
    private boolean generarEstados(Estado Actual,Point Jugador){
        
        //Arriba
        if(Actual.getMovimiento().y-1>=0 && 
           Laberinto[Actual.getMovimiento().y-1][Actual.getMovimiento().x]!= Mapa.ARBOL){
        Estado Aux = new Estado(Actual,new Point(Actual.getMovimiento().x,Actual.getMovimiento().y-1),Actual.getCosto()+1);
        if(!Historial.containsKey(Aux.getMovimiento())){
         Estados.add(Aux); 
         Historial.put(Aux.getMovimiento(), Aux);
                                       }
        if(Aux.getMovimiento().equals(Jugador))return true;
                                         }
        //Abajo
        if(Actual.getMovimiento().y+1<Laberinto.length &&
           Laberinto[Actual.getMovimiento().y+1][Actual.getMovimiento().x]!= Mapa.ARBOL){
        Estado Aux = new Estado(Actual,new Point(Actual.getMovimiento().x,Actual.getMovimiento().y+1),Actual.getCosto()+1);
        if(!Historial.containsKey(Aux.getMovimiento())){
         Estados.add(Aux); 
         Historial.put(Aux.getMovimiento(), Aux);
                                       }
        if(Aux.getMovimiento().equals(Jugador))return true;
                                                     }
        //Izquierda
        if(Actual.getMovimiento().x-1>=0 &&
           Laberinto[Actual.getMovimiento().y][Actual.getMovimiento().x-1]!= Mapa.ARBOL){
        Estado Aux = new Estado(Actual,new Point(Actual.getMovimiento().x-1,Actual.getMovimiento().y),Actual.getCosto()+1);
        if(!Historial.containsKey(Aux.getMovimiento())){
         Estados.add(Aux); 
         Historial.put(Aux.getMovimiento(), Aux);
                                       }
        if(Aux.getMovimiento().equals(Jugador))return true;
                                                     }
        //Derecha
        if(Actual.getMovimiento().x+1<Laberinto[0].length &&
           Laberinto[Actual.getMovimiento().y][Actual.getMovimiento().x+1]!= Mapa.ARBOL){
        Estado Aux = new Estado(Actual,new Point(Actual.getMovimiento().x+1,Actual.getMovimiento().y),Actual.getCosto()+1);
        if(!Historial.containsKey(Aux.getMovimiento())){
         Estados.add(Aux); 
         Historial.put(Aux.getMovimiento(), Aux);
                                       }
        if(Aux.getMovimiento().equals(Jugador))return true;
                                                     }
        
        return false;
    }    
    
    private byte obtenerRespuesta(Point Enemigo,Point Jugador){
        
        Estado Actual = Historial.get(Jugador);
        
        if(Actual.getPadre()==null)return-1;
        
        while(Actual.getPadre().getPadre()!=null){
         //System.out.println(Actual.getMovimiento());
         Actual = Actual.getPadre();
                                                 }
        
        // 1 - Derecha
        if(Enemigo.x<Actual.getMovimiento().x) return 1;
        // 2 - Izquierda
        if(Enemigo.x>Actual.getMovimiento().x) return 2;
        // 3 - Abajo
        if(Enemigo.y<Actual.getMovimiento().y) return 3;
        // 4 - Arriba
        if(Enemigo.y>Actual.getMovimiento().y) return 4;
        return -1;
    
}
    
}
