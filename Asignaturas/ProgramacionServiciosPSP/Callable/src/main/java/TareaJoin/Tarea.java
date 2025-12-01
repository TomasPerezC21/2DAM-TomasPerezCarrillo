package TareaJoin;


public class Tarea implements Runnable{

    private String nombreTarea;
    private int duracion;

    public Tarea(String nombreTarea, int duracion) {
        this.nombreTarea = nombreTarea;
        this.duracion = duracion;
    }

    @Override
    public void run() {
        System.out.println("Iniciando: " +nombreTarea);
        try {
            Thread.sleep(duracion*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Tarea " +  nombreTarea + " finalizada.");
    }
}
