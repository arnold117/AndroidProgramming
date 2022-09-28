package com.example.administrator.dictionary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dictionary.database.DBWords;
import com.example.administrator.dictionary.util.ToastUtil;

public class DetailWordActivity extends AppCompatActivity {

    //创建元素
    DBWords dbWords;
    TextView textView_word,textView_translate;
    Button button_modify,button_delete;
    Bundle bundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_word);

        /**
         * 初始化
         */
        dbWords = new DBWords(getApplication(),"tb_words",null,1);
        textView_word = (TextView) findViewById(R.id.detail_tv_word);
        textView_translate = (TextView) findViewById(R.id.detail_tv_translate);
        button_modify = (Button) findViewById(R.id.detail_btn_modify);
        button_delete = (Button) findViewById(R.id.detail_btn_delete);

        /**
         * 获取bundle，显示传来的单词和翻译
         */
        bundle = getIntent().getExtras();
        textView_word.setText(bundle.getString("word"));
        textView_translate.setText(bundle.getString("translate"));

        /**
         * 给修改按钮设置监听
         */
        button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnExit();
            }
        });

        /**
         * 给删除按钮设置监听
         */
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbWords.deleteWord(bundle.getString("number"));
                ToastUtil.showMsg(getApplicationContext(),"删除成功！");
            }
        });
    }

    public void OnExit()
    {
        /**
         * 设置没有标题栏
         */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Mdialog mdialog = new Mdialog(this);  //实例化自定义对话框

        //给取消按钮设置监听事件
        mdialog.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mdialog.isShowing()){
                    mdialog.dismiss();  //关闭对话框
                }
            }
        });
        //给确定按钮设置监听事件
        mdialog.ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mdialog.isShowing()){
                    String word = mdialog.editText_word.getText().toString();
                    String translate = mdialog.editText_translate.getText().toString();
                    dbWords.updateWord(bundle.getString("number"),word,translate);
                    System.out.print(bundle.getString("number"));
                    mdialog.dismiss();      //关闭对话框
                    textView_word.setText(word);
                    textView_translate.setText(translate);
                }
            }
        });
        mdialog.show();
    }

}