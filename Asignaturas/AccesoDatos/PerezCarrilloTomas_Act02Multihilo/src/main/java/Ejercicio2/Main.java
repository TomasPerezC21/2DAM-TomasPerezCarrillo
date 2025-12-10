package Ejercicio2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ScheduledExecutorService planificador = Executors.newSingleThreadScheduledExecutor();

        Relojes miReloj = new Relojes();

        System.out.println("Iniciando aplicación... (El reloj comenzará en 3 segundos)");

        planificador.scheduleAtFixedRate(miReloj, 3, 5, TimeUnit.SECONDS);

        //Lambda para apagar el planificador a los 22 segundos
        planificador.schedule(() -> {
            System.out.println("Tiempo cumplido (22s). Deteniendo el reloj.");
            planificador.shutdown();
        }, 22, TimeUnit.SECONDS);
    }
}