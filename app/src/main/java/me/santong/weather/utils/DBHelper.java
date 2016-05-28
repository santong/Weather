package me.santong.weather.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.santong.weather.models.City;
import me.santong.weather.models.Condition;

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
            "code number primary key," +
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

    public boolean SaveOrUpdateCityList(List<City> cityList) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean flag = true;
        for (City city : cityList) {
            flag = db.insertOrThrow(TABLE_CITY, null, getCityValues(city)) != 1;
        }
        db.close();
        return flag;
    }

    public boolean SaveOrUpdateCondList(List<Condition> conditionList) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean flag = true;
        for (Condition cond : conditionList) {
            flag = db.insertOrThrow(TABLE_COND, null, getCondValues(cond)) != 1;
        }
        db.close();
        return flag;
    }

    public List<City> getCityListFromDisk() {
        List<City> cityList = new LinkedList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CITY, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();

                city.setId(cursor.getString(cursor.getColumnIndex("id")));
                city.setCity(cursor.getString(cursor.getColumnIndex("city")));
                city.setCnty(cursor.getString(cursor.getColumnIndex("cnty")));
                city.setProv(cursor.getString(cursor.getColumnIndex("prov")));
                city.setLat(cursor.getDouble(cursor.getColumnIndex("lat")));
                city.setLon(cursor.getDouble(cursor.getColumnIndex("lon")));

                cityList.add(city);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cityList;
    }

    public List<Condition> getCondLitFromDisk() {
        List<Condition> conditionList = new LinkedList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_COND, null, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Condition cond = new Condition();

                cond.setCode(cursor.getInt(cursor.getColumnIndex("code")));
                cond.setIcon(cursor.getString(cursor.getColumnIndex("url")));
                cond.setTxt(cursor.getString(cursor.getColumnIndex("txt")));
                cond.setTxt_en(cursor.getString(cursor.getColumnIndex("txt_en")));

                conditionList.add(cond);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return conditionList;
    }

    public String getWeatherIconPath(int code) {
        List<Condition> conditionList = getCondLitFromDisk();
        String targetPath = "";
        for (Condition condition : conditionList) {
            if (condition.getCode() == code)
                targetPath = condition.getIcon();
        }
        return targetPath;
    }

    public String getCityCode(String cityName) {
        List<City> cityList = getCityListFromDisk();
        String cityId = "";
        for (City city : cityList) {
            if (city.getCity().equals(cityName))
                cityId = city.getId();
        }
        return cityId;
    }

    private ContentValues getCityValues(City city) {
        ContentValues values = new ContentValues();
        values.put("id", city.getId());
        values.put("city", city.getCity());
        values.put("cnty", city.getCnty());
        values.put("lat", city.getLat());
        values.put("lon", city.getLon());
        return values;
    }

    private ContentValues getCondValues(Condition condition) {
        ContentValues values = new ContentValues();
        values.put("code", condition.getCode());
        values.put("txt", condition.getTxt());
        values.put("txt_en", condition.getTxt_en());
        values.put("url", condition.getIcon());
        return values;
    }
}
