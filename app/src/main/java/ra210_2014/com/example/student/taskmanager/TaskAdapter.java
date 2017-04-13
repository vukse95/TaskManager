package ra210_2014.com.example.student.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Vukse on 13.4.2017..
 */

public class TaskAdapter extends BaseAdapter {

    private final Context context;
    private final TaskModel item;

    public TaskAdapter(Context context, TaskModel item) {
        //super(context, R.layout.task_element, item);
        this.context = context;
        this.item = item;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_element, parent, false);
        return rowView;
    }
}
