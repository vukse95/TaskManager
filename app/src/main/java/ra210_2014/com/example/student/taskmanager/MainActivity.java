package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        final Bundle extras = getIntent().getExtras();

        //View priorityColor = findViewById(R.id.priority);

        adapter = new TaskAdapter(this);
        TaskDatabase db = new TaskDatabase(this, "Tasks", null, 1);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        TaskModel[] tasks;

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
                adapter.addTask(tmp);
            } else if (extras.getInt("update") == 1) {
                //pretrazi bazu i edituj

            }
        }


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Odraditi do kraja onLongClick, izmeniti ZadatakLayout
                Log.d("LOG", "usao u LongClick");

                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
                noviZadatak.putExtra("update", 1);

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

        //TODO: Resi bug ako je baza prazna
        db.insertTask(new TaskModel("Kupi leba", "kupis leba u radnji bato", 2019, 4, 25, 6, 56, 2, true));
        //ubaci test polja u listu
        tasks = db.readTaskModel();
        //if(tasks.isEmpty())

        //adapter.addTask(new TaskModel("Kupi leba", "kupis leba u radnji bato", 2017, 4, 25, 6, 56, 2, true));

        if (db.isNotEmpty()) {
            for (int i = 0; i < tasks.length; i++) {
                adapter.addTask(tasks[i]);
            }
        }


        list.setAdapter(adapter);

    }

}
