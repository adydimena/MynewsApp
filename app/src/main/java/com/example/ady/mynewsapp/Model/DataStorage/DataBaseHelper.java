package com.example.ady.mynewsapp.Model.DataStorage;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Ady on 11/14/2017.
 */
    public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_BASE ="NEWSDETAIL.DB";
    public static final String TABLE_NAME = "NEWS";
    public static final String COL_1 = "ImageURL";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_BASE, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ImageURL TEXT PRIMARY KEY)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData (String ImageURL ){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ImageURL);
        long row  = database.insert(TABLE_NAME,null,contentValues);
        if (row == -1)
            return false;
        else
            return true;
    }
    public Cursor getallgata(String ColunmName)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor results = database.rawQuery("SELECT " +ColunmName+  " FROM "+ TABLE_NAME,null);
        return  results;
    }
}