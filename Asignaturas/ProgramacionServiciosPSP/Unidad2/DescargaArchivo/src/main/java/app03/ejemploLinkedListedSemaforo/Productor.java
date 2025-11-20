package app03.ejemploLinkedListedSemaforo;

import app02.Semaforo;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Productor implements Runnable{

    private String nombre;
    private LinkedList<Integer> lista;
    private Semaphore semaforo;

    public Productor(String nombre,LinkedList<Integer> lista, Semaphore semaforo) {
        this.nombre = nombre;
        this.lista = lista;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {

            synchronized (lista) {
                lista.add(i);
                System.out.println("El productor " + nombre + " ha producido el número: " + i);
            }
            semaforo.release();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
