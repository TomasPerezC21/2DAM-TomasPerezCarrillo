package Ejercicio8;

public class Coche implements Runnable {

    private String matricula;
    private Estacion estacion;

    public Coche(String matricula, Estacion estacion) {
        this.matricula = matricula;
        this.estacion = estacion;
    }

    @Override
    public void run() {
        // El coche simplemente intenta cargar
        estacion.cargarBateria(matricula);
    }
}
