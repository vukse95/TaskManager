package ra210_2014.com.example.student.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vukse on 28.5.2017..
 */

public class TaskDatabase extends SQLiteOpenHelper {

    public static String TASK_DB = "Tasks";
    public static String TASK_NAME = "NameOfAssignment";
    public static String TASK_DESCRIPTION = "Assignment";
    public static String TASK_TIME_YEAR = "Year";
    public static String TASK_TIME_MONTH = "Month";
    public static String TASK_TIME_DAY = "Day";
    public static String TASK_TIME_HOUR = "Hour";
    public static String TASK_TIME_MINUTE = "Minute";
    public static String TASK_TIME_FLAG = "PriorityFlag";
    public static String TASK_TIME_REMINDER = "Reminder";

    public TaskDatabase(Context context) {
        super(context, TASK_DB, null, 1);
    }

    public TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TASK_DB +"(" + TASK_NAME + " TEXT, " + TASK_DESCRIPTION + " TEXT," + TASK_TIME_YEAR + " INTEGER, " +
                TASK_TIME_MONTH + " INTEGER,"+ TASK_TIME_DAY +" INTEGER,"+ TASK_TIME_HOUR + " INTEGER,"+ TASK_TIME_MINUTE +" INTEGER,"+
                TASK_TIME_FLAG +" INTEGER," + TASK_TIME_REMINDER +" INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTask(TaskModel taskModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TASK_NAME, taskModel.getNameOfAssignment());
        contentValues.put(TASK_DESCRIPTION, taskModel.getAssignment());
        contentValues.put(TASK_TIME_YEAR, taskModel.getYear());
        contentValues.put(TASK_TIME_MONTH, taskModel.getMonth());
        contentValues.put(TASK_TIME_DAY, taskModel.getDay());
        contentValues.put(TASK_TIME_HOUR, taskModel.getHour());
        contentValues.put(TASK_TIME_MINUTE, taskModel.getMinute());
        contentValues.put(TASK_TIME_FLAG, taskModel.getPriorityFlag());

        //isReminder vraca bool
        if (taskModel.isReminder()) {
            contentValues.put(TASK_TIME_REMINDER, 1);
        } else {
            contentValues.put(TASK_TIME_REMINDER, 0);
        }


        db.insert(TASK_DB, null, contentValues);
        db.close();
    }

    public void updateTask(TaskModel oldTask, TaskModel  newTask){
        //TODO:update

        /*
        UPDATE
                Customers
        SET
                ContactName = 'Alfred Schmidt',
                City        = 'Frankfurt'
        WHERE
                CustomerID = 1
        AND
                ContactName = 'Stefan'
       */
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //u slucaju greske
        if(newTask.getAssignment() == null)
        {
            newTask.setAssignment("Prazno!");
        }
        if(newTask.getNameOfAssignment() == null)
        {
            newTask.setNameOfAssignment("Prazno");
        }
        if (oldTask.getAssignment() == null)
        {
            oldTask.setAssignment("Prazno!");
        }
        if(oldTask.getNameOfAssignment() == null)
        {
            oldTask.setNameOfAssignment("Prazno");
        }

        contentValues.put(TASK_NAME, newTask.getNameOfAssignment());
        contentValues.put(TASK_DESCRIPTION, newTask.getAssignment());
        contentValues.put(TASK_TIME_YEAR, newTask.getYear());
        contentValues.put(TASK_TIME_MONTH, newTask.getMonth());
        contentValues.put(TASK_TIME_DAY, newTask.getDay());
        contentValues.put(TASK_TIME_HOUR, newTask.getHour());
        contentValues.put(TASK_TIME_MINUTE, newTask.getMinute());
        contentValues.put(TASK_TIME_FLAG, newTask.getPriorityFlag());

        //isReminder vraca bool
        if (newTask.isReminder()) {
            contentValues.put(TASK_TIME_REMINDER, 1);
        } else {
            contentValues.put(TASK_TIME_REMINDER, 0);
        }

        //update bug-uje, resiti rucno!
        db.update(TASK_DB, contentValues,"" + TASK_NAME + "= '" + oldTask.getNameOfAssignment() +"' AND " + TASK_DESCRIPTION + "= '" + oldTask.getAssignment() , null);
        db.close();
    }

    public TaskModel[] readTaskModel() {

        boolean ReminderTmp;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Tasks", null, null, null, null, null, null);

        if (cursor.getCount() <= 0)
            return null;
        TaskModel[] tasks = new TaskModel[cursor.getCount()];

        int i = 0;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String NameOfAssignment = cursor.getString(cursor.getColumnIndex(TASK_NAME));
            String Assignment = cursor.getString(cursor.getColumnIndex(TASK_DESCRIPTION));
            int Year = cursor.getInt(cursor.getColumnIndex(TASK_TIME_YEAR));
            int Month = cursor.getInt(cursor.getColumnIndex(TASK_TIME_MONTH));
            int Day = cursor.getInt(cursor.getColumnIndex(TASK_TIME_DAY));
            int Hour = cursor.getInt(cursor.getColumnIndex(TASK_TIME_HOUR));
            int Minute = cursor.getInt(cursor.getColumnIndex(TASK_TIME_MINUTE));
            int PriorityFlag = cursor.getInt(cursor.getColumnIndex(TASK_TIME_FLAG));
            int Reminder = cursor.getInt(cursor.getColumnIndex(TASK_TIME_REMINDER));

            if (Reminder > 0) {
                ReminderTmp = true;
            } else {
                ReminderTmp = false;
            }

            tasks[i] = new TaskModel(NameOfAssignment, Assignment, Year, Month, Day, Hour, Minute, PriorityFlag, ReminderTmp);
            i++;
        }

        return tasks;
    }

    public boolean isNotEmpty() {
        boolean rowExists = false;
        SQLiteDatabase db = getReadableDatabase();

        Cursor mCursor = db.rawQuery("SELECT * FROM " + "Tasks", null);
        mCursor.moveToFirst();

        if (mCursor.getInt(mCursor.getColumnIndex("Year")) != 0) {
            rowExists = true;
        } else {
            rowExists = false;
        }
        return rowExists;
    }
}
