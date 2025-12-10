package Ejercicio5;

public class Filosofo implements Runnable {

    private int id;
    private Object palilloIzquierdo;
    private Object palilloDerecho;

    public Filosofo(int id, Object palilloIzquierdo, Object palilloDerecho) {
        this.id = id;
        this.palilloIzquierdo = palilloIzquierdo;
        this.palilloDerecho = palilloDerecho;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Filósofo " + id + " pensando.");
                Thread.sleep((long) (Math.random() * 1000));
                if (id % 2 == 0) {
                    synchronized (palilloIzquierdo) { // Coge el primero
                        synchronized (palilloDerecho) { // Coge el segundo
                            comer();
                        }
                    }
                } else {
                    synchronized (palilloDerecho) {
                        synchronized (palilloIzquierdo) {
                            comer();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " se dispone a comer.");
        Thread.sleep((long) (Math.random() * 1000));
        System.out.println("Filósofo " + id + " terminó de comer y soltó los palillos.");
    }
}
