package app02;

import java.util.TimerTask;

public class Semaforo implements Runnable {

    String[] colores = {"Verde", "Ámbar", "Rojo"};
    int color = 0;


    @Override
    public void run() {
        System.out.println("Semáforo en "+ colores[color]);
        color = (color + 1) % colores.length;
    }
}
