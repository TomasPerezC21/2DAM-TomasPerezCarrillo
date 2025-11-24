package app03.ejemploLinkedListedSemaforo;

import app02.Semaforo;

import java.util.LinkedList;
import java.util.Random;
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
        Random r = new Random();

        for (int i = 0; i < 20; i++) {
            synchronized (lista) {
                int num = r.nextInt(1,100);
                lista.add(num);
                System.out.println("El productor " + nombre + " ha producido el número: " + num);
            }
            semaforo.release();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
