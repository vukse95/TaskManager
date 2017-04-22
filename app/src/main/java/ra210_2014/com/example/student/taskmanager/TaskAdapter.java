package ra210_2014.com.example.student.taskmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskAdapter extends BaseAdapter {

    //fali holder
    private Context context;
    private ArrayList<TaskModel> tasks;

    public TaskAdapter(Context context) {
        //super(context, R.layout.task_element, item);
        this.context = context;
        this.tasks = new ArrayList<>();
    }

    public void addTask(TaskModel taskModel){
        tasks.add(taskModel);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;

        try{
            rv = tasks.get(position);
        }catch (IndexOutOfBoundsException e){
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

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.task_element, null);
            ViewHolder holder = new ViewHolder();
            holder.nazivZadatka = (TextView) view.findViewById(R.id.title);
            holder.datum = (TextView) view.findViewById(R.id.date);
            holder.podsetnik = (CheckBox) view.findViewById(R.id.reminder);
            holder.prioritet =  view.findViewById(R.id.priority);
            view.setTag(holder);
        }

        //ovde budzim za boju i ostalo

        TaskModel tModel = (TaskModel) getItem(position);
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.nazivZadatka.setText(tModel.nameOfAssignment);
        //promeniti da postoji juce, danas, sutra, itd...
        holder.datum.setText(tModel.day + "." + tModel.month + "." + tModel.year);
        holder.podsetnik.setChecked(tModel.reminder);
        //promeniti da na osnovu priorityFlag menja jednu od 3 boja
        switch(tModel.priorityFlag){
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


        return view;
    }

    private class ViewHolder {
        public TextView nazivZadatka = null;
        public TextView datum = null;
        public CheckBox podsetnik = null;
        public View  prioritet = null;
    }


}
