import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Customize welcome message
        System.out.println("\nHello, AttendanceApp!");
        System.out.print("\nEnter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        System.out.println("Welcome, " + name + "!\n");

        //Create and output list of absences
        ArrayList<Integer> absentList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < name.length(); i++) {
            absentList.add(rand.nextInt(11));
        }
        System.out.println("The elements are: " + absentList);

        //Number of students with perfect attendance
        int countAttendance = 0;
        for (int i = 0; i < name.length(); i++) {
            if (absentList.get(i) == 0){
                countAttendance++;
            }
        }
        System.out.println("Number of students with perfect attendance: " + countAttendance);
    }
}
