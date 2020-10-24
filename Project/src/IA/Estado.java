package IA;

import java.awt.Point;

public class Estado<T> implements Comparable<Estado>{
    
    private final Point Movimiento;
    public double h;
    public double g;
    public double Costo;
    private final Estado Padre;
    
    public Estado(Estado Padre,Point Movimiento,double Costo){
    this.Movimiento=Movimiento;
    this.Costo=Costo;
    this.Padre=Padre;                                     }
    
    public double getCosto(){return Costo;}
    public Point getMovimiento(){return Movimiento;}
    public Estado getPadre(){ return Padre;}
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Estado))
            return false;
        Estado estado = (Estado)o;
        return this.getMovimiento().x == estado.getMovimiento().x && 
               this.getMovimiento().y == estado.getMovimiento().y;
    }
    
    @Override
    public int hashCode() {
        return (Movimiento.x+1*1000) + (Movimiento.y+1);
    }
    
    @Override
    public int compareTo(Estado e) {
        if ( this.Costo == e.Costo ) return 0;
        else {
            if ( this.Costo > e.Costo ) return 1;
            else return -1;
        }
    }
    
    @Override
    public String toString(){
        return Movimiento + "Costo:"+Costo;
    }
    
}
