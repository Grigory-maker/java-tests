public class Numbers {
    static int convert1(String base){
        final String template = "0123456789abcdef";
        if (base.charAt(0) != '0' || !template.contains(base)){
            System.out.println("Система исчисления. На пример, «01» - двоичная, «012» - троичная, «0123456789abcdef» - шеснадцатиричная, «котики» - система исчисления в котиках.");
            System.exit(0);
        }
        return base.length();
    }
    static String itoBase( int nb, String base){
            if (nb <0)
            {
                return  "введите целое положительное число  ";
            }
            else
            {
                return  Integer.toString(nb,convert1(base));
            }
        }
        static String itoBase(String nb, String baseSrc, String baseDst)
        {
            int num = Integer.parseInt(nb, convert1(baseSrc));
            if (num <0)
            {
                System.out.println("введите целое положительное число");
                System.exit(0);
            }
            return  itoBase(num, baseDst);
        }
        public static void main(String[] args){
            switch (args.length){
                case 2 :
                    int nb = Integer.parseInt(args[0]);
                    String base = args[1];
                    System.out.println(Numbers.itoBase(nb,base));
                    break;
                case 3 :
                    System.out.println(Numbers.itoBase(args[0],args[1],args[2]));
                    break;
                default:
                    System.out.println("введите число и систему исчесления");
                    break;
            }
        }
    }
