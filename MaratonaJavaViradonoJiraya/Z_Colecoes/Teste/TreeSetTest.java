package Z_Colecoes.Teste;
import Z_Colecoes.Entidades.Produto;
import java.util.*;

public class TreeSetTest {
    public static void main(String[] args) {
        NavigableSet<Produto> produtoNavigableSet = new TreeSet<>(Set.of(
                new Produto("123", "Laptop Lenovo", 2000.0, 10),
                new Produto("321", "Picanha", 10d, 10),
                new Produto("879", "Teclado Razer", 1000.0, 0),
                new Produto("023", "Samsung galaxy S6 64Gb", 1d, 0),
                new Produto("012", "Samsung galaxy S7 64Gb", 3250.5, 0)
        ));

        for(Produto produto : produtoNavigableSet.descendingSet()){
            System.out.println(produto);
        }
    }
}
