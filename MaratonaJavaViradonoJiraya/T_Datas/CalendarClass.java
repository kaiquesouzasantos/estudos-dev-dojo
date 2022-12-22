package T_Datas;

import java.util.Calendar;

public class CalendarClass {
    public static void main(String[] args) {
        // Calendar -> classe abstrata com implementações de acordo com os tipos de calendarios(gregoriano, japones e  budista)

        // getInstance() -> retorna o instante atual de acordo com a localidade
        Calendar calendar = Calendar.getInstance();

        // getTime() -> formata a data(Date.getTime() -> milesegundos) atual completa
        System.out.println(calendar.getTime());

        // getFirstDayOfWeek() -> retorna o primeiro dia da semana(domingo) de acordo com o pais
        System.out.println(calendar.getFirstDayOfWeek());
        /*
            Calendar.<constante_temporal> -> a constante pode ser comparada atraves de seu inteiro respectivo

            - DIAS DA SEMANA:
                SUNDAY = 1, MONDAY = 2, TUESDAY = 3, WEDNESDAY = 4, THURSDAY = 5, FRIDAY = 6, SATURDAY = 7

            - MESES:
                JANUARY = 0, FEBRUARY = 1, MARCH = 2, APRIL = 3, MAY = 4, JUNE = 5, JULY = 6,
                AUGUST = 7, SEPTEMBER = 8, OCTOBER = 9, NOVEMBER = 10, DECEMBER = 11

            - OUTRAS:
                DAY_OF_WEEK -> dia da semana
                DAY_OF_MONTH -> dia do mes
                DAY_OF_WEEK_IN_MONTH ->
                DAY_OF_YEAR -> dia do ano
                HOUR_OF_DAY -> hora do dia

         */
        System.out.println(calendar.getFirstDayOfWeek() == Calendar.DAY_OF_WEEK);

        // get() -> extrai um inteiro de determinada data
        // Nos casos abaixo, com base na data, retorna o valor respectivo constante
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        // add(<atributo_cost>, <valor_int>) -> adiciona determinado inteiro na data, de acordo com sua constante
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        // getCalendarType() -> retorna o tipo de calendario da sua localidade
        System.out.println(calendar.getCalendarType());
    }
}
