package ru.nsu.ccfit.dymova.planner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView tasksList;
    private PlannerDbHelper dbHelper;
//    private SQLiteDatabase db;

    private PlannerDbHelper.TaskCursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new PlannerDbHelper(getApplicationContext());
        cursor = dbHelper.queryTasks();

        initToolbar();
        initFloatingActionButton();
        fillingTasksList();

    }


    private void fillingTasksList() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = cursor.getTask();
        while(task != null) {
            tasks.add(task);
            task = cursor.getTask();
        }

        TaskAdapter adapter = new TaskAdapter(tasks, this, dbHelper);
        tasksList = (ListView) findViewById(R.id.tasks_list);
        tasksList.setAdapter(adapter);
//        ArrayList<String> items = new ArrayList<>();
//        Task task = cursor.getTask();
//        while(task != null) {
//            items.add(task.getName());
//            task = cursor.getTask();
//        }

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, items);

    }

    private void initFloatingActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewActivity(v);
            }
        });
    }

    public void goToNewActivity(View v) {
        Intent intent = new Intent(this, TaskDescriptionActivity.class);
        startActivity(intent);
    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setTitle(R.string.app_name);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return false;
                }
            });
            toolbar.inflateMenu(R.menu.menu);
        }
    }



}
