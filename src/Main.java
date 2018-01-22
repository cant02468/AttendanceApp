import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Read user's name and add to welcome message
        System.out.println("\nHello, AttendanceApp!");
        System.out.print("\nEnter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("Welcome, " + name + "!\n");
    }
}
