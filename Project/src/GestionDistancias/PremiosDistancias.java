package GestionDistancias;

public class PremiosDistancias {

    private String a,b,c,d;
    
    public PremiosDistancias(String a, String b, String c, String d){
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
        if (!(o instanceof PremiosDistancias)) {
            return false;
        }
        return a.equals(((PremiosDistancias) o).getA()) && b.equals(((PremiosDistancias) o).getB()) && c.equals(((PremiosDistancias) o).getC())
                && d.equals(((PremiosDistancias) o).getD());
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
