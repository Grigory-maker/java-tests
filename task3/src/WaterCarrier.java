import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WaterCarrier {
 //   String name;
    Date date;
    float v;
    boolean in = true;

//    boolean success;

    WaterCarrier(String str)   { // конструктор

        str = str.replaceAll("[^A-Za-zА-Яа-я0-9\\s\\.\\-\\:]", ""); // убираем кирилицу
        str = str.replace("T", ""); //  убираем  англ. T

        if (str.contains("scoop")){ // зачерпывает
            this.in = false;
        }
        Scanner scanner = new Scanner(str);

        while (scanner.hasNext()) {
            String temp = (scanner.next());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS'Z'");
            try {

                this.date = simpleDateFormat.parse(temp);
                if (temp.equals("")) System.out.println("непонятные пустые строки  " + this.date);
            }catch (Exception e){
                System.out.println("что-то с датой не так  " + this.date);
            }
//            System.out.println("дата водоноса  " + this.date);
            scanner.next(); // пропускаем имя водоноса
            scanner.next(); // пропускаем -
            scanner.next(); // пропускаем wanna

            if (scanner.next().equals("scoop")){ // зачерпывает
                this.in = false;
            }
            else {
                scanner.next(); // пропускаем  top up
            }
            String vStr = scanner.next();
            vStr = vStr.substring(0,vStr.length()-1);
            try {
                this.v = Float.parseFloat(vStr);

            }
            catch (Exception e)
            {
                System.out.println("Ошибка в количестве литров " + vStr);
            }

        }
        scanner.close();


    }

}
