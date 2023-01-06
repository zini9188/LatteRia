package app;

import java.util.Scanner;

public class Reader {
    public static Scanner scanner = new Scanner(System.in);

    public static String readString(){
        return scanner.nextLine();
    }

    public static int readInteger() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("정수만 입력 부탁");
            return readInteger();
        }
    }

    public static int readDiscount(){
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("1,2만 입력");
            return readDiscount();
        }
    }
}
