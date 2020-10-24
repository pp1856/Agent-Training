package Animacion;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import PanelPrincipal.Mapa;

public class Premios {
    private Image Premios;
    public ArrayList <Point>ListaPremios = new ArrayList<>();

    public Premios(){
    
        //Se obtiene la imagen que se utilizara como representacion de los premios que debera capturar el jugador
        Premios= new ImageIcon(getClass().getResource("/Sprite_Utileria/Pokeball.gif")).getImage();
    }
    
    //se almacenan en una lista las posiciones de los premios, obtenidos desde la matriz, que simula el escenario
    public ArrayList<Point> getListaPremios(){
        if(ListaPremios.size() < 11) {
            ListaPremios.clear();
            byte [][]a=Mapa.getLaberinto(1);
            int x=0,y=0;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    if(a[i][j]==Mapa.PREMIO) {
                        x=i; y=j;
                        ListaPremios.add(new Point(y, x));
                    }
                }
            }
        }
        return ListaPremios;
    }

    public Image getPremio(){return Premios;}
    
}
