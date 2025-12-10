package Ejercicio5;

public class Main {

    public static void main(String[] args) {

        Object[] palillos = new Object[5];
        for (int i = 0; i < 5; i++) {
            palillos[i] = new Object();
        }

        for (int i = 0; i < 5; i++) {
            Object palilloIzq = palillos[i];
            Object palilloDer = palillos[(i + 1) % 5]; // Misma forma circular

            Filosofo f = new Filosofo(i, palilloIzq, palilloDer);
            new Thread(f).start();
        }

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Se acabó la fiesta.");
        System.exit(0);
    }
}