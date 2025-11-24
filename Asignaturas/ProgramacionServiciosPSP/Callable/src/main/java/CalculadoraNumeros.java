import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

public class CalculadoraNumeros implements Callable<ArrayList<Integer>> {


    @Override
    public ArrayList<Integer> call() throws Exception {

        ArrayList<Integer> numeros = new ArrayList<>();
        Random aleatorio = new Random();


        for (int i = 0; i < 5; i++) {
            int num = aleatorio.nextInt(1,11);
            numeros.add(num);
            Thread.sleep(1500);

        }
        return numeros;
    }
}
