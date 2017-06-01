package ra210_2014.com.example.student.taskmanager;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.ViewDebug;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.LogRecord;

/**
 * Created by stefa on 5/29/2017.
 */

public class NotificationService extends Service {
    private TaskModel[] tasks = null;
    private final IBinder myBinder = new MyLocalBinder();
    NotificationManager mng;
    NotificationCompat.Builder builder = null;
    Calendar calender = Calendar.getInstance();
    int Day = calender.get(Calendar.DAY_OF_MONTH);
    int Month = calender.get(Calendar.MONTH);
    int Year = calender.get(Calendar.YEAR);
    int Hour = calender.get(Calendar.HOUR);
    int Min = calender.get(Calendar.MINUTE);



    public NotificationService() {
        Log.d("LOG", String.valueOf(Day));
        Log.d("LOG", String.valueOf(Month));
        Log.d("LOG", String.valueOf(Year));
        Log.d("LOG", String.valueOf(Hour));
        Log.d("LOG", String.valueOf(Min));

        final Handler handler = new Handler();
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                if (tasks != null) {
                    for (TaskModel i : tasks) {
                        if (i.isReminder()) {
                            if (i.getDay() == Day && i.getMonth() == Month && i.getYear() == Year
                                    && i.getHour() == Hour){
                                    if (i.getMinute() - Min <= 15 && i.getMinute() - Min >= 0){
                                        mng = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        builder = new NotificationCompat.Builder(NotificationService.this)
                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                .setContentText("15 min to isteka taska!!")
                                                .setContentTitle("TaskManager");
                                    }
                            }
                        }
                    }
                }
            }
        };
        thread.run();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("BIND", "onBind: USAOBATO");
        return myBinder;

    }


    public class MyLocalBinder extends Binder {
        NotificationService getService() {
            return NotificationService.this;
        }

        public void setTasks(TaskModel[] lista) {
            tasks = lista;
        }
    }
}

