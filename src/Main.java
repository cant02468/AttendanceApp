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
        double avg = average(absentList, absentList);
        System.out.println("The average number of absences is " + avg);

        //Percentage of students who had fewer than 3 absences who also had perfect attendance
        double percentAttendance = perfectOverThree(absentList , countAttendance);
        System.out.println("The percentage of students who had fewer than 3 absences who also had perfect attendance is " + percentAttendance + "%.");

        //Identify the indexes of students with a specific number of absences
        System.out.println("\nWhat is the specified number of absences? ");
        Scanner sc2 = new Scanner(System.in);
        Integer numAbsences = sc.nextInt();
        ArrayList<Integer> indexes = absencesFinder(absentList, numAbsences);
        if (indexes.isEmpty()) {
            System.out.println("No students have " + numAbsences + " absences." );
        } else {
            System.out.println("The index(es) of the students who had " + numAbsences + " absences are " + indexes);
        }

        //Identify the index(es) of the student(s) who were absent more than twice the number of times the course meets per week
        System.out.println("\nHow many times does the course meet per week? ");
        Scanner sc3 = new Scanner(System.in);
        Integer meetings = sc3.nextInt();
        ArrayList<Integer> indexFE = FEFinder(absentList, meetings);
        double FEPercentage = (double)indexFE.size()/absentList.size() * 100;
        if (indexFE.isEmpty()) {
            System.out.println("No students have FE'd");
        } else {
            System.out.println("The index(es) of the student(s) who were absent more than twice the number of times the course meets per week is " + indexFE);
            System.out.printf("%.1f%% have FE'd the course", FEPercentage);
        }

        //Find the average of only the non-FE'd absences


    }

    private static ArrayList<Integer> FEFinder(ArrayList<Integer> absentList, Integer meetings) {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) > meetings * 2){
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static ArrayList<Integer> absencesFinder(ArrayList<Integer> absentList, Integer numAbsences) {
        ArrayList<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) == numAbsences){
                indexes.add(i);
            }
        }
        return indexes;
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

    private static double average(ArrayList<Integer> part, ArrayList<Integer> whole) {
        return (double)sum(part)/whole.size();
    }

    private static int sum(ArrayList<Integer> part) {
        int sum = 0;
        for (int i = 0; i < part.size(); i++) {
            sum += part.get(i);
        }
        return sum;
    }
}
