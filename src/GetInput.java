import java.util.Scanner;

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
    public static double getHoursCompleted(){
        return doubleInput("Hours Completed: ", true);
    }
}
