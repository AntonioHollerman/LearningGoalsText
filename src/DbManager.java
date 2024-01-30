import java.util.HashMap;
import java.util.Set;
public class DbManager {
    private final HashMap<String, Double> coursesMap = new HashMap<>();
    private double hoursLearning;

    public DbManager(){
        hoursLearning = 0;
    }
    public void addCourse(String courseName, double courseHours){
       coursesMap.put(courseName, courseHours);
    }
    public void removeCourse(String courseName){
        coursesMap.remove(courseName);
    }
    public void editCourse(String courseName, double hoursSpent){
        double hoursLeft = coursesMap.get(courseName) - hoursSpent;
        hoursLearning += hoursSpent;
        coursesMap.put(courseName, hoursLeft);

        if (hoursLeft <= 0){
            removeCourse(courseName);
        }
    }
    public void writeInfo(){

    }
    public void getInfo(){

    }
    public double getTotalHours(){
        Set<String> courses = coursesMap.keySet();
        double totalHours = 0;

        for (String course : courses){
            totalHours += coursesMap.get(course);
        }
        return totalHours;
    }
    public void displayInfo() {
        Set<String> courses = coursesMap.keySet();
        double totalHours = getTotalHours();
        double courseHours;

        System.out.println("Hours Left: " + totalHours);
        System.out.println("Hours Spent Learning: " + hoursLearning);
        for (String course : courses) {
            courseHours = coursesMap.get(course);
            System.out.println(course + ": " + courseHours + "hours");
        }
    }
}
