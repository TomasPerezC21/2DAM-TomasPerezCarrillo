package Ejercicio7;

public class Coche implements Runnable {

    private String nombre;
    private String sentido; // "NORTE" o "SUR"
    private Puente puente;

    public Coche(String nombre, String sentido, Puente puente) {
        this.nombre = nombre;
        this.sentido = sentido;
        this.puente = puente;
    }

    @Override
    public void run() {
        try {

            System.out.println(nombre + " llega desde el " + sentido + ".");

            puente.entrar(sentido, nombre);

            Thread.sleep((long) (Math.random() * 1000) + 500);

            puente.salir(nombre);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}