package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
    int PriorityFlag;
    boolean Reminder;

    String zadatakImeString;
    String zadatakOpisString;

    final EditText zadatakIme = (EditText) findViewById(R.id.editText);
    final EditText zadatakOpis = (EditText) findViewById(R.id.editText2);

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

        final CheckBox reminder = (CheckBox) findViewById(R.id.checkBox);
        final Intent MainActivityIntent = new Intent(ZadatakLayout.this, MainActivity.class);
        final Bundle extras = getIntent().getExtras();
        Intent in2 = getIntent();

        if (extras != null) {
            if(extras.getString("update") != null){
                //update popuni iz liste, promeni button za na sacuvaj
                TaskModel task = (TaskModel) in2.getSerializableExtra("update");

                button3.setText("Sacuvaj");
                button4.setText("Otkazi");

                zadatakImeString = task.getNameOfAssignment();
                zadatakOpisString = task.getAssigment();
                DateYear = task.getYear();
                DateMonth =task.getMonth();
                DateDay = task.getDay();
                TimeHour = task.getHour();
                TimeMinute = task.getMinute();
                PriorityFlag = task.getPriorityFlag();
                Reminder = task.isReminder();

            }else{
                DateYear = extras.getInt("DateYear");
                DateMonth = extras.getInt("DateMonth");
                DateDay = extras.getInt("DateDay");
                TimeHour = extras.getInt("TimeHour");
                TimeMinute = extras.getInt("TimeMinute");

                zadatakImeString = extras.getString("zadatakIme");
                zadatakOpisString = extras.getString("zadatakOpis");

                zadatakIme.setText(zadatakImeString , TextView.BufferType.EDITABLE);
                TextView prikazIzabranogDatuma = (TextView) findViewById(R.id.textView2);
                prikazIzabranogDatuma.setText("Izabrano vreme: " + Integer.toString(DateDay) + "." + Integer.toString(DateMonth) + "." + Integer.toString(DateYear)
                        + "  " + Integer.toString(TimeHour) + ":" + Integer.toString(TimeMinute));
                zadatakOpis.setText(zadatakOpisString, TextView.BufferType.EDITABLE);
            }

        }
        //TOdo ako je vec uneo datum uzmi trenutni text iz polja


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
                    //dodaj u listu
                    //adapter.addTask(new TaskModel(zadatakIme.getText(), "kupis leba u radnji bato", 2017, 6, 18
                    //        , 6, 56, 15, true));
                    MainActivityIntent.putExtra("DateYear", DateYear);
                    MainActivityIntent.putExtra("DateMonth", DateMonth);
                    MainActivityIntent.putExtra("DateDay",  DateDay);
                    MainActivityIntent.putExtra("TimeHour", TimeHour);
                    MainActivityIntent.putExtra("TimeMinute", TimeMinute);

                    MainActivityIntent.putExtra("zadatakIme", zadatakImeString);
                    MainActivityIntent.putExtra("zadatakOpis", zadatakOpisString);

                    MainActivityIntent.putExtra("priority", dugmeFlag);
                    if(reminder.isChecked()){
                        MainActivityIntent.putExtra("reminder", true);
                    }else{
                        MainActivityIntent.putExtra("reminder", false);
                    }


                    //ZadatakLayout.this.startActivity(MainActivityIntent);
                    setResult(RESULT_OK, MainActivityIntent);
                    finish();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori MainActivity
                ZadatakLayout.this.startActivity(MainActivityIntent);
                startActivity(MainActivityIntent);
                finish();
            }
        });
        kalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //otvori DatePicker
                Intent Datum = new Intent(ZadatakLayout.this, KalendarLayout.class);


                Datum.putExtra("datumAccess", 2);
                if(zadatakIme.getText().length() != 0)
                   Datum.putExtra("zadatakIme", zadatakIme.getText().toString());
                if(zadatakOpis.getText().length() != 0)
                    Datum.putExtra("zadatakOpis", zadatakOpis.getText().toString());

                //ZadatakLayout.this.startActivity(Datum);
                startActivityForResult(Datum, 2);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("PROBLEM", "USAO");
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                DateYear = data.getIntExtra("DateYear", 0);
                DateMonth = data.getIntExtra("DateMonth", 0);
                DateDay = data.getIntExtra("DateDay", 0);
                TimeHour = data.getIntExtra("TimeHour", 0);
                TimeMinute = data.getIntExtra("TimeMinute", 0);

                zadatakImeString = data.getStringExtra("zadatakIme");
                zadatakOpisString = data.getStringExtra("zadatakOpis");

                dugmeFlag = data.getIntExtra("priority", 0);
                zadatakIme.setText(zadatakImeString , TextView.BufferType.EDITABLE);
                TextView prikazIzabranogDatuma = (TextView) findViewById(R.id.textView2);
                prikazIzabranogDatuma.setText("Izabrano vreme: " + Integer.toString(DateDay) + "." + Integer.toString(DateMonth) + "." + Integer.toString(DateYear)
                        + "  " + Integer.toString(TimeHour) + ":" + Integer.toString(TimeMinute));
                zadatakOpis.setText(zadatakOpisString, TextView.BufferType.EDITABLE);
            }
        }
    }
}
