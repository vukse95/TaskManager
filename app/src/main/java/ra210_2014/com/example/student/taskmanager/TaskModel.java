package ra210_2014.com.example.student.taskmanager;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskModel {
    private String nameOfAssignment;
    private String assignment;
    private int year, month, day;
    private int hour, minute;
    private int priorityFlag;
    private boolean reminder;


    public TaskModel(String nameOfAssignment, String assignment, int year, int month, int day
                                            , int hour, int minute, int priorityFlag, boolean reminder) {
        this.nameOfAssignment = nameOfAssignment;
        this.assignment = assignment;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.priorityFlag = priorityFlag;
        this.reminder = reminder;
    }

    public String getNameOfAssignment() {
        return nameOfAssignment;
    }

    public String getAssignment() {
        return assignment;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getPriorityFlag() {
        return priorityFlag;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setNameOfAssignment(String nameOfAssignment) {
        this.nameOfAssignment = nameOfAssignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setPriorityFlag(int priorityFlag) {
        this.priorityFlag = priorityFlag;
    }

    public void setReminder(boolean reminder) {
        this.reminder = reminder;
    }
}
