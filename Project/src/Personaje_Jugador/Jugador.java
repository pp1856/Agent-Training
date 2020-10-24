package Personaje_Jugador;

import Animacion.Premios;
import GestionDistancias.EstadosDistancias4;
import ReglasPersonajes.ReglasJugador;
import GestionDistancias.GestionDistancias;
import GestionDistancias.Movimientos;
import IA.AEstrella;
import IA.BusquedaA;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import PanelPrincipal.Mapa;
import PanelPrincipal.PanelPrincipal;
import PanelPrincipal.Tabla;
import PanelPrincipal.TablaDistancias;
import PanelPrincipal.Traza;
import PanelPrincipal.Traza_1;
import PanelPrincipal.Traza_1v2;
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Jugador implements KeyListener{
    
    /**
     * Atributos que almacena las imagenes que representan al jugador.
     */
    private final Image Arriba   ;
    private final Image Abajo    ;
    private final Image Izquierda;
    private final Image Derecha  ;
    private final Image Abajo_Quieto;
    private final Image Arriba_Quieto;
    private final Image Izquierda_Quieto;
    private final Image Derecha_Quieto;
    
    /**
     * Atributos que almacenan un valor numérico con el cual es utilizado para interpretar la imagen a mostrar.
     */
    public static final byte ARRIBA_CORRER   =0;
    public static final byte ABAJO_CORRER    =1;
    public static final byte IZQUIERDA_CORRER=2;
    public static final byte DERECHA_CORRER  =3;
    public static final byte QUIETO_ABAJO=4;
    public static final byte QUIETO_ARRIBA=5;
    public static final byte QUIETO_IZQUIERDA=6;
    public static final byte QUIETO_DERECHA=7;
    public byte DireccionAccion=4;
    private boolean Quieto;
    
    /**
     * Atributos que almacena la posición del jugador en la matriz.
     */
    private int X;
    private int Y;
    
    /**
     * Atributos que almacenan la posición en la pantalla del jugador.
     */
    private double PixelX;
    private double PixelY;
    
    private boolean Caminando;
    
    private JPanel Cuadro;
    public byte Laberinto [][]= Mapa.getLaberinto(1);
    private final ReglasJugador Reglas;
    private final Premios Premio;
    private final GestionDistancias Distancia;
    private final TablaDistancias tabla;
    public boolean TipoJugador;
    String DireccionActual="null";
    String DireccionAnterior="null";
    private Thread Hilo;
    private boolean Ciclo=true;
    
    /**
     * Almacena los valores del archivo Execution_Trace_1_Linguistic2_filter.txt
     */
    private ArrayList<String> DatosDocumento = new ArrayList<>();
    
    /**
     * Almacena la información contenida en la tabla, cambiando los valores numéricos en palabras.
     */
    private ArrayList<String> DatosTabla = new ArrayList<>();
    
    /**
     * Almacena en cada posicion de la lista N atributos del archivo txt
     * Donde N puede tomar los valores de 4, 5.
     * 
     * <trong>Atributos almacenados</strong>
     * 
     * N:4 
     * DistanciaJugadorEnemigo, DistanciaPremioJugador, DistanciaPremioEnemigo, Protección
     * 
     * N:5
     * DistanciaJugadorEnemigo, DistanciaPremioJugador, DistanciaPremioEnemigo, Protección, Dirección
     * 
     */
    private final ArrayList<Movimientos>        ListaCincoAtributos = new ArrayList<>();
    private final ArrayList<EstadosDistancias4> ListaCuatroAtributos= new ArrayList<>();
    
    public long tiempoInicio;
    private static BusquedaA busqueda;
    
    /**
     * 
     * @param Distancia Referencia hacia la Clase GestionDistancias.
     * @param Premio Referencia hacia la lista de premios(Posiciones).
     * @param Tabla Referencia hacia la tabla de datos
     * @param TipoJugador Indica el valor si el jugador será entrenado o no. TRUE: Modo Entrenamiento FALSE: Modo Entrenado
     */
    public Jugador(GestionDistancias Distancia,Premios Premio,TablaDistancias Tabla, boolean TipoJugador) {
        
        tiempoInicio = 0;

        busqueda = new BusquedaA();
        
        this.Premio = Premio;

        this.tabla=Tabla;
        Point Pixel = Mapa.getPosicionJugador(0);
        //Se obtiene la ubicacion en pixeles del Personaje Jugador
        PixelX = Pixel.getX() * 30;
        PixelY = Pixel.getY() * 30;
        
        // Se obtienela ubicacion en la Matriz del Personaje Jugador
        X = (int) Pixel.getX();
        Y = (int) Pixel.getY();
        
        /**
         * Referencia a la Clase GestionDistancias, donde se hacen los calculos de las distancias a obtener
         * y el nivel de proteccion.
         */
        this.Distancia = Distancia;
        
        /**
         * Referencia a la Clases ReglaJugador, donde se obtienen las reglas para que el Personaje Jugador 
         * Pueda dezplazarse por el escenario.
         */
        Reglas = new ReglasJugador(this.Premio.getListaPremios(),this.Distancia, TipoJugador);
        
        /**
         * Se pasa por referencia al metodo setListaPremios, de la Clase GestionDistancia, la lista de las 
         * coordenadas de los premios, para poder efectuar el calculo de las distancias hacia cada una de ellas.
         */
        this.Distancia.setListaPremios(this.Premio.getListaPremios());
        
        Arriba = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Arriba.gif")).getImage();
        Abajo = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Abajo.gif")).getImage();
        Izquierda = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Izquierda.gif")).getImage();
        Derecha = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Derecha.gif")).getImage();
        Abajo_Quieto = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Quieto_Abajo.png")).getImage();
        Arriba_Quieto = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Quieto_Arriba.png")).getImage();
        Izquierda_Quieto = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Quieto_Izquierda.png")).getImage();
        Derecha_Quieto = new ImageIcon(getClass().getResource("/Sprite_Jugador/Tayzon_Quieto_Derecha.png")).getImage();
        this.TipoJugador = TipoJugador;
        
        //Desactivar funciones para implementar Entrenamiento.
                try {
            leerTXT();
        } catch (Exception e) {}
        JugadorEntrenado();
//        JugadorEntrenado2();
    }
        
    public void setDireccionAccion(int DireccionAccion){
        switch(DireccionAccion){
        case ARRIBA_CORRER    : this.DireccionAccion=ARRIBA_CORRER    ; break;
        case ABAJO_CORRER     : this.DireccionAccion=ABAJO_CORRER     ; break;
        case IZQUIERDA_CORRER : this.DireccionAccion=IZQUIERDA_CORRER ; break;
        case DERECHA_CORRER   : this.DireccionAccion=DERECHA_CORRER   ; break;
        case QUIETO_ABAJO     : this.DireccionAccion=QUIETO_ABAJO     ; break;
        case QUIETO_ARRIBA    : this.DireccionAccion=QUIETO_ARRIBA    ; break;
        case QUIETO_IZQUIERDA : this.DireccionAccion=QUIETO_IZQUIERDA ; break;
        case QUIETO_DERECHA   : this.DireccionAccion=QUIETO_DERECHA   ; break;
        default:               this.DireccionAccion=ABAJO_CORRER    ;
                                                       }
                                                       }
    
    public Image getSpriteAccion(){
       
        if(DireccionAccion==ARRIBA_CORRER)
            return Arriba;
        if(DireccionAccion==ABAJO_CORRER)
            return Abajo;
        if(DireccionAccion==IZQUIERDA_CORRER)
            return Izquierda;
        if(DireccionAccion==DERECHA_CORRER)
            return Derecha;
        if(DireccionAccion==QUIETO_ABAJO)
            return Abajo_Quieto;
        if(DireccionAccion==QUIETO_ARRIBA)
            return Arriba_Quieto;
        if(DireccionAccion==QUIETO_IZQUIERDA)
            return Izquierda_Quieto;
        if(DireccionAccion==QUIETO_DERECHA)
            return Derecha_Quieto;
       
        return Abajo_Quieto;
                                 }

    @Override
    public void keyPressed(KeyEvent ke){
        /**
         * Si se cumple esta condicion, todo lo que continue despues del return, no se ejecutara.
         */
        if (!TipoJugador) { return;}
          /**
         * Si se cumple esta condicion, todo lo que continue despues del return, no se ejecutara.
         */
        if (Caminando) {return;}
        
        /**
         * Si se cumple esta condicion, es que el Enemigo coliciono con el Personaje Jugador, por lo cual se 
         * bloquearan las teclas de movimiento.
         */
        if (!Distancia.isSeMueve()) {return;}
        
//        if(Reglas.Perder((int)Distancia.getPixelEneX(), (int)Distancia.getPixelEneY(), (int)PixelX, (int)PixelY))
//            return;

        final long tiempo = System.currentTimeMillis();                 // tiempo actual
        boolean premio_eliminado = false;
        
        if (ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN) {
            //Si se cumple la condicion, quiere decir que el jugador se puede mover en esa direccion.
            if (Reglas.getCaminoAbajo(Laberinto, Y, X)) {
                CaminarAbajo();
                Distancia.setDireccion("down");
                premio_eliminado = Reglas.EliminarPremio(Y, X);
                Distancia.setDistJugEne(X, Y, Distancia.getPosEneX(), Distancia.getPosEneY());
                Distancia.setDistPre(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                Distancia.setMurrallas(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                tabla.setFila(Distancia.getDistJugEne(), Distancia.getDistJugPre(), Distancia.getDistEnePre(),
                Distancia.getMurrallas(), Distancia.getDireccion(), Distancia.getPremio());
            } else {
                setDireccionAccion(Jugador.QUIETO_ABAJO);
                Distancia.setDireccion("null");
            }
        }

        if (ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP) {
            //Si se cumple la condicion, quiere decir que el jugador se puede mover en esa direccion.
            if (Reglas.getCaminoArriba(Laberinto, Y, X)) {
                CaminarArriba();
                Distancia.setDireccion("up");
                premio_eliminado = Reglas.EliminarPremio(Y, X);
                Distancia.setDistJugEne(X, Y, Distancia.getPosEneX(), Distancia.getPosEneY());
                Distancia.setDistPre(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                Distancia.setMurrallas(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                tabla.setFila(Distancia.getDistJugEne(), Distancia.getDistJugPre(), Distancia.getDistEnePre(),
                Distancia.getMurrallas(), Distancia.getDireccion(), Distancia.getPremio());
            } else {
                setDireccionAccion(Jugador.QUIETO_ARRIBA);
                Distancia.setDireccion("null");
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT) {
            //Si se cumple la condicion, quiere decir que el jugador se puede mover en esa direccion.
            if (Reglas.getCaminoIzquierda(Laberinto, Y, X)) {
                CaminarIzquierda();
                Distancia.setDireccion("left");
                premio_eliminado = Reglas.EliminarPremio(Y, X);
                Distancia.setDistJugEne(X, Y, Distancia.getPosEneX(), Distancia.getPosEneY());
                Distancia.setDistPre(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                Distancia.setMurrallas(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                tabla.setFila(Distancia.getDistJugEne(), Distancia.getDistJugPre(), Distancia.getDistEnePre(),
                Distancia.getMurrallas(), Distancia.getDireccion(), Distancia.getPremio());
            } else {
                setDireccionAccion(Jugador.QUIETO_IZQUIERDA);
                Distancia.setDireccion("null");
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            //Si se cumple la condicion, quiere decir que el jugador se puede mover en esa direccion.
            if (Reglas.getCaminoDerecha(Laberinto, Y, X)) {
                CaminarDerecha();
                Distancia.setDireccion("right");
                premio_eliminado = Reglas.EliminarPremio(Y, X);
                Distancia.setDistJugEne(X, Y, Distancia.getPosEneX(), Distancia.getPosEneY());
                Distancia.setDistPre(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                Distancia.setMurrallas(Y, X, Distancia.getPosEneY(), Distancia.getPosEneX());
                tabla.setFila(Distancia.getDistJugEne(), Distancia.getDistJugPre(), Distancia.getDistEnePre(),
                Distancia.getMurrallas(), Distancia.getDireccion(), Distancia.getPremio());
            } else {
                setDireccionAccion(Jugador.QUIETO_DERECHA);
                Distancia.setDireccion("null");
            }
        }
        
        if(tiempoInicio==0)tiempoInicio = tiempo;                       // si no se ha establedcido tiempo de partida

        int recompensa = (premio_eliminado) ? 1 : 0;

        Tabla.insertarFila( X,                                           // añade datos a la tabla
                            Y,
                            PanelPrincipal.Rival.getX(),
                            PanelPrincipal.Rival.getY(),
                            Tabla.proteccion(PanelPrincipal.Player,PanelPrincipal.Rival),
                            Premio.ListaPremios,
                            recompensa,
                            tiempo - this.tiempoInicio,
                            'J');

        if(X==PanelPrincipal.Rival.getX()&&Y==PanelPrincipal.Rival.getY()) Tabla.detenereImprimir();    // si el jugador alcanza al adversario todas las tareas se cancelan
        if(GestionDistancias.Meta==false) Tabla.detenereImprimir();
        
        Reglas.Perder(PixelX, PixelY, Distancia.getPixelEneX(),Distancia.getPixelEneY() );
    }
    
      public void leerTXT() throws FileNotFoundException, IOException{
        
        //Si el valor de TipoJugador es true, todo lo que se encuentra despues del return, no se ejecuta
        //Este metodo solo se ejecuta cuando se preniona el boton Jugador Entrenado del menu principal
        if (TipoJugador) {
            return;
        }

        //Se entrega el nombre del archivo que se desea leer
        Scanner leer = new Scanner(new FileReader("Execution_Trace_1_Linguistic_2_Filter.txt"));
        while (leer.hasNextLine()) {
            //Se lee una linea del archivo y cada palabra separada por "espacio"
            //Se almacena en una variable
            String linea = leer.nextLine();
            Scanner sl = new Scanner(linea);
            sl.useDelimiter("\\s");
            String a = sl.next();
            String b = sl.next();
            String c = sl.next();
            String d = sl.next();
            String e = sl.next();

            //Esta variables son almacenadas en una lista de tipo String
            DatosDocumento.add(a);
            DatosDocumento.add(b);
            DatosDocumento.add(c);
            DatosDocumento.add(d);
            DatosDocumento.add(e);

            //Variable que guarda todos los atributos, por cada linea en el archivo
            Movimientos aux= new Movimientos(DatosDocumento.get(0), DatosDocumento.get(1),
                    DatosDocumento.get(2), DatosDocumento.get(3), DatosDocumento.get(4));
            
            
            //Variable que almacena los datos de cada liena del documento, menos la direccion de movimiento
            //y la posicion del premio cercano.
            EstadosDistancias4 aux2 = new EstadosDistancias4(DatosDocumento.get(1), DatosDocumento.get(2),
                    DatosDocumento.get(3), DatosDocumento.get(4));
            //Se alamcenan las variables adecuadas, para cada tipo de Lista.
            ListaCincoAtributos.add(aux);
            ListaCuatroAtributos.add(aux2);

            DatosDocumento.clear();
        }
    }

    private void JugadorEntrenado() {
        //Si el valor de TipoJugador es true, todo lo que se encuentra despues del return, no se ejecuta
        //Este metodo solo se ejecuta cuando se preniona el boton Jugador Entrenado del menu principal
        if (TipoJugador) {
            return;
        }
        Hilo = new Thread("Hilo Jugador Alterno") {

            @Override
            public void run() {
                while (Distancia.isSeMueve()) {
                    Colision();
                    Distancia.setPosJugX(X);
                    Distancia.setPosJugY(Y);
                    
                    final long tiempo = System.currentTimeMillis();                 // tiempo actual
                    boolean premio_eliminado = toMove();
                    
                    if(tiempoInicio==0)tiempoInicio = tiempo;                       // si no se ha establedcido tiempo de partida
                    int recompensa = (premio_eliminado) ? 1 : 0;

                    if(PanelPrincipal.Rival != null)
                        Tabla.insertarFila( X,                                      // añade datos a la tabla
                                            Y,
                                            PanelPrincipal.Rival.getX(),
                                            PanelPrincipal.Rival.getY(),
                                            Tabla.proteccion(PanelPrincipal.Player,PanelPrincipal.Rival),
                                            Premio.ListaPremios,
                                            recompensa,
                                            tiempo - tiempoInicio,
                                            'J');
                    
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {}
                    if(X==22 && Y==4){
                        CaminarDerechaEntrenado();
                        JOptionPane.showMessageDialog(null, "Ganaste");
                        Distancia.setMeta(false);
                    }
                }
            }

        };
        Hilo.start();
    }
    private void JugadorEntrenado2() {
        //Si el valor de TipoJugador es true, todo lo que se encuentra despues del return, no se ejecuta
        //Este metodo solo se ejecuta cuando se preniona el boton Jugador Entrenado del menu principal
        if (TipoJugador) {
            return;
        }
        Hilo = new Thread("Hilo Jugador Alterno") {

            @Override
            public void run() {
                while (Distancia.isSeMueve()) {
                    
                    Distancia.setPosJugX(X);
                    Distancia.setPosJugY(Y);
                    AEstrella star = new AEstrella(new Point(X, Y), new Point(Distancia.getPremioX(),Distancia.getPremioY()));
                    if(star.procesar()==4)
                    if (Reglas.getCaminoArriba(Laberinto, Y, X)) {
                        CaminarArribaEntrenado();
                        Reglas.EliminarPremio( Y, X);
                        Distancia.setDireccion("up");
                    }
                if (star.procesar() == 2) {
                    if (Reglas.getCaminoIzquierda(Laberinto, Y, X)) {
                        CaminarIzquierdaEntrenado();
                        Reglas.EliminarPremio( Y, X);
                        Distancia.setDireccion("left");
                    }
                }

                if (star.procesar() == 1) {
                    if (Reglas.getCaminoDerecha(Laberinto, Y, X)) {
                        CaminarDerechaEntrenado();
                        Reglas.EliminarPremio( Y, X);
                        Distancia.setDireccion("right");
                    }
                }

                if (star.procesar() == 3) {
                    if (Reglas.getCaminoAbajo(Laberinto, Y, X)) {
                        CaminarAbajoEntrenado();
                        Reglas.EliminarPremio( Y, X);
                        Distancia.setDireccion("down");
                    }
                }
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                    }
                }
            }

        };
        Hilo.start();
    }

    //Metodos que se utilizan cuando el Jugador mueve al Personaje
    private void CaminarArriba(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.ARRIBA_CORRER);
        new Thread("Hilo Mover Jugador Arriba") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PixelY -= 0.3;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                    }
                }
                if (!Quieto) {
                    setDireccionAccion(Jugador.QUIETO_ARRIBA);
                }
                //Y--;
                Caminando = false;
            }
        }.start();
        Y--;
    }
    
    private void CaminarIzquierda(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.IZQUIERDA_CORRER);
        new Thread("Hilo Mover Jugador Izquierda") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PixelX -= 0.3;
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                    }
                }
                if (!Quieto) {
                    setDireccionAccion(Jugador.QUIETO_IZQUIERDA);
                }
                //X--;
                Caminando = false;
            }
        }.start();
        X--;
    }
    
    private void CaminarAbajo(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.ABAJO_CORRER);
        new Thread("Hilo Mover Jugador Abajo") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PixelY += 0.3;
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {}
                }
                if (!Quieto) {
                    setDireccionAccion(Jugador.QUIETO_ABAJO);
                }
                //Y++;
                Caminando = false;
            }
        }.start();
        Y++;
    }
     
    private void CaminarDerecha(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.DERECHA_CORRER);
        new Thread("Hilo Mover Jugador Derecha") {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    PixelX += 0.3;
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                    }
                }
                if (!Quieto) {
                    setDireccionAccion(Jugador.QUIETO_DERECHA);
                }
                //X++;
                Caminando = false;
            }
        }.start();
        X++;
    }
    
    
    //Metodos para usar en movimientos automaticos en el Personaje.
    private void CaminarArribaEntrenado(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.ARRIBA_CORRER);
        for (int i = 0; i < 100; i++) {
            PixelY -= 0.3;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        if (!Quieto) {
            setDireccionAccion(Jugador.QUIETO_ARRIBA);
        }
        Y--;
        Caminando = false;
    }
    
    private void CaminarIzquierdaEntrenado(){
        Caminando = true;
        Quieto = true;
        setDireccionAccion(Jugador.IZQUIERDA_CORRER);
        for (int i = 0; i < 100; i++) {
            PixelX -= 0.3;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        if (!Quieto) {
            setDireccionAccion(Jugador.QUIETO_IZQUIERDA);
        }
        X--;
        Caminando = false;
    }
    
    private void CaminarAbajoEntrenado(){
         Caminando = true;
         Quieto = true;
         setDireccionAccion(Jugador.ABAJO_CORRER);
         for (int i = 0; i < 100; i++) {
             PixelY += 0.3;
             try {
                 Thread.sleep(5);
             } catch (InterruptedException e) {
             }
         }
         if (!Quieto) {
             setDireccionAccion(Jugador.QUIETO_ABAJO);
         }
         Y++;
         Caminando = false;
    }
     
    private void CaminarDerechaEntrenado(){
          Caminando = true;
          Quieto = true;
          setDireccionAccion(Jugador.DERECHA_CORRER);
          for (int i = 0; i < 100; i++) {
              PixelX += 0.3;
              try {
                  Thread.sleep(5);
              } catch (InterruptedException e) {
              }
          }
          if (!Quieto) {
              setDireccionAccion(Jugador.QUIETO_DERECHA);
          }
          X++;
          Caminando = false;
      }

    //********************
    
    //Metodos que retornan el valor verdadero si es que pueden moverse en X direccion.
    //Si hay un camnino disponible, hara el moviemiento.
    public boolean MoverAbajo(String DireccionAnterior) {
            if (Reglas.getCaminoAbajo(Laberinto, Y, X) && !DireccionAnterior.equals("up,")) {
                CaminarAbajoEntrenado();
                Reglas.EliminarPremio(Y, X);
                Distancia.setDireccion("down");
                this.DireccionAnterior=this.DireccionActual;
                this.DireccionActual="null";
                return true;
            }
        return false;
    }
    public boolean MoverArriba(String DireccionAnterior) {
            if (Reglas.getCaminoArriba(Laberinto, Y, X) && !DireccionAnterior.equals("down,")) {
                CaminarArribaEntrenado();
                Reglas.EliminarPremio(Y, X);
                Distancia.setDireccion("up");
                this.DireccionAnterior=this.DireccionActual;
                this.DireccionActual="null";
                return true;
        }
        return false;
    }
    public boolean MoverDerecha(String DireccionAnterior) {
            if (Reglas.getCaminoDerecha(Laberinto, Y, X) && !DireccionAnterior.equals("left,")) {
                CaminarDerechaEntrenado();
                Reglas.EliminarPremio(Y, X);
                Distancia.setDireccion("right");
                this.DireccionAnterior=this.DireccionActual;
                this.DireccionActual="null";
                return true;
        }
        return false;
    }
    public boolean MoverIzquierda(String DireccionAnterior) {
            if (Reglas.getCaminoIzquierda(Laberinto, Y, X) && !DireccionAnterior.equals("right,")) {
                CaminarIzquierdaEntrenado();
                Reglas.EliminarPremio(Y, X);
                Distancia.setDireccion("left");
                this.DireccionAnterior=this.DireccionActual;
                this.DireccionActual="null";
                return true;
        }
        return false;
    }
    
    public boolean toMove(){
        String[] row = Tabla.getLatestRow();
        byte result;
        if (row == null || row.length == 0) {
            if(PanelPrincipal.Rival == null)
                return false;
            result = busqueda.search(1);
        }
        else {
            try {
                Traza traza = new Traza(null);
                Object[] instance = traza.getInstance(row);
                if(instance != null) {
                    int value = (int)Player.classify(instance);
                    result = busqueda.search(value);
                } else
                    result = busqueda.search(1);
            } catch (Exception ex) {
                Logger.getLogger(Jugador.class.getName()).log(Level.SEVERE, null, ex);
                result = busqueda.search(1);
            }
        }
        
        boolean premio_eliminado = false;
        
        if(result == 4) {
            if (Reglas.getCaminoArriba(Laberinto, Y, X)) {
                CaminarArribaEntrenado();
                premio_eliminado = Reglas.EliminarPremio( Y, X);
                Distancia.setDireccion("up");
            }
        }
        
        if (result == 2) {
            if (Reglas.getCaminoIzquierda(Laberinto, Y, X)) {
                CaminarIzquierdaEntrenado();
                premio_eliminado = Reglas.EliminarPremio( Y, X);
                Distancia.setDireccion("left");
            }
        }

        if (result == 1) {
            if (Reglas.getCaminoDerecha(Laberinto, Y, X)) {
                CaminarDerechaEntrenado();
                premio_eliminado = Reglas.EliminarPremio( Y, X);
                Distancia.setDireccion("right");
            }
        }

        if (result == 3) {
            if (Reglas.getCaminoAbajo(Laberinto, Y, X)) {
                CaminarAbajoEntrenado();
                premio_eliminado = Reglas.EliminarPremio( Y, X);
                Distancia.setDireccion("down");
            }
        }
        
        return premio_eliminado;
    }
    public String Combinacion4(EstadosDistancias4 Combinacion, ArrayList<EstadosDistancias4> Lista, ArrayList<Movimientos>ListaMovimientos, String MoverAux) {
        for (int i = 0; i < Lista.size(); i++) {
            if(Combinacion.equals(Lista.get(i)) && !MoverAux.equals(ListaMovimientos.get(i).getA()))
                return ListaMovimientos.get(i).getA();
        }
        return "2";
    }
    @Override
    public void keyTyped(KeyEvent ke){}

    @Override
    public void keyReleased(KeyEvent ke){

        if (ke.getKeyCode() == KeyEvent.VK_S || ke.getKeyCode() == KeyEvent.VK_DOWN) {
            Quieto = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_W || ke.getKeyCode() == KeyEvent.VK_UP) {
            Quieto = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_A || ke.getKeyCode() == KeyEvent.VK_LEFT) {
            Quieto = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_D || ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            Quieto = false;
        }
        Distancia.setDireccion("null");
        
    }
    
    public void Colision(){
    
       new Thread(){

           @Override
           public void run() {
               while(Ciclo){
                   
                Rectangle r1 = new Rectangle((int)PixelX,(int)PixelY,25,25);
                Rectangle r2 = new Rectangle((int)Distancia.getPixelEneX(),(int)Distancia.getPixelEneY(),30,30);
        
        if(r1.intersects(r2)){
            Distancia.setMeta(false);
            Distancia.setSeMueve(false);
            System.out.println("Colision");
            Ciclo=false;
            JOptionPane.showMessageDialog(null, "Perdiste");
        }
                   try {
                       Thread.sleep(300);
                   } catch (InterruptedException e) {}
               }
           }
       
       }.start();

    }
    public double getPixelX(){return PixelX;}
    public double getPixelY(){return PixelY;}
    public void setCuadro(JPanel Cuadro){this.Cuadro=Cuadro;}
    public int getX() {return X;}
    public int getY() {return Y;}
      
}

 