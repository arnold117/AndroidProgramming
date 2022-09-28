package nchu.bme.checkboxtry0412;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11;
    private Button onBtn;
    private StringBuffer buffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        cb1 = (CheckBox) findViewById(R.id.checkbox1);
        cb2 = (CheckBox) findViewById(R.id.checkbox2);
        cb3 = (CheckBox) findViewById(R.id.checkbox3);
        cb4 = (CheckBox) findViewById(R.id.checkbox4);
        cb5 = (CheckBox) findViewById(R.id.checkbox5);
        cb6 = (CheckBox) findViewById(R.id.checkbox6);
        cb7 = (CheckBox) findViewById(R.id.checkbox7);
        cb8 = (CheckBox) findViewById(R.id.checkbox8);
        cb9 = (CheckBox) findViewById(R.id.checkbox9);
        cb10 = (CheckBox) findViewById(R.id.checkbox10);
        cb11 = (CheckBox) findViewById(R.id.checkbox11);
        onBtn = (Button) findViewById(R.id.ok_btn);
        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = new StringBuffer();  //初始化
                if (cb1.isChecked()){
                    buffer.append(cb1.getText().toString()+"  ");
                }
                if (cb2.isChecked()){
                    buffer.append(cb2.getText().toString()+"  ");
                }
                if (cb3.isChecked()){
                    buffer.append(cb3.getText().toString()+"  ");
                }
                if (cb4.isChecked()){
                    buffer.append(cb4.getText().toString()+"  ");
                }
                if (cb5.isChecked()){
                    buffer.append(cb5.getText().toString()+"  ");
                }
                if (cb6.isChecked()){
                    buffer.append(cb6.getText().toString()+"  ");
                }
                if (cb7.isChecked()){
                    buffer.append(cb7.getText().toString()+"  ");
                }
                if (cb8.isChecked()){
                    buffer.append(cb8.getText().toString()+"  ");
                }
                if (cb9.isChecked()){
                    buffer.append(cb9.getText().toString()+"  ");
                }
                if (cb10.isChecked()){
                    buffer.append(cb10.getText().toString()+"  ");
                }
                if (cb11.isChecked()){
                    buffer.append(cb11.getText().toString());
                }
                Toast.makeText(MainActivity.this, "你感兴趣的" + buffer, Toast.LENGTH_SHORT).show();  //弹出提示
            }
        });
    }
}
