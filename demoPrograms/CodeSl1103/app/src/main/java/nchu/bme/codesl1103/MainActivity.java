package nchu.bme.codesl1103;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    byte[] buffer = null;   //定义保存数据的数组
    private File file;      //定义存储路径
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etext = (EditText) findViewById(R.id.editText);      //获取用于填写记事本信息的编辑框组件
        ImageButton btn_save = (ImageButton) findViewById(R.id.save);        //获取保存按钮
        ImageButton btn_abolish = (ImageButton) findViewById(R.id.abolish);  //获取取消按钮
        //在外部存储根目录上创建文件
        file = new File(Environment.getExternalStorageDirectory(), "Text.text");

        btn_save.setOnClickListener(new View.OnClickListener() {  //实现外部存储填写的文本信息
            @Override
            public void onClick(View v) {
                FileOutputStream fos = null;              //定义文件输出流
                String text = etext.getText().toString();        //获取文本信息
                try {
                    fos = new FileOutputStream(file);     //获得文件输出流,并指定文件保存的位置
                    fos.write(text.getBytes());           //保存文本信息
                    fos.flush();                           //清除缓存
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();                    //关闭文件输出流
                            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //实现第二次打开应用时显示上一次所保存的文本信息
        FileInputStream fis = null;                         //定义文件输入流
        try {
            fis = new FileInputStream(file);                 //获得文件输入流
            Log.i("路径",""+file);
            buffer = new byte[fis.available()];            //保存数据的数组
            fis.read(buffer);                               //从输入流中读取数据
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();                                      //关闭输入流
                    String data = new String(buffer);                // 获得数组中保存的数据
                    etext.setText(data);                              //保存到编辑框中
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        btn_abolish.setOnClickListener(new View.OnClickListener() {  //实现单击取消按钮，退出应用
            @Override
            public void onClick(View v) {
                finish();                                      //退出应用
            }
        });
    }
}
