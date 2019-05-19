package archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.Date;
import java.util.Timer;
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
public class Temporizador {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
    
        copiarArchivos();
        verificar();

    }




    public static void copiarArchivos() throws InterruptedException {
        Archivos archivos = new Archivos();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    File raiz = new File("prueba");
                    File respaldo = new File("respaldo");

                    archivos.recorrido(raiz, respaldo);
                } catch (IOException ex) {
                    Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay = 10000L;
        long period = 5000L;
        executor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(16000);
        executor.shutdown();

    }
    public static void verificar() throws InterruptedException{
       Archivos c = new Archivos();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                try {
                    File raiz = new File("prueba");
                    File respaldo = new File("respaldo");
                    c.verificar(raiz, respaldo);
                    c.verificarRespaldo(raiz, respaldo);
                } catch (IOException ex) {
                    Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay = 16000L;
        long period =60000L;
        executor.scheduleAtFixedRate(timer, delay, period, TimeUnit.MILLISECONDS);
        Thread.sleep(196000);
        executor.shutdown();
        
    }

}
