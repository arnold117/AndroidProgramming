package com.example.calculator6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextView Textview;    //朗读的文字
    private Button button0,button1,button2,button3;      //开始按钮
    private TextToSpeech tts;   // TTS对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Textview = (TextView) findViewById(R.id.res);
        button0 = (Button) findViewById(R.id.add);
        button1 = (Button) findViewById(R.id.sub);
        button2 = (Button) findViewById(R.id.mul);
        button3 = (Button) findViewById(R.id.div);
        //初始化tts
        tts = new TextToSpeech(this, this);// 参数Context,TextToSpeech.OnInitListener

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a + b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText("\n"+Double.toString(a)+"+"+Double.toString(b)+"="
                        +Double.toString(c));
                if (tts != null && !tts.isSpeaking()) {
                    // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    tts.setPitch(1.0f);
                    //设定语速 ，默认1.0正常语速
                    tts.setSpeechRate(1.0f);
                    //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
                    tts.speak(Textview.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a - b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText("\n"+Double.toString(a)+"-"+Double.toString(b)+"="+Double.toString(c));
                if (tts != null && !tts.isSpeaking()) {
                    // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    tts.setPitch(1.0f);
                    //设定语速 ，默认1.0正常语速
                    tts.setSpeechRate(1.0f);
                    //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
                    tts.speak(Textview.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a * b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText("\n"+Double.toString(a)+"*"+Double.toString(b)+"="+Double.toString(c));
                if (tts != null && !tts.isSpeaking()) {
                    // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    tts.setPitch(1.0f);
                    //设定语速 ，默认1.0正常语速
                    tts.setSpeechRate(1.0f);
                    //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
                    tts.speak(Textview.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = ((EditText) findViewById(R.id.num1)).getText().toString();
                double a = Double.parseDouble(s1.trim());
                String s2 = ((EditText) findViewById(R.id.num2)).getText().toString();
                double b =  Double.parseDouble(s2.trim());
                double c = a / b;
                TextView res1 = (TextView) findViewById(R.id.res);
                res1.setText("\n"+Double.toString(a)+"/"+Double.toString(b)+"="+Double.toString(c));
                if (tts != null && !tts.isSpeaking()) {
                    // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                    tts.setPitch(1.0f);
                    //设定语速 ，默认1.0正常语速
                    tts.setSpeechRate(1.0f);
                    //朗读，注意这里三个参数的added in API level 4   四个参数的added in API level 21
                    tts.speak(Textview.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });
    }


    /**
     * 用来初始化TextToSpeech引擎
     * status:SUCCESS或ERROR这2个值
     * setLanguage设置语言，帮助文档里面写了有22种
     * TextToSpeech.LANG_MISSING_DATA：表示语言的数据丢失。
     * TextToSpeech.LANG_NOT_SUPPORTED:不支持
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(MainActivity.this, "数据丢失/不支持" + result, Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        tts.stop();// 不管是否正在朗读TTS都被打断
        tts.shutdown();//关闭释放资源
    }

}

