package ra210_2014.com.example.student.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatistikaLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistika_layout);

        PieChart pieChart1 = (PieChart) findViewById(R.id.pie_chart1);
        PieChart pieChart2 = (PieChart) findViewById(R.id.pie_chart2);
        PieChart pieChart3 = (PieChart) findViewById(R.id.pie_chart3);

        Button backButton = (Button) findViewById(R.id.backButton);

        float[] datapoints1 = {75, 35};
        float[] datapoints2= {45, 55};
        float[] datapoints3 = {10, 90};

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //privremeno
                Intent noviZadatak = new Intent(StatistikaLayout.this, MainActivity.class);
                StatistikaLayout.this.startActivity(noviZadatak);
            }
        });


        pieChart1.setDataPoints(datapoints1, 1);
        pieChart2.setDataPoints(datapoints2, 2);
        pieChart3.setDataPoints(datapoints3, 3);
    }
}
