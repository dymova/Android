package ru.nsu.ccfit.dymova.planner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlannerDbHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Planner.sqlite";
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_NAME_TASK_NAME = "task_name";
    public static final String COLUMN_NAME_TASK_TYPE = "task_type";
    public static final String COLUMN_NAME_TASK_DESCRIPTION = "task_description";



    public PlannerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
//        System.out.println(PlanerDbContract.SQL_CREATE_ENTRIES);
//        db.execSQL(PlanerDbContract.SQL_CREATE_ENTRIES);
        db.execSQL("create table tasks (" +
                        "_id integer primary key autoincrement, " +
                        "task_name varchar(20), " +
                        "task_type varchar(30), " +
                        "task_description varchar(200))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PlanerDbContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long insertTask(Task task) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_TASK_NAME, task.getName());
        cv.put(COLUMN_NAME_TASK_TYPE, task.getType());
        cv.put(COLUMN_NAME_TASK_DESCRIPTION, task.getDescription());
        return getWritableDatabase().insert(TABLE_NAME, null, cv);
    }

    public int deleteTask(long id) {
        return getWritableDatabase().delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
    }

    public TaskCursor queryTasks() {
        Cursor wrapper = getReadableDatabase().query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME_TASK_TYPE + " asc");
        Log.d("qwert", String.valueOf(wrapper.getCount()));
        wrapper.moveToFirst();
        return new TaskCursor(wrapper);
    }

    public static class TaskCursor extends CursorWrapper {

        public TaskCursor(Cursor cursor) {
            super(cursor);
        }

        public Task getTask() {
            if(isBeforeFirst() || isAfterLast()) {
                return null;
            }
            long id = getLong(getColumnIndex(PlanerDbContract.Tasks._ID));
            String name = getString(getColumnIndex(COLUMN_NAME_TASK_NAME));
            String type = getString(getColumnIndex(COLUMN_NAME_TASK_TYPE));
            String description = getString(getColumnIndex(COLUMN_NAME_TASK_DESCRIPTION));
            Log.d("task", name + type + description);
            moveToNext();
            Task task = new Task(name, type, description);
            task.setId(id);
            return task;
        }
    }

}
