package archivos2;

import archivos.Archivos;
import java.io.File;
import java.io.IOException;
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
                    File raiz = new File("Origen");
                    File respaldo = new File("Destino");

                    archivos.recorrido(raiz, respaldo);
                } catch (IOException ex) {
                    Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay = 1L;
        long period = 3L;
        executor.scheduleAtFixedRate(task, delay, period, TimeUnit.SECONDS);
        Thread.sleep(7000);
        executor.shutdown();

    }
    public static void verificar() throws InterruptedException{
       Archivos c = new Archivos();
        TimerTask timer = new TimerTask() {
            @Override
            public void run() {
                try {
                    File raiz = new File("Origen");
                    File respaldo = new File("Destino");
                    c.verificar(raiz, respaldo);
                    c.verificarRespaldo(raiz, respaldo);
                } catch (IOException ex) {
                    Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        long delay = 1L;
        long period =3L;
        executor.scheduleAtFixedRate(timer, delay, period, TimeUnit.SECONDS);
        Thread.sleep(7000);
        executor.shutdown();
        
    }

}
