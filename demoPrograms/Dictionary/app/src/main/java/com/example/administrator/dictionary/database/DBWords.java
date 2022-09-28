package com.example.administrator.dictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.dictionary.info.Word;



public class DBWords extends SQLiteOpenHelper {

    int number = 0;
    final String Create_Table_SQL = "create table tb_words (_id integer primary key autoincrement,numbers,word,translate)";

    public DBWords(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("delete from tb_words");
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_words'");
    }

    /**
     * 单词的添加方法
     */
    public void writeWord(String word, String translate) {
        number++;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("numbers",number+"");
        values.put("word", word);
        values.put("translate", translate);
        db.insert("tb_words", null, values);

    }

    /**
     * 删除单词方法
     *
     * @param id
     */
    public void deleteWord(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereValue = { id+"" };
        db.delete("tb_words", "numbers = ?", whereValue);

    }

    /**
     * 修该单词方法
     *
     * @param id
     * @param word
     * @param translate
     */
    public void updateWord(String id, String word, String translate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "numbers = ?";
        String[] whereValue = { id };

        ContentValues values = new ContentValues();
        values.put("word", word);
        values.put("translate", translate);
        db.update("tb_words", values, where, whereValue);
    }

    /**
     * 获取单词
     *
     * @param id
     * @return
     */
    public Word getWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Word word = new Word();
        Cursor cursor = db.query("tb_words", null, null, null, null, null, null);
        cursor.moveToPosition(id);
        try {
            word.setWord(cursor.getString(cursor.getColumnIndex("word")));
            word.setTranslate(cursor.getString(cursor.getColumnIndex("translate")));
        }catch (Exception e){

        }
        return word;
    }

    public int getCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_words", null, null, null, null, null, null);
        return cursor.getCount();
    }
}
