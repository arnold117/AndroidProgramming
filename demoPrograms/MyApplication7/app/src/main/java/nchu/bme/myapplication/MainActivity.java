package nchu.bme.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView yu_hello = (TextView) findViewById(R.id.yu_hello);
        yu_hello.setText("今天天气真热啊，火辣辣的");
        yu_hello.setTextColor(Color.RED);
        yu_hello.setTextSize(30);
    }
}