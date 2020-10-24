package GestionDistancias;

public class Movimientos {

    private String a,b,c,d,e,f;
    
    public Movimientos(String a, String b, String c, String d, String e){
    this.a=a;
    this.b=b;
    this.c=c;
    this.d=d;
    this.e=e;
    }
    
    @Override
    public String toString(){
    
        return a+" "+b+" "+c+" "+d+" "+e+" "+f;
    }
    @Override
    public boolean equals(Object o) {
    if (o == null) {
        return false;
    }
    if (!(o instanceof Movimientos)) {
        return false;
    }
    
    Movimientos Aux = (Movimientos)o;
    
    return ( a.equals(Aux.getA()) && b.equals(Aux.getB()) && c.equals(Aux.getC())
            && d.equals(Aux.getD())&& e.equals(Aux.getE()));
}
    
    
    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getE() {
        return e;
    }

}
