package com.arnold.setdiaolog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class SecondActivity extends AppCompatActivity {
    private EditText etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    /*** 绑定id*/
    private void init() {
        etMsg = (EditText) findViewById(R.id.msg_et);
        Button okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*** 将结果返回上一个界面* */
                Intent mIntent = new Intent();
                mIntent.putExtra("Message", etMsg.getText().toString().trim());
                SecondActivity.this.setResult(0, mIntent);
                SecondActivity.this.finish();  //关闭当前界面
            }
        });
    }
}