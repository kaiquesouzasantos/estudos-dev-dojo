package ZA_Generics.Entidades;
import java.util.List;

public class ServicoRentavel<T> {
    // [class]<T> -> qualquer tipo de objeto, generazila o type
    private List<T> objetosDisponiveis;

    public ServicoRentavel(List<T> objetosDisponiveis){
        this.objetosDisponiveis = objetosDisponiveis;
    }

    public T buscarObjetoDisponivel(){
        System.out.println("Buscando carro disponival....");
        T t = objetosDisponiveis.remove(0);
        System.out.println("Alugando objeto:"+t);
        System.out.println("Objetos disponiveis: "+objetosDisponiveis);
        return t;
    }

    public void retornaObjetoAlugado(T t){
        System.out.println("Devolvendo objeto"+t);
        objetosDisponiveis.add(t);
        System.out.println("Objetos disponiveis para aluguel: "+objetosDisponiveis);
    }
}
