package Personaje_Enemigo;
import Animacion.Premios;
import PanelPrincipal.Tabla;
import PanelPrincipal.PanelPrincipal;
import GestionDistancias.GestionDistancias;
import IA.AEstrella;
import ReglasPersonajes.ReglasEnemigo;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import PanelPrincipal.Mapa;
import PanelPrincipal.TablaDistancias;
import Personaje_Jugador.Jugador;
import java.util.List;

public class Enemigo {
     
    private final Image Izquierda;
    private final Image Derecha;
    private final Image Arriba;
    private final Image Abajo;
    private static final byte DERECHA=1;
    private static final byte IZQUIERDA=2;
    private static final byte ARRIBA=3;
    private static final byte ABAJO=4;

    private byte DireccionAccion=4;
    
    private boolean Flotando;
    
    private int X;
    private int Y;
    private double PixelX;
    private double PixelY;
    
    private JPanel Cuadro;
    byte Laberinto[][]=Mapa.getLaberinto(1);
    private final ReglasEnemigo Reglas;
    private final GestionDistancias Distancia;
    private final Jugador jugador;
    private TablaDistancias tabla;
    private boolean TipoJugador;

    /**
     * 
     * @param Distancia Referencia hacia la Clase GestionDistancias.
     * @param Jugador Referencia hacia la clase Jugador.
     * @param Tabla Referencia hacia la Tabla
     * @param JugadorTipo Indica el valor si el jugador será entrenado o no. TRUE: Modo Entrenamiento FALSE: Modo Entrenado
     */
    public Enemigo(GestionDistancias Distancia, Jugador Jugador, TablaDistancias Tabla, boolean JugadorTipo){
        Point Pixel = Mapa.getPosicionEnemigo(0);
        
        //Almacenan la posición en pixeles del Enemigo.
        PixelX=Pixel.getX()*30;
        PixelY=Pixel.getY()*30;
        
        //Alamacenan la posición dentro de la matriz del Enemigo.
        X=(byte)Pixel.getX();
        Y=(byte)Pixel.getY();
        
        this.Distancia=Distancia;
        this.jugador=Jugador;
        this.tabla=Tabla;
        this.TipoJugador=JugadorTipo;
        
        Derecha = new ImageIcon(getClass().getResource("/Sprite_Enemigo/Enemigo_Derecha.gif")).getImage();
        Izquierda = new ImageIcon(getClass().getResource("/Sprite_Enemigo/Enemigo_Izquierda.gif")).getImage();
        Arriba = new ImageIcon(getClass().getResource("/Sprite_Enemigo/Enemigo_Arriba.gif")).getImage();
        Abajo = new ImageIcon(getClass().getResource("/Sprite_Enemigo/Enemigo_Abajo.gif")).getImage();
        /**
         * Referencia a la Clases ReglaJugador, donde se obtienen las reglas para que el Personaje Enemigo 
         * Pueda dezplazarse por el escenario.
         */
        this.Reglas= new ReglasEnemigo(Distancia);
        
        this.moverEnemigo();
        this.moverEnemigoAlterno();
    }
    
    public void setDireccion(int DireccionAccion){
    switch(DireccionAccion)
    {
        case DERECHA: this.DireccionAccion=DERECHA; break;
        case IZQUIERDA: this.DireccionAccion=IZQUIERDA; break;
        case ARRIBA: this.DireccionAccion=ARRIBA; break;
        case ABAJO: this.DireccionAccion=ABAJO; break;
        default: this.DireccionAccion=ABAJO;
    }
    }
    
    public Image getSpriteAccion(){
    if(DireccionAccion==DERECHA)
        return Derecha;
    if(DireccionAccion==IZQUIERDA)
        return Izquierda;
    if(DireccionAccion==ARRIBA)
        return Arriba;
    if(DireccionAccion==ABAJO)
        return Abajo;
    return Abajo;
    }
    
    public void moverEnemigo(){
        
        if (!TipoJugador) { return;}
        
    if(Flotando)return;
    
       new Thread("Hilo Movimiento Enemigo"){

        @Override
        public void run() {
            //Cuando el valor de isSeMueve es falso, el Enemigo dejara de moverse por el escenario.
            while (Distancia.isSeMueve()){
                //Habilitar busqueda Inteligente.
         AEstrella star = new AEstrella(new Point(X, Y), new Point(jugador.getX(),jugador.getY()));
                    if(star.procesar()==4)
                    if (Reglas.getCaminoArribaEnemigo(Laberinto, Y, X)) {
                        CaminarArriba();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                if (star.procesar() == 2) {
                    if (Reglas.getCaminoIzquierdaEnemigo(Laberinto, Y, X)) {
                        CaminarIzquierda();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                }

                if (star.procesar() == 1) {
                    if (Reglas.getCaminoDerechaEnemigo(Laberinto, Y, X)) {
                        CaminarDerecha();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                }

                if (star.procesar() == 3) {
                    if (Reglas.getCaminoAbajoEnemigo(Laberinto, Y, X)) {
                        CaminarAbajo();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                }
                
                long tiempo = System.currentTimeMillis();                       // tiempo actual
                if(jugador.tiempoInicio==0)jugador.tiempoInicio = tiempo;

                Tabla.insertarFila( jugador.getX(),                              // añade datos a la tabla
                                    jugador.getY(),
                                    X,
                                    Y,
                                    Tabla.proteccion(jugador,PanelPrincipal.Rival),
                                    PanelPrincipal.Pokeball.ListaPremios,
                                    0,
                                    tiempo - jugador.tiempoInicio,
                                    'A');
                
                if(jugador.getX()==X&&jugador.getY()==Y) Tabla.detenereImprimir();    // si el jugador alcanza al adversario todas las tareas se cancelan
                if(GestionDistancias.Meta==false) Tabla.detenereImprimir();
                
                Reglas.Perder(jugador.getPixelX(),jugador.getPixelY(),PixelX,PixelY);                    
                //Velocidad con la que el Enemigo se movera, a mayor valor, más lento será el Enemigo.
                try{Thread.sleep(650);}catch(InterruptedException e){}
            }
        }
       }.start();
    }
    
    public void moverEnemigoAlterno(){
        if (TipoJugador) { return;}
        
    if(Flotando)return;
    
       new Thread("Hilo Movimiento Enemigo"){

        @Override
        public void run() {
            //Cuando el valor de isSeMueve es falso, el Enemigo dejara de moverse por el escenario.
            while (Distancia.isSeMueve()){
                //Habilitar busqueda Inteligente.
                AEstrella star = new AEstrella(new Point(X, Y), new Point(jugador.getX(),jugador.getY()));
                if(star.procesar()==4)
                    if (Reglas.getCaminoArribaEnemigo(Laberinto, Y, X)) {
                        CaminarArriba();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                if (star.procesar() == 2)
                    if (Reglas.getCaminoIzquierdaEnemigo(Laberinto, Y, X)) {
                        CaminarIzquierda();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                if (star.procesar() == 1)
                    if (Reglas.getCaminoDerechaEnemigo(Laberinto, Y, X)) {
                        CaminarDerecha();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                if (star.procesar() == 3)
                    if (Reglas.getCaminoAbajoEnemigo(Laberinto, Y, X)) {
                        CaminarAbajo();
                        Distancia.setPosEneX(X,PixelX);
                        Distancia.setPosEneY(Y,PixelY);
                    }
                
                long tiempo = System.currentTimeMillis();                       // tiempo actual
                if(jugador.tiempoInicio==0)jugador.tiempoInicio = tiempo;

                Tabla.insertarFila( jugador.getX(),                              // añade datos a la tabla
                                    jugador.getY(),
                                    X,
                                    Y,
                                    Tabla.proteccion(jugador,PanelPrincipal.Rival),
                                    PanelPrincipal.Pokeball.ListaPremios,
                                    0,
                                    tiempo - jugador.tiempoInicio,
                                    'A');
                
                if(jugador.getX()==X&&jugador.getY()==Y) Tabla.detenereImprimir();    // si el jugador alcanza al adversario todas las tareas se cancelan
                if(GestionDistancias.Meta==false) Tabla.detenereImprimir();
                
                Reglas.Perder(PixelX,PixelY,jugador.getPixelX(),jugador.getPixelY());                    
                //Velocidad con la que el Enemigo se movera, a mayor valor, más lento será el Enemigo.
                try{Thread.sleep(900);}catch(InterruptedException e){}
            }
        }
       }.start();
    }
    
    //Metodos para mover al Enemigo.
    private void CaminarArriba(){
      Flotando=true;
      setDireccion(Enemigo.ARRIBA);
      new Thread(){
       @Override
          public void run(){
              for(int i=0;i<100;i++){
              PixelY-=0.3;
                  try { Cuadro.repaint();} 
                  catch (NullPointerException e) {}
              try{Thread.sleep(5);}catch(Exception e){}
                                   }
              Y--; Flotando=false;}
      }.start();
    }
    
    private void CaminarIzquierda(){
        Flotando=true;
        setDireccion(Enemigo.IZQUIERDA);
      new Thread(){
       @Override
          public void run(){
              for(int i=0;i<100;i++){
              PixelX-=0.3;
                try { Cuadro.repaint();} 
                catch (NullPointerException e) {}
              try{Thread.sleep(5);}
              catch(Exception e){}
                                   }
              X--; Flotando=false;
           }
      }.start();
    }
    
    private void CaminarAbajo(){
        Flotando=true;
        setDireccion(Enemigo.ABAJO);
      new Thread(){
       @Override
          public void run(){
              for(int i=0;i<100;i++){
              PixelY+=0.3;
                try {Cuadro.repaint();}
                catch (NullPointerException e) {}
              try{Thread.sleep(5);}catch(Exception e){}
                                   }
              Y++; Flotando=false;}
      }.start();
    }
     
    private void CaminarDerecha(){
        Flotando=true;
        setDireccion(Enemigo.DERECHA);
      new Thread(){
       @Override
          public void run(){
              for(int i=0;i<100;i++){
              PixelX+=0.3;
                try {Cuadro.repaint();} 
                catch (NullPointerException e) {}
              try{Thread.sleep(5);}catch(Exception e){}
                                   }
              X++; Flotando=false;}
      }.start();
    }
    
    public double getPixelX(){return PixelX;}
    public double getPixelY(){return PixelY;}
    public void setCuadro(JPanel Cuadro){this.Cuadro=Cuadro;}
    public int getX() {return X;}
    public int getY() {return Y;}
    

}
