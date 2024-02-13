import java.util.Set;

public class InputManagement {
    private final DbManager manager;
    public InputManagement(DbManager manager){
        this.manager = manager;
    }
    public void addCourse(){
        System.out.println("Add a new course to study");
        String courseName = GetInput.getCourseName();
        double courseHours = GetInput.getCourseHours();
        manager.addCourse(courseName, courseHours);
    }
    public void updateCourse(){
        Set<String> coursesNames = manager.getCoursesNames();
        String[] namesInArray = coursesNames.toArray(new String[0]);
        int choiceIndex = GetInput.selectOption(namesInArray);
        double hours = GetInput.getHoursCompleted(namesInArray[choiceIndex]);
        manager.editCourse(namesInArray[choiceIndex], hours);
    }
    public void removeCourse(){
        Set<String> coursesNames = manager.getCoursesNames();
        String[] namesInArray = coursesNames.toArray(new String[0]);
        int choiceIndex = GetInput.selectOption(namesInArray);
        manager.removeCourse(namesInArray[choiceIndex]);
    }
}
