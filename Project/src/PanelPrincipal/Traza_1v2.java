package PanelPrincipal;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;

public class Traza_1v2 {
    
    private final String home;                                                  // ubicación del escritorio
    private final Charset charset;
    
    public static String[][] datos;                                             // datos de la tabla
    private String[][] traza;                                                   // datos del archivo de la traza
    
    // traza linguística
    private static String[][] texto = new String[1][7];                         // array con los datos de la traza linguística
    private int texto_length = 0;
    private static boolean primera_linea = true;
    //private static final int texto_width = 3;
    
    private final String nombre_alumno;
    
    private static double dja_prev, p_prev, djr_prev, dar_prev;
    private String a_prev;
    
    // Atributos
    public final String a_peligro    = "peligro",
                        a_riesgo     = "riesgo",
                        a_distancia  = "distancia",
                        a_cercania   = "cercania",
                        a_accion     = "accion",
            
                        a_distancia_ar = "distancia_ar",
                        a_distancia_jr = "distancia_jr",
                        a_proteccion   = "proteccion",
                        a_distancia_ja = "distancia_ja",
                        a_tiempo       = "tiempo",
                        a_actitud      = "actitud",
                        a_situacion    = "situacion",
                        a_movimiento   = "movimiento",
            
                        gv_distancia_ar = "gv_distancia_ar",
                        gv_distancia_jr = "gv_distancia_jr",
                        gv_proteccion   = "gv_proteccion",
                        gv_distancia_ja = "gv_distancia_ja",
                        gv_tiempo       = "gv_tiempo",
                        gv_actitud      = "gv_actitud",
                        gv_situacion    = "gv_situacion",
                        gv_movimiento   = "gv_movimiento",
            
                        gv_cercania_jr  = "gv_cercania_jr";
    
    // Valores nominales
    public static final String v_Alta            =   "alta",
                               v_Alto            =   "alto",
                               v_Baja            =   "baja",
                               v_Bajo            =   "bajo",
                               v_Elevado         =   "elevado",
                               v_Esperar         =   "esperar",
                               v_Evadir          =   "evadir",
                               v_Extremo         =   "extremo",
                               v_Grande          =   "grande",
                               v_Igual           =   "igual",
                               v_Leve            =   "leve",
                               v_Mas_cerca       = "\"mas cerca\"",
                               v_Mas_lejos       = "\"mas lejos\"",
                               v_Media           =   "media",
                               v_Moderado        =   "moderado",
                               v_Mucho           =   "Mucho",
                               v_Mucho_mas_cerca = "\"mucho mas cerca\"",
                               v_Mucho_mas_lejos = "\"mucho mas lejos\"",
                               v_Pequena         =   "pequena",
                               v_Poco            =   "poco",
                               v_Razonable       =   "razonable",
                               v_Seguir_adelante = "\"seguir adelante\"",
            
                               v_Sabio           =   "sabio",
                               v_Valiente        =   "valiente",
                               v_Prudente        =   "prudente",
                               v_Pasivo          =   "pasivo",
            
                               v_Arriesgada      =   "arriesgada",
                               v_Peligrosa       =   "peligrosa",
                               v_Segura          =   "segura",
                               v_facil           =   "facil",
            
                               v_Bueno           =   "bueno",
                               v_Asustado        =   "asustado",
                               v_Kamikaze        =   "kamikaze",
                               v_Malo            =   "malo";
            
    public static final String Mas_cerca         =   "mas cerca",
                               Mas_lejos         =   "mas lejos",
                               Mucho_mas_cerca   = "\"mucho mas cerca\"",
                               Mucho_mas_lejos   = "\"mucho mas lejos\"",
                               Seguir_adelante   =   "seguir adelante";
    
    // Atributos a predecir
    public static String[] valores = { Seguir_adelante, v_Evadir };   // array con los valores a predecir (debe estar en el mismo orden que los atributos en el archivo arff)
    
    public Traza_1v2(String[][] data){
        home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(); // establece la ubicación del escritorio o home (metodo independiente del SO)
        charset = Charset.forName("UTF-8");
        datos = data;
        nombre_alumno = "HF";
    }
    
    /**
     * Imprime la traza de ejecución a un archivo txt ubicado en el escritorio o home.
     */
    public void trazadeEjecucion(){
        
        String file = home+"\\traza_"+nombre_alumno+".txt";
        System.out.println(file);
        Path path = Paths.get(file);
        System.out.println("FILAS: "+datos.length);
        System.out.println("Columnas: "+datos[0].length);
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {
            for (String[] fila : datos) {
                for (int j = 0; j < datos[0].length; j++) { 
                    if (j<3) {
                        Scanner s = new Scanner(fila[j]).useDelimiter(",");
                        String st = s.next() + ", " + s.next() + ", ";
                        writer.write(st, 0, st.length());
                    } else if (j==datos[0].length-1) {
                        writer.write(fila[j], 0, fila[j].length());
                    } else {
                        writer.write(fila[j] + ", ", 0, (fila[j] + ", ").length());
                    }
                }
                writer.write("\r\n", 0, "\r\n".length());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
    }
    
    /**
     * imrprime la traza de ejecución linguística a un archivo txt en el escritorio o home.
     */
    public void trazadeEjecucionlinguistica(){
        String file = home+"\\dataset.arff";
        System.out.println(file);
        System.out.println("FILAS: "+texto.length);
        System.out.println("Columnas: "+texto[0].length);
        Path path = Paths.get(file);
        
        // Attribute-Relation File Format (Arff) file header section -----------
        String  relation_name = "player",
                //attribute01 =    a_distancia_ar+"       {"+v_Pequena+","+v_Media+","+v_Grande+"}",
                //attribute02 =    gv_distancia_ar+"    NUMERIC",
                //attribute03 =    a_distancia_jr+"       {"+v_Pequena+","+v_Media+","+v_Grande+"}",
                //attribute04 =    gv_distancia_jr+"    NUMERIC",
                attribute05 =    a_proteccion+"         {"+v_Baja+","+v_Media+","+v_Alta+"}",
                attribute06 =    gv_proteccion+"      NUMERIC",
                attribute07 =    a_distancia_ja+"       {"+v_Pequena+","+v_Media+","+v_Grande+"}",
                attribute08 =    gv_distancia_ja+"    NUMERIC",
                //attribute09 =    a_tiempo+"             {"+v_Poco+","+v_Razonable+","+v_Mucho+"}",
                //attribute10 =    gv_tiempo+"          NUMERIC",
                //attribute11 =    a_actitud+"            {"+v_Sabio+","+v_Valiente+","+v_Prudente+","+v_Pasivo+"}",
                //attribute12 =    gv_actitud+"         NUMERIC",
                //attribute13 =    a_situacion+"          {"+v_Arriesgada+","+v_Peligrosa+","+v_Segura+","+v_facil+"}",
                //attribute14 =    gv_situacion+"       NUMERIC",
                //attribute15 =    a_movimiento+"         {"+v_Bueno+","+v_Asustado+","+v_Kamikaze+","+v_Malo+"}",
                //attribute16 =    gv_movimiento+"      NUMERIC",
                attribute17 =    a_cercania+"           {"+v_Mucho_mas_cerca+","+v_Mas_cerca+","+v_Mas_lejos+","+v_Mucho_mas_lejos+"}",
                attribute18 =    gv_cercania_jr+"      NUMERIC",
                attribute19 =    a_accion+"             {"+v_Seguir_adelante+","+v_Evadir+"}";
                
        String header = "@RELATION " +relation_name+"\n\n"+
                        //"@ATTRIBUTE "+attribute01+"\n"+
                        //"@ATTRIBUTE "+attribute02+"\n"+
                        //"@ATTRIBUTE "+attribute03+"\n"+
                        //"@ATTRIBUTE "+attribute04+"\n"+
                        "@ATTRIBUTE "+attribute05+"\n"+
                        "@ATTRIBUTE "+attribute06+"\n"+
                        "@ATTRIBUTE "+attribute07+"\n"+
                        "@ATTRIBUTE "+attribute08+"\n"+
                        //"@ATTRIBUTE "+attribute09+"\n"+
                        //"@ATTRIBUTE "+attribute10+"\n"+
                        //"@ATTRIBUTE "+attribute11+"\n"+
                        //"@ATTRIBUTE "+attribute12+"\n"+
                        //"@ATTRIBUTE "+attribute13+"\n"+
                        //"@ATTRIBUTE "+attribute14+"\n"+
                        //"@ATTRIBUTE "+attribute15+"\n"+
                        //"@ATTRIBUTE "+attribute16+"\n"+
                        "@ATTRIBUTE "+attribute17+"\n"+
                        "@ATTRIBUTE "+attribute18+"\n"+
                        "@ATTRIBUTE "+attribute19+"\n\n"+
                        "@DATA\n";
        //----------------------------------------------------------------------
        
        try(BufferedWriter writer = Files.newBufferedWriter(path, charset)) {           
            writer.write(header, 0, header.length());
            for(int i = 0; i < texto_length; i++){
                for(int j = 0; j < texto[0].length; j++){
                    if(j==texto[0].length-1)
                        writer.write(texto[i][j], 0, texto[i][j].length());
                    else
                        writer.write(texto[i][j]+",", 0, (texto[i][j]+",").length());
                }
                writer.write("\r\n", 0, "\r\n".length());
            }
        } catch(IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
    }
    
    /**
     * lee la traza de ejecución desde el archivo creado.
     */
    public void leeTrazadeejecucion(){
        
        String file = home+"\\traza_"+nombre_alumno+".txt";
        System.out.println(file);
        Path path = Paths.get(file);
        
        int columnCount = (datos.length > 0) ? datos[0].length + 3 : 0;
        try {
            LineNumberReader lnr = new LineNumberReader(new FileReader(path.toFile()));
            lnr.skip(Long.MAX_VALUE);
            traza = new String[lnr.getLineNumber()][columnCount];
            //lnr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Traza.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Traza.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                int j = 0;
                Scanner s = new Scanner(line).useDelimiter(", ");
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                traza[i][j++] = s.next();
                i = i + 1;
            }
            System.out.println("FILAS: "+traza.length);
            System.out.println("Columnas: "+traza[0].length);
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        
    }
    
    /**
     * interpreta los datos leídos de la traza de ejecución para la creación de
     * la traza de ejecución linguistica.
     */
    public void datosaTexto(){
        
        texto = new String [datos.length][texto[0].length];
        texto_length = 0;
        primera_linea = true;
        dja_prev = 999;
        p_prev = 1.00d;
        djr_prev = 999;
        dar_prev = 999;
        a_prev = "";
        
        int i = 0;
        for(String[] fila : datos){
            
            Object[] row = getInstance(fila);
            if(row != null && fila[9].equals("J")) {
                texto[i] = Arrays.copyOf(row, row.length, String[].class);
                ++texto_length;
                ++i;
            }
            a_prev = fila[9];

        }
        
    }
    
    public Object[] getInstance(String[] fila) {
        Object[] result = new Object[texto[0].length];
        boolean  plinea = primera_linea;
        if(plinea)
            primera_linea = false;
        int j = 0;   
        
        double d = Double.parseDouble(fila[4]);         // distancia jugador-adversario
        double dja = d;
        String distancia_ja;
        double gv_distancia_ja;
        d = (datos == null) ? d : dja_prev;
        if (d<=4) {
            distancia_ja = v_Pequena;
            gv_distancia_ja = pertenenciaTrapezoidal(d, 0, 0, 2, 6);
        } else if(d<=8) {
            distancia_ja = v_Media;
            gv_distancia_ja = pertenenciaTrapezoidal(d, 2, 6, 6, 10);
        } else {
            distancia_ja = v_Grande;
            gv_distancia_ja = pertenenciaTrapezoidal(d, 6, 10, 1000, 1000);
        }
        
        d = Double.parseDouble(fila[3]);                // protección jugador-adversario1
        double p = d;
        String proteccion;
        double gv_proteccion;
        d = (datos == null) ? d : p_prev;
        if(d <= 0.33) {
            proteccion = v_Baja;
            gv_proteccion = pertenenciaTrapezoidal(d, 0, 0, 0.25, 0.41);
        } else if(d <= 0.66) {
            proteccion = v_Media;
            gv_proteccion = pertenenciaTrapezoidal(d, 0.25, 0.41, 0.58, 0.75);
        } else {
            proteccion = v_Alta;
            gv_proteccion = pertenenciaTrapezoidal(d, 0.58, 0.75, 1.0, 1.0);
        }
        
        d = Double.parseDouble(fila[6]);                // distancia jugador-recompensa cercana
        double djr = d;
        /*String distancia_jr;
        double gv_distancia_jr;
        d = (datos == null) ? d : djr_prev;
        if (d<=4) {
            distancia_jr = v_Pequena;
            gv_distancia_jr = pertenenciaTrapezoidal(d, 0, 0, 2, 6);
        } else if(d<=8) {
            distancia_jr = v_Media;
            gv_distancia_jr = pertenenciaTrapezoidal(d, 2, 6, 6, 10);
        } else {
            distancia_jr = v_Grande;
            gv_distancia_jr = pertenenciaTrapezoidal(d, 6, 10, 1000, 1000);
        }*/

        d = Double.parseDouble(fila[5]);                // distancia adversario-recompensa cercana
        double dar = d;
        /*String distancia_ar;
        double gv_distancia_ar;
        d = (datos == null) ? d : dar_prev;
        if (d<=4) {
            distancia_ar = v_Pequena;
            gv_distancia_ar = pertenenciaTrapezoidal(d, 0, 0, 2, 6);
        } else if(d<=8) {
            distancia_ar = v_Media;
            gv_distancia_ar = pertenenciaTrapezoidal(d, 2, 6, 6, 10);
        } else {
            distancia_ar = v_Grande;
            gv_distancia_ar = pertenenciaTrapezoidal(d, 6, 10, 1000, 1000);
        }*/
        
        String cercania_jr;
        double gv_cercania_jr;
        d = (datos == null) ? dar - djr : dar_prev - djr_prev;
        if(d > 4) {
            cercania_jr = (datos == null)? Mucho_mas_cerca : v_Mucho_mas_cerca;
            gv_cercania_jr = pertenenciaTrapezoidal(d, 2, 6, 1000, 1000);
        } else if(d > 0) {
            cercania_jr = (datos == null)? Mas_cerca : v_Mas_cerca;
            gv_cercania_jr = pertenenciaTrapezoidal(d, -2, 2, 2, 6);
        } else if(d >= -4) {
            cercania_jr = (datos == null)? Mas_lejos : v_Mas_lejos;
            gv_cercania_jr = pertenenciaTrapezoidal(d, -6, -2, -2, 2);
        } else {
            cercania_jr = (datos == null)? Mucho_mas_lejos : v_Mucho_mas_lejos;
            gv_cercania_jr = pertenenciaTrapezoidal(d, -1000, -1000, -6, -2);
        }
        
        String accion;
        if (dja >= dja_prev)
            accion = v_Evadir;
        else
            accion = v_Seguir_adelante;
        
        dja_prev = dja;
        p_prev   = p;
        djr_prev = djr;
        dar_prev = dar;
        
        result[j++] = proteccion;
        result[j++] = (datos == null) ? gv_proteccion : String.valueOf(gv_proteccion);
        result[j++] = distancia_ja;
        result[j++] = (datos == null) ? gv_distancia_ja : String.valueOf(gv_distancia_ja);
        result[j++] = cercania_jr;
        result[j++] = (datos == null) ? gv_cercania_jr : String.valueOf(gv_cercania_jr);
        result[j++] = accion;
        return (plinea) ? null : result;
    }
    
    /**
     * Calcula la distancia entre dos puntos (x1,y1) y (x2,y2)
     * 
     * @param jugx
     * @param jugy
     * @param advx
     * @param advy
     * @return 
     */
    public static double distancia(String jugx, String jugy, String advx, String advy){
        
        int jugadorx = Integer.parseInt(jugx);
        int jugadory = Integer.parseInt(jugy);
        int adversariox = Integer.parseInt(advx);
        int adversarioy = Integer.parseInt(advy);
        return Math.sqrt((jugadorx - adversariox) *  (jugadorx - adversariox) + (jugadory - adversarioy) *  (jugadory - adversarioy));
        
    }
    
    private static double pretenenciaTriangular(double x, double a, double b, double c) {
        if ( a == b && b == c ) {
            if ( a == x ) return 1.0;
            else return 0.0;
        }else {
            if ( x  <=  a ) return 0.0;
            else {
                if ( x > a && x <= b ) return (x-a)/(b-a);
                else {
                    if ( x >= c ) return 0.0;
                    else {        
                        if ( x > b && x < c ) return (c-x)/(c-b);
                        else return 0.0;
                    } 
                }
            }        
        }
    }

    private static double pertenenciaTrapezoidal(double x, double a, double b, double c, double d) {
        double h = 1.0;
        if ( a == b && b == c && c == d ) {  
            if ( x == a ) return h;
            else return 0.0;
        }else {    
            if ( x  <=  a ) {
                if ( a == b ) return h;
                else return 0.0;  
            }else {
                if ( a < x && x <= b ) return (x-a)/(b-a);
                else {
                    if ( b < x && x <= c ) return h;
                    else {        
                        if ( c < x && x <= d ) {
                            if ( c == d ) return h;
                            else return (d-x)/(d-c);
                        } else return 0.0;
                    } 
                }
            }        
        }
    }
    
}
