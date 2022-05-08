package com.example.book.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandlerAbove extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CustomerMg";

    public DBHandlerAbove(Context context){
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
            "CREATE TABLE " + ElderDetails.Elder.TABLE_NAME + " (" +
                    ElderDetails.Elder._ID + " String PRIMARY KEY," +
                    ElderDetails.Elder.COLUMN_1 + " TEXT," +
                    ElderDetails.Elder.COLUMN_2 + " TEXT," +
                    ElderDetails.Elder.COLUMN_3 + " TEXT," +
                    ElderDetails.Elder.COLUMN_4 + " TEXT," +
                    ElderDetails.Elder.COLUMN_5 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ElderDetails.Elder.TABLE_NAME;

    public long addInfo(String customer_name, String address, String contact_number, String customer_designation, String interested_area){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ElderDetails.Elder.COLUMN_1, customer_name);
        values.put(ElderDetails.Elder.COLUMN_2, address);
        values.put(ElderDetails.Elder.COLUMN_3, contact_number);
        values.put(ElderDetails.Elder.COLUMN_4, customer_designation);
        values.put(ElderDetails.Elder.COLUMN_5, interested_area);

        long newRowId = db.insert(ElderDetails.Elder.TABLE_NAME, null, values);
        return newRowId;
    }
    public boolean updateInfo(String id,String customer_name,String address, String contact_number, String customer_designation,String interested_area){
        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ElderDetails.Elder.COLUMN_1, customer_name);
        values.put(ElderDetails.Elder.COLUMN_2, address);
        values.put(ElderDetails.Elder.COLUMN_3, contact_number);
        values.put(ElderDetails.Elder.COLUMN_4, customer_designation);
        values.put(ElderDetails.Elder.COLUMN_5, interested_area);

        String selection = ElderDetails.Elder._ID + " LIKE ?";
        String[] selectionArgs = { id };

        int count = db.update(
                ElderDetails.Elder.TABLE_NAME,
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


        String selection = ElderDetails.Elder._ID + " LIKE ?";
        String[] selectionArgs = { id };
        int deletedRows = db.delete(ElderDetails.Elder.TABLE_NAME, selection, selectionArgs);
    }

    public List readAllInfo(String id){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                ElderDetails.Elder.COLUMN_1,
                ElderDetails.Elder.COLUMN_2,
                ElderDetails.Elder.COLUMN_3,
                ElderDetails.Elder.COLUMN_4,
                ElderDetails.Elder.COLUMN_5
        };

        String selection = ElderDetails.Elder._ID + " LIKE ?";
        String[] selectionArgs = {id};

        String sortOrder =
                ElderDetails.Elder.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                ElderDetails.Elder.TABLE_NAME,
                projection,
                selection,
                selectionArgs ,
                null,
                null,
                sortOrder
        );

        List UserInfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(ElderDetails.Elder.COLUMN_1));
            String dob = cursor.getString(
                    cursor.getColumnIndexOrThrow(ElderDetails.Elder.COLUMN_2));
            String pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(ElderDetails.Elder.COLUMN_3));
            String gen = cursor.getString(
                    cursor.getColumnIndexOrThrow(ElderDetails.Elder.COLUMN_4));
            String spe = cursor.getString(
                    cursor.getColumnIndexOrThrow(ElderDetails.Elder.COLUMN_5));
            UserInfo.add(user);
            UserInfo.add(dob);
            UserInfo.add(pass);
            UserInfo.add(gen);
            UserInfo.add(spe);
        }
        cursor.close();
        return UserInfo;
    }

    public Cursor getproductdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ElderInfo", null);
        return cursor;

    }

    }
