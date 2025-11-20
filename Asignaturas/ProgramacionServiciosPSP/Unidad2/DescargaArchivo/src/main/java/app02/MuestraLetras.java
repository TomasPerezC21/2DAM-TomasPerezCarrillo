package app02;

public class MuestraLetras implements Runnable {

    @Override
    public void run() {
        char letra = 65;
        for (int i = 0; i < 25; i++) {
            System.out.println(letra);
            letra++;
        }

    }
}
