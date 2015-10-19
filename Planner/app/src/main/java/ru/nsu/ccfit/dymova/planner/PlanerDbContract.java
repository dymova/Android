package ru.nsu.ccfit.dymova.planner;

import android.provider.BaseColumns;

public class PlanerDbContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Tasks.TABLE_NAME + " (" +
                    Tasks._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Tasks.COLUMN_NAME_TASK_NAME + TEXT_TYPE + COMMA_SEP +
                    Tasks.COLUMN_NAME_TASK_TYPE + TEXT_TYPE + COMMA_SEP +
                    Tasks.COLUMN_NAME_TASK_DESCRIPTION + TEXT_TYPE +
            " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Tasks.TABLE_NAME;

    public static abstract class Tasks implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TASK_NAME = "task_name";
        public static final String COLUMN_NAME_TASK_TYPE = "task_type";
        public static final String COLUMN_NAME_TASK_DESCRIPTION = "task_description";
    }

}
