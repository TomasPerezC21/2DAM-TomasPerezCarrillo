package app03.ejemploLinkedList;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Productor implements Runnable{

    private String nombre;
    private LinkedList<Integer> lista;

    public Productor(String nombre,LinkedList<Integer> lista) {
        this.nombre = nombre;
        this.lista = lista;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            lista.add(i);

            System.out.println("El productor " + nombre + " ha producido el número: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
