package GestionDistancias;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import PanelPrincipal.Mapa;

public class GestionDistancias {

    //Almacena la distancia del Jugador al Enemigo.
    private int DistJugEne=25;
    //Almacena la distancia del Jugador al Premio mas cercano.
    private int DistJugPre=3;
    //Almacena la distancia del Enemigo al Premio mas cercano al Jugador.
    private int DistEnePre=23;
    //Almacena la cantidad de murallas que se encuentran entre la posicion del Jugador y el Enemigo
    private int Murrallas=101;
    
    private final int DistJugPreAlt=0;
    private int PremioXX=0,PremioYY=0;
    
    private int premioX=1;
    private int premioY=4;
    private int PosEneX=23;
    private int PosEneY=13;
    
    private int PosJugX=1;
    private int PosJugY=1;

    private double PixelEneX=690;
    private double PixelEneY=390;
    
    private String Direccion="null";
    private byte Laberinto[][]=Mapa.getLaberinto(1);
    private ArrayList <Point> Premios;
    private static ArrayList <PremiosDistancias> listaPremiosREne= new ArrayList<>();
    private ArrayList<PremiosDistancias> listaPremios= new ArrayList<>();
    private ArrayList<PremiosDistancias> ListaPremiosCuadranteUno = new ArrayList<>();
    private ArrayList<PremiosDistancias> ListaPremiosCuadranteDos = new ArrayList<>();
    private ArrayList<PremiosDistancias> ListaPremiosCuadranteTres = new ArrayList<>();
    private ArrayList<PremiosDistancias> ListaPremiosCuadranteCuatro = new ArrayList<>();
    public static boolean Meta=true;
    private boolean seMueve=true;
    private boolean premio=false;

    public boolean isPremio() {
        return premio;
    }

    /**
     * 
     * @param premio Si un premio es capturado, su valor sera true.
     */
    public void setPremio(boolean premio) {
        this.premio = premio;
    }
    
    /**
     * 
     * @return Devuelve la posición en el Eje X del premio mas cercano al Jugador.
     */
    public int getPremioX() {
        return premioX;
    }
    /**
     * 
     * @return Devuelve la posición en el Eje X del premio mas cercano al Jugador.
     */
    public int getPremioY() {
        return premioY;
    }
    /**
     * @return Devuelve la posicion en el Eje X del Enemigo.
     */
    public int getPosEneX() {
        return PosEneX;
    }
    
    /**
     * @return Devuelve la posicion en el Eje X del Enemigo.
     */
    public int getPosEneY() {
        return PosEneY;
    }
    /**
     * @return Devuelve la posición del pixel en el Eje X del enemigo
     */
    public double getPixelEneX() {
        return PixelEneX;
    }

    /**
     * @return Devuelve la posición del pixel en el Eje Y del enemigo
     */
    public double getPixelEneY() {
        return PixelEneY;
    }

    /**
     * Recibe la Posicion en la Matriz y en Pixel del Enemigo en el Eje X.
     * @param PosEneX posición en la matriz de Enemigo
     * @param PixelEneX posición en pixel del Enemgio.
     */
    public void setPosEneX(int PosEneX, double PixelEneX) {
        this.PosEneX = PosEneX;
        this.PixelEneX= PixelEneX;
    }

  /**
     * Recibe la Posicion en la Matriz y en Pixel del Enemigo en el Eje Y.
     * @param PosEneY posición en la matriz de Enemigo
     * @param PixelEneY posición en pixel del Enemgio.
     */
    public void setPosEneY(int PosEneY, double PixelEneY) {
        this.PosEneY = PosEneY;
        this.PixelEneY= PixelEneY;
    }

    /**
     * @return Devuelve el valor en formato coordenada del premio mas cercano al Jugador.
     */
    public String getPremio() {
        return premioX+"."+premioY;
    }

    public int getDistJugPreAlt() {
        return DistJugPreAlt;
    }

    
    public int getPosJugX() {
        return PosJugX;
    }

    public void setPosJugX(int PosJugX) {
        this.PosJugX = PosJugX;
    }

    public int getPosJugY() {
        return PosJugY;
    }

    public void setPosJugY(int PosJugY) {
        this.PosJugY = PosJugY;
    }

    /**
     * Metado para saber si el oponente se esta moviendo aun
     * @return true, aun se mueve. false, no se mueve.
     */
    public boolean isSeMueve() {
        return seMueve;
    }

    /**
     * Metodo para hacer que el oponente deje de moverse
     * @param seMueve true, puede moverse. false, no puede moverse.
     */
    public void setSeMueve(boolean seMueve) {
        this.seMueve = seMueve;
    }

    
    /**
     * Metodo para saber si el jugador llego a la Meta
     * @return true, Jugador, llego a la meta. false, Jugador aun no llega a la meta.
     */
    public boolean isMeta() {
        return Meta;
    }

    /**
     * Metodo que cambia el valor, para saber que el jugador ya llego a la meta
     * @param Meta true, Jugador, llego a la meta. false, Jugador aun no llega a la meta.
     */
    public void setMeta(boolean Meta) {
        this.Meta = Meta;
    }
    
    public void setListaPremios(ArrayList<Point>Lista){
    this.Premios=Lista;
    }
    
    /**
     * Metodo que calcula la Distancia entre el Jugador y el Oponente
     * @param X Posición del Jugador en el Eje X.
     * @param Y Posición del Jugador en el Eje Y.
     * @param X1 Posición del Enemigo en el Eje X.
     * @param Y1 Posición del Enemigo en el Eje Y.
     */
    public void setDistJugEne(int X,int Y, int X1, int Y1){

        int distX=(int)Math.pow((X-X1), 2);
        int distY=(int)Math.pow((Y-Y1), 2);
        DistJugEne=(int)Math.sqrt(distX+distY);

    }
    
    /**
     * Metodo que calcula la cantidad de obtaculos entre el Jugador y el Oponente
     * @param Y Posición del Jugador en el Eje Y.
     * @param X Posición del Jugador en el Eje X.
     * @param Y1 Posición del Enemigo en el Eje Y.
     * @param X1 Posición del Enemigo en el Eje X.
     */
    public void setMurrallas(int Y, int X, int Y1, int X1) {
        int menorX = 0, mayorX = 0, menorY = 0, mayorY = 0;
        Murrallas = 0;
        if (Y < Y1) {
            menorX = Y;
            mayorX = Y1;
        }
        if (Y1 < Y) {
            menorX = Y1;
            mayorX = Y;
        }
        if (Y == Y1) {
            menorX = Y;
            mayorX = Y1;
        }

        if (X < X1) {
            menorY = X;
            mayorY = X1;
        }
        if (X1 < X) {
            menorY = X1;
            mayorY = X;
        }
        if (X == X1) {
            menorY = X;
            mayorY = X1;
        }

        for (int i = menorX; i <= mayorX; i++) {
            for (int j = menorY; j <= mayorY; j++) {
                if (Laberinto[i][j] == Mapa.ARBOL) {
                    Murrallas++;
                }
            }
        }

    }
    

    
    /**
     * Metodo que calcula la Distancia entre el Jugador y el premio mas cercano,
     * la Distancia entre el Oponente y el premio mas cercano al Jugador.
     * X obtiene las coordenadas del premio cercano el jugador.
     * @param Y Posición del Jugador en el Eje Y.
     * @param X Posición del Jugador en el Eje X.
     * @param Y1 Posición del Enemigo en el Eje Y.
     * @param X1 Posición del Enemigo en el Eje X. 
     */
    public void setDistPre(int Y, int X, int Y1, int X1) {
        listaPremiosREne.clear();
        listaPremios.clear();
        ArrayList<PremiosDistancias> ListaPremios = new ArrayList<>();
        for (int i = 0; i < Premios.size(); i++) {
            int distXJ = (int) Math.pow((Y - (int) Premios.get(i).y), 2);
            int distYJ = (int) Math.pow((X - (int) Premios.get(i).x), 2);
            int distanciaJ = (int) Math.sqrt(distXJ + distYJ);

            int distXE = (int) Math.pow((Y1 - (int) Premios.get(i).y), 2);
            int distYE = (int) Math.pow((X1 - (int) Premios.get(i).x), 2);
            int distanciaE = (int) Math.sqrt(distXE + distYE);
            PremioXX = Premios.get(i).x;
            PremioYY = Premios.get(i).y;
            listaPremiosREne.add(new PremiosDistancias(Integer.toString(distanciaE), Integer.toString(distanciaJ),
            Integer.toString(PremioXX), Integer.toString(PremioYY)));
            listaPremios.add(new PremiosDistancias(Integer.toString(distanciaJ), Integer.toString(distanciaE),
            Integer.toString(PremioXX), Integer.toString(PremioYY)));

        }

        //Ordena la listaPremios de menor a mayor, para optener la distancia menor menor a los premios en un momento Y
        Collections.sort(listaPremios, new PointCompare());
        Collections.sort(listaPremiosREne, new PointCompare());
        try {
            
//            for(int i=0; i<listaPremios.size();i++)
//            {
//                if(!ListaPremios.contains(listaPremios.get(i)))
//                    ListaPremios.add(listaPremios.get(i));
//            }
            for(int i=0; i<listaPremios.size();i++)
            {
                if(!ListaPremios.contains(listaPremios.get(i)))
                    ListaPremios.add(listaPremios.get(i));
            }
            int tamaño=ListaPremios.size();
            Huir(ListaPremios);
            DistJugPre = Integer.parseInt(ListaPremios.get(0).getA()); 
            DistEnePre = Integer.parseInt(ListaPremios.get(0).getB());
              if(DistEnePre>3)
            {
                premioX = Integer.parseInt(listaPremios.get(0).getC());
                premioY = Integer.parseInt(listaPremios.get(0).getD());
            }

        } catch (IndexOutOfBoundsException e) {}

    }
    
    public void Huir(ArrayList<PremiosDistancias> Lista)
    {
        ListaPremiosCuadranteUno.clear();
        ListaPremiosCuadranteDos.clear();
        ListaPremiosCuadranteTres.clear();
        ListaPremiosCuadranteCuatro.clear();
        
        for (PremiosDistancias Lista1 : Lista) {
            if (Integer.parseInt(Lista1.getC()) <= 12 && Integer.parseInt(Lista1.getD()) <= 7) {
                ListaPremiosCuadranteUno.add(Lista1);
            }
            if (Integer.parseInt(Lista1.getC()) <= 12 && Integer.parseInt(Lista1.getD()) > 7) {
                ListaPremiosCuadranteTres.add(Lista1);
            }
            if (Integer.parseInt(Lista1.getC()) > 12 && Integer.parseInt(Lista1.getD()) <= 7) {
                ListaPremiosCuadranteDos.add(Lista1);
            }
            if (Integer.parseInt(Lista1.getC()) > 12 && Integer.parseInt(Lista1.getD()) > 7) {
                ListaPremiosCuadranteCuatro.add(Lista1);
            }
        }
        
//        System.out.println(ListaPremiosCuadranteCuatro);
        if (DistEnePre <= 3) {
            
            //Cuadrante 1
            if (PosEneX <= 12 && PosEneY <= 7 && PosEneX<=PosJugX) {

                System.out.println("1");
                premioX = Integer.parseInt(ListaPremiosCuadranteCuatro.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteCuatro.get(0).getD());
            }
            //Cuadrante 1
            if (PosEneX <= 12 && PosEneY <= 7 && PosJugX <= PosEneX) {

                if (PosEneY > PosJugY) {
                    System.out.println("2");
                    premioX=1;
                    premioY=1;
                } else {
                    System.out.println("3");
                    premioX = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getC());
                    premioY = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getD());
                }
            }
            //Cuandrante 2
            if (PosEneX > 12 && PosEneY <= 7 && PosJugX<=PosEneX) {
                System.out.println("4");
                premioX = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getD());
            }
            //Cuadrante 2
             if (PosEneX > 12 && PosEneY <= 7 && PosEneX <= PosJugX) {
                 
                if (PosEneY > PosJugY && ListaPremiosCuadranteDos.isEmpty()) {
                    System.out.println("5");
                    premioX = 1;
                    premioY = 1;
                }
                if (PosEneY > PosJugY && !ListaPremiosCuadranteDos.isEmpty()) {
                    System.out.println("6");
                    premioX = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getC());
                    premioY = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getD());
                } else {
                    System.out.println("7");
                    premioX = Integer.parseInt(ListaPremiosCuadranteCuatro.get(0).getC());
                    premioY = Integer.parseInt(ListaPremiosCuadranteCuatro.get(0).getD());
                }
            }
             //Cuandrante 3
            if (PosEneX <= 12 && PosEneY > 7 && PosEneX<=PosJugX) {
                System.out.println("8");
                premioX = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getD());
            }
            //Cuandrante 3
            if (PosEneX <= 12 && PosEneY > 7 && PosJugX<=PosEneX) {
                
                if(!ListaPremiosCuadranteDos.isEmpty())
                {
                    System.out.println("9");
                    premioX = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getD());
                }else{
                    System.out.println("10");
                premioX = Integer.parseInt(ListaPremiosCuadranteUno.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteUno.get(0).getD());}
            }
            //Cuandrante 4
            if (PosEneX > 12 && PosEneY > 7 && PosJugX <= PosEneX) {
                
                if (PosEneY < PosJugY) {
                    System.out.println("11");
                    premioX = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getC());
                    premioY = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getD());
                } else {
                    if (!ListaPremiosCuadranteUno.isEmpty()) {
                        System.out.println("12");
                        premioX = Integer.parseInt(ListaPremiosCuadranteUno.get(0).getC());
                        premioY = Integer.parseInt(ListaPremiosCuadranteUno.get(0).getD());
                    } else {
                        System.out.println("13");
                        premioX = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getC());
                        premioY = Integer.parseInt(ListaPremiosCuadranteTres.get(0).getD());
                    }
                }
            }
            //Cuandrante 4
            if (PosEneX > 12 && PosEneY > 7 && PosEneX<=PosJugX) {
                System.out.println("14");
                premioX = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getC());
                premioY = Integer.parseInt(ListaPremiosCuadranteDos.get(0).getD());
            }
        }
    }
    
    /**
     * 
     * @param Direccion Direccion hacia donde se Mueve el Jugador.
     */
    public void setDireccion(String Direccion){
            this.Direccion=Direccion;
    }
    /**
     * 
     * @return Devuelve la distancia del Jugador al Enemigo.
     */
    public int getDistJugEne(){return DistJugEne;}
    /**
     * 
     * @return Devuelve la distancia del Jugador al premio más cercano.
     */
    public int getDistJugPre() { return DistJugPre;}
    /**
     * 
     * @return Devuelve la distancia del Enemigo al premio mas cercano al Jugador.
     */
    public int getDistEnePre() {return DistEnePre;}
    /**
     * 
     * @return Devuelve la cantidad de obtaculos que se encuentrar entre el area formada,
     * por las posiciones del Jugador y el Enemigo.
     */
    public int getMurrallas() {return Murrallas;}
    /**
     * 
     * @return Devuelve la direccion hacia donde se movio el Jugador.
     */
    public String getDireccion() {return Direccion;} 

}

class PointCompare implements Comparator<PremiosDistancias> {

        @Override
        public int compare(final PremiosDistancias a, final PremiosDistancias b) {
            if ( Integer.parseInt(a.getA()) < Integer.parseInt(b.getA())) {
                return -1;
            }
            else if (Integer.parseInt(a.getA()) > Integer.parseInt(b.getA())) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }