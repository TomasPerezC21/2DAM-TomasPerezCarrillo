package Ejercicio7;

public class Puente {

    private int cochesEnPuente = 0;
    private String sentidoActual = null;

    //Contadores de coches esperando
    private int esperandoNorte = 0;
    private int esperandoSur = 0;

    public synchronized void entrar(String sentido, String nombreCoche) throws InterruptedException {

        if (sentido.equals("NORTE")) {
            esperandoNorte++;
        } else {
            esperandoSur++;
        }

        while (debeEsperar(sentido)) {
            System.out.println(nombreCoche + " (" + sentido + ") ESPERA. (Norte: " + esperandoNorte + ", Sur: " + esperandoSur + ")");
            wait();
        }

        if (sentido.equals("NORTE")) {
            esperandoNorte--;
        } else {
            esperandoSur--;
        }

        cochesEnPuente++;
        sentidoActual = sentido;

        System.out.println(nombreCoche + " entra dirección " + sentido +
                ". (Dentro: " + cochesEnPuente + ")");
    }

    public synchronized void salir(String nombreCoche) {
        cochesEnPuente--;
        System.out.println(nombreCoche + " sale. (Quedan: " + cochesEnPuente + ")");

        if (cochesEnPuente == 0) {
            sentidoActual = null;
            System.out.println("PUENTE VACÍO. Cambio de turno posible.");
        }

        notifyAll();
    }

   //si devuelve false puede pasar si es true debe esperar
    private boolean debeEsperar(String miSentido) {
        if (cochesEnPuente == 0) return false;

        if (!miSentido.equals(sentidoActual)) return true;

        if (miSentido.equals("NORTE") && esperandoSur >= 2) return true;

        if (miSentido.equals("SUR") && esperandoNorte >= 2) return true;

        return false;
    }
}