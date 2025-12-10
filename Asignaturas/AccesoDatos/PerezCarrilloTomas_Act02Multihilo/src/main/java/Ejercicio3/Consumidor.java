package Ejercicio3;

public class Consumidor implements Runnable{

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {

        try{
            for (int i = 0; i < 15; i++) {
                buffer.consumirLetra();
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
