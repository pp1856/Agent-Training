package IA;


import PanelPrincipal.Mapa;
import PanelPrincipal.PanelPrincipal;
import PanelPrincipal.Tabla;
import PanelPrincipal.Traza;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class BusquedaA {
    
    private final byte lienzo[][];
    private final int columnSize;
    private final int rowSize;
    private final Queue<Estado> colaEstados;
    private final Map<Point, Estado> historial;
    private final ArrayList<Point> pasos;
    public Estado inicial;
    public Estado objetivo;
    public boolean exito;
    public boolean evadir;
    
    public BusquedaA(){
        lienzo = Mapa.getLaberinto(1);
        columnSize = lienzo.length;
        rowSize = lienzo[0].length;      
        colaEstados = new PriorityQueue<>();
        historial = Collections.synchronizedMap(new HashMap<>());        
        pasos = new ArrayList<>();
    }
    
    public byte search(int index){
        this.evadir = (Traza.valores[index]).equals(Traza.v_Evadir);
        colaEstados.clear();
        historial.clear();
        pasos.clear();
        inicial = new Estado(null, new Point(PanelPrincipal.Player.getX(), PanelPrincipal.Player.getY()), 0.0);
        objetivo = new Estado(null, Tabla.recompensaCercana(PanelPrincipal.Player.getX(), PanelPrincipal.Player.getY(), PanelPrincipal.Player.getX(), PanelPrincipal.Player.getY(), PanelPrincipal.Pokeball.ListaPremios), 0.0);
        exito = inicial.equals(objetivo);
        
        distanciaG(inicial,objetivo.getMovimiento());
        distanciaH(inicial,new Point(PanelPrincipal.Rival.getX(), PanelPrincipal.Rival.getY()));
        colaEstados.add(inicial);
        historial.put(inicial.getMovimiento(), inicial);

        while( !colaEstados.isEmpty() && !exito ) {
            Estado temp = colaEstados.poll();
            moverArriba(temp);
            moverAbajo(temp);
            moverIzquierda(temp);
            moverDerecha(temp);
        }
        System.out.print("BUSQUEDA("+evadir+"): ");
        if( exito ){
            this.calcularRuta();
            System.out.println("EXITO - PASOS: "+ pasos.size());
            Point movimiento = (pasos.size() >= 2) ? pasos.get(1) : pasos.get(0);
            // 1 - Derecha
            if(inicial.getMovimiento().x < movimiento.x) return 1;
            // 2 - Izquierda
            if(inicial.getMovimiento().x > movimiento.x) return 2;
            // 3 - Abajo
            if(inicial.getMovimiento().y < movimiento.y) return 3;
            // 4 - Arriba
            if(inicial.getMovimiento().y > movimiento.y) return 4;
            //}
        }
        System.out.println("La ruta no pudo calcularse");
        return -1;
    }
    
    private void moverArriba(Estado e){
        
        if( e.getMovimiento().y > 1 ) {
            if( lienzo[e.getMovimiento().y-1][e.getMovimiento().x] != 5 ) {
                Estado arriba = new Estado(e, new Point(e.getMovimiento().x,e.getMovimiento().y-1), 0);
                if (evadir) {
                    distanciaG(arriba,objetivo.getMovimiento());
                    distanciaH(arriba,new Point(PanelPrincipal.Rival.getX(), PanelPrincipal.Rival.getY()));
                } else {
                    distanciaG(inicial,arriba.getMovimiento());
                    distanciaH(arriba,objetivo.getMovimiento());
                }
                if (!historial.containsKey(arriba.getMovimiento())) {
                    colaEstados.add(arriba);
                    historial.put(arriba.getMovimiento(), arriba);
                    if( arriba.equals(objetivo) ){
                        objetivo = arriba;
                        exito = true;
                    }
                }
            }
        }
        
    }
    
    private void moverAbajo(Estado e){
        
        if( e.getMovimiento().y < columnSize-2 ) {
            if( lienzo[e.getMovimiento().y+1][e.getMovimiento().x] != 5 ) {
                Estado abajo = new Estado(e, new Point(e.getMovimiento().x,e.getMovimiento().y+1), 0);
                if (evadir) {
                    distanciaG(abajo,objetivo.getMovimiento());
                    distanciaH(abajo,new Point(PanelPrincipal.Rival.getX(), PanelPrincipal.Rival.getY()));
                } else {
                    distanciaG(inicial,abajo.getMovimiento());
                    distanciaH(abajo,objetivo.getMovimiento());
                }
                if (!historial.containsKey(abajo.getMovimiento())) {
                    colaEstados.add(abajo);
                    historial.put(abajo.getMovimiento(), abajo);
                    if( abajo.equals(objetivo) ){
                        objetivo = abajo;
                        exito = true;
                    }
                }
            }
        }
        
    }
    
    private void moverIzquierda(Estado e){
        
        if( e.getMovimiento().x > 1 ) {
            if( lienzo[e.getMovimiento().y][e.getMovimiento().x-1] != 5 ) {
                Estado izquierda = new Estado(e, new Point(e.getMovimiento().x-1,e.getMovimiento().y), 0);
                if (evadir) {
                    distanciaG(izquierda,objetivo.getMovimiento());
                    distanciaH(izquierda,new Point(PanelPrincipal.Rival.getX(), PanelPrincipal.Rival.getY()));
                } else {
                    distanciaG(inicial,izquierda.getMovimiento());
                    distanciaH(izquierda,objetivo.getMovimiento());
                }
                if (!historial.containsKey(izquierda.getMovimiento())) {
                    colaEstados.add(izquierda);
                    historial.put(izquierda.getMovimiento(), izquierda);
                    if( izquierda.equals(objetivo) ){
                        objetivo = izquierda;
                        exito = true;
                    }
                }
            }
        }
        
    }
    
    private void moverDerecha(Estado e){
        
        if( e.getMovimiento().x < rowSize-2 ) {
            if( lienzo[e.getMovimiento().y][e.getMovimiento().x+1] != 5 ) {
                Estado derecha = new Estado(e, new Point(e.getMovimiento().x+1,e.getMovimiento().y), 0);
                if (evadir) {
                    distanciaG(derecha,objetivo.getMovimiento());
                    distanciaH(derecha,new Point(PanelPrincipal.Rival.getX(), PanelPrincipal.Rival.getY()));
                } else {
                    distanciaG(inicial,derecha.getMovimiento());
                    distanciaH(derecha,objetivo.getMovimiento());
                }
                if (!historial.containsKey(derecha.getMovimiento())) {
                    colaEstados.add(derecha);
                    historial.put(derecha.getMovimiento(), derecha);
                    if( derecha.equals(objetivo) ){
                        objetivo = derecha;
                        exito = true;
                    }
                }
            }
        }
        
    }
    
    //distancia actual al final
    public void distanciaG(Estado a, Point b) {
        a.g = distancia(a.getMovimiento().x, a.getMovimiento().y, b.x, b.y);
    }
    
    //distancia actual a obstaculo
    public void distanciaH(Estado a, Point b) {
        a.h = distancia(a.getMovimiento().x, a.getMovimiento().y, b.x, b.y);
        if(evadir)
            a.Costo=(a.g-(a.h*2));
        else
            a.Costo=a.g + a.h;
    }
    
    public double distancia(int x1,int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    public void calcularRuta() {
        
        Estado predecesor = objetivo;
        do{
            pasos.add(0,predecesor.getMovimiento());
            predecesor = predecesor.getPadre();
        }while( predecesor != null );
        
    }
}
