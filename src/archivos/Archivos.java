package archivos;

import archivos2.MakerFiles;
import archivos2.Temporizador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CODE.zip
 */
public class Archivos {

    static ArrayList<String> rutas = new ArrayList<>();
    public static ArrayList<String> ruras = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        /* System.out.println(" arrayList:\n\n\n");
        for (int i = 0; i < rutas.size(); i++) {
            System.out.println(rutas.get(i));
        
        }*/
        
        
        File file = new File("Origen");
       Archivos c = new Archivos();

//        for (int i = 0; i <ruras.size(); i++) {
//            System.out.println("hahaha: "+ruras.get(i));
//        }
    }

    public void recorrido(File file, File respaldo) throws IOException {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {

                CorregirRuta ruta = new CorregirRuta(f.getPath(), "\\", "\\\\");
                String co = ruta.obtenerRutaCorregidaWindows();

                String trozarRuta = co.substring(7, co.length());

                respaldo = new File("Destino" + trozarRuta);

                String rutacompleta = respaldo + trozarRuta;
                rutas.add(rutacompleta);
                respaldo.mkdir();

                //System.out.println("Carpeta"+f.getPath());
                System.out.println("Entrada");
                recorrido(f, respaldo);
                System.out.println("Saliendo...");

            } else {
                CorregirRuta ruta = new CorregirRuta(f.getPath(), "\\", "\\\\");
                String co = ruta.obtenerRutaCorregidaWindows();

                String trozarRuta = co.substring(7, co.length());
                respaldo = new File("Destino\\" + trozarRuta);
                String rutacompleta = respaldo + "\\" + trozarRuta;
                rutas.add(rutacompleta);
                File prue = new File(co);
                copiarArchivos(prue, respaldo);

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
            Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificar(File prueba, File respaldo) throws IOException {
        for (File f: prueba.listFiles()) {
            if(f.isDirectory()){
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(7,rutaC.length());
                //SOUT ANT
                respaldo= new File("Destino"+Cortarura);
                if(!respaldo.exists()){
                   MakerFiles.guardarCuenta("se agrego la carpeta:: "+respaldo.getPath()+" "+new Date()+"\n"," archivo.txt");
                   System.out.println("Se agrego una nueva carpeta : "+Cortarura);
                    respaldo.mkdir();
                }
                verificar(f, respaldo);
            }else{
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(7,rutaC.length());
                //SOUT ANT
                respaldo=new File("Destino"+Cortarura);
                if(!respaldo.exists()){
                    MakerFiles.guardarCuenta("se agrego el archivo: "+respaldo.getPath()+" "+new Date()+"\n", "archivo.txt");
                    System.out.println("Se agrego un archivo : "+Cortarura);
                    File prue1 = new File(rutaC);
                    copiarArchivos(prue1, respaldo);
                }
            }
        }
        
    }
    public void verificarRespaldo(File prueba,File respaldo){
        
       for (File f:respaldo.listFiles()) {
            
            if(f.isDirectory()){
                System.out.println(f.getPath());
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(9,rutaC.length());
                System.out.println("aaaaaaaaaaaa: "+Cortarura);
                prueba=new File("Origen"+Cortarura);
                if(!prueba.exists()){
                   MakerFiles.guardarCuenta(" se elimino la carpeta:"+f.getPath()+" "+new Date()+"\n","archivo.txt");
                    f.delete();
                }
                verificarRespaldo(prueba,f);
            }else{
                CorregirRuta co= new CorregirRuta(f.getPath(),"\\","\\\\");
                String rutaC=co.obtenerRutaCorregidaWindows();
                String Cortarura=rutaC.substring(9,rutaC.length());
                prueba=new File("Origen"+Cortarura);
                if(!prueba.exists()){
                    MakerFiles.guardarCuenta("se elimino el archivo: "+f.getPath()+" "+new Date()+"\n","archivo.txt");
                    f.delete();
                }
                
            }
        }
    }
}