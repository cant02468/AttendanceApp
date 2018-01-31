import java.util.*;

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
        int countAttendance = countNum(absentList, 0);
        System.out.println("Number of students with perfect attendance: " + countAttendance);

        //Calculate average of absences
        double avg = average(absentList);
        System.out.println("The average number of absences is " + avg);

        //Percentage of students who had fewer than 3 absences who also had perfect attendance
        double percentAttendance = perfectOver(absentList , 3);
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
        double FEPercentage = percentage(indexFE.size(),absentList.size());
        if (indexFE.isEmpty()) {
            System.out.println("No students have FE'd");
        } else {
            System.out.println("The index(es) of the student(s) who were absent more than twice the number of times the course meets per week is " + indexFE);
            System.out.printf("%.1f%% have FE'd the course", FEPercentage);
        }

        //Find the average of only the non-FE'd absences.
        ArrayList<Integer> elemNonFE = nonFE(absentList, meetings);
        double avgNonFE = average(elemNonFE);
        System.out.println("\nThe average of the non-FE'd absences is " + avgNonFE);

        //Add [X] to any absences greater than [Y].
        System.out.println("\nHow many absences are to be added? The value can be positive, negative, or zero.");
        Scanner sc4 = new Scanner(System.in);
        Integer addedAbsences = sc.nextInt();
        System.out.println("What is the target number of absences?");
        Scanner sc5 = new Scanner(System.in);
        Integer targetElem = sc.nextInt();
        absentList = AddToElem(absentList, addedAbsences, targetElem);
        System.out.println("The new list of absences is " + absentList);

        //Sort the absences using a library function.
        Collections.sort(absentList);
        System.out.println("\nThe sorted list of absences is " + absentList);

        //Shuffle the absences using a library function.
        Collections.shuffle(absentList);
        System.out.println("\nThe shuffled list of absences is " + absentList);

        //Seek for the number of absences that are unique.
        Set<Integer> uniqueAbsences = seekUnique(absentList);
        System.out.println("\nThe unique absences are " + uniqueAbsences);

    }

    private static Set<Integer> seekUnique(ArrayList<Integer> userList) {
        ArrayList<Integer> storeDuplicates = new ArrayList<>();
        Set<Integer> returnSet = new HashSet<>();
        for (int i = 0; i < userList.size(); i++) {
            if (returnSet.contains(userList.get(i))) {
                storeDuplicates.add(userList.get(i));
            } else {
                returnSet.add(userList.get(i));
            }
        }
        for (int i = 0; i < storeDuplicates.size(); i++) {
            returnSet.remove(storeDuplicates.get(i));
        }
        return returnSet;
    }

    private static ArrayList<Integer> AddToElem(ArrayList<Integer> userList, Integer modifier, Integer target) {
        ArrayList<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < userList.size() ; i++) {
            if (userList.get(i) >= target) {
                Integer newElem = userList.get(i) + modifier;
                if (newElem > 15) {
                    newElem = 15;
                }
                if (newElem < 0) {
                    newElem = 0;
                }
                returnList.add(newElem);
            } else {
                returnList.add(userList.get(i));
            }

        }
        return returnList;
    }

    private static double percentage(int numerator, int denominator) {
        return ((double)numerator/denominator * 100);
    }

    private static int countNum(ArrayList<Integer> absentList, int num) {
        int count = 0;
        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) == num) {
                count++;
            }
        }
        return count;
    }

    private static double average(ArrayList<Integer> itself) {
        return average(itself, itself);
    }

    private static ArrayList<Integer> nonFE(ArrayList<Integer> absentList, Integer meetings) {
        ArrayList<Integer> elements = new ArrayList<>();

        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) <= meetings * 2) {
                elements.add(absentList.get(i));
            }
        }
        return elements;
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
            if (absentList.get(i).equals(numAbsences)){
                indexes.add(i);
            }
        }
        return indexes;
    }

    private static double perfectOver(ArrayList<Integer> absentList, int num) {
        int fewerThanThree = 0;
        for (int i = 0; i < absentList.size(); i++) {
            if (absentList.get(i) < num) {
                fewerThanThree++;
            }
        }
        return percentage(countNum(absentList, 0),fewerThanThree);
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
