package app03.ejemploLinkedListedSemaforo;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Consumidor implements Runnable {

    private String nombre;
    private LinkedList<Integer> lista;
    Semaphore semaforo;

    public Consumidor(String nombre, LinkedList<Integer> lista, Semaphore semaforo) {
        this.nombre = nombre;
        this.lista = lista;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        Random r  = new Random();

        for (int i = 0; i < 5; i++) {
            try {
                semaforo.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (lista) {
                int numero = lista.removeFirst();
                System.out.println("El consumidor " + nombre + " ha consumido el número: "+ numero);
            }
            //semaforo.release();

            int tiempo = r.nextInt(300, 600);

            try{
                Thread.sleep(tiempo);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
