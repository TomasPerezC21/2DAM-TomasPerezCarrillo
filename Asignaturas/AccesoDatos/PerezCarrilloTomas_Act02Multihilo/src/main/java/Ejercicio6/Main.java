package Ejercicio6;

public class Main {

    public static void main(String[] args) {

        Puente puenteArchena = new Puente();

        String[] sentidos = {"NORTE", "SUR"};

        for (int i = 1; i <= 10; i++) {
            //Sentido al azar
            String sentidoAsignado = sentidos[(int)(Math.random() * 2)];

            Coche c = new Coche("Coche-" + i, sentidoAsignado, puenteArchena);
            new Thread(c).start();

            // Pequeña pausa entre llegadas para que no lleguen todos en el milisegundo 0
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}