package Ejercicio1;

public class Main {

    private static final String nombreArchivo = "src/main/java/Ejercicio1/ejercicio1.txt";

    public static void main(String[] args) {


        EscritorNumeros escritor1 = new EscritorNumeros(nombreArchivo, 11, 20);
        Thread e1 = new Thread(escritor1);

        EscritorNumeros escritor2 = new EscritorNumeros(nombreArchivo, 21, 30);
        Thread e2 = new Thread(escritor2);

        EscritorNumeros escritor3 = new EscritorNumeros(nombreArchivo, 1, 10);
        Thread e3 = new Thread(escritor3);

        try {
            e3.start();
            e3.join();

            e1.start();
            e1.join();

            e2.start();
            e2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
