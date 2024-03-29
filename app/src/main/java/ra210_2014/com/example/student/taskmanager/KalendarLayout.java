package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class KalendarLayout extends AppCompatActivity {

    String zadatakIme;
    String zadatakOpis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalendar_layout);

        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        final Intent noviZadatak = new Intent(KalendarLayout.this, ZadatakLayout.class);
        Intent in2 = getIntent();


        zadatakIme = in2.getStringExtra("zadatakIme");
        zadatakOpis = in2.getStringExtra("zadatakOpis");


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                //dodaj zastitu unusa
                //ne moze se dodati godina u nazad

                noviZadatak.putExtra("DateYear", datePicker.getYear());
                noviZadatak.putExtra("DateMonth", datePicker.getMonth() + 1);
                noviZadatak.putExtra("DateDay", datePicker.getDayOfMonth());
                noviZadatak.putExtra("TimeHour", timePicker.getHour());
                noviZadatak.putExtra("TimeMinute", timePicker.getMinute());
                Log.d("DATUM", "Kalendar Month " + datePicker.getMonth());
                noviZadatak.putExtra("zadatakIme", zadatakIme);
                noviZadatak.putExtra("zadatakOpis", zadatakOpis);

                noviZadatak.putExtra("calendar", 1);
                KalendarLayout.this.startActivity(noviZadatak);
                //setResult(RESULT_OK, noviZadatak);
                //finish();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori ZadatakLayout
                KalendarLayout.this.startActivity(noviZadatak);
            }
        });
    }

}
