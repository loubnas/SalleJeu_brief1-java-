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

}
