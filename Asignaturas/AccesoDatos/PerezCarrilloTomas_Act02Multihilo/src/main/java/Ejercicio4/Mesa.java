package Ejercicio4;

import java.util.concurrent.Semaphore;


public class Mesa {

    public static void main(String[] args) {

        Semaphore[] palillos = new Semaphore[5];

        for (int i = 0; i < 5; i++) {
            palillos[i] = new Semaphore(1);
        }

        for (int i = 0; i < 5; i++) {
            Semaphore palilloIzq = palillos[i];

            //palillo derecho del ultimo = palillo izquierdo 1er filosofo
            Semaphore palilloDer = palillos[(i + 1) % 5];

            Filosofo f = new Filosofo(i, palilloIzq, palilloDer);
            new Thread(f).start();
        }

        //Para que no dure infinitamente el programa
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Se acabó la cena. Cerrando programa.");
        System.exit(0);
    }

}
