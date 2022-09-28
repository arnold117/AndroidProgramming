package com.example.administrator.dictionary.tools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.dictionary.CheckWordsActivity;
import com.example.administrator.dictionary.InputWordsActivity;
import com.example.administrator.dictionary.R;
import com.example.administrator.dictionary.ReciteWordsActivity;
import com.example.administrator.dictionary.SelfCenterActivity;


public class Buttons extends AppCompatActivity {

    Button button_input, button_recite, button_check, button_self;

    public Buttons(){
        getButtons();
    }

    public void getButtons() {
        button_input = (Button) findViewById(R.id.btn_input);
        button_recite = (Button) findViewById(R.id.btn_recite);
        button_check = (Button) findViewById(R.id.btn_check);
        button_check = (Button) findViewById(R.id.btn_selfcenter);
    }


    /**
     * 录词按钮设置监听
     * 实现跳转
     */
    public void setButton_inputListener(View view) {
        button_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InputWordsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 背词按钮设置监听
     * 实现跳转
     */
    public void setButton_reciteListener(View view) {
        button_recite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ReciteWordsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 查词按钮设置监听
     * 实现跳转
     */
    public void setButton_checkListener(View view) {
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CheckWordsActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 个人中心按钮设置监听
     * 实现跳转
     */
    public void setButton_selfListener(View view) {
        button_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SelfCenterActivity.class);
                startActivity(intent);
            }
        });
    }
}
