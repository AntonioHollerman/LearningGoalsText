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
                    manager.displayInfo();
                    break;
                case 2:
                    inputHandler.addCourse();
                    break;
                case 3:
                    inputHandler.updateCourse();
                    break;
                case 4:
                    inputHandler.removeCourse();
                    break;
                case 5:
                    manager.resetGoals();
                    break;
                case 6:
                    manager.changeTargetDate(GetInput.getTargetDate());
                    break;
            }
        }
        manager.writeInfo();
    }
}