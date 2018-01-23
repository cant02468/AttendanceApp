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

        //Calculate average of absences
        double avg = average(absentList);
        System.out.println("The average number of absences is " + avg);

        //Percentage of students who had fewer than 3 absences who also had perfect attendance
        double percentAttendance = perfectOverThree(absentList , countAttendance);
        System.out.println("The percentage of students who had fewer than 3 absences who also had perfect attendance is " + percentAttendance + "%.");
    }

    private static double perfectOverThree(ArrayList<Integer> absentList, int countAttendance) {
        int fewerThanThree = 0;
        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) <3) {
                fewerThanThree++;
            }
        }
        return (double)countAttendance/fewerThanThree * 100;
    }

    private static double average(ArrayList<Integer> absentList) {
        return (double)sum(absentList)/absentList.size();
    }

    private static int sum(ArrayList<Integer> absentList) {
        int sum = 0;
        for (int i = 0; i < absentList.size(); i++) {
            sum += absentList.get(i);
        }
        return sum;
    }
}
