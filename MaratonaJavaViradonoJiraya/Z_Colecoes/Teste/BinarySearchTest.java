package Z_Colecoes.Teste;
import Z_Colecoes.Entidades.Produto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearchTest {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(List.of(
                2, 0, 4, 3
        ));
        Collections.sort(numeros);
        System.out.println(Collections.binarySearch(numeros, 1));

        List<Produto> produtos = new ArrayList<>(List.of(
                new Produto("123", "Laptop Lenovo", 2000.0),
                new Produto("321", "Picanha", 26.4),
                new Produto("879", "Teclado Razer", 1000.0),
                new Produto("012", "Samsung galaxy S7 64Gb", 3250.5)
        ));

        Collections.sort(produtos, new ProdutoNomeComparator());
        Produto produto5 = new Produto("000", "Antena", 50);
        produtos.forEach(System.out::println);
        System.out.println(Collections.binarySearch(produtos, produto5, new ProdutoNomeComparator()));

        Integer[] arrayInteger = new Integer[]{2, 0, 4, 3};
        Arrays.sort(arrayInteger);
        System.out.println(Arrays.binarySearch(arrayInteger,1));
    }
}
