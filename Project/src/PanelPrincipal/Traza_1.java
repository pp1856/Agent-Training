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

public class Traza_1 {
    
    private final String home;                                                  // ubicación del escritorio
    private final Charset charset;
    
    public static String[][] datos;                                             // datos de la tabla
    private String[][] traza;                                                   // datos del archivo de la traza
    
    // traza linguística
    private static String[][] texto = new String[1][4];                         // array con los datos de la traza linguística
    private int texto_length = 0;
    //private static final int texto_width = 3;
    
    private final String nombre_alumno;
    
    private double dja_prev, djr_prev, dar_prev;
    private String a_prev;
    
    // Atributos
    public final String a_peligro    = "peligro",
                        a_riesgo     = "riesgo",
                        a_proteccion = "proteccion",
                        a_distancia  = "distancia",
                        a_cercania   = "cercania",
                        a_accion     = "accion";
    
    // Valores nominales
    public static final String v_Baja            =   "baja",
                               v_Bajo            =   "bajo",
                               v_Leve            =   "leve",
                               v_Media           =   "media",
                               v_Moderado        =   "moderado",
                               v_Elevado         =   "elevado",
                               v_Alta            =   "alta",
                               v_Alto            =   "alto",
                               v_Extremo         =   "extremo",
                               v_Mas_cerca       = "\"mas cerca\"",
                               v_Mas_lejos       = "\"mas lejos\"",
                               v_Igual           =   "Igual",
                               v_Pequena         =   "pequena",
                               v_Grande          =   "grande",
                               v_Esperar         =   "esperar",
                               v_Seguir_adelante = "\"seguir adelante\"",
                               v_Evadir          =   "evadir";
    public static final String Mas_cerca         =   "mas cerca",
                               Mas_lejos         =   "mas lejos",
                               Seguir_adelante   =   "seguir adelante";
    
    // Atributos a predecir
    public static String[] valores = { Seguir_adelante, v_Evadir };   // array con los valores a predecir (debe estar en el mismo orden que los atributos en el archivo arff)
    
    public Traza_1(String[][] data){
        home = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath(); // establece la ubicación del escritorio o home (metodo independiente del SO)
        charset = Charset.forName("UTF-8");
        datos = data;
        nombre_alumno = "HF";
        djr_prev = 999;
        dar_prev = 999;
        a_prev = "";
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
                //attribute1 =  a_peligro+"         {"+v_Bajo+","+v_Moderado+","+v_Alto+"}",
                attribute1 =    a_proteccion+"      {"+v_Baja+","+v_Media+","+v_Alta+"}",
                attribute2 =    a_distancia+"       {"+v_Pequena+","+v_Media+","+v_Grande+"}",
                attribute3 =    a_cercania+"        {"+v_Mas_cerca+","+v_Mas_lejos+","+v_Igual+"}",
                attribute4 =    a_accion+"          {"+v_Seguir_adelante+","+v_Evadir+"}";
                //attribute3 =    "objetivo         {\"Mantiene objetivo\",\"Cambia de objetivo\"}",
                //attribute4 =    "proximidad       {\"Manteniendo distancia\",Acercandose,Alejandose}";
                
        String header = "@RELATION " +relation_name+"\n\n"+
                        "@ATTRIBUTE "+attribute1+"\n\n"+
                        "@ATTRIBUTE "+attribute2+"\n\n"+
                        "@ATTRIBUTE "+attribute3+"\n\n"+
                        "@ATTRIBUTE "+attribute4+"\n\n"+
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
        djr_prev = 999;
        dar_prev = 999;
        a_prev = "";
        //String o_prev = "";
        
        int i = 0;
        for(String[] fila : datos){
            /*
            //for(int j = 0; j < traza[0].length; j++){
                //double d = distancia(traza[i][0],traza[i][1],traza[i][2],traza[i][3]);  // distancia jugador-adversario1
                //if(d<=3)
                    //texto[i][0] = "Pequeña";
                //else if(d<=6)
                    //texto[i][0] = "Normal";
                //else
                    //texto[i][0] = "Grande";
                //d = distancia(traza[i][0],traza[i][1],traza[i][4],traza[i][5]);         // distancia jugador-adversario2
                //if(d<=3)
                    //texto[i][1] = "Pequeña";
                //else if(d<=6)
                    //texto[i][1] = "Normal";
                //else
                    //texto[i][1] = "Grande";
                //d = distancia(traza[i][0],traza[i][1],traza[i][6],traza[i][7]);         // distancia jugador-adversario3
                //if(d<=3)
                    //texto[i][2] = "Pequeña";
                //else if(d<=6)
                    //texto[i][2] = "Normal";
                //else
                    //texto[i][2] = "Grande";
                //int energia = Integer.parseInt(traza[i][8]);        // energia jugador
                //if(energia<=5)
                    //texto[i][3] = "Baja";
                //else if(energia<=10)
                    //texto[i][3] = "Normal";
                //else
                    //texto[i][3] = "Alta";*/
            if(fila[8].equals("J") || a_prev.equals("A")) {
                ++texto_length;
                /*int j = 0;
                int p = Integer.parseInt(fila[3]);              // protección jugador-adversario1
                String proteccion;
                if (p == 0)
                    proteccion = "Ninguna";
                else if(p <= 3)
                    proteccion = "Baja";
                else if(p <= 6)
                    proteccion = "Media";
                else
                    proteccion = "Alta";
                double d = Double.parseDouble(fila[4]);         // distancia jugador-adversario
                String distancia_ja;
                if (d<=5)
                    distancia_ja = "Pequeña";
                else if(d<=11)
                    distancia_ja = "Normal";
                else
                    distancia_ja = "Grande";
                
                String peligro;
                if (proteccion.equals("Ninguna") && distancia_ja.equals("Pequeña"))
                    peligro = "\"Muy inseguro\"";
                else if (proteccion.equals("Baja") && (distancia_ja.equals("Pequeña") || distancia_ja.equals("Normal")))
                    peligro = "Inseguro";
                else if (proteccion.equals("Media") && (distancia_ja.equals("Pequeña") || distancia_ja.equals("Normal")))
                    peligro = "Seguro";
                else
                    peligro = "\"Muy seguro\"";
                    
                d = Double.parseDouble(fila[5]);                // distancia adversario-recompensa cercana
                String distancia_ar;
                if (d<=5)
                    distancia_ar = "Pequeña";
                else if(d<=11)
                    distancia_ar = "Normal";
                else
                    distancia_ar = "Grande";
                
                String movimiento_ar;
                if (d == dar_prev)
                    movimiento_ar = "\"Manteniendo distancia\"";
                else if (d<dar_prev)
                    movimiento_ar = "Acercandose";
                else
                    movimiento_ar = "Alejandose";
                dar_prev = d;
                
                d = Double.parseDouble(fila[6]);                // distancia jugador-recompensa cercana
                String distancia_jr;
                if (d<=5)
                    distancia_jr = "Pequeña";
                else if(d<=11)
                    distancia_jr = "Normal";
                else
                    distancia_jr = "Grande";
                
                String movimiento_jr;
                if (d == djr_prev)
                    movimiento_jr = "\"Manteniendo distancia\"";
                else if (d<djr_prev)
                    movimiento_jr = "Acercandose";
                else
                    movimiento_jr = "Alejandose";
                djr_prev = d;
                
                String riesgo;
                if((distancia_jr.equals("Grande") || distancia_jr.equals("Normal")) && distancia_ar.equals("Pequeña"))
                    riesgo = "\"Muy alto\"";
                else if (distancia_jr.equals("Pequeña") && distancia_ar.equals("Pequeña"))
                    riesgo = "Alto";
                else if (distancia_jr.equals("Grande") && distancia_ar.equals("Normal"))
                    riesgo = "Alto";
                else if (distancia_jr.equals("Normal") && distancia_ar.equals("Normal"))
                    riesgo = "Bajo";
                else if (distancia_jr.equals("Grande") && distancia_ar.equals("Grande"))
                    riesgo = "Bajo";
                else
                    riesgo = "\"Muy bajo\"";
                
                String accion;
                if (movimiento_jr.equals("\"Manteniendo distancia\""))
                    accion = "Esperar";
                else if (movimiento_jr.equals("Acercandose") && movimiento_ar.equals("Acercandose"))
                    accion = "\"Seguir adelante\"";
                else
                    accion = "Evadir";
                
                //String objetivo, o = fila[2];
                //if(o.equals(o_prev))
                    //objetivo = "\"Mantiene objetivo\"";
                //else
                    //objetivo = "\"Cambia de objetivo\"";
                //o_prev = o;
                
                //d = Double.parseDouble(fila[6]);                // distancia jugador-recompensa cercana
                //if(d<=3)
                    //texto[i][j++] = "Pequeña";
                //else if(d<=6)
                    //texto[i][j++] = "Normal";
                //else
                    //texto[i][j++] = "Grande";
                
                //String accion;
                //if(proximidad.equals("Mantiene_distancia"))
                    //accion = "Esperar";
                //else if (proximidad.equals("Se_acerca"))
                    //accion = "Seguir";
                //else 
                    //accion = "Huir";
                    
                texto[i][j++] = peligro;
                texto[i][j++] = riesgo;
                //texto[i][j++] = objetivo;
                //texto[i][j++] = movimiento_jr;
                texto[i][j++] = accion;
                */
                /*int j = 0;
                String[] variables = getLinguisticVariables(fila);
                texto[i][j++] = variables[j];
                texto[i][j++] = variables[j];
                texto[i][j++] = variables[j];*/
                Object[] row = getLinguisticVariables(fila);
                texto[i] = Arrays.copyOf(row, row.length, String[].class);
                
                ++i;
            }
            a_prev = fila[8];
                /*proteccion = Float.parseFloat(traza[i][10]);        // protección jugador-adversario2
                if(proteccion<2)
                    texto[i][5] = "Baja";
                else if(proteccion<3)
                    texto[i][5] = "Media";
                else
                    texto[i][5] = "Alta";
                proteccion = Float.parseFloat(traza[i][11]);        // protección jugador-adversario3
                if(proteccion<2)
                    texto[i][6] = "Baja";
                else if(proteccion<3)
                    texto[i][6] = "Media";
                else
                    texto[i][6] = "Alta";
                d = Double.parseDouble(traza[i][14]);               // distancia adversario2-recompensa cercana
                if(d<=3)
                    texto[i][9] = "Pequeña";
                else if(d<=6)
                    texto[i][9] = "Normal";
                else
                    texto[i][9] = "Grande";
                d = Double.parseDouble(traza[i][15]);               // distancia adversario3-recompensa cercana
                if(d<=3)
                    texto[i][10] = "Pequeña";
                else if(d<=6)
                    texto[i][10] = "Normal";
                else
                    texto[i][10] = "Grande";
                Long tiempo = Long.parseLong(traza[i][16]);         // tiempo
                if(tiempo<=60000)
                    texto[i][11] = "Poco";
                else if(tiempo<=120000)
                    texto[i][11] = "Razonable";
                else
                    texto[i][11] = "Mucho";
                int iteraciones = Integer.parseInt(traza[i][18]);   // iteraciones
                if(iteraciones<=13)
                    texto[i][12] = "Pocas";
                else if(iteraciones<=26)
                    texto[i][12] = "Normal";
                else
                    texto[i][12] = "Muchas";
                int memoria = Integer.parseInt(traza[i][19]);       // memoria
                if(memoria<=1024)
                    texto[i][13] = "Baja";
                else if(memoria<=2048)
                    texto[i][13] = "Media";
                else
                    texto[i][13] = "Alta";*/
            //}
        }
        
    }
    
    public Object[] getLinguisticVariables(String[] fila) {
        Object[] result = new String[texto[0].length];
        int j = 0;
        
        double p = Double.parseDouble(fila[3]);         // protección jugador-adversario1
        String proteccion;
        if(p <= 0.33)
            proteccion = v_Baja;
        else if(p <= 0.66)
            proteccion = v_Media;
        else
            proteccion = v_Alta;
        
        double d = Double.parseDouble(fila[4]);         // distancia jugador-adversario
        String distancia_ja;
        if (d<=4)
            distancia_ja = v_Pequena;
        else if(d<=8)
            distancia_ja = v_Media;
        else
            distancia_ja = v_Grande;
        double dja = d;
        
        /*
        String peligro;
        if ((proteccion.equals("Alta") || proteccion.equals("Media")) && distancia_ja.equals("Grande"))
            peligro = v_Bajo;
        else
            if (proteccion.equals("Alta") && distancia_ja.equals("Media"))
            peligro = v_Bajo;
        else
            if (distancia_ja.equals("Grande"))
            peligro = v_Moderado;
        else 
            if (proteccion.equals("Media") && distancia_ja.equals("Media"))
            peligro = v_Moderado;
        //else 
        //    if (proteccion.equals("Baja") && distancia_ja.equals("Grande"))
        //    peligro = v_Moderado;
        else
            peligro = v_Alto;
        */

        d = Double.parseDouble(fila[5]);                // distancia adversario-recompensa cercana
        String distancia_ar;
        if (d<=4)
            distancia_ar = "Pequeña";
        else if(d<=8)
            distancia_ar = "Media";
        else
            distancia_ar = "Grande";

        String movimiento_ar;
        if (d == dar_prev)
            movimiento_ar = "\"Manteniendo distancia\"";
        else if (d<dar_prev)
            movimiento_ar = "Acercandose";
        else
            movimiento_ar = "Alejandose";
        dar_prev = d;

        d = Double.parseDouble(fila[6]);                // distancia jugador-recompensa cercana
        String distancia_jr;
        if (d<=4)
            distancia_jr = "Pequeña";
        else if(d<=8)
            distancia_jr = "Media";
        else
            distancia_jr = "Grande";
        double djr = d;

        String movimiento_jr;
        if (d == djr_prev)
            movimiento_jr = "\"Manteniendo distancia\"";
        else if (d<djr_prev)
            movimiento_jr = "Acercandose";
        else
            movimiento_jr = "Alejandose";

        String cercania_jr;
        if(djr < dar_prev)
            cercania_jr = (datos == null)? Mas_cerca : v_Mas_cerca;
        else if((djr > dar_prev))
            cercania_jr = (datos == null)? Mas_lejos : v_Mas_lejos;
        else
            cercania_jr = v_Igual;
        /*
        String riesgo;
        if(distancia_jr.equals("Media") && distancia_ar.equals("Grande"))
            riesgo = v_Leve;
        else 
            if(distancia_jr.equals("Pequeña") && distancia_ar.equals("Media"))
            riesgo = v_Leve;
        else if (distancia_jr.equals("Pequeña") && distancia_ar.equals("Pequeña"))
            riesgo = v_Elevado;
        else if (distancia_jr.equals("Media") && distancia_ar.equals("Media"))
            riesgo = v_Elevado;
        else if (distancia_jr.equals("Grande") && distancia_ar.equals("Grande"))
            riesgo = v_Elevado;
        else
            riesgo = v_Extremo;
        */

        /*
        String accion;
        if (movimiento_jr.equals("\"Manteniendo distancia\""))
            accion = v_Esperar;
        else if (movimiento_jr.equals("Acercandose") && movimiento_ar.equals("Acercandose"))
            accion = v_Seguir_adelante;
        else
            accion = v_Evadir;
        */
        
        String accion;
        /*if (djr == djr_prev)
            accion = v_Evadir;
        else */if (dja >= dja_prev)
            accion = v_Evadir;
        else
            accion = v_Seguir_adelante;
        
        dja_prev = dja;
        djr_prev = djr;
        
        result[j++] = proteccion;
        result[j++] = distancia_ja;
        result[j++] = cercania_jr;
        result[j++] = accion;
        return result;
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
    
    public void reset() {
        djr_prev = 999;
        dar_prev = 999;
        a_prev = "";
    }
}
