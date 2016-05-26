package me.santong.weather.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by santong.
 * At 16/5/25 17:09
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "DB_ST_WEATHER";

    private final static int DATABASE_VERSION = 1;

    private final static String TABLE_CITY = "city";
    private final static String TABLE_COND = "cond";

    private static DBHelper dbHelper;

    public static DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            synchronized (DBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper(context);
                }
            }
        }
        return dbHelper;
    }

    private final static String CREATE_CITY = "create table " + TABLE_CITY + "(" +
            "id varchar(20) primary key," +
            "city varchar(20)," +
            "cnty varchar(20)," +
            "prov varchar(20)," +
            "lat number default 0," +
            "lon number default 0)";

    private final static String CREATE_COND = "create table " + TABLE_COND + "(" +
            "id number primary key," +
            "txt varchar(20)," +
            "txt_en varchar(20)," +
            "url varchar(50))";

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
