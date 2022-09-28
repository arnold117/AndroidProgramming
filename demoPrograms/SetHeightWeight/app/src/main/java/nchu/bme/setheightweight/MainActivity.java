package nchu.bme.setheightweight;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rl;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*** 绑定id* */
        rl = (RelativeLayout) findViewById(R.id.rl_layout);
        tv = (TextView) findViewById(R.id.tv_show);
        //设置布局的点击事件
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data!=null){
            String height = data.getStringExtra("height");//回调输入的身高
            String weight = data.getStringExtra("weight");//回调输入的体重
            tv.setText("你的身高为："+height+"cm" +"\n"+"你的体重为："+weight+"kg");  //显示出来
        }

    }
}