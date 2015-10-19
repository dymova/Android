package ru.nsu.ccfit.dymova.planner;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskDescriptionActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private PlannerDbHelper dbHelper;
    private SQLiteDatabase db;
    private TextView taskName;
    private Spinner taskType;
    private TextView taskDescription;
    private Button saveButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);

        initToolbar();
        taskName = (TextView) findViewById(R.id.edit_task_name);
        taskType = (Spinner) findViewById(R.id.spinner_task_type);
        taskDescription = (TextView) findViewById(R.id.edit_task_description);

        saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask(new Task(taskName.getText().toString(),
                        taskType.getSelectedItem().toString(),
                        taskDescription.getText().toString()));
                check();
                goToHome();
            }


        });


        dbHelper = new PlannerDbHelper(getBaseContext());

    }

    private void check() {
        PlannerDbHelper.TaskCursor cursor = dbHelper.queryTasks();
        Log.d("check", String.valueOf(cursor.getCount()));
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_new_task);
        toolbar.setTitleTextColor(Color.WHITE);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goToHome();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveTask(Task task) {
        dbHelper.insertTask(task);
//
//        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select * from tasks", null);
//        if (cursor.moveToFirst()){
//            do{
//                String data = cursor.getString(cursor.getColumnIndex("data"));
//                // do what ever you want here
//            }while(cursor.moveToNext());
//        }
//        cursor.close();
//        long newRowId;
//        newRowId = db.insert(
//                PlanerDbContract.Tasks.TABLE_NAME,
//                "null",
//                values);

    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
