package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ZadatakLayout extends AppCompatActivity {


    int  dugmeFlag = 0;
    int DateYear;
    int DateMonth;
    int DateDay;
    int TimeHour;
    int TimeMinute;

    String zadatakImeString;
    String zadatakOpisString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadatak_layout);

        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        ImageButton kalendar = (ImageButton) findViewById(R.id.imageButton);
        final ImageButton crvenoDugme = (ImageButton) findViewById(R.id.imageButton2);
        final ImageButton zutoDugme = (ImageButton) findViewById(R.id.imageButton3);
        final ImageButton zelenoDugme = (ImageButton) findViewById(R.id.imageButton4);
        final EditText zadatakIme = (EditText) findViewById(R.id.editText);
        final EditText zadatakOpis = (EditText) findViewById(R.id.editText2);
        final Intent MainActivityIntent = new Intent(ZadatakLayout.this, MainActivity.class);
        final Bundle extras = getIntent().getExtras();


        if (extras != null) {
            DateYear = extras.getInt("DateYear");
            DateMonth = extras.getInt("DateMonth");
            DateDay = extras.getInt("DateDay");
            TimeHour = extras.getInt("TimeHour");
            TimeMinute = extras.getInt("TimeMinute");

            zadatakImeString = extras.getString("zadatakIme");
            zadatakOpisString = extras.getString("zadatakOpis");

            zadatakIme.setText(zadatakImeString , TextView.BufferType.EDITABLE);
            TextView prikazIzabranogDatuma = (TextView) findViewById(R.id.textView2);
            prikazIzabranogDatuma.setText("Izabrano vreme: " + Integer.toString(DateDay) + "." + Integer.toString(DateMonth + 1) + "." + Integer.toString(DateYear)
                                                             + "  " + Integer.toString(TimeHour) + ":" + Integer.toString(TimeMinute));
            zadatakOpis.setText(zadatakOpisString, TextView.BufferType.EDITABLE);
        }



        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori MainActivity
                if(zadatakIme.getText().toString().isEmpty() || zadatakOpis.getText().toString().isEmpty() || dugmeFlag == 0 || extras == null) {
                    //disable button
                    if(zadatakIme.getText().toString().isEmpty())
                        zadatakIme.setError("Unesite ime zadatka!");
                    if(zadatakOpis.getText().toString().isEmpty())
                        zadatakOpis.setError("Unesite opis zadatka!");
                }
                else{
                    ZadatakLayout.this.startActivity(MainActivityIntent);
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori MainActivity
                ZadatakLayout.this.startActivity(MainActivityIntent);
            }
        });
        kalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori DatePicker
                Intent Datum = new Intent(ZadatakLayout.this, KalendarLayout.class);

                if(zadatakIme.getText().length() != 0)
                   Datum.putExtra("zadatakIme", zadatakIme.getText().toString());
                if(zadatakOpis.getText().length() != 0)
                    Datum.putExtra("zadatakOpis", zadatakOpis.getText().toString());

                ZadatakLayout.this.startActivity(Datum);
            }
        });

        crvenoDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crvenoDugme.setImageResource(R.drawable.crvena);
                zutoDugme.setImageResource(R.drawable.siva);
                zelenoDugme.setImageResource(R.drawable.siva);
                dugmeFlag = 1;
            }
        });

        zutoDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crvenoDugme.setImageResource(R.drawable.siva);
                zutoDugme.setImageResource(R.drawable.zuta);
                zelenoDugme.setImageResource(R.drawable.siva);
                dugmeFlag = 2;
            }
        });

        zelenoDugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crvenoDugme.setImageResource(R.drawable.siva);
                zutoDugme.setImageResource(R.drawable.siva);
                zelenoDugme.setImageResource(R.drawable.zelena);
                dugmeFlag = 3;
            }
        });


    }
}
