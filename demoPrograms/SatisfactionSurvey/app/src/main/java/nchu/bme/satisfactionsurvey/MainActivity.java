package nchu.bme.satisfactionsurvey;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioButton btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (RadioButton) findViewById(R.id.btn1);
        btn2 = (RadioButton) findViewById(R.id.btn2);
        btn3 = (RadioButton) findViewById(R.id.btn3);
        findViewById(R.id.on_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn1.isChecked()) {
                    Toast.makeText(MainActivity.this, "你选择了" + btn1.getText(), Toast.LENGTH_SHORT).show();
                }else if (btn2.isChecked()){
                    Toast.makeText(MainActivity.this, "你选择了" + btn2.getText(), Toast.LENGTH_SHORT).show();

                }else if (btn3.isChecked()){
                    Toast.makeText(MainActivity.this, "你选择了" + btn3.getText(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, "难道你没有手机？", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
