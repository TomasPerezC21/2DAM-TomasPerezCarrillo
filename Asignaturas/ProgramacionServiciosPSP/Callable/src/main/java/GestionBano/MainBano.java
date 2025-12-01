package GestionBano;

public class MainBano {
    public static void main(String[] args) {

        Bano bano = new  Bano();

        for (int i = 0; i <10 ; i++) {
            Persona p = new Persona("Persona " + (i+1), bano);
            Thread t = new Thread(p);
            t.start();
        }
    }

}
