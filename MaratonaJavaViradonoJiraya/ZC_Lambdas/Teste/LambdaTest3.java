package ZC_Lambdas.Teste;
import ZC_Lambdas.Entidades.Carro;
import ZC_Lambdas.Entidades.ComparadorCarro;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.*;
import static java.util.Arrays.asList;

public class LambdaTest3 {
    public static void main(String[] args) {
        forEach(asList("Kaique", "Etec", "facebook.com/<opa>"), System.out::println);

        List<Carro> carros = asList(new Carro("Vermelho", 1999), new Carro("Azul", 2005), new Carro("Preto", 1985));
        Collections.sort(carros, ComparadorCarro::comparePorCor);
        System.out.println(carros);


        Supplier<ComparadorCarro> comparadorCarroSupplier = ComparadorCarro::new;
        Collections.sort(carros, comparadorCarroSupplier.get()::comparePorAno);
        System.out.println(carros);

        List<String> nomes = asList("Kaique", "Joana", "Danone", "Calvo");
        nomes.sort(String::compareTo);
        System.out.println(nomes);

        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
        Function<String, Integer> stringToInteger2 = Integer::parseInt;

        BiPredicate<List<String>, String> contains = (lista, elemento) -> lista.contains(elemento);
        BiPredicate<List<String>, String> contains2 = List::contains;

        System.out.println(stringToInteger2.apply("10"));
        System.out.println(contains2.test(nomes,"Furiosa"));

        BiFunction<String, Integer, Carro> carroBiFunction = (s,i) -> new Carro(s,i);
        BiFunction<String, Integer, Carro> carroBiFunction2 = Carro::new;
        System.out.println(carroBiFunction.apply("rosa", 1994));
        System.out.println(carroBiFunction2.apply("rosa", 1994));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T e : list) {c.accept(e);}
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();

        for (T e : list) {
            result.add(f.apply(e));
        }
        return result;
    }
}
