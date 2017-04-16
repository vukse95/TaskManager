package ra210_2014.com.example.student.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskAdapter extends BaseAdapter {

    //fali holder
    ArrayList<TaskModel> tasks;
    private final Context context;

    public TaskAdapter(Context context) {
        //super(context, R.layout.task_element, item);
        this.context = context;
        this.tasks = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.task_element, null);

        TaskModel tasks = (TaskModel) getItem(position);
        return convertView;
    }
}
