package com.example.administrator.dictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /**
     * 创建控件
     */
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 初始化控件
         */
        editText = (EditText) findViewById(R.id.et_name);
        button = (Button) findViewById(R.id.main_btn);

        /**
         * 给登录按钮设置监听
         * 把姓名传输给登录页面并实现跳转
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, InputWordsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", editText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
