package nchu.bme.codetry1103;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.text_et);
        tv = (TextView) findViewById(R.id.show_tv);
        findViewById(R.id.save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream os = null;
                if (et.getText().toString().trim().length() > 0) {
                    try {
                        //打开内部存储(往手机内存存储东西)
                        /**
                         * "user_files" : 文件名称
                         * MODE_PRIVATE : 操作文件的模式 ，（共有四种）私有模式，只有本应用可以进行读取操作
                         * Context.MODE_PRIVATE：为默认操作模式，代表该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容，如果想把新写入的内容追加到原文件中。可以使用Context.MODE_APPEND
                         * Context.MODE_APPEND：模式会检查文件是否存在，存在就往文件追加内容，否则就创建新文件。
                         * Context.MODE_WORLD_READABLE和Context.MODE_WORLD_WRITEABLE用来控制其他应用是否有权限读写该文件。
                         * MODE_WORLD_READABLE：表示当前文件可以被其他应用读取；MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入。
                         */

                        os = openFileOutput("my_files", MODE_PRIVATE);//
                        os.write(et.getText().toString().getBytes());//此处和普通IO操作无异，所以不进行大规模演示了。。
                        Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        et.setText("");

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "输入框内容为空", Toast.LENGTH_SHORT).show();

                }
            }
        });
        findViewById(R.id.read_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    //用来保存数据
                    StringBuilder sb = new StringBuilder();
                    //获取输入流
                    fis = openFileInput("my_files");
                    //循环取数据
                    byte[] buff = new byte[1024];
                    int len = 0;
                    while ((len = fis.read(buff)) != -1) {
                        sb.append(new String(buff, 0, len));
                    }
                    tv.setText(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
