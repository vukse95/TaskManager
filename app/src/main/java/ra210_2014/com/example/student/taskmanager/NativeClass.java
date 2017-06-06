package ra210_2014.com.example.student.taskmanager;

/**
 * Created by Vukse on 6.6.2017.
 */

public class NativeClass {
    public native float percentageCalculation(float done,float sum);

    static {
        System.loadLibrary("precentage");
    }
}
