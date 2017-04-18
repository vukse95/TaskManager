package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    int  dugmeFlag;
    boolean reminder;
    int DateYear;
    int DateMonth;
    int DateDay;
    int TimeHour;
    int TimeMinute;

    String zadatakImeString;
    String zadatakOpisString;

    //TODO Gubi listu svaki put kada se otvori novi intent!!!!!!!!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        final Bundle extras = getIntent().getExtras();

        //View priorityColor = findViewById(R.id.priority);

        TaskAdapter adapter = new TaskAdapter(this);
        ListView list =(ListView) findViewById(R.id.list);

        if (extras != null){
            DateYear = extras.getInt("DateYear");
            DateMonth = extras.getInt("DateMonth");
            DateDay = extras.getInt("DateDay");
            TimeHour = extras.getInt("TimeHour");
            TimeMinute = extras.getInt("TimeMinute");

            zadatakImeString = extras.getString("zadatakIme");
            zadatakOpisString = extras.getString("zadatakOpis");

            dugmeFlag = extras.getInt("priority");
            reminder = extras.getBoolean("reminder");
            //public TaskModel(String nameOfAssignment, String assigment, int year, int month, int day, int hour, int minute, int priorityFlag, boolean reminder)
            adapter.addTask(new TaskModel(zadatakImeString, zadatakOpisString, DateYear, DateMonth
                                          , DateDay, TimeHour, TimeMinute, dugmeFlag, reminder));
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
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


        //ubaci test polja u listu
        adapter.addTask(new TaskModel("Kupi leba", "kupis leba u radnji bato", 2017, 6, 18
                                                 , 6, 56, 15, true));


        list.setAdapter(adapter);

    }
}
