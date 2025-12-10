package Ejercicio3;

public class Productor implements Runnable {

    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 15 ; i++) {
                char letra = (char) ('A' + i);
                buffer.producirLetra(letra);
                Thread.sleep(350);//pausa breve
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
