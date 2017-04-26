package ra210_2014.com.example.student.taskmanager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskAdapter extends BaseAdapter {

    //fali holder
    private Context context;
    private ArrayList<TaskModel> tasks;
    Calendar c = Calendar.getInstance();
    private String nameOfAssignment;

    public TaskAdapter(Context context) {
        //super(context, R.layout.task_element, item);
        this.context = context;
        this.tasks = new ArrayList<>();
    }

    public void addTask(TaskModel taskModel) {
        tasks.add(taskModel);
        notifyDataSetChanged();
    }

    public void removeTask(TaskModel taskModel) {
        tasks.remove(taskModel);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;

        try {
            rv = tasks.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return rv;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        //ne dozvoljava direktnu komparaciju??
        int CurrentDay = c.get(Calendar.DAY_OF_MONTH);
        int CurrentMont = c.get(Calendar.MONTH);
        int CurrentYear = c.get(Calendar.YEAR);

        CurrentMont += 1;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_element, null);
            ViewHolder holder = new ViewHolder();
            holder.nazivZadatka = (TextView) view.findViewById(R.id.title);
            holder.datum = (TextView) view.findViewById(R.id.date);
            holder.podsetnik = (CheckBox) view.findViewById(R.id.reminder);
            holder.prioritet = view.findViewById(R.id.priority);
            holder.podsetnik2 = (RadioButton) view.findViewById(R.id.reminder1);
            view.setTag(holder);
        }

        //ovde budzim za boju i ostalo

        TaskModel tModel = (TaskModel) getItem(position);
        final ViewHolder holder = (ViewHolder) view.getTag();

        holder.nazivZadatka.setText(tModel.nameOfAssignment);

        Log.d("DATUM", "----------------------------");
        Log.d("DATUM", "tModel.day " + tModel.day);
        Log.d("DATUM", "tModel.month " + tModel.month);
        Log.d("DATUM", "tModel.year " + tModel.year);
        Log.d("DATUM", "CurrentDay " + CurrentDay);
        Log.d("DATUM", "CurrentMont " + CurrentMont);
        Log.d("DATUM", "CurrentYear " + CurrentYear);
        Log.d("DATUM", "----------------------------");

        if (tModel.year == CurrentYear && tModel.month == CurrentMont) {
            if (tModel.day - CurrentDay <= 7) {
                if (tModel.day == CurrentDay) {
                    holder.datum.setText(context.getResources().getString(R.string.Today));
                } else if (CurrentDay + 1 == tModel.day) {
                    holder.datum.setText(context.getResources().getString(R.string.Tomorrow));
                } else if (tModel.day - CurrentDay >= 2 && tModel.day - CurrentDay <= 7) {
                    //Konvertujemo u Date format
                    String dateString = String.format("%d-%d-%d", tModel.year, tModel.month, tModel.day);
                    Date date = null;
                    try {
                        date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    //Uzmemo dan
                    String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
                    holder.datum.setText(dayOfWeek);

                    switch (dayOfWeek) {
                        case "Monday":
                            holder.datum.setText(context.getResources().getString(R.string.Monday));
                            break;
                        case "Tuesday":
                            holder.datum.setText(context.getResources().getString(R.string.Tuesday));
                            break;
                        case "Wednesday":
                            holder.datum.setText(context.getResources().getString(R.string.Wednesday));
                            break;
                        case "Thursday":
                            holder.datum.setText(context.getResources().getString(R.string.Thursday));
                            break;
                        case "Friday":
                            holder.datum.setText(context.getResources().getString(R.string.Friday));
                            break;
                        case "Saturday":
                            holder.datum.setText(context.getResources().getString(R.string.Saturday));
                            break;
                        case "Sunday":
                            holder.datum.setText(context.getResources().getString(R.string.Sunday));
                    }
                }
            } else {
                holder.datum.setText(tModel.day + "." + tModel.month + "." + tModel.year);
            }
        } else {
            holder.datum.setText(tModel.day + "." + tModel.month + "." + tModel.year);
        }

        holder.podsetnik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.podsetnik.isChecked()) {
                    holder.nazivZadatka.setPaintFlags(holder.nazivZadatka.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.nazivZadatka.setPaintFlags(holder.nazivZadatka.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        switch (tModel.priorityFlag) {
            case 1:
                holder.prioritet.setBackgroundColor(Color.RED);
                break;
            case 2:
                holder.prioritet.setBackgroundColor(Color.YELLOW);
                break;
            case 3:
                holder.prioritet.setBackgroundColor(Color.GREEN);
                break;
        }

        if(tModel.reminder)
        {
            holder.podsetnik2.setChecked(true);

        }else{
            holder.podsetnik2.setChecked(false);
        }

        return view;
    }



    private class ViewHolder {
        public TextView nazivZadatka = null;
        public TextView datum = null;
        public CheckBox podsetnik = null;
        public RadioButton podsetnik2 = null;
        public View prioritet = null;
    }


}
