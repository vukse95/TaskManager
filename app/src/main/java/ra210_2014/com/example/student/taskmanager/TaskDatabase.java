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
    public TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public TaskDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tasks(NameOfAssignment TEXT, Assignment TEXT, Year INTEGER" +
                ", Month INTEGER, Day INTEGER, Hour INTEGER, Minute INTEGER, PriorityFlag INTEGER" +
                ", Reminder INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTask(TaskModel taskModel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("NameOfAssignment", taskModel.getNameOfAssignment());
        contentValues.put("Assignment", taskModel.getAssignment());
        contentValues.put("Year", taskModel.getYear());
        contentValues.put("Month", taskModel.getMonth());
        contentValues.put("Day", taskModel.getDay());
        contentValues.put("Hour", taskModel.getHour());
        contentValues.put("Minute", taskModel.getMinute());
        contentValues.put("PriorityFlag", taskModel.getPriorityFlag());

        //isReminder vraca bool
        if (taskModel.isReminder()) {
            contentValues.put("Reminder", 1);
        } else {
            contentValues.put("Reminder", 0);
        }


        db.insert("Tasks", null, contentValues);
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
            String NameOfAssignment = cursor.getString(cursor.getColumnIndex("NameOfAssignment"));
            String Assignment = cursor.getString(cursor.getColumnIndex("Assignment"));
            int Year = cursor.getInt(cursor.getColumnIndex("Year"));
            int Month = cursor.getInt(cursor.getColumnIndex("Month"));
            int Day = cursor.getInt(cursor.getColumnIndex("Day"));
            int Hour = cursor.getInt(cursor.getColumnIndex("Hour"));
            int Minute = cursor.getInt(cursor.getColumnIndex("Minute"));
            int PriorityFlag = cursor.getInt(cursor.getColumnIndex("PriorityFlag"));
            int Reminder = cursor.getInt(cursor.getColumnIndex("Reminder"));

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
}
