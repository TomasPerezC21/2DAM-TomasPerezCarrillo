public class DescargaArchivo implements Runnable { //tambien extends Thread override run llamando a metodo descarga

    private String nombreFichero;
    private int tiempo;
    private int cantidadDescarga = 0;


    public DescargaArchivo(String nombreFichero, int tiempo) {
        this.nombreFichero = nombreFichero;
        this.tiempo = tiempo;
    }

    public String getNombreFichero() {
        return nombreFichero;
    }



    public int getTiempo() {
        return tiempo;
    }

   public int getCantidadDescarga() {
        return cantidadDescarga;
   }

    public void descargarArchivo() throws InterruptedException {

        System.out.println("Iniciando la descarga del archivo " + getNombreFichero());
        cantidadDescarga = cantidadDescarga + 1;
        Thread.sleep(1000L);
        for (int i = 0; i < getTiempo(); i++) {
            System.out.println("Duración de descarga de " + getNombreFichero()+ ": " +  (i+1) +" segundos.");
            Thread.sleep(1000L);
        }
        System.out.println("Descarga de "+ getNombreFichero() +" finalizada");
        System.out.println("NÚMERO DE DESCARGAS DE " + getNombreFichero().toUpperCase() +": "+ getCantidadDescarga());
        System.out.println("=====================================");

    }

    @Override
    public void run() {
        try {
            descargarArchivo();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
