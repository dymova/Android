package ru.nsu.ccfit.dymova.planner;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter{
    private static final String IMPORTANT_EMERGENCY = "Важное, срочное";
    private static final String NOT_IMPORTANT_EMERGENCY = "Не важное, срочное";
    private static final String IMPORTANT_NOT_EMERGENCY = "Важное,  не срочное";
    private static final String NOT_IMPORTANT_NOT_EMERGENCY = "Не важное, не срочное";

    private ArrayList<Task> tasks;
    private Context context;
    private LayoutInflater lInflater;
    private PlannerDbHelper dbHelper;
    private View color;

    public TaskAdapter(ArrayList<Task> tasks, Context context, PlannerDbHelper dbHelper) {
        this.tasks = tasks;
        this.context = context;
        this.dbHelper = dbHelper;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.task_item, parent, false);
        }

        final Task task = (Task) getItem(position);

        TextView textView = (TextView)view.findViewById(R.id.text_task_name);
        textView.setText(task != null ? task.getName() : "No name");
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        setColor(task, view);

        ImageButton button = (ImageButton) view.findViewById(R.id.button_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task != null) {
                    long id = task.getId();
                    int t = dbHelper.deleteTask(task.getId());
                    Log.d("delete", String.valueOf(t) +  id);
                }
                tasks.remove(task);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public void setColor(Task task, View view) {
//        switch(task.getType()) {
//            case IMPORTANT_EMERGENCY:
//                view.setBackgroundColor(Color.parseColor("240128128"));
////                view.setBackgroundColor(Color.parseColor("fd7c6e"));
//                break;
//            case IMPORTANT_NOT_EMERGENCY:
////                view.setBackgroundColor(Color.parseColor("ffa474"));
//                break;
//            case NOT_IMPORTANT_EMERGENCY:
////                view.setBackgroundColor();
//                break;
//            case NOT_IMPORTANT_NOT_EMERGENCY:
////                view.setBackgroundColor(Color.parseColor("#33ff66"));
//                break;
//            default:
//                break;
//        }
    }
}
