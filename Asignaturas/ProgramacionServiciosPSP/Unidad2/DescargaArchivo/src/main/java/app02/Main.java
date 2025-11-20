package app02;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
//        Timer timer  = new Timer();
//        Semaforo semaforo = new Semaforo();
//        timer.schedule(semaforo,0,1000);

        ScheduledExecutorService ejecutor = Executors.newSingleThreadScheduledExecutor();
        Semaforo s = new Semaforo();
        ejecutor.scheduleAtFixedRate(s, 0, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ejecutor.shutdown();

    }



}
