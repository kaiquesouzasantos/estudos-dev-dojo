package ZA_Generics.Teste;
import ZA_Generics.Entidades.Barco;
import ZA_Generics.Entidades.Carro;
import java.util.List;

public class TesteMetodosGenerics {
    public static void main(String[] args) {
        criarArrayComObjeto(new Barco("Canoa"));

        List<Carro> carroList = criarArrayComObjeto02(new Carro("BMW"));
        System.out.println(carroList);
    }

    private static <T> void criarArrayComObjeto(T t){
        List<T> list = List.of(t); // a lista define o type do object quando recebe sua ultima incidencia
        System.out.println(list);
    }

    private static <T> List<T> criarArrayComObjeto02(T t){
        return List.of(t);
    }
}