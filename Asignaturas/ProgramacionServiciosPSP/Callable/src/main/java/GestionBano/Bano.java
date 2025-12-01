package GestionBano;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Bano {

    Semaphore semaforo = new Semaphore(3);

    ArrayList<String> personas = new ArrayList<>();

    public void usarBano(String nombre) throws InterruptedException {

        System.out.println("La persona " +  nombre + " quiere usar el baño.");
        semaforo.acquire();
        synchronized (personas) {
            personas.add(nombre);
            System.out.println("La persona " + nombre + " ya está utilizando el baño.");
            System.out.println("Personas utilizando el baño: " + personas);
        }

        Thread.sleep(2000);

        synchronized (personas) {
            System.out.println("La persona " + nombre + " ha dejado de usar el baño");
            personas.remove(nombre);
            System.out.println("Personas utilizando el baño: " + personas);
        }

        semaforo.release();

    }


}
