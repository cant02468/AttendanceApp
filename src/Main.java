import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Read user's name and add to welcome message
        System.out.println("\nHello, AttendanceApp!");
        System.out.print("\nEnter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("Welcome, " + name + "!\n");

        //Initialize an ArrayList for a list of random absences
        ArrayList<Integer> absentList = new ArrayList<>();

        //Randomly generate values for absences until absentLength is the size of the user's name
        Random rand = new Random();
        for (int i = 0; i < name.length(); i++) {
            int absences = rand.nextInt(11);
            absentList.add(absences);
        }

        //Print the list of absences
        System.out.println("The absences are: " + absentList);
    }
}
