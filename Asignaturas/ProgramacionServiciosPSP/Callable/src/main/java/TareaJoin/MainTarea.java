package TareaJoin;

public class MainTarea {

    public static void main(String[] args) {

        Tarea t1 = new Tarea("Descarga apuntes", 3);
        Tarea t2 = new Tarea("Comer", 5);
        Tarea t3 = new Tarea("Ir al baño", 2);

        Thread hilo1 = new Thread(t1);
        Thread hilo2 = new Thread(t2);
        Thread hilo3 = new Thread(t3);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            System.out.println("He terminado todas las tareas correctamente.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }



    }


}
