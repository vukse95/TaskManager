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

        TaskDatabase db = new TaskDatabase(StatistikaLayout.this);
        TaskModel[] tasks = null;
        float greenPriority = 0;
        float yellowPriority = 0;
        float redPriority = 0;

        int greenPriorityPercentage = 0;
        int yellowPriorityPercentage = 0;
        int redPriorityPercentage = 0;

        tasks = db.readTaskModel();

        if(tasks != null){
            for (int j = 0; j < tasks.length; j++) {
                switch (tasks[j].getPriorityFlag())
                {
                    case 1:
                        greenPriority++;
                        break;
                    case 2:
                        redPriority++;
                        break;
                    case 3:
                        yellowPriority++;
                        break;
                }
            }
        }

        greenPriorityPercentage = Math.round((greenPriority / tasks.length) * 100);
        yellowPriorityPercentage = Math.round((yellowPriority / tasks.length) * 100);
        redPriorityPercentage = Math.round((redPriority / tasks.length) * 100);

        float[] datapoints1 = {100 - greenPriorityPercentage, greenPriorityPercentage};
        float[] datapoints2= {100 - yellowPriorityPercentage, yellowPriorityPercentage};
        float[] datapoints3 = {100 - redPriorityPercentage, redPriorityPercentage};

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //privremeno
                Intent noviZadatak = new Intent(StatistikaLayout.this, MainActivity.class);
                StatistikaLayout.this.startActivity(noviZadatak);
            }
        });

        pieChart1.setDataPoints(datapoints1, 1);
        pieChart1.animation();
        pieChart2.setDataPoints(datapoints2, 2);
        pieChart2.animation();
        pieChart3.setDataPoints(datapoints3, 3);
        pieChart3.animation();
    }
}
