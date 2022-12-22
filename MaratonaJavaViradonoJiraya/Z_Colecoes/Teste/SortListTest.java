package Z_Colecoes.Teste;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortListTest {
    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>(List.of(
                "William", "Suane", "DevDojo", "Brenon", "Bruno"
        ));

        Collections.sort(nomes);
        nomes.forEach(System.out::println);

        List<Double> numeros = new ArrayList<>(List.of(
                1.5, 1.3, 1.9, 2d
        ));

        Collections.sort(numeros);
        numeros.forEach(System.out::println);
    }
}
