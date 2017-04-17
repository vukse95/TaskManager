package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        ListView lv;

        //samo privremeno
        TaskModel test1, test2;


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

        TaskAdapter adapter = new TaskAdapter(this);
        //ubaci test polja u listu
        adapter.addTask(new TaskModel("Kupi leba", "kupis leba u radnji bato", 2017, 6, 18
                                                 , 6, 56, 15, true));

        ListView list =(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

    }
}
