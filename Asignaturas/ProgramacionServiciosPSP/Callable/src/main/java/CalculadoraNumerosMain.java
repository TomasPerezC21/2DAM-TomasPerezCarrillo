import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculadoraNumerosMain {

    public static void main(String[] args) {
        
        CalculadoraNumeros calc = new CalculadoraNumeros();
        
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<ArrayList<Integer>> futuroResultado = executor.submit(calc);

        int suma = 0;

        for (int i = 0; i <100 ; i++) {
            suma+=i;
        }

        System.out.println("Suma: "+suma);

        //Ahora quiero mostrar los 5 aleatorios
        try {
            ArrayList<Integer> lista = futuroResultado.get();
            System.out.println("Los enteros que ha calculado el hilo son: ");
            for (int i : lista) {
                System.out.printf("%d ", i);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    
}
