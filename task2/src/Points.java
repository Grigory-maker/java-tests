import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;


public class Points {

    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("Нет такого файла " + file);
        }
        float xSphere=0, ySphere=0, zSphere=0, rSphere=0;
        float x1=0, y1=0, z1=0, x2=0, y2=0, z2=0;
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();
        s = s.replaceAll("[^A-Za-zА-Яа-я0-9\\s\\.]", ""); // убираем скобки
        //System.out.println(s);
        Scanner scanner = new Scanner(s);
        String temp;
        while (scanner.hasNext()) {
            temp = scanner.next();
            if (temp.contains("center")){ // координаты сферы
                temp = scanner.next();
                xSphere = Float.parseFloat(temp);

                temp = scanner.next();
                ySphere = Float.parseFloat(temp);

                temp = scanner.next();
                zSphere = Float.parseFloat(temp);

            }
            if (temp.contains("radius")){ // координаты сферы
                temp = scanner.next();
                rSphere = Float.parseFloat(temp);
            }
            if (temp.contains("line")) { // координаты сферы
                temp = scanner.next();
                x1 = Float.parseFloat(temp);

                temp = scanner.next();
                y1 = Float.parseFloat(temp);

                temp = scanner.next();
                z1 = Float.parseFloat(temp);

                temp = scanner.next();
                x2 = Float.parseFloat(temp);

                temp = scanner.next();
                y2 = Float.parseFloat(temp);

                temp = scanner.next();
                z2 = Float.parseFloat(temp);
            }

        }
        scanner.close();
        if ((x1==x2)&(y1==y2)&(z1==z2))
        {
            System.out.println("Не верно задана прямая");
            System.exit(0);
        }
        // вектор прямой
        float k1 = x2 - x1;
        float k2 = y2 - y1;
        float k3 = z2 - z1;

        float a = k1*k1+k2*k2+k3*k3;
        float b = 2*(k1*(x1-xSphere)+k2*(y1-ySphere)+k3*(z1-zSphere));
        float c = (x1-xSphere)*(x1-xSphere)+(y1-ySphere)*(y1-ySphere)+(z1-zSphere)*(z1-zSphere)-rSphere*rSphere;
        float d = b*b-4*a*c; // дескрименант
        if (d<0)
        {
            System.out.println("Коллизий не найдено");
            System.exit(0);
        }
        if (d ==0) // одно решение
        {
            double t = (-b/(2*a));
            System.out.println((x1+k1*t) + " " + (y1+k2*t) + " " + (z1+k3*t));
            System.exit(0);
        }
        // два решения
        double t = (-b+Math.sqrt(d))/(2*a);
        System.out.println((x1+k1*t) + " " + (y1+k2*t) + " " + (z1+k3*t));
        t = (-b-Math.sqrt(d))/(2*a);
        System.out.println((x1+k1*t) + " " + (y1+k2*t) + " " + (z1+k3*t));


    }

}
