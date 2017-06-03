package ra210_2014.com.example.student.taskmanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.LogRecord;

/**
 * Created by stefa on 5/29/2017.
 */

public class NotificationService extends Service {
    private ArrayList<TaskModel> tasks = null;
    private final IBinder myBinder = new MyLocalBinder();
    NotificationManager mng;
    NotificationCompat.Builder builder = null;
    SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm");
    Calendar calender = Calendar.getInstance();
    int Day = calender.get(Calendar.DAY_OF_MONTH);
    int Month = calender.get(Calendar.MONTH) + 1;
    int Year = calender.get(Calendar.YEAR);
    int Hour = calender.get(Calendar.HOUR);
    int Min = calender.get(Calendar.MINUTE);
    int id = 0;
    public String TAG = "service";
    String time_now;


    public NotificationService() {
        Log.d(TAG, String.valueOf(Day));
        Log.d(TAG, String.valueOf(Month));
        Log.d(TAG, String.valueOf(Year));
        Log.d(TAG, String.valueOf(Hour));
        Log.d(TAG, String.valueOf(Min));

        final Handler handler = new Handler();
        final Runnable thread = new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "run: try");
                    if (tasks != null) {
                        Log.d(TAG, "run: ima tasks");
                        for (TaskModel i : tasks) {
                            if (i.isReminder()) {
                                Log.d(TAG, "run: isReminder");
                                time_now = sdf_time.format(calender.getTime());
                                if (i.getDay() == Day && i.getMonth() == Month && i.getYear() == Year) {
                                    Log.d(TAG, "run: datum");
                                    int hour_now = Integer.parseInt(time_now.substring(0, 2));
                                    int min_now = Integer.parseInt(time_now.substring(3, 5));
                                    long milli = hour_now * 60 * 60 * 1000 + min_now * 60 * 1000;
                                    long task_milli = i.getHour() * 60 * 60 * 1000 + i.getMinute() * 60 * 1000;
                                    if (task_milli - milli < 16 * 60 * 1000) {
                                        Log.d(TAG, "run: usao u 15 min");
                                        mng = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                        builder = new NotificationCompat.Builder(NotificationService.this)
                                                .setSmallIcon(R.mipmap.ic_launcher)
                                                .setContentText("15 min to isteka taska!!")
                                                .setContentTitle("TaskManager");

                                        Intent intent = new Intent(NotificationService.this, MainActivity.class);
                                        PendingIntent pi = PendingIntent.getActivity(NotificationService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                                        builder.setContentIntent(pi);

                                        mng.notify(id, builder.build());
                                        id++;
                                        i.setReminder(false);
                                    }
                                }
                            }
                        }
                    }
                } finally {
                    handler.postDelayed(this,6000);
                    //handler.postDelayed(this,60000);
                }
            }
        }; thread.run();

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

        public void setTasks(ArrayList<TaskModel> lista) {
            tasks = lista;
        }
    }
}

