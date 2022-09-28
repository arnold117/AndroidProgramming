package nchu.bme.dicdatabase;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DBOpenHelper dbOpenHelper;   //定义DBOpenHelper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建DBOpenHelper对象,指定名称、版本号并保存在databases目录下
        dbOpenHelper = new DBOpenHelper(MainActivity.this, "dict.db", null, 1);
        //获取显示结果的ListView
        final ListView listView = (ListView) findViewById(R.id.result_listView);
        //获取查询内容的编辑框
        final EditText etSearch = (EditText) findViewById(R.id.search_et);
        //获取查询按钮
        ImageButton btnSearch = (ImageButton) findViewById(R.id.search_btn);
        //获取跳转添加生词界面的按钮
        Button btn_add = (Button) findViewById(R.id.btn_add);
        //单击添加生词按钮，实现跳转到添加生词的界面
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过Intent跳转添加生词界面
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        //单击翻译按钮，实现查询词库中的单词
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = etSearch.getText().toString();  //获取要查询的单词
                //查询单词
                Cursor cursor=dbOpenHelper.getReadableDatabase().query("dict",null
                        ,"word = ?",new String[]{key},null,null,null);

                //创建ArrayList对象，用于保存查询出的结果
                ArrayList<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
                while (cursor.moveToNext()) {  // 遍历Cursor结果集
                    Map<String, String> map = new HashMap<>();  // 将结果集中的数据存入HashMap中
                    // 取出查询记录中第2列、第3列的值
                    map.put("word", cursor.getString(1));
                    map.put("interpret", cursor.getString(2));
                    resultList.add(map);                        //将查询出的数据存入ArrayList中
                }

                if (resultList == null || resultList.size() == 0) {  //如果数据库中没有数据
                    // 显示提示信息，没有相关记录
                    Toast.makeText(MainActivity.this,
                            "很遗憾，没有相关记录！", Toast.LENGTH_LONG).show();
                } else {
                    // 否则将查询的结果显示到ListView列表中
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, resultList,
                            R.layout.result_main,
                            new String[]{"word", "interpret"}, new int[]{
                            R.id.result_word, R.id.result_interpret});
                    listView.setAdapter(simpleAdapter);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {  //实现退出应用时，关闭数据库连接
        super.onDestroy();
        if (dbOpenHelper != null) {   //如果数据库不为空时
            dbOpenHelper.close();     //关闭数据库连接
        }
    }

}
