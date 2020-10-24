package PanelPrincipal;

import java.awt.Point;
import java.util.ArrayList;

public class Mapa {

    // 0 Camino
    // 1 Personaje
    // 2 Meta
    // 5 Arbol
    // 7 Premio
    // 8 Enemigo
    public static final byte CAMINO = 0;
    public static final byte PERSONAJE = 1;
    public static final byte META = 2;
    public static final byte ARBOL = 5;
    public static final byte PREMIO = 7;
    public static final byte ENEMIGO = 8;

    public static final short CENTRAR_X = 75;
    public static final short CENTRAR_Y = 25;

    // mapa para el laberinto
    private static byte Nivel1[][] = 
    {{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
     {5, 1, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 5, 0, 0, 5},
     {5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 0, 5, 0, 5, 5, 5, 5},
     {5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 5, 5, 0, 5, 0, 0, 0, 5, 5},
     {5, 7, 0, 0, 0, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 5, 5, 0, 0, 0, 5, 0, 2, 5},
     {5, 0, 0, 5, 5, 0, 0, 0, 5, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 7, 5, 5, 5, 5},
     {5, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 0, 5, 0, 5, 5, 5, 5},
     {5, 0, 0, 7, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5},
     {5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 5, 5, 0, 5, 7, 5, 5, 0, 5, 5, 5, 5},
     {5, 5, 0, 5, 0, 0, 5, 5, 5, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 5},
     {5, 0, 7, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 5, 5, 0, 0, 0, 5},
     {5, 0, 0, 5, 5, 0, 5, 0, 5, 0, 0, 0, 5, 0, 5, 0, 0, 0, 5, 5, 5, 0, 5, 0, 5},
     {5, 0, 0, 0, 0, 0, 5, 5, 5, 0, 5, 7, 0, 0, 5, 0, 0, 0, 5, 5, 5, 0, 5, 0, 5},
     {5, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 7, 0, 0, 0, 0, 0, 8, 5},
     {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}};
    
    
    private static ArrayList<byte[][]> Listado = new ArrayList<>();

    // lista para agregar los posibles niveles

    static {
        Listado.add(Nivel1);
    }

    // se agregan los niveles a la lista

    public Mapa() {
    }

    public static byte[][] getLaberinto(int Nivel) {
        return Listado.get(Nivel - 1);
    }

    //metodo que devuelve el laberinto de una posicion de la lista

    public Point getTamaño(int Nivel) {
        return new Point(Listado.get(Nivel - 1)[0].length, Listado.get(Nivel - 1).length);
    }

    //metodo para retornar posicion del jugador
    public static Point getPosicionJugador(int Nivel) {
        byte[][] a = Listado.get(Nivel);
        int x = 0, y = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == Mapa.PERSONAJE) {
                    x = i;
                    y = j;
                }
            }
        }
        return new Point(y, x);

    }

    //metodo para retornar posicion del enemigo
    public static Point getPosicionEnemigo(int Nivel) {
        byte[][] a = Listado.get(Nivel);
        int x = 0, y = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == Mapa.ENEMIGO) {
                    x = i;
                    y = j;
                }
            }
        }
        return new Point(y, x);
    }

}
