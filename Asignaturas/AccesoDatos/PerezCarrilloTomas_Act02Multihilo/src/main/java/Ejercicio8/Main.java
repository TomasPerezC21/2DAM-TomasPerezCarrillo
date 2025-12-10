package Ejercicio8;

public class Main {

    public static void main(String[] args) {

        // estancia con 3 puntos de carga
        Estacion estacion = new Estacion(3);

        System.out.println("ESTACIÓN DE CARGA (3 PUNTOS)");

        // Lanzamos 5 coches
        for (int i = 1; i <= 5; i++) {
            Coche c = new Coche("Coche-" + i, estacion);
            new Thread(c).start();

            // Pequeña pausa para que lleguen escalonados y se aprecie el orden
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}