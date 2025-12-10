package Ejercicio8;

import java.util.concurrent.Semaphore;

public class Estacion {

    private Semaphore puntosCarga;
    private int capacidadTotal;

    public Estacion(int numeroPuntos) {
        this.capacidadTotal = numeroPuntos;
        this.puntosCarga = new Semaphore(numeroPuntos, true);
    }

    public void cargarBateria(String matricula) {
        try {

            System.out.println(matricula + " llega y espera turno...");

            puntosCarga.acquire(); // Resta 1 permiso. Si es 0, espera.

            int libres = puntosCarga.availablePermits();
            System.out.println(matricula + " ENTRA a cargar. (Sitios libres: " + libres + ")");

            //tiempo de carga simulado
            long tiempoCarga = (long) (Math.random() * 2000) + 1000;
            Thread.sleep(tiempoCarga);

            System.out.println(matricula + " termina de cargar (Tiempo: " + tiempoCarga + "ms). Sale de la estación.");

            puntosCarga.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
