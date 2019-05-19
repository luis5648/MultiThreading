package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CODE.zip
 */
public class Archivos {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static ArrayList<String> rutas = new ArrayList<String>();
    public static ArrayList<String> ruras = new ArrayList<String>();
    MakerFiles l = new MakerFiles();
    
    public static void main(String... args) throws IOException {
//      try {
//
//            Archivo();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
//        }
        File file = new File("prueba");
        Archivos c = new Archivos();
        
        

    }

    public static void Archivo()
            throws InterruptedException, IOException {

        String[] mish = new String[9];
        String path = "Origen";

        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();

                mish[i] = files;

                Path FROM = Paths.get("Origen\\" + mish[i] + "");
                Path TO = Paths.get("Destino\\" + mish[i] + "");
                //overwrite existing file, if exists
                CopyOption[] options = new CopyOption[]{
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.COPY_ATTRIBUTES,
                    
                    
                };
                Files.copy(FROM, TO, options);
            }
        }

        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                String m = null;
                String s = "Actualizacion";
                m = ("\n Se realizo un respaldo en  : " + new Date());

                File f = new File(s);
                try {
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.append(m);
                    pw.close();
                    bw.close();

                } catch (IOException e) {
                    System.err.println("error" + e);
                }
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(repeatedTask, 1L, 3L, TimeUnit.SECONDS);
        
        Thread.sleep(7000);
        executor.shutdown();
    }
    
    
    
    
     public void recorrido(File file, File Destino) throws IOException {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {

                CorregirRuta ruta = new CorregirRuta(f.getPath(), "\\", "\\\\");
                String co = ruta.obtenerRutaCorregidaWindows();

                String trozarRuta = co.substring(7, co.length());

                Destino = new File("respaldo" + trozarRuta);

                String rutacompleta = Destino + trozarRuta;
                rutas.add(rutacompleta);
                Destino.mkdir();

                //System.out.println("Carpeta"+f.getPath());
                System.out.println("Entrada");
                recorrido(f, Destino);
                System.out.println("Saliendo...");

            } else {
                CorregirRuta ruta = new CorregirRuta(f.getPath(), "\\", "\\\\");
                String co = ruta.obtenerRutaCorregidaWindows();

                String trozarRuta = co.substring(7, co.length());
                Destino = new File("respaldo\\" + trozarRuta);
                String rutacompleta = Destino + "\\" + trozarRuta;
                rutas.add(rutacompleta);
                File prue = new File(co);
                copiarArchivos(prue, Destino);

                System.out.println("ruta " + co);

            }
        }

    }
    
    
    
      public void copiarArchivos(File pruebaOrigen, File respaldoDestino) throws IOException {
        try {
            FileInputStream in = new FileInputStream(pruebaOrigen);

            FileOutputStream out = new FileOutputStream(respaldoDestino);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

            in.close();
            out.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      

    public void guardarArchivo(String msj) {
        String s = "Actualizacion";
        String mensaje = null;
        msj = mensaje;
        File f = new File(s);

        try {
            FileWriter fw = new FileWriter(f, true);
            try (BufferedWriter bw = new BufferedWriter(fw); 
                    PrintWriter pw = new PrintWriter(bw)) {
                pw.append(mensaje);
            }

        } catch (IOException e) {
            System.err.println("error" + e);
        }

    }
    
    
    
        public void verificar(File Origen, File Destino) throws IOException {
        for (File f: Origen.listFiles()) {
            if(f.isDirectory()){
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(7,rutaC.length());
                System.out.println("cocococo"+Cortarura);
                Destino= new File("respaldo"+Cortarura);
                if(!Destino.exists()){
                   l.guardarCuenta("Se agrego la carpeta:: "+Destino.getPath()+" "+new Date()+"\n"," archivo.txt");
                    Destino.mkdir();
                }
                verificar(f, Destino);
            }else{
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(7,rutaC.length());
                Destino=new File("respaldo"+Cortarura);
                if(!Destino.exists()){
                    MakerFiles.guardarCuenta("Se agrego el archivo: "+Destino.getPath()+" "+new Date()+"\n", "archivo.txt");
                    File prue1 = new File(rutaC);
                    copiarArchivos(prue1, Destino);
                }
            }
        }
        
    }
    
    
    
       public void verificarRespaldo(File Origen,File Destino){
        
       for (File f:Origen.listFiles()) {
            
            if(f.isDirectory()){
                System.out.println(f.getPath());
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(9,rutaC.length());
                System.out.println("aaaaaaaaaaaa: "+Cortarura);
                Origen=new File("prueba"+Cortarura);
                if(!Origen.exists()){
                   MakerFiles.guardarCuenta(" se elimino la carpeta:"+f.getPath()+" "+new Date()+"\n","archivo.txt");
                    f.delete();
                }
                verificarRespaldo(Origen,f);
            }else{
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(9,rutaC.length());
                Origen=new File("Origen"+Cortarura);
                if(!Origen.exists()){
                    MakerFiles.guardarCuenta("se elimino el archivo: "+f.getPath()+" "+new Date()+"\n","archivo.txt");
                    f.delete();
                }
                
            }
        }
    }

}