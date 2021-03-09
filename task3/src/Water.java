import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Water {
    private static Date dateBegin;
    private static Date dateEnd;
    Water(String begin, String end){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            dateBegin = simpleDateFormat.parse(begin);
        }
        catch (Exception e){
            System.out.println("что-то с первой датой не так  " + begin);
        }
        try{
            dateEnd = simpleDateFormat.parse(end);
        }
        catch (Exception e){
            System.out.println("что-то со второй датой не так  " + end);
        }
        if (dateBegin.after(dateEnd))
        {
            System.out.println("неверный интервал " + begin +"   "+ end);
            System.exit(0);
        }
    }
    public boolean NeededDate(Date date){
        return (date.after(dateBegin) & date.before(dateEnd));
    }

    public static void main(String[] args) throws Exception{
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("Нет такого файла " + file);
            System.exit(0);
        }
        Water water= new Water(args[1],args[2]);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s;
        float vMax=0, vCurrent=0; // объемы
        float vIn=0, vTryIn=0, // объемы налитой и недолитой воды
        vOut=0, vTryOut=0; // объемы вылитой и недовылитой воды
        int inTry=0, inSuccess=0, inFail =0, // попытки налить
                outTry=0, outSuccess=0, outFail=0;// попытки  вылить

        s = bufferedReader.readLine();
        if (s.contains("META DATA")){ // объем бочки
            s = bufferedReader.readLine();
            Scanner scanner = new Scanner(s);
            while (scanner.hasNext()) {
                if (scanner.hasNextFloat()) {
                    vMax = scanner.nextFloat();
          //          System.out.println("объем " + vMax);
                }
                scanner.next();
            }
            scanner.close();
            s = bufferedReader.readLine();
            scanner = new Scanner(s);
            while (scanner.hasNext()) {
                if (scanner.hasNextFloat()) {
                    vCurrent = scanner.nextFloat();
            //        System.out.println("начальный объем " + vCurrent);
                }
                scanner.next();
            }
            scanner.close();
        }

        while (((s = bufferedReader.readLine()) != null)){

// оновное тело лога с датами
            WaterCarrier waterCarrier = new WaterCarrier(s);
            if (waterCarrier.in){ // попытался зачерпнуть
                outTry++; // колличество попыток
                if (vCurrent-waterCarrier.v>0){ // если удалось зачерпнуть
                    vCurrent = vCurrent-waterCarrier.v;
                    if (water.NeededDate(waterCarrier.date)){ // если дата в нужном интервале
                        outSuccess++;
                        vOut = vOut + waterCarrier.v;
                    }
                }
                else {
                    outFail++;
                    vTryOut = vTryOut + waterCarrier.v;
                }
            }
            else{ // попытался долить
                inTry++;
                if (vCurrent+waterCarrier.v < vMax) { // если удалось долить
                    vCurrent = vCurrent+waterCarrier.v;
                    if (water.NeededDate(waterCarrier.date)){ // если дата в нужном интервале
                        inSuccess++;
                        vIn = vIn + waterCarrier.v;
                    }
                }
                else {
                    inFail++;
                    vTryIn = vTryIn + waterCarrier.v;
                }
            }
        }
        bufferedReader.close(); // конец обработки данных

        file = new File("csv.csv");
        if (!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write("quantity try top up,% mistake,water top up, water not top up \n");
        float inPr = 0;
        if (inTry >0) {
            inPr =(inFail*100/inTry);} // процент ошибок налива
        float outPr = 0;
        if (outTry >0) {outPr =(outFail*100/outTry);} // процент ошибок отлива
        fileWriter.write(inTry +",                                  " + inPr + ",            "+vIn+ ",               " +vTryIn + "\n\n");
        fileWriter.write("quantity try scoop,% mistake,water scoop, water not scoop \n");
        fileWriter.write(outTry +",                               " + outPr + ",              "+vOut+ ",           " +vTryOut + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
}