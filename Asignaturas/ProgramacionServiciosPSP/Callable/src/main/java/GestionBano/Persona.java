package GestionBano;

public class Persona implements Runnable{

    private String nombre;
    private Bano bano;

    public Persona(String nombre, Bano bano) {
        this.nombre = nombre;
        this.bano = bano;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            bano.usarBano(nombre);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
