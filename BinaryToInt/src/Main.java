import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        switch (args[0]) {
            case "MissMatch" -> System.out.println("Enter a number");
            case "Start" ->
                    System.out.print("""
                                        Int to Bi - 1, Bi to Int - 2, TC to int - 3, Hex to int - 4
                                        """);
            case "WrongNum" -> System.out.println("Enter a number between 1-3");
        }
        int a = 0;
        try {
            String b = sc.nextLine();
            if (b.equalsIgnoreCase("stop") || b.equalsIgnoreCase("break") || b.equalsIgnoreCase("quit")) {
                System.out.println("GoodBye");
                System.exit(0);
            } else a = Integer.parseInt(b);
        } catch (InputMismatchException e) {
            main(new String[]{"MissMatch"});
        }
        Calc calc = new Calc();
        switch (a) {
            case 1 -> calc.i2b("None");
            case 2 -> calc.b2i("None");
            case 3 -> calc.b2iC("None");
            case 4 -> calc.h2i("None");
            default -> main(new String[]{"WrongNum"});

        }

    }
}

class Calc {
    public Scanner sc = new Scanner(System.in);

    public void i2b(String error) {

        switch (error) {
            case "None" -> System.out.println("Enter a valid Long");
            case "MissMatch" -> System.out.println("Enter an actual number");
        }
        long a = 0;
        try {
            String b = sc.nextLine();
            if (b.equalsIgnoreCase("stop") || b.equalsIgnoreCase("break") || b.equalsIgnoreCase("quit")) {
                Main.main(new String[]{"Start"});
            } else a = Long.parseLong(b);
        } catch (NumberFormatException e) {
            i2b("MissMatch");
        }
        StringBuilder ans = new StringBuilder();
        while (a > 0) {
            ans.insert(0, a % 2);
            a /= 2;
        }
        System.out.println(ans);
        Main.main(new String[]{"Start"});

    }

    public void b2i(String error) {

        switch (error) {
            case "None" -> System.out.println("Enter a valid binary number");
            case "MissMatch" -> System.out.println("That was not a valid binary number, enter a new one");
        }
        String a = sc.nextLine();
        if (a.equalsIgnoreCase("stop") || a.equalsIgnoreCase("break") || a.equalsIgnoreCase("quit")) {
            Main.main(new String[]{"Start"});
        }
        char[] chars = reverse(a.toCharArray(), a.length());
        long ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0' && chars[i] != '1') {
                b2i("MissMatch");
            }
            ans += Long.parseLong(chars[i] + "") * Math.pow(2, i);
        }
        System.out.println(ans);
        Main.main(new String[]{"Start"});

    }

    public void b2iC(String error) {
        switch (error) {
            case "None" -> System.out.println("Enter a valid binary number");
            case "MissMatch" -> System.out.println("That was not a valid binary number, enter a new one");
        }
        String a = sc.nextLine();
        if (a.equalsIgnoreCase("stop") || a.equalsIgnoreCase("break") || a.equalsIgnoreCase("quit")) {
            Main.main(new String[]{"Start"});
        }
        char[] chars = reverse(a.toCharArray(), a.length());
        long ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0' && chars[i] != '1') {
                b2i("MissMatch");
            }

            long l = (long) (Long.parseLong(chars[i] + "") * Math.pow(2, i));
            if(i==chars.length-1){
                l *= -1;
            }
            ans += l;
        }
        System.out.println(ans);
        Main.main(new String[]{"Start"});
    }

    public void h2i(String error) {

        switch (error) {
            case "None" -> System.out.println("Enter a valid hexadecimal number");
            case "MissMatch" -> System.out.println("That was not a valid hexadecimal number, enter a new one");
        }
        String a = sc.nextLine();
        if (a.equalsIgnoreCase("stop") || a.equalsIgnoreCase("break") || a.equalsIgnoreCase("quit")) {
            Main.main(new String[]{"Start"});
        }
        char[] chars = reverse(a.toCharArray(), a.length());
        long ans = 0;
        try {
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];

                if ((temp > 57 || temp < 48) && (temp > 102 || temp < 97) && (temp<65 || temp > 70) ) {
                    h2i("MissMatch");
                }
                String thing = temp + "";
                switch (temp) {
                    case 'a','A' -> thing = "10";
                    case 'b','B' -> thing = "11";
                    case 'c','C' -> thing = "12";
                    case 'd','D' -> thing = "13";
                    case 'e','E' -> thing = "14";
                    case 'f','F' -> thing = "15";
                }
                ans += Long.parseLong(thing) * Math.pow(16, i);
            }
        } catch (NumberFormatException e){
            h2i("MissMatch");
        }
        System.out.println(ans);
        Main.main(new String[]{"Start"});

    }



    char[] reverse(char[] a, int n) {
        char[] b = new char[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;
        }
        return b;
    }
}
