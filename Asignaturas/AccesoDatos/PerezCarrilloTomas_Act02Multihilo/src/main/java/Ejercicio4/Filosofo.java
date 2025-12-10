package Ejercicio4;

import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable {

    private int id;
    private Semaphore palilloIzquierdo;
    private Semaphore palilloDerecho;

    public Filosofo(int id, Semaphore palilloIzquierdo, Semaphore palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
    }

    @Override
    public void run() {
        try {
            while (true) { // Bucle infinito: pensar -> comer -> pensar

                System.out.println("Filósofo " + id + " pensando...");
                Thread.sleep((long) (Math.random() * 2000)); // Tiempo aleatorio pensando

                if (id % 2 == 0) {
                    // Filósofos PARES: Izquierda primero, luego Derecha
                    palilloIzquierdo.acquire();
                    palilloDerecho.acquire();
                } else {
                    // Filósofos IMPARES: Derecha primero, luego Izquierda
                    palilloDerecho.acquire();
                    palilloIzquierdo.acquire();
                }

                System.out.println("Filósofo " + id + " se dispone a comer.");

                // Tiempo comiendo
                Thread.sleep((long) (Math.random() * 2000));

                palilloDerecho.release();
                palilloIzquierdo.release();

                System.out.println("Filósofo " + id + " terminó y soltó los palillos.");
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
