package ZC_Lambdas.Teste;
import ZC_Lambdas.Entidades.Carro;
import ZC_Lambdas.Interface.CarroPredicate;
import java.util.function.Predicate;

public class LambdaTest {
    public static void main(String[] args) {
        CarroPredicate carroPredicate = new CarroPredicate() {
            @Override
            public boolean test(Carro carro) {
                return carro.getCor().equals("verde");
            }
        };

        Predicate<Carro> carroPredicate2 = (Carro carro) -> carro.getCor().equals("verde");

        System.out.println(carroPredicate.test(new Carro("verde", 2012)));
        System.out.println(carroPredicate2.test(new Carro("verde", 2012)));

        Runnable runnable = () -> System.out.println("Dentro do run");
        new Thread(runnable).start();
    }
}
