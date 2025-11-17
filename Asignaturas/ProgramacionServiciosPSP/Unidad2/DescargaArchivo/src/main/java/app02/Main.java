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

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Semaforo s = new Semaforo();
        scheduler.scheduleAtFixedRate(s, 0, 1, TimeUnit.SECONDS);


    }

}
