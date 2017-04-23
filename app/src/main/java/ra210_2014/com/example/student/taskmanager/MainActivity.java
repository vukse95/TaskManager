package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;

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

    int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        final Bundle extras = getIntent().getExtras();

        //View priorityColor = findViewById(R.id.priority);

        adapter = new TaskAdapter(this);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
                TaskAdapter zadatak = (TaskAdapter) list.getItemAtPosition(position);
                noviZadatak.putExtra("update", (Serializable) zadatak);
                startActivityForResult(noviZadatak, REQUEST_CODE);

                return true;
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                Intent noviZadatak = new Intent(MainActivity.this, ZadatakLayout.class);
                //MainActivity.this.startActivity(noviZadatak);
                startActivityForResult(noviZadatak, REQUEST_CODE);
                //TODO:menjao!
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


        //list.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                DateYear = data.getIntExtra("DateYear", 0);
                DateMonth = data.getIntExtra("DateMonth", 0);
                DateDay = data.getIntExtra("DateDay", 0);
                TimeHour = data.getIntExtra("TimeHour", 0);
                TimeMinute = data.getIntExtra("TimeMinute", 0);

                zadatakImeString = data.getStringExtra("zadatakIme");
                zadatakOpisString = data.getStringExtra("zadatakOpis");

                dugmeFlag = data.getIntExtra("priority", 0);
                reminder = data.getBooleanExtra("reminder", false);
                //public TaskModel(String nameOfAssignment, String assigment, int year, int month, int day, int hour, int minute, int priorityFlag, boolean reminder)
                adapter.addTask(new TaskModel(zadatakImeString, zadatakOpisString, DateYear, DateMonth
                        , DateDay, TimeHour, TimeMinute, dugmeFlag, reminder));

            }
        }


    }
}
