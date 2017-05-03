package ra210_2014.com.example.student.taskmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Vukse on 3.5.2017..
 */

public class PieChart extends View {
    private Paint slicePaint;
    private int[] sliceClrs = {Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
    private int sliceColor;
    private float[] datapoints; //Our values
    Paint paintText = new Paint();

    public PieChart(Context context, AttributeSet attrs)  {
        super(context, attrs);
        slicePaint = new Paint();
        slicePaint.setAntiAlias(true);
        slicePaint.setDither(true);
        slicePaint.setStyle(Paint.Style.FILL);
        paintText.setTextSize(60);
        paintText.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (this.datapoints != null) {
            int startTop = 0;
            int startLeft = 0;
            int endBottom = getWidth();
            int endRight = endBottom; // To make this an equal square
            // Create the box
            RectF rectf = new RectF(startLeft, startTop, endRight, endBottom); // Creating the box

            float[] scaledValues = scale(); // Get the scaled values
            float sliceStartPoint = 0;
            for (int i = 0; i < scaledValues.length; i++) {
                if(i <= 0)
                    slicePaint.setColor(sliceClrs[i]);
                else
                    slicePaint.setColor(sliceClrs[sliceColor]);

                canvas.drawArc(rectf, sliceStartPoint, scaledValues[i], true, slicePaint); // Draw slice
                sliceStartPoint += scaledValues[i]; // Update starting point of the next slice
                canvas.drawText(Float.toString(Math.round(datapoints[1])) + "%", getWidth()/2 - 75, getHeight()/2 + 20 , paintText);
            }
        }
    }
    public void setDataPoints(float[] dataPoints, int sliceColor) {
        this.datapoints = dataPoints;
        this.sliceColor = sliceColor;
        //Nacrtaj se sam :)
        invalidate();
    }

    private float[] scale() {
        float[] scaledValues = new float[this.datapoints.length];
        float total = getTotal(); // Total all values supplied to the chart
        for (int i = 0; i < this.datapoints.length; i++) {
            scaledValues[i] = (this.datapoints[i] / total) * 360; // Scale each value
        }
        return scaledValues;
    }

    private float getTotal() {
        float total = 0;
        for (float val : this.datapoints)
            total += val;
        return total;
    }
}
