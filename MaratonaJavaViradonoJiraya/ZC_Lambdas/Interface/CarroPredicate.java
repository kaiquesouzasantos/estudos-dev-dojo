package ZC_Lambdas.Interface;
import ZC_Lambdas.Entidades.*;

@FunctionalInterface
public interface CarroPredicate {
    boolean test(Carro carro);
}
