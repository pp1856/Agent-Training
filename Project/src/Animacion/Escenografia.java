package Animacion;

import java.awt.Image;
import javax.swing.ImageIcon;


public class Escenografia {
    
    private Image Arbol;
    private Image Piso;
    private Image Meta;
    private Image Fondo;
    private Image Cerro;
    
    //se obtienen las imagens desde una direccion ubicada en el mismo proyecto, para poder
    //armalas en otra clase y luego poder pintarlas
    public Escenografia(){
    
        Arbol = new ImageIcon(getClass().getResource("/Sprite_Utileria/Arbol.png" )).getImage();
        Piso  = new ImageIcon(getClass().getResource("/Sprite_Utileria/Piso.png"  )).getImage();
        Meta  = new ImageIcon(getClass().getResource("/Sprite_Utileria/Salida.png")).getImage();
        Fondo = new ImageIcon(getClass().getResource("/Sprite_Utileria/Fondo.png" )).getImage();
        Cerro = new ImageIcon(getClass().getResource("/Sprite_Utileria/Cerro.png" )).getImage();
                         }
    //metodos que retornar las imagenes
    public Image getArbol (){return Arbol ;}
    public Image getPiso  (){return Piso  ;}
    public Image getMeta  (){return Meta  ;}
    public Image getFondo (){return Fondo ;}
    public Image getCerro (){return Cerro ;}
    
    
}
