import java.util.Scanner;
import java.time.LocalDate;
import java.time.DateTimeException;
public class GetInput {
    public static int selectOption(String[] options){
        // displays the different options and return the index of user choice

        Scanner scanner = new Scanner(System.in);
        String input;
        int index; // index of the user choice

        for (int i=0; i < options.length; i++){
            System.out.println((i + 1) + ": " + options[i]);
        }
        while (true){
            System.out.print("Choose an option: ");
            try {
                input = scanner.nextLine();
                index = Integer.parseInt(input) - 1;
                if (index >= 0 & index < options.length){
                    return index;
                }
                System.out.println("Invalid input please provide proper input");
            } catch (NumberFormatException e){
                System.out.println("Invalid input please provide proper input");
            }
        }
    }
    public static double doubleInput(String display, boolean allowNegZero){
        // Prompts user input for an int and returns the value
        Scanner scanner = new Scanner(System.in);
        String input;
        double num;

        while (true){
            System.out.print(display);
            try {
                input = scanner.nextLine();
                num = Double.parseDouble(input);
                // logic for allowing negative and zero
                if (allowNegZero){
                    return num;
                    // logic for not allowing negative and zero
                } else {
                    if (num > 0){
                        return num;
                    }
                    System.out.println("Invalid input please provide an int greater than zero");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input please provide an int greater than zero");
            }
        }
    }
    public static int intInput(String display, boolean allowNegZero){
        // Prompts user input for an int and returns the value
        Scanner scanner = new Scanner(System.in);
        String input;
        int num;

        while (true){
            System.out.print(display);
            try {
                input = scanner.nextLine();
                num = Integer.parseInt(input);
                // logic for allowing negative and zero
                if (allowNegZero){
                    return num;
                    // logic for not allowing negative and zero
                } else {
                    if (num > 0){
                        return num;
                    }
                    System.out.println("Invalid input please provide an int greater than zero");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input please provide an int greater than zero");
            }
        }
    }
    public static String stringInput(String display){
        Scanner scanner = new Scanner(System.in);
        System.out.print(display);
        return scanner.nextLine();
    }
    public static String getCourseName(){
        return stringInput("Course name: ");
    }
    public static double getCourseHours(){
        return doubleInput("Amount of hours: ", false);
    }
    public static double getHoursCompleted(String display){
        return doubleInput(display, true);
    }
    public static LocalDate getTargetDate(){
        int year;
        int month;
        int day;
        LocalDate target;
        System.out.println("Provide an integer of the following");
        while (true){
            try {
                year = intInput("Year: ", false);
                month = intInput("Month: ", false);
                day = intInput("Day: ", false);
                target = LocalDate.of(year, month, day);
                if (target.toEpochDay() > LocalDate.now().toEpochDay()){
                    return target;
                }
                System.out.println("Pick a ");
            } catch (DateTimeException e) {
                System.out.println("Give a proper date");
            }
        }
    }
}
