package nchu.bme.setheightweight;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText etHeight, etWeight;
    private Button okBtn;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }
    /*** 绑定id*/
    private void init() {
        etHeight = (EditText) findViewById(R.id.height_et);
        etWeight = (EditText) findViewById(R.id.weight_et);
        okBtn = (Button) findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*** 将结果返回上一个界面* */
                Intent mIntent = new Intent();
                mIntent.putExtra("height", etHeight.getText().toString().trim());
                mIntent.putExtra("weight", etWeight.getText().toString().trim());
                // 设置结果，并进行传送城市
                SecondActivity.this.setResult(0, mIntent);
                SecondActivity.this.finish();  //关闭当前界面
            }
        });
    }
}