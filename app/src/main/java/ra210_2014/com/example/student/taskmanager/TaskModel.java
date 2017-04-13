package ra210_2014.com.example.student.taskmanager;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskModel {
    private String nameOfAssignment;
    private String assigment;
    private int year, month, day;
    private int hour, minute;
    private int priorityFlag;
    private boolean reminder;

    public TaskModel(String nameOfAssignment, String assigment, int year, int month, int day, int hour, int minute, int priorityFlag, boolean reminder) {
        this.nameOfAssignment = nameOfAssignment;
        this.assigment = assigment;
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

    public String getAssigment() {
        return assigment;
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
}
