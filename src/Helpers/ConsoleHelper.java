package Helpers;

import java.util.Scanner;

public class ConsoleHelper {
    public static void Print(String Message){

        System.out.println(Message);
    }

    public static int ReadInt(String Message){
        System.out.print(Message);
        Scanner scanner=new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String ReadString(String Message){
        System.out.print(Message);
        Scanner scanner=new Scanner(System.in);
        return scanner.nextLine();
    }


    public static String padLeft(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append(' ');
        }
        sb.append(inputString);

        return sb.toString();
    }
    public static String padRight(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();

        sb.append(inputString);
        while (sb.length() < length ) {
            sb.append(' ');
        }

        return sb.toString();
    }
}
