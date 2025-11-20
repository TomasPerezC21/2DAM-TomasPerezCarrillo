package app03.ejemploLinkedList;

import java.util.LinkedList;

public class Consumidor implements Runnable {

    private String nombre;
    private LinkedList<Integer> lista;

    public Consumidor(String nombre, LinkedList<Integer> lista) {
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
