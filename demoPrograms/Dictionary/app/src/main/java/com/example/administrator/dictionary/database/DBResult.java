package com.example.administrator.dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBResult extends SQLiteOpenHelper {

    public DBResult(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + "tb_result" + " (" +
                "id			INTEGER 		PRIMARY KEY     autoincrement ," +
                " right		VARCHAR(50)		NOT NULL ," +
                " wrong   	varchar(50)		NOT NULL ," +
                " remove     varchar(50)        NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("delete from tb_result");
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_result'");
    }

    //单词的添加方法
    public void writeData(SQLiteDatabase sqLiteDatabase,String right,String wrong,String delete){
        ContentValues values = new ContentValues();
        values.put("right",right);
        values.put("wrong",wrong);
        values.put("remove",delete);
        sqLiteDatabase.insert("tb_result",null,values);//保存功能
    }

    //单词的删除方法
    //判断是否是合法用户

}
