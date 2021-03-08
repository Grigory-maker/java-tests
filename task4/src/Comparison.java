public class Comparison {

    static String star(String one, String two)
    {
        for (int i=0;i<two.length();i++)
        {}
        return one;
    }
    static String okko( String one, String two)
    {
        if (one.equals(two)) return "OK"; // обе строки одинаковые
        two = two.replaceAll("\\*+","*"); // удаление парных звезд
        if (one.length()>two.length() & !two.contains("*")) // Проверка на длинну втрой строки, чтобы не вылетало при сравнениии симолов
            return "KO";

        int begin; // начало  сравниваемого куска строки (первой строки)
        int end1 = one.length()-1; //  конец сравниваемого куска первой строки
        int end2; //  конец сравниваемого куска второй строки

        for (begin = 0; begin < one.length(); begin++) {
            if (one.charAt(begin) != two.charAt(begin)) // поиск первой звезды с начала
            {
                if (two.charAt(begin) == '*')   // первая звезда
                {
                    for (end2 = two.length() - 1; two.charAt(end2) != '*'; end2--, end1--) //поиск последней звезды c конца
                    {
                        if (two.charAt(end2) != one.charAt(end1)) return "KO";
                    }
                    if (begin == end2) return "OK"; // если начало и конец одинаковы, и внутри одна звезда
                    else
                    {
                        one = one.substring(begin , end1+1);
                        two = two.substring(begin+1 , end2+1); // Первую звезду тоже обрежем
                        int cut =0; // текущий проверяемый символ из второй строки
                        while(one.length() > 0)
                        {
                            if (one.charAt(0) == two.charAt(cut))
                            {
                                cut++;
                            }
                            one = one.substring(1); // отрезаем проверенный символ из первой строки
                            if (two.charAt(cut) == '*')
                            {
                                if (cut == two.length()-1) return "OK"; // если все символы из 2й строки нашлись и звезда последняя
                                cut++;
                            }

                        }

                    }
                }
            }

        }
        if (begin>end1) // первая строка полностью содержится в начале второй
        {
            if ((two.charAt(begin) == '*')&(two.length()-1==begin)) return "OK"; // aa aa*
        }
            return "KO";

    }
    public static void main(String[] args){

        System.out.println(okko(args[0], args[1]));
    }
}
