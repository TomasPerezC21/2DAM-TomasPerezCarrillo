package Ejercicio3;

import java.util.LinkedList;

public class Buffer {

    private LinkedList<Character> lista;

    private int capacidad;

    public Buffer(int capacidad1) {
        lista = new LinkedList<>();
        this.capacidad = capacidad1;
    }

    public synchronized void producirLetra(char letra) throws InterruptedException {

        //Si la lista del buffer está llena:
        while (lista.size() == capacidad) {
            System.out.println("Bufer lleno. Productor espera");
            wait();
        }

        lista.add(letra);
        System.out.println("Letra agregada al bufer: " + letra);
        notifyAll();
    }

    public synchronized void consumirLetra() throws InterruptedException {

        while (lista.isEmpty()) {
            System.out.println("Búfer sin letras para consumir. Consumidor espera.");
            wait();
        }

        char letra = lista.removeFirst();
        System.out.println("Letra consumida " + letra);
        notifyAll();
    }

}
