package ZA_Generics.Teste;
import java.util.ArrayList;
import java.util.List;

public class TesteGenerics {
    public static void main(String[] args) {
        antesJava8();
        System.out.println("-----------------");
        java8();
    }

    public static void antesJava8(){
        List lista = new ArrayList<>(List.of(
                "Stranger Things", "Top Gun: Maverick", 5455
        )); // nao define o tipo de entrada

        // como não se sabe o que a lista vai conter, cada item é verificado -> antes do Java8
        for(Object o:lista){
            if(o instanceof String){System.out.println(o);}
            if(o instanceof Integer){System.out.println(o);}
        }
    }

    public static void java8(){
        List<String> lista = new ArrayList<>(List.of(
                "Stranger Things", "Top Gun: Maverick"
        )); // Type erasure -> List<Generics>, aceita somente do tipo definido

        lista.forEach(System.out::println);
    }
}
