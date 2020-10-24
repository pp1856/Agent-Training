package GestionDistancias;

import Personaje_Enemigo.Enemigo;
import Personaje_Jugador.Jugador;
import PanelPrincipal.TablaDistancias;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestionaArchivos {

    private final Jugador Jugador;
    private final Enemigo Oponente;
    private final TablaDistancias TablaDistancias;
    private GestionDistancias GestionaDistancias;
    private File archivo1;
    private File archivo2;
    private File archivo4;
    private File archivo5;
    private ArrayList<Movimientos> ListaMovimientos = new ArrayList<>();
    private ArrayList<Movimientos> FiltrarMovimientos = new ArrayList<>();
    private ArrayList<String> DatosDeLaTabla = new ArrayList<>();
    public boolean TipoJugador;
    private boolean Ciclo=true;

    public GestionaArchivos(Jugador jugador, Enemigo enemigo,GestionDistancias distancia, TablaDistancias tabladistancias, boolean estado) {

        this.Jugador = jugador;
        this.Oponente = enemigo;
        this.TablaDistancias = tabladistancias;
        this.GestionaDistancias = distancia;
        this.TipoJugador = estado;

        //Este metodo inserta filas solo cuando el enemigo hace un movimiento.
        tabla2();
        Archivos();
    }

        //Este metodo inserta filas a la CrearArchivos, para poder ver las combinaciones que se generan al obtener movimientos
        //del Jugador y el Enemigo.
    public void CrearArchivos() {
        //Si el valor de TipoJugador es false, todo que que esta debajo del return no se ejecutara.
        //Este metodo solo se ejecuta cuando se presiona el boton Entrenar Jugador del menu principal.
        if (!TipoJugador) {
                    return;
                }
        new Thread("Hilo Llenar Tabla") {


            @Override
            public void run() {
                String ruta = "Execution_Trace_1.txt";
                archivo1 = new File(ruta);
                BufferedWriter bw;
                String ruta2 = "Execution_Trace_1_Linguistic.txt";
                archivo2 = new File(ruta2);
                BufferedWriter bw2;
                String ruta4 = "Execution_Trace_1_Linguistic_2_Filter.txt";
                archivo4 = new File(ruta4);
                BufferedWriter bw4;
                
                //Este es el documento, en lenguaje Prolog.
                //Cambiar a .bpl si se necesita esa extencion.
                String ruta5 = "Execution_Trace_1_Linguistic2_Filter_2.txt";
                archivo5 = new File(ruta5);
                BufferedWriter bw5;
                try {

                    bw = new BufferedWriter(new FileWriter(archivo1));
                    bw2 = new BufferedWriter(new FileWriter(archivo2));
                    bw4 = new BufferedWriter(new FileWriter(archivo4));
                    bw5 = new BufferedWriter(new FileWriter(archivo5));

                    //Primer Archivo
                    // Se genera un archivo txt que contiene todos los datos de la CrearArchivos en valores numericos
                    //--------------------------------------------------------
                    for (int i = 0; i < TablaDistancias.totalFilas(); i++) {
                        try {
                            while (TablaDistancias.getCeldaDireccion(i, 4).equals("null")) {
                                i++;
                            }
                        } catch (Exception e) {
                        }
                        bw.write(TablaDistancias.getCelda(i, 0) + ", " + TablaDistancias.getCelda(i, 1) + ", " + TablaDistancias.getCelda(i, 2)
                                + ", " + TablaDistancias.getCelda(i, 3) + ", " + TablaDistancias.getCeldaDireccion(i, 4) 
                                + ", " + TablaDistancias.getCeldaPremio(i, 5));
                        bw.newLine();
                    }
                    bw.close();

                    //Segundo Archivo
                    //Se genera un archivo txt que transforma todos los datos generados en la CrearArchivos en palabras
                    //todos estos datos son procesados en rangos para determinar a que tipo de palabra corresponde
                    //-----------------------------------------------------------------------
                    bw2.write(" DistJugEne, DistJugPre, DistEnePre, Protec    , Direccion,  Premio");
                    bw2.newLine();
                    bw2.newLine();
                    for (int i = 0; i < TablaDistancias.totalFilas(); i++) {

                        if (TablaDistancias.getCelda(i, 0) <= 3) {
                            bw2.write(" Muy Cerca ");
                        }
                        if (3 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 8) {
                            bw2.write(" Cerca     ");
                        }
                        if (8 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 18) {
                            bw2.write(" Lejos     ");
                        }
                        if (18 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 25) {
                            bw2.write(" Muy Lejos ");
                        }
                        //-----------------------------
                        if (TablaDistancias.getCelda(i, 1) <= 2) {
                            bw2.write(", Muy Cerca ");
                        }
                        if (2 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 6) {
                            bw2.write(", Cerca     ");
                        }
                        if (6 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 13) {
                            bw2.write(", Lejos     ");
                        }
                        if (13 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 21) {
                            bw2.write(", Muy Lejos ");
                        }
                        //-----------------------------
                        if (TablaDistancias.getCelda(i, 2) <= 2) {
                            bw2.write(", Muy Cerca ");
                        }
                        if (2 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 8) {
                            bw2.write(", Cerca     ");
                        }
                        if (8 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 18) {
                            bw2.write(", Lejos     ");
                        }
                        if (18 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 25) {
                            bw2.write(", Muy Lejos ");
                        }
                        //-----------------------------
                        if (TablaDistancias.getCelda(i, 3) <= 5) {
                            bw2.write(", Muy Baja  ");
                        }
                        if (5 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 20) {
                            bw2.write(", Baja      ");
                        }
                        if (20 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 45) {
                            bw2.write(", Alta      ");
                        }
                        if (45 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 150) {
                            bw2.write(", Muy Alta  ");
                        }
                        bw2.write(", " + TablaDistancias.getCeldaDireccion(i, 4) + "  ");
                        bw2.write(",    " + TablaDistancias.getCeldaPremio(i, 5) + "->");
                        bw2.newLine();
                    }
                    bw2.close();
                    
                    /////////////////////////////////////
                    //Tercer Archivo
                    //Se genera un archivo, que filtra los datos de la CrearArchivos, para que no hayan combinaciones de movimiento repetidos.
                    for (int i = 0; i <= TablaDistancias.totalFilas(); i++) {
                        try {
                            while (TablaDistancias.getCeldaDireccion(i, 4).equals("null")) {
                                i++;
                            }
                            DatosDeLaTabla.add(TablaDistancias.getCeldaDireccion(i, 4));

                            if (TablaDistancias.getCelda(i, 0) <= 3) {
                                DatosDeLaTabla.add("very_close");
                            }

                            if (3 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 8) {
                                DatosDeLaTabla.add("close");
                            }

                            if (8 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 18) {
                                DatosDeLaTabla.add("far");
                            }

                            if (18 < TablaDistancias.getCelda(i, 0) && TablaDistancias.getCelda(i, 0) <= 25) {
                                DatosDeLaTabla.add("far_away");
                            }

                            //-----------------------------
                            if (TablaDistancias.getCelda(i, 1) <= 2) {
                                DatosDeLaTabla.add("very_close");
                            }

                            if (2 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 6) {
                                DatosDeLaTabla.add("close");
                            }

                            if (6 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 13) {
                                DatosDeLaTabla.add("far");
                            }

                            if (13 < TablaDistancias.getCelda(i, 1) && TablaDistancias.getCelda(i, 1) <= 21) {
                                DatosDeLaTabla.add("far_away");
                            }

                            //-----------------------------
                            if (TablaDistancias.getCelda(i, 2) <= 2) {
                                DatosDeLaTabla.add("very_close");
                            }

                            if (2 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 8) {
                                DatosDeLaTabla.add("close");
                            }

                            if (8 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 18) {
                                DatosDeLaTabla.add("far");
                            }

                            if (18 < TablaDistancias.getCelda(i, 2) && TablaDistancias.getCelda(i, 2) <= 25) {
                                DatosDeLaTabla.add("far_away");
                            }

                            //-----------------------------
                            if (TablaDistancias.getCelda(i, 3) <= 5) {
                                DatosDeLaTabla.add("very_low");
                            }

                            if (5 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 20) {
                                DatosDeLaTabla.add("low");
                            }

                            if (20 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 45) {
                                DatosDeLaTabla.add("high");
                            }

                            if (45 < TablaDistancias.getCelda(i, 3) && TablaDistancias.getCelda(i, 3) <= 150) {
                                DatosDeLaTabla.add("very_high");
                            }

                            DatosDeLaTabla.add(TablaDistancias.getCeldaPremio(i, 5));

                            Movimientos Aux = new Movimientos(DatosDeLaTabla.get(0), DatosDeLaTabla.get(1), DatosDeLaTabla.get(2),
                                    DatosDeLaTabla.get(3), DatosDeLaTabla.get(4));

                            if (FiltrarMovimientos.contains(Aux) == false) {
                                FiltrarMovimientos.add(Aux);
                            }
                            DatosDeLaTabla.clear();

                        } catch (ArrayIndexOutOfBoundsException e) {}

                    }
                    for (int i = 0; i < FiltrarMovimientos.size(); i++) {

                        bw4.write(FiltrarMovimientos.get(i).getA()/*+"):-"*/);
                        bw4.write(/*"distance(player,opponent,"+*/", " + FiltrarMovimientos.get(i).getB()/*+")"*/);
                        bw4.write(/*", distance(player,coin,"*/", " + FiltrarMovimientos.get(i).getC()/*+")"*/);
                        bw4.write(/*", distance(opponent,coin,"*/", " + FiltrarMovimientos.get(i).getD()/*+")"*/);
                        bw4.write(/*", protection(player,opponent,"*/", " + FiltrarMovimientos.get(i).getE()/*+")."*/);
                        //bw4.write(" " + FiltrarMovimientos.get(i).getF());
                        bw4.newLine();
                    }
                    bw4.close();

                    //Cuarto Archivo
                    //Se genera un archivo txt que transforma todos los datos de la CrearArchivos en el lenguaje prolog
                    //Se genera un archivo en Prolog, solo con combinaciones unicas.
                    for (int i = 0; i < FiltrarMovimientos.size(); i++) {

                        bw5.write("move(player," + FiltrarMovimientos.get(i).getA() + "):-");
                        bw5.write("distance(player,opponent," + FiltrarMovimientos.get(i).getB() + ")");
                        bw5.write(", distance(player,coin," + FiltrarMovimientos.get(i).getC() + ")");
                        bw5.write(", distance(opponent,coin," + FiltrarMovimientos.get(i).getD() + ")");
                        bw5.write(", protection(player,opponent," + FiltrarMovimientos.get(i).getE() + ").");
                        bw5.newLine();
                    }
                    bw5.close();
                } catch (IOException ex) {
                    Logger.getLogger(GestionaArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
    public void tabla2() {
        
        //Si el valor de TipoJugador es true, todo que que esta debajo del return no se ejecutara.
        //Este metodo solo se ejecuta cuando se presiona el boton Jugador Entrenado del menu principal.
        if (TipoJugador) {
                    return;
                }
        new Thread("Hilo Llenar Tabla") {

            @Override
            public void run() {
                while (GestionaDistancias.isMeta()) {
                    try {
                        //Se entregan las posiciones de Jugador y Oponente, para hacer el calculos de las Distancias
                        GestionaDistancias.setDistJugEne(Jugador.getX(), Jugador.getY(), Oponente.getX(), Oponente.getY());
                        GestionaDistancias.setMurrallas(Jugador.getY(), Jugador.getX(), Oponente.getY(), Oponente.getX());
                        GestionaDistancias.setDistPre(Jugador.getY(), Jugador.getX(), Oponente.getY(), Oponente.getX());

                        TablaDistancias.setFila(GestionaDistancias.getDistJugEne(), GestionaDistancias.getDistJugPre(),
                                GestionaDistancias.getDistEnePre(),
                                GestionaDistancias.getMurrallas(), GestionaDistancias.getDireccion(), 
                                GestionaDistancias.getPremio());
                        
                        //Colocar mismo valor con el cual se movera el enemigo.
                        Thread.sleep(400);
                    } catch (InterruptedException e) {}
                }               
            }
        }.start();
    }
    
    public void Archivos()
    {
        new Thread(){

            @Override
            public void run() {
                while(Ciclo){
                    try {
                        if(Ciclo && !GestionaDistancias.isSeMueve()){
                            CrearArchivos();
                            Ciclo=false;
                        }
                        Thread.sleep(350);
                    } catch (Exception e) {
                    }
                }
            }
        
        }.start();
    }

}
