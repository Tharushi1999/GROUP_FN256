package com.example.book.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerBelow extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CustomerMg";

    public DBHandlerBelow(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentDetails.Student.TABLE_NAME + " (" +
                    StudentDetails.Student._ID + " String PRIMARY KEY," +
                    StudentDetails.Student.COLUMN_1 + " TEXT," +
                    StudentDetails.Student.COLUMN_2 + " TEXT," +
                    StudentDetails.Student.COLUMN_3 + " TEXT," +
                    StudentDetails.Student.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentDetails.Student.TABLE_NAME;

    public long addInfo(String student_name, String address, String contact_number,String interested_areas) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentDetails.Student.COLUMN_1, student_name);
        values.put(StudentDetails.Student.COLUMN_2, address);
        values.put(StudentDetails.Student.COLUMN_3, contact_number);
        values.put(StudentDetails.Student.COLUMN_4, interested_areas);

        long newRowId = db.insert(StudentDetails.Student.TABLE_NAME, null, values);
        return newRowId;
    }
        public List readAllInfo(String id){
        SQLiteDatabase db = getReadableDatabase();

            String[] projection = {
                    BaseColumns._ID,
                    StudentDetails.Student.COLUMN_1,
                    StudentDetails.Student.COLUMN_2,
                    StudentDetails.Student.COLUMN_3
            };

            String selection = StudentDetails.Student._ID + " LIKE ?";
            String[] selectionArgs = {id};

            String sortOrder =
                    StudentDetails.Student.COLUMN_1 + " ASC";

            Cursor cursor = db.query(
                    StudentDetails.Student.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,               // The columns for the WHERE clause
                    selectionArgs ,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            );

            List UserInfo = new ArrayList<>();
            while(cursor.moveToNext()) {
                String user = cursor.getString(
                        cursor.getColumnIndexOrThrow(StudentDetails.Student.COLUMN_1));
                String dob = cursor.getString(
                        cursor.getColumnIndexOrThrow(StudentDetails.Student.COLUMN_2));
                String pass = cursor.getString(
                        cursor.getColumnIndexOrThrow(StudentDetails.Student.COLUMN_3));
                UserInfo.add(user);
                UserInfo.add(dob);
                UserInfo.add(pass);
            }
            cursor.close();
            return UserInfo;
    }
    public boolean updateInfo(String id,String s_name,String address, String contact_number, String interested){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentDetails.Student.COLUMN_1, s_name);
        values.put(StudentDetails.Student.COLUMN_2, address);
        values.put(StudentDetails.Student.COLUMN_3, contact_number);
        values.put(StudentDetails.Student.COLUMN_4, interested);

        String selection = StudentDetails.Student._ID + " LIKE ?";
        String[] selectionArgs = {id};

        int count = db.update(
                StudentDetails.Student.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count >0){
            return true;
        }
        else{
            return false;
        }

    }

    public void deleteInfo(String id){

        SQLiteDatabase db = getWritableDatabase();


        String selection = StudentDetails.Student._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int deletedRows = db.delete(StudentDetails.Student.TABLE_NAME, selection, selectionArgs);
    }

    public Cursor getproductdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from StudentInfo", null);
        return cursor;

    }
}
