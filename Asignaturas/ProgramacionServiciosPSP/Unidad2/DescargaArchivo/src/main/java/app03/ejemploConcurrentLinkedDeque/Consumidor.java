package app03.ejemploConcurrentLinkedDeque;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Consumidor implements Runnable {

    private String nombre;
    private ConcurrentLinkedDeque<Integer> lista;

    public Consumidor(String nombre, ConcurrentLinkedDeque<Integer> lista) {
        this.nombre = nombre;
        this.lista = lista;
    }

    @Override
    public void run() {
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.isEmpty()) {
                int numero = lista.removeFirst();
                System.out.println("El consumidor " + nombre + " ha consumido el número: "+ i);
            }

        }

    }
}
