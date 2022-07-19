package com.example.studentsmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "SinhVien.db";
    private static final String CREATE_TABLE = "CREATE TABLE SinhVien (" +
                                                            "ID TEXT PRIMARY KEY, " +
                                                            "HO_TEN TEXT, " +
                                                            "EMAIL TEXT, " +
                                                            "NGAY_SINH TEXT)";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS SinhVien";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public Boolean insertData(String id, String ho_ten, String email, String ngay_sinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("HO_TEN", ho_ten);
        contentValues.put("EMAIL", email);
        contentValues.put("NGAY_SINH", ngay_sinh);

        long result = db.insert("SinhVien", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean updateData(String id, String ho_ten, String email, String ngay_sinh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ID", id);
        contentValues.put("HO_TEN", ho_ten);
        contentValues.put("EMAIL", email);
        contentValues.put("NGAY_SINH", ngay_sinh);

        Cursor cursor = db.rawQuery("SELECT * FROM SinhVien WHERE ID=?", new String[] {id});
        if (cursor.getCount() > 0) {
            long result = db.update("SinhVien", contentValues, "id=?", new String[]{id});
            if (result == -1) return false;
            else return true;
        }
        else {
            return false;
        }
    }

    public Boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM SinhVien WHERE ID=?", new String[] {id});
        if (cursor.getCount() > 0) {
            long result = db.delete("SinhVien","id=?", new String[]{id});
            if (result == -1) return false;
            else return true;
        }
        else {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SinhVien", null);
        return cursor;
    }

//
//    SELECT
//            select_list
//    FROM
//            table
//    ORDER BY
//    column_1 ASC,
//    column_2 DESC;
}
