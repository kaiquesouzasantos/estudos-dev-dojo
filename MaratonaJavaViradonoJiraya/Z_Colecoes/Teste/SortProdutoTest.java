package Z_Colecoes.Teste;
import Z_Colecoes.Entidades.Produto;
import java.util.*;

class ProdutoNomeComparator implements Comparator<Produto> {

    @Override
    public int compare(Produto o1, Produto o2) {
        return o1.getNome().compareTo(o2.getNome());
    }
}

public class SortProdutoTest {
    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>(List.of(
                new Produto("123", "Laptop Lenovo", 2000.0),
                new Produto("321", "Picanha", 26.4),
                new Produto("879", "Teclado Razer", 1000.0),
                new Produto("012", "Samsung galaxy S7 64Gb", 3250.5)
        ));

        Produto[] produtosArray =  new Produto[produtos.size()];
        produtos.toArray(produtosArray);

        Collections.sort(produtos, new ProdutoNomeComparator());
        Arrays.sort(produtosArray, new ProdutoNomeComparator());

        for (Produto produto : produtosArray) {
            System.out.println(produto);
        }
    }
}
