package nchu.bme.taobaosl0409;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private RatingBar ratingbar;	//星级评分条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar1);	//获取星级评分条
        Button button=(Button)findViewById(R.id.btn);		//获取“提交”按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = ratingbar.getProgress();            //获取进度
                float rating = ratingbar.getRating();            //获取等级
                float step = ratingbar.getStepSize();            //获取每次最少要改变多少个星级
              //  Log.v("星级评分条","step="+step+" result="+result+" rating="+rating);
                Toast.makeText(MainActivity.this,
                        "你得到了" + rating + "颗星", Toast.LENGTH_SHORT).show();
            }
        });

    }
}