package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
public class DbManager {
    private final HashMap<String, Double> coursesMap = new HashMap<>();
    private double totalHours;
    private double hoursLearning;
    private double totalHoursLeft;
    private double hoursNeededPerWeek;
    private double hoursLearningForTheWeek;
    private int weeksUntilOver;
    private LocalDate dateOfLastMonday;
    private LocalDate dateOfTarget;

    public DbManager(){
        String FILE_NAME = "src\\goalsDB.txt";
        Path pathToFile = Path.of(System.getProperty("user.dir"), FILE_NAME);

        List<String> dbLines = null;
        if (!Files.exists(pathToFile)){
            System.out.println("Hello User. To get started fill out when you want to have these " +
                    "courses completed by. :D");
            changeTargetDate(GetInput.getTargetDate());
        } else {
            try {
                dbLines = Files.readAllLines(pathToFile);
            } catch (IOException e) {
                System.out.println("Oppsies :P");
            }

            assert dbLines != null;
            getCourses(dbLines.get(4));
            weeksUntilOver = getWeeksUntilOver(dbLines.get(2));
            totalHours = Double.parseDouble(dbLines.get(0));
            totalHoursLeft = totalHoursLeft();
            hoursLearning = totalHours - totalHoursLeft;
            hoursNeededPerWeek = totalHoursLeft / weeksUntilOver;
            hoursLearningForTheWeek = Double.parseDouble(dbLines.get(1));
            checkDate(dbLines.get(3));
            checkDatePass();
        }
    }
    public Set<String> getCoursesNames(){
        return coursesMap.keySet();
    }
    private LocalDate formatDate(String date){
        if (date.equals("null")){
            return null;
        }
        String[] info = date.split("-");
        int year = Integer.parseInt(info[0]);
        int month = Integer.parseInt(info[1]);
        int day = Integer.parseInt(info[2]);
        return LocalDate.of(year, month, day);
    }
    private void checkDate(String lastMonday){
        LocalDate mondayDate = formatDate(lastMonday);
        LocalDate currentDate = LocalDate.now();

        if (mondayDate == null){
            mondayDate = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }

        long days = ChronoUnit.DAYS.between(mondayDate, currentDate);
        if (days >= 7){
            hoursLearningForTheWeek = 0;
        }
        dateOfLastMonday = currentDate.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
    }
    public void checkDatePass(){
        String[] choices = {"Clear current courses", "Continue courses for new date"};
        if (dateOfTarget.toEpochDay() > LocalDate.now().toEpochDay()){
            return;
        }
        System.out.println("Target date has pass! Look how far you progress");
        System.out.println("Total hours needed: " + totalHours);
        System.out.println("Total hours completed: " + hoursLearning);
        System.out.println("Percentage done: %" + ((int) (100 * (totalHours / hoursLearning))));
        System.out.println("What do you to do now?");
        int choiceIndex = GetInput.selectOption(choices);
        if (choiceIndex == 0){
            coursesMap.clear();
            totalHours = 0;
            hoursNeededPerWeek = 0;
        } else {
            totalHours = totalHoursLeft();
        }
        hoursLearning = 0;
        dateOfTarget = GetInput.getTargetDate();
    }
    private void getCourses(String coursesLine){
        String[] courses = coursesLine.split(" - ");
        String name;
        double hours;
        for (String course : courses){
            String[] info = course.split(":");
            name = info[0];
            hours = Double.parseDouble(info[1]);
            coursesMap.put(name, hours);
        }
    }
    public void changeTargetDate(LocalDate dateOfTarget){
        this.dateOfTarget = dateOfTarget;
        weeksUntilOver = getWeeksUntilOver(dateOfTarget.toString());
    }
    public void addCourse(String courseName, double courseHours){
        totalHours += courseHours;
        totalHoursLeft += courseHours;
        coursesMap.put(courseName, courseHours);
        hoursNeededPerWeek = totalHoursLeft / weeksUntilOver;
    }
    public void removeCourse(String courseName){
        coursesMap.remove(courseName);
    }
    public void editCourse(String courseName, double hoursSpent){
        double hoursLeft = coursesMap.get(courseName) - hoursSpent;
        hoursLearning += hoursSpent;
        totalHoursLeft -= hoursSpent;
        hoursLearningForTheWeek += hoursSpent;
        coursesMap.put(courseName, hoursLeft);

        if (hoursLeft <= 0){
            removeCourse(courseName);
        }
    }
    private double totalHoursLeft(){
        Set<String> courses = coursesMap.keySet();
        double totalHours = 0;

        for (String course : courses){
            totalHours += coursesMap.get(course);
        }
        return totalHours;
    }
    private int getWeeksUntilOver(String targetDate){
        LocalDate dateInFuture = formatDate(targetDate);
        LocalDate currentDate = LocalDate.now();
        dateOfTarget = dateInFuture;
        return (int) ChronoUnit.WEEKS.between(currentDate, dateInFuture);
    }
    public void displayInfo() {
        Set<String> courses = coursesMap.keySet();
        double courseHours;
        double timeLeft = hoursNeededPerWeek > hoursLearningForTheWeek ?
                hoursNeededPerWeek - hoursLearningForTheWeek : 0;
        int minutesLearning = (int) ((timeLeft - (int) timeLeft) * 60);

        System.out.println("Percent Done: %" + GetInput.round(((hoursLearning / totalHours) * 100)));
        System.out.println("Total Hours: " + GetInput.round(totalHours));
        System.out.println("Hours Spent Learning: " + GetInput.round(hoursLearning));
        System.out.println("Hours Needed Every Week: " + ((int) hoursNeededPerWeek + 1));
        System.out.println("Hours Left For The Week: " + ((int) timeLeft) + " hours and " +
                minutesLearning + " minutes");
        System.out.println();
        for (String course : courses) {
            courseHours = coursesMap.get(course);
            System.out.println(course + ": " + (int) courseHours + " hours left");
        }
    }
    public void writeInfo(){
        String FILE_NAME = "src\\goalsDB.txt";
        Path pathToFile = Path.of(System.getProperty("user.dir"), FILE_NAME);

        Set<String> coursesSet = coursesMap.keySet();
        String[] coursesArr = new String[coursesSet.size()];

        int i = 0;
        for (String course : coursesSet){
            coursesArr[i] = course + ":" + coursesMap.get(course);
            i ++;
        }

        String coursesText = String.join(" - ", coursesArr);

        try {
            FileWriter fileWriter = new FileWriter(String.valueOf(pathToFile));
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(totalHours);
            printWriter.println(hoursLearningForTheWeek);
            printWriter.println(dateOfTarget);
            printWriter.println(dateOfLastMonday);
            printWriter.println(coursesText);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }
    public void resetGoals(){
        totalHours = totalHoursLeft();
        hoursLearning = 0;
        hoursLearningForTheWeek = 0;
    }
}
