package T_Datas;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateFormatClass {
    public static void main(String[] args) {
        // DateFormat -> classe abstrata que formata datas

        Calendar calendar = Calendar.getInstance();
        Date data = new Date();

        List<Locale> localidades = List.of(
                // parametros -> (<idioma>, <pais>)
                new Locale("pt", "BR"),
                new Locale("pt", "PT"),
                new Locale("it", "IT"),
                new Locale("it", "CH"),
                new Locale("hi", "IN"),
                new Locale("ja", "JA")
        );

        List<DateFormat> dateFormat = List.of(
                // getInstance() -> pode receber um padraoConstante e localidade

                // 15/07/2022 23:53
                DateFormat.getInstance(),
                // 15 de jul de 2022
                DateFormat.getDateInstance(),
                // 15/07/2022
                DateFormat.getDateInstance(DateFormat.SHORT),
                // 15 de jul de 2022
                DateFormat.getDateInstance(DateFormat.MEDIUM),
                // 15 de julho de 2022
                DateFormat.getDateInstance(DateFormat.LONG),
                // sexta-feira, 15 de julho de 2022
                DateFormat.getDateInstance(DateFormat.FULL)
        );

        // format(<instante>) -> formata a data de acordo com a instrução acima, recebe Date ou Calendar
        dateFormat.forEach(date -> System.out.println(date.format(data.getTime())));
        dateFormat.forEach(date -> System.out.println(date.format(calendar.getTime())));

        localidades.forEach(locale -> System.out.println(DateFormat.getDateInstance(DateFormat.FULL, locale).format(data.getTime())));

        // getDisplayName() -> retorna o idioma sem abreviação
        System.out.println(new Locale("ja").getDisplayName());
        // getDisplayCountry() -> retorna o pais
        System.out.println(new Locale("pt", "BR").getDisplayCountry());
    }
}
