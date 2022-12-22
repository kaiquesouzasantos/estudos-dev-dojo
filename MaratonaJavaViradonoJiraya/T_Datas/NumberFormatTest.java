package T_Datas;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NumberFormatTest {
    public static void main(String[] args) {
        float valor = 212.4567f;
        Locale locIT = new Locale("it");

        List<NumberFormat> lista = List.of(
                NumberFormat.getInstance(),
                NumberFormat.getInstance(locIT),
                // getCurrencyInstance() -> formata de acordo com a localidade
                NumberFormat.getCurrencyInstance(),
                NumberFormat.getCurrencyInstance(locIT)
        );

        lista.forEach(n -> System.out.println(n.format(valor)));

        System.out.println("------------------------------------");

        NumberFormat numberFormat = NumberFormat.getInstance();
        // getMaximumFractionDigits() -> quantidade de casas decimais

        System.out.println(numberFormat.getMaximumFractionDigits());
        // setMaximumFractionDigits(<int>) -> limita a quantidade de decimais
        numberFormat.setMaximumFractionDigits(1);

        // aplica a formatacao sobre o valor
        System.out.println(numberFormat.format(valor));
    }
}
