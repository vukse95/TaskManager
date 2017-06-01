package ra210_2014.com.example.student.taskmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    int dugmeFlag;
    boolean reminder;
    int DateYear;
    int DateMonth;
    int DateDay;
    int TimeHour;
    int TimeMinute;

    String zadatakImeString;
    String zadatakOpisString;

    TaskAdapter adapter;
    ListView list;
    NotificationService mService;

    TaskModel[] tasks = null;
    boolean isBounded = false;
    TaskModel zadatak;


    private ServiceConnection cnnt = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            NotificationService.MyLocalBinder binder = (NotificationService.MyLocalBinder) service;
            mService = binder.getService();
            binder.getService();
            binder.setTasks(tasks);
            isBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBounded = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        final Bundle extras = getIntent().getExtras();


        adapter = new TaskAdapter(this);
        final TaskDatabase db = new TaskDatabase(this, "Tasks", null, 1);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        Intent i = new Intent(MainActivity.this, NotificationService.class);
        bindService(i, cnnt, BIND_AUTO_CREATE);

        //ako vrati da je novo dodaj u bazu i obnovi listu adaptera
        //ako je update pronadji u bazi zameni s novim i obnovi listu adaptera

        //TODO: Napuni adapter iz baze

        if (extras != null) {

            DateYear = extras.getInt("DateYear");
            DateMonth = extras.getInt("DateMonth");
            DateDay = extras.getInt("DateDay");
            TimeHour = extras.getInt("TimeHour");
            TimeMinute = extras.getInt("TimeMinute");
            zadatakImeString = extras.getString("zadatakIme");
            zadatakOpisString = extras.getString("zadatakOpis");
            dugmeFlag = extras.getInt("priority");
            reminder = extras.getBoolean("reminder");

            if (extras.getInt("new") == 1) {
                TaskModel tmp = new TaskModel(zadatakImeString, zadatakOpisString, DateYear, DateMonth
                        , DateDay, TimeHour, TimeMinute, dugmeFlag, reminder);
                db.insertTask(tmp);
                //adapter.addTask(tmp);
            } else if (extras.getInt("update") == 1) {
                //pretrazi bazu i edituj
                TaskModel tmp = new TaskModel(zadatakImeString, zadatakOpisString, DateYear, DateMonth
                        , DateDay, TimeHour, TimeMinute, dugmeFlag, reminder);

                //zadatak_oldTask, tmp_newTask
                //TODO:BUG!!!!!!!
                db.updateTask(zadatak, tmp);

            }
        }

        tasks = db.readTaskModel();

        if (tasks != null) {
            for (int j = 0; j < tasks.length; j++) {
                adapter.addTask(tasks[j]);
            }
        }




        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Assignment je null (ostali parametri ok), zasto?
                Log.d("LOG", "usao u LongClick");

                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
                zadatak = (TaskModel) list.getItemAtPosition(position);
                noviZadatak.putExtra("update", 1);
                noviZadatak.putExtra("DateYear", zadatak.getYear());
                noviZadatak.putExtra("DateMonth", zadatak.getMonth());
                noviZadatak.putExtra("DateDay", zadatak.getDay());
                noviZadatak.putExtra("TimeHour", zadatak.getHour());
                noviZadatak.putExtra("TimeMinute", zadatak.getMinute());
                noviZadatak.putExtra("zadatakIme", zadatak.getNameOfAssignment());
                noviZadatak.putExtra("zadatakOpis", zadatak.getAssignment());
                noviZadatak.putExtra("priority", zadatak.getPriorityFlag());
                noviZadatak.putExtra("priority", zadatak.isReminder());

                MainActivity.this.startActivity(noviZadatak);

                return true;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
                noviZadatak.putExtra("new", 1);
                MainActivity.this.startActivity(noviZadatak);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                Intent Statistika = new Intent(MainActivity.this, StatistikaLayout.class);

                MainActivity.this.startActivity(Statistika);
            }
        });

        list.setAdapter(adapter);

    }


/*
    @Override
    protected void onStop() {
        super.onStop();
        if (isBounded){
            unbindService(cnnt);
            isBounded = false;
        }

    }
*/
/*
    @Override
    protected void onStart() {
        super.onStart();
        Intent in = new Intent(this, NotificationService.class);
        bindService(in, cnnt, Context.BIND_AUTO_CREATE);
    }
    */
}
