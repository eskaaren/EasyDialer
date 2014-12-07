package com.giskeskaaren.easydialer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Creator: Eivin Giske Skaaren
 * Email: eskaaren@yahoo.no
 * Date: 6/15/12
 * Time: 8:47 PM
 */
public class SQLiteAdapter {

    protected String MYDATABASE_NAME;

    protected static final String MYDATABASE_TABLE = "MY_TABLE";
    protected static final int MYDATABASE_VERSION = 1;
    protected static final String KEY_CONTENT = "Content";
    protected static final String KEY_DATE = "Date";


    private static final String SCRIPT_CREATE_DATABASE =
            "create table " + MYDATABASE_TABLE + " ("
                    + KEY_CONTENT + " text not null, " + KEY_DATE + " text not null);";

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public SQLiteAdapter(Context c){
        context = c;
    }

    public SQLiteAdapter(Context c, String dbname){
        context = c;
        MYDATABASE_NAME = dbname;
    }

    public SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteHelper.close();
    }

    public long insert(String content, String date){

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT, content);
        contentValues.put(KEY_DATE, date);
        return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);
    }

    public int deleteAll(){
        return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
    }

    public String queueAll(){
        String[] columns = new String[]{KEY_CONTENT, KEY_DATE};
        Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
                null, null, null, null, null);
        String result = "";

        int index_CONTENT = cursor.getColumnIndex(KEY_CONTENT);
        int index_DATE = cursor.getColumnIndex(KEY_DATE);
        for(cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()){
            result = result + cursor.getString(index_CONTENT) + "\n" + cursor.getString(index_DATE) + "\t";
        }

        return result;
    }


    public static class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SCRIPT_CREATE_DATABASE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }

}