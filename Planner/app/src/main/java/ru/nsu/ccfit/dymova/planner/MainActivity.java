package ru.nsu.ccfit.dymova.planner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ListView tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initToolbar();
        initFloatingActionButton();
        fillingTasksList();

    }

    private void fillingTasksList() {
        tasksList = (ListView) findViewById(R.id.tasks_list);
//        todo
        String[] items = new String[20];
        for (int i = 0; i < 20; i++) {
            items[i] = "intem [" + i + "]";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        tasksList.setAdapter(adapter);
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
