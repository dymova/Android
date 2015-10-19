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
    private SQLiteDatabase db;

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
        tasksList = (ListView) findViewById(R.id.tasks_list);
        ArrayList<String> items = new ArrayList<>();
        Task task = cursor.getTask();
        Log.d("ASSERT", String.valueOf(task == null));
        while(task != null) {
            items.add(task.getName());
            task = cursor.getTask();
        }

//        String[] items = new String[20];
//        for (int i = 0; i < 20; i++) {
//            items[i] = "intem [" + i + "]";
//        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        tasksList.setAdapter(adapter);
//        String[] projection = {
//                PlanerDbContract.Tasks._ID,
//                PlanerDbContract.Tasks.COLUMN_NAME_TASK_NAME,
//                PlanerDbContract.Tasks.COLUMN_NAME_TASK_TYPE
//        };
//
//        String sortOrder =
//                PlanerDbContract.Tasks.COLUMN_NAME_TASK_NAME + " DESC";
//
//        Cursor cursor = db.query(
//                PlanerDbContract.Tasks.TABLE_NAME,  // The table to query
//                projection,                               // The columns to return
//                null,                                // The columns for the WHERE clause
//                null,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );
//
//        cursor.moveToFirst();
//        long itemId = cursor.getLong(
//                cursor.getColumnIndexOrThrow(PlanerDbContract.Tasks._ID)
//        );



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
