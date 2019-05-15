/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
     */
    public static void main(String... args) throws IOException {
      try {

            Archivo();
        } catch (InterruptedException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public void guardarArchivo(String msj) {
        String s = "Actualizacion";
        String mensaje = null;
        msj = mensaje;
        File f = new File(s);

        try {
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.append(mensaje);
            pw.close();
            bw.close();

        } catch (IOException e) {
            System.err.println("error" + e);
        }

    }

}