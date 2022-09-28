package com.example.administrator.dictionary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.administrator.dictionary.database.DBWords;
import com.example.administrator.dictionary.info.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class allWordsBase extends AppCompatActivity {

    DBWords dbWords;
    ListView listView;
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_all_base);
        dbWords = new DBWords(allWordsBase.this, "tb_words", null, 1);

        final ArrayList<Word> words = getWords();

        listView = (ListView) findViewById(R.id.list2);

        list = new ArrayList<Map<String, Object>>();


        /**
         * 把单词添加到列表组里
         */
        for (int i = 0; i < words.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", words.get(i).getNumber() + ".");
            map.put("word", words.get(i).getWord());
            map.put("translate", words.get(i).getTranslate());
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(allWordsBase.this, list, R.layout.list_item,
                new String[]{"id", "word", "translate"}, new int[]{R.id.id, R.id.word, R.id.translate});

        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                String word =  words.get(position).getWord();
                String translate = words.get(position).getTranslate();
                String number = words.get(position).getNumber();

                Intent intent = new Intent(getApplication(),DetailWordActivity.class);
                bundle.putString("word",word);
                bundle.putString("translate",translate);
                bundle.putString("number",number);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取单词组
     * @return
     */
    private ArrayList<Word> getWords() {
        ArrayList<Word> words = new ArrayList<>();
        Cursor cursor = dbWords.getReadableDatabase().query("tb_words", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Word word = new Word();
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            word.setNumber(cursor.getString(cursor.getColumnIndex("numbers")));
            word.setWord(cursor.getString(cursor.getColumnIndex("word")));
            word.setTranslate(cursor.getString(cursor.getColumnIndex("translate")));
            words.add(word);
        }
        return words;
    }

    @Override
    protected void onRestart() {
        super.onResume();
        ArrayList<Word> words = getWords();

        list = new ArrayList<Map<String, Object>>();

        /**
         * 把单词添加到列表组里
         */
        for (int i = 0; i < words.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", words.get(i).getNumber() + ".");
            map.put("word", words.get(i).getWord());
            map.put("translate", words.get(i).getTranslate());
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(allWordsBase.this, list, R.layout.list_item,
                new String[]{"id", "word", "translate"}, new int[]{R.id.id, R.id.word, R.id.translate});

        listView.setAdapter(simpleAdapter);

    }
}
