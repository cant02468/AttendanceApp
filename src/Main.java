import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Customize welcome message
        System.out.println("\nHello, AttendanceApp!");
        System.out.print("\nEnter your name: ");
        String name = stringInput();
        System.out.println("Welcome, " + name + "!\n");

        //Create and output list of absences.
        ArrayList<Integer> absentList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < name.length(); i++) {
            absentList.add(rand.nextInt(11));
        }
        System.out.println("The elements are: " + absentList);

        //Number of students with perfect attendance.
        int countAttendance = countNum(absentList, 0);
        System.out.println("Number of students with perfect attendance: " + countAttendance);

        //Calculate average of absences.
        double avg = average(absentList);
        System.out.println("The average number of absences is " + avg);

        //Percentage of students who had fewer than 3 absences who also had perfect attendance.
        double percentAttendance = perfectOver(absentList , 3);
        System.out.println("The percentage of students who had fewer than 3 absences who also had perfect attendance is " + percentAttendance + "%.");

        //Identify the indexes of students with a specific number of absences.
        System.out.println("\nWhat is the specified number of absences? ");
        Integer numAbsences = intInput();
        ArrayList<Integer> indexes = absencesFinder(absentList, numAbsences);
        if (indexes.isEmpty()) {
            System.out.println("No students have " + numAbsences + " absences." );
        } else {
            System.out.println("The index(es) of the students who had " + numAbsences + " absences are " + indexes);
        }

        //Identify the index(es) of the student(s) who were absent more than twice the number of times the course meets per week.
        System.out.println("\nHow many times does the course meet per week? ");
        Integer meetings = intInput();
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
        Integer addedAbsences = intInput();
        System.out.println("What is the target number of absences?");
        Integer targetElem = intInput();
        absentList = AddToElem(absentList, addedAbsences, targetElem);
        absentList = limitElems(absentList, 0,15);
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

        //Count the number of absences for each value.
        Map<Integer, Integer> absentCount = elemCount(absentList);
        System.out.println("\nThe frequency of absences are " + absentCount);

        //Output frequency of absences as a histogram.
        histogramDisplay(absentList);

        //Sort the absences using a user-defined sort function.
        bubbleSort(absentList);
        System.out.println("The user-sorted absences are " + absentList);

        //Shuffle the absences using a user-defined shuffle function.
        shuffleIntegerList(absentList);
        System.out.println("The user-shuffled absences are " + absentList);

        //Create and output an ArrayList of 5 distinct names.
        System.out.println("\nPlease enter a distinct name and press 'enter' 5 times.");
        ArrayList<String> nameList =  customArrayList(5);
        System.out.println("The 5 distinct names are " + nameList);

        //Shuffle the names using a user-defined shuffle function.
        shuffleStringList(nameList);
        System.out.println("\nThe user-shuffled list of names is " + nameList);

        //Using the 5 names, create another list that has the same size as the absences list.
        ArrayList<String> randomNameList = randomNames(nameList, absentList.size());
        System.out.println("\nThe random list of names that is the same size as the absences list is " + randomNameList);

        //Determine if all  5 names used at least once.
        boolean nameUsageCheck = ArrayInArray(nameList, randomNameList);
        if (nameUsageCheck) {
            System.out.println("\nAll 5 names have been used at least once.");
        } else {
            System.out.println("\nSome of the names have not been used at least once.");
        }

    }

    private static boolean ArrayInArray(ArrayList<String> shortUserList, ArrayList<String> largeUserList) {
        int count = 0;
        Set<String> shortSet = new HashSet<>(shortUserList);
        ArrayList<String> shortList = new ArrayList<>(shortSet);
        Set<String> longSet = new HashSet<>(largeUserList);
        ArrayList<String> longList = new ArrayList<>(longSet);
        for (int i = 0; i < shortUserList.size(); i++) {
            if (longList.get(i).equals(shortList.get(i))){
                count++;
            } else {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<String> randomNames(ArrayList<String> userList, int size) {
        ArrayList<String> returnList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            returnList.add(userList.get(rand.nextInt(userList.size())));
        }
        return returnList;
    }

    private static ArrayList<String> customArrayList (Integer size){
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String userString = stringInput();
            while (returnList.contains(userString)) {
                System.out.println("Input must be unique. Please enter a unique input.");
                userString = stringInput();
            }
            returnList.add(userString);

        }
        return returnList;
    }

    private static Integer intInput(){
        Scanner sc = new Scanner(System.in);
        Integer returnInteger = sc.nextInt();
        return returnInteger;
    }

    private static String stringInput (){
        Scanner sc = new Scanner(System.in);
        String returnString = sc.next();
        return returnString;
    }

    private static ArrayList<String> shuffleStringList (ArrayList<String> userList) {
        Random rand = new Random();
        for (int i = 0; i < userList.size(); i++) {
            int newID = rand.nextInt(userList.size());
            String temp = userList.get(i);
            userList.set(i, userList.get(newID));
            userList.set(newID, temp);
        }
        return userList;
    }

    private static ArrayList<Integer> shuffleIntegerList(ArrayList<Integer> userList) {
        Random rand = new Random();
        for (int i = 0; i < userList.size(); i++) {
            int newID = rand.nextInt(userList.size());
            int temp = userList.get(i);
            userList.set(i, userList.get(newID));
            userList.set(newID, temp);
        }
        return userList;
    }

    private static ArrayList<Integer> bubbleSort (ArrayList<Integer> userList) {
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < userList.size(); j++) {
                if (userList.get(i) < userList.get(j)) {
                    int temp = userList.get(i);
                    userList.set(i, userList.get(j));
                    userList.set(j, temp);
                }
            }
        }
        return userList;
    }

    private static void histogramDisplay(ArrayList<Integer> userList) {
        Set<Integer> set = new HashSet<>(userList);
        ArrayList<Integer> yElem = new ArrayList<>(set);
        Map<Integer,Integer> xCount = elemCount(userList);
        for (int i = 0; i < yElem.size(); i++) {
            String displayAsterisk = " ";
            int frequency = xCount.get(yElem.get(i));
            for (int j = 0; j < frequency; j++) displayAsterisk += "*";
            System.out.println(Integer.toString(yElem.get(i)) + ' ' + displayAsterisk);
        }
    }


    private static Map<Integer, Integer> elemCount(ArrayList<Integer> userList) {
        Map<Integer,Integer> returnMap = new HashMap<>();
        for (int i = 0; i < userList.size(); i++) {
            Integer count = returnMap.get(userList.get(i));
            returnMap.put(userList.get(i), (count == null) ? 1 : count +1);
        }
        return returnMap;
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

    private static ArrayList<Integer> limitElems(ArrayList<Integer> userList, Integer minimum, Integer maximum) {
        ArrayList<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            Integer newElem = userList.get(i);
            if (newElem > maximum) {
                newElem = maximum;
            }
            if (newElem < minimum) {
                newElem = minimum;
            }
            returnList.add(newElem);
        }
        return returnList;
    }

    private static ArrayList<Integer> AddToElem(ArrayList<Integer> userList, Integer modifier, Integer target) {
        ArrayList<Integer> returnList = new ArrayList<>();
        for (int i = 0; i < userList.size() ; i++) {
            if (userList.get(i) >= target) {
                Integer newElem = userList.get(i) + modifier;
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
