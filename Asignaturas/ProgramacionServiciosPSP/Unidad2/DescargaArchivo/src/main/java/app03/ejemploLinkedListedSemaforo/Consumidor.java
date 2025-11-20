package app03.ejemploLinkedListedSemaforo;

import java.util.LinkedList;
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
        for (int i = 0; i < lista.size(); i++) {
            try {
                semaforo.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lista) {
                int numero = lista.removeFirst();
                System.out.println("El consumidor " + nombre + " ha consumido el número: "+ i);
            }
            semaforo.release();
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }
}
