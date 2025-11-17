public class Main {


    public static void main(String[] args) {

        DescargaArchivo archivo1 = new DescargaArchivo("Fichero de prueba", 5);
        DescargaArchivo archivo2 = new DescargaArchivo("GTA 6", 10);
        DescargaArchivo archivo3 = new DescargaArchivo("Música", 7);

        //Metodo con la interfaz

            Thread thread1 = new Thread(archivo1);
            thread1.start();

            Thread thread2 = new Thread(archivo2);
            thread2.start();


        Thread thread3 = new Thread(archivo3);
        thread3.start();

            //Forma heredando de thread e implementando run
        /*
            archivo2.start();
            archivo3.start();
        */

    }


}
