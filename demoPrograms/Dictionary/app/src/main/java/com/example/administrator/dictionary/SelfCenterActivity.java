package com.example.administrator.dictionary;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class SelfCenterActivity extends AppCompatActivity {

    TextView  textView_resetname, textView_allword, textView_result;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_center);
        textView_resetname = (TextView) findViewById(R.id.self_tv_resetname);

        textView_allword = (TextView) findViewById(R.id.self_tv_allwords);
       // textView_result = (TextView) findViewById(R.id.self_tv_result);



        /*//点击修改用户名
        textView_resetname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ResetUsername.class);
                startActivity(intent);
            }
        });*/

        //点击单词库
        textView_allword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), allWordsBase.class);
                startActivity(intent);
            }
        });

        /*//点击单词战况
        textView_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Result.class);
                startActivity(intent);
            }
        });*/
    }
}
