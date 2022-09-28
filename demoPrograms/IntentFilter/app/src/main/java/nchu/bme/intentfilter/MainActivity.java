package nchu.bme.intentfilter;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.btn); //获取按钮组件
        //为按钮创建单击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(); //创建Intent对象
                intent.setAction(intent.ACTION_VIEW); //为Intent设置动作
                startActivity(intent); //启动Activity
            }
        });

    }
}