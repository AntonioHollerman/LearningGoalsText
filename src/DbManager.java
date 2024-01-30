import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;
public class DbManager {
    private final HashMap<String, Double> coursesMap = new HashMap<>();
    private double totalHours;
    private double hoursLearning;
    private double totalHoursLeft;
    private double hoursNeededPerWeek;
    private double hoursLearningForTheWeek;
    private int weeksUntilOver;

    public DbManager(){
        String FILE_NAME = "src\\goalsDB.txt";
        Path pathToFile = Path.of(System.getProperty("user.dir"), FILE_NAME);

        weeksUntilOver = getWeeksUntilOver();
        totalHours = ;
        hoursLearning = ;
        totalHoursLeft = totalHoursLeft();
        hoursNeededPerWeek = totalHoursLeft / weeksUntilOver;
        hoursLearningForTheWeek = ;
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
        totalHoursLeft -= hoursSpent;
        coursesMap.put(courseName, hoursLeft);

        if (hoursLeft <= 0){
            removeCourse(courseName);
        }
    }
    public void writeInfo(){

    }
    public void getInfo(){

    }
    private double totalHoursLeft(){
        Set<String> courses = coursesMap.keySet();
        double totalHours = 0;

        for (String course : courses){
            totalHours += coursesMap.get(course);
        }
        return totalHours;
    }
    private int getWeeksUntilOver(){

    }
    public void displayInfo() {
        Set<String> courses = coursesMap.keySet();
        double courseHours;
        int minutesLearning = (int) (hoursLearningForTheWeek - (int) hoursLearningForTheWeek) * 60;

        System.out.println("Total Hours: " + totalHours);
        System.out.println("Hours Spent Learning: " + hoursLearning);
        System.out.println("Hours Needed Every Week: " + ((int) hoursNeededPerWeek + 1));
        System.out.println("Hours Left For The Week: " + ((int) hoursLearningForTheWeek) + " hours and " +
                minutesLearning + " minutes");
        for (String course : courses) {
            courseHours = coursesMap.get(course);
            System.out.println(course + ": " + courseHours + "hours left");
        }
    }
}
