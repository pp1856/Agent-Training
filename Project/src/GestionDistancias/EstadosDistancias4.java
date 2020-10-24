package GestionDistancias;

public class EstadosDistancias4 {

    private String a,b,c,d;
    
    public EstadosDistancias4(String a, String b, String c, String d){
    this.a=a;
    this.b=b;
    this.c=c;
    this.d=d;
    }
    
    @Override
    public String toString(){
    
        return a+" "+b+" "+c+" "+d;
    }

   
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof EstadosDistancias4)) {
            return false;
        }
        return a.equals(((EstadosDistancias4) o).getA()) && b.equals(((EstadosDistancias4) o).getB()) && c.equals(((EstadosDistancias4) o).getC())
                && d.equals(((EstadosDistancias4) o).getD());
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
}
