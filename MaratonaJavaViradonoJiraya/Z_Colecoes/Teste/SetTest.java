package Z_Colecoes.Teste;
import Z_Colecoes.Entidades.Produto;
import java.util.LinkedHashSet;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<Produto> produtoSet = new LinkedHashSet<>(Set.of(
                new Produto("123", "Laptop Lenovo", 2000.0, 10),
                new Produto("321", "Picanha", 10d, 10),
                new Produto("879", "Teclado Razer", 1000.0, 0),
                new Produto("012", "Samsung galaxy S7 64Gb", 3250.5, 0)
        ));

        produtoSet.forEach(System.out::println);
    }
}
