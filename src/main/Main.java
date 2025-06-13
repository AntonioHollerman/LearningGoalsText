package main;

public class Main {
    public static void main(String[] args) {
        DbManager manager = new DbManager();

        String[] options = {
                "Exit",
                "Display Progress",
                "Add a Course",
                "Update Progress",
                "Remove Course",
                "Forget Past Courses",
                "Change Target Date"
        };

        InputManagement inputHandler = new InputManagement(manager);
        int indexChoice;
        outer:
        while (true){
            indexChoice = GetInput.selectOption(options);
            switch (indexChoice){
                case 0:
                    break outer;
                case 1:
                    System.out.println("\n---------------------------------\n");
                    manager.displayInfo();
                    System.out.println("\n---------------------------------\n");
                    break;
                case 2:
                    System.out.println();
                    inputHandler.addCourse();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    inputHandler.updateCourse();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    inputHandler.removeCourse();
                    System.out.println();
                    break;
                case 5:
                    System.out.println();
                    manager.resetGoals();
                    System.out.println();
                    break;
                case 6:
                    System.out.println();
                    manager.changeTargetDate(GetInput.getTargetDate());
                    System.out.println();
                    break;
            }
        }
        manager.writeInfo();
    }
}