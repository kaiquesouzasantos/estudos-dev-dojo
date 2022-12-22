package ZA_Generics.Teste;
import java.util.ArrayList;
import java.util.List;

public class TesteWildcard02 {
    public static void main(String[] args) {
        List<Cachorro> cachorros = List.of(new Cachorro(), new Cachorro());
        List<Gato> gatos = List.of(new Gato(), new Gato());
        List<Animal> animal = new ArrayList<>();

        // Generics Simples
        System.out.println("-----------------");
        printConsulta_01(cachorros);
        System.out.println("-----------------");
        printConsulta_02(gatos);
        System.out.println("-----------------");

        // Generics com Wildcard -> recebe somente classes que extendem de uma mesma superclass
        // nao permite que elementos sejam adiconados a lista
        printWildcard(cachorros);
        System.out.println("-----------------");
        printWildcard(gatos);
        System.out.println("-----------------");
        printWildcard(animal);
    }

    // List<Animal> -> náo é valido no tocante a listas, List<C> = List<C>
    private static void printConsulta_01(List<Cachorro> c){
        for(Cachorro cachorro:c){
            cachorro.consulta();
        }
    }

    private static void printConsulta_02(List<Gato> g){
        for(Gato gato:g){
            gato.consulta();
        }
    }

    private static void printWildcard(List<? extends Animal> animals){
        // List<? extends [superclass]> referenciador -> recebe super e subclass
        for(Animal animal:animals){
            animal.consulta();
        }
    }
}