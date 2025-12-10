package Ejercicio6;

public class Puente {

    private int cochesEnPuente = 0;
    private String sentidoActual = null; // "NORTE", "SUR" o null (vacío)

    public synchronized void entrar(String sentido, String nombreCoche) throws InterruptedException {

        while (cochesEnPuente > 0 && !sentido.equals(sentidoActual)) {
            System.out.println(nombreCoche + " (" + sentido + ") debe ESPERAR. Puente ocupado por " + sentidoActual);
            wait();
        }

        sentidoActual = sentido;
        cochesEnPuente++;

        System.out.println(nombreCoche + " entra al puente dirección " + sentido +
                ". (Coches dentro: " + cochesEnPuente + ")");
    }

    public synchronized void salir(String nombreCoche) {
        cochesEnPuente--;
        System.out.println(nombreCoche + " sale del puente. (Quedan: " + cochesEnPuente + ")");

        if (cochesEnPuente == 0) {
            sentidoActual = null;
            System.out.println("PUENTE VACÍO. Pueden entrar coches de cualquier lado.");
        }

        notifyAll();
    }

}
